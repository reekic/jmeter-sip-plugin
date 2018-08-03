package com.polycom.sampler;

import com.oracle.net.Sdp;

import gov.nist.javax.sip.Utils;
import gov.nist.javax.sip.address.SipUri;
import gov.nist.javax.sip.header.Contact;
import gov.nist.javax.sip.header.Via;
import org.apache.jmeter.samplers.SampleResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sdp.Media;
import javax.sdp.MediaDescription;
import javax.sdp.SdpFactory;
import javax.sdp.SessionDescription;
import javax.sip.*;
import javax.sip.address.*;
import javax.sip.header.*;
import javax.sip.message.*;

import java.text.ParseException;
import java.util.*;

public class SipClient implements SipListener{
    private static final Logger log = LoggerFactory.getLogger(SipClient.class);
    private String callerAlias; //"Automation Account1"
    private String callerName; // "auto"
    private String callerDomain; //"user1.com"

    private String proxyIp; // local host ip
    private int proxyPort; // local host port

    private String callerUuid;

    private String calleeName; // conference id
    private String calleeIp; // dma ip

    private int calleePort; // 5060 ? 5061
    private String transport; //tcp udp tls
    private String sdp; //sdp body


    private SipProvider sipProvider;

    private SipStack sipStack;
    private AddressFactory addressFactory;
    private MessageFactory messageFactory;
    private HeaderFactory headerFactory;
    private ContactHeader contactHeader;

    private ListeningPoint listeningPoint;

    private ClientTransaction inviteTransactionId;
    private Dialog dialog;
    private Request ackRequest;

    private boolean byeTaskRunning;

    private SampleResult sr;

    public String getCallerAlias() {
        return callerAlias;
    }

    public void setCallerAlias(String callerAlias) {
        this.callerAlias = callerAlias;
    }

    public String getCallerName() {
        return callerName;
    }

    public void setCallerName(String callerName) {
        this.callerName = callerName;
    }

    public String getCallerDomain() {
        return callerDomain;
    }

    public void setCallerDomain(String callerDomain) {
        this.callerDomain = callerDomain;
    }

    public String getProxyIp() {
        return proxyIp;
    }

    public void setProxyIp(String proxyIp) {
        this.proxyIp = proxyIp;
    }

    public int getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
    }

    public String getCallerUuid() {
        return callerUuid;
    }

    public void setCallerUuid(String callerUuid) {
        this.callerUuid = callerUuid;
    }

    public String getCalleeName() {
        return calleeName;
    }

    public void setCalleeName(String calleeName) {
        this.calleeName = calleeName;
    }

    public String getCalleeIp() {
        return calleeIp;
    }

    public void setCalleeIp(String calleeIp) {
        this.calleeIp = calleeIp;
    }

    public int getCalleePort() {
        return calleePort;
    }

    public void setCalleePort(int calleePort) {
        this.calleePort = calleePort;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getSdp() {
        return sdp;
    }

    public void setSdp(String sdp) {
        this.sdp = sdp;
    }

    public SampleResult getSr() {
        return sr;
    }

    public void setSr(SampleResult sr) {
        this.sr = sr;
    }

    class ByeTask  extends TimerTask {
        Dialog dialog;
        public ByeTask(Dialog dialog)  {
            this.dialog = dialog;
        }
        public void run () {
            try {
                Request byeRequest = this.dialog.createRequest(Request.BYE);
                ClientTransaction ct = sipProvider.getNewClientTransaction(byeRequest);
                dialog.sendRequest(ct);
            } catch (Exception ex) {
                ex.printStackTrace();
                System.exit(0);
            }

        }
    }

    public void startInvite() {
        SipFactory sipFactory = SipFactory.getInstance();
        sipFactory.setPathName("gov.nist");
        Properties prop = new Properties();
        prop.setProperty("javax.sip.STACK_NAME", Thread.currentThread().getName());
        prop.setProperty("javax.sip.IP_ADDRESS", getProxyIp());
        prop.setProperty("gov.nist.javax.sip.CACHE_CLIENT_CONNECTIONS", "false");

        try {
            sipStack = sipFactory.createSipStack(prop);
        } catch (PeerUnavailableException e) {
            e.printStackTrace();
            System.exit(0);
        }

        try {
            headerFactory = sipFactory.createHeaderFactory();
            addressFactory = sipFactory.createAddressFactory();
            messageFactory = sipFactory.createMessageFactory();

            listeningPoint = sipStack.createListeningPoint(this.proxyIp, this.proxyPort, this.transport);

            sipProvider = sipStack.createSipProvider(listeningPoint);

            sipProvider.addSipListener(this);

            SipURI requestUri = addressFactory.createSipURI(this.calleeName, this.calleeIp+":"+this.calleePort);

            ArrayList viaList = createViaListHeader(this.transport, this.proxyPort);
            FromHeader from = createFromHeader(this.callerAlias, this.callerName, this.callerDomain);
            ToHeader to = createToHeader(null, this.calleeName, this.calleeIp);
            CSeqHeader cseq = headerFactory.createCSeqHeader(1L, Request.INVITE);
            CallIdHeader callId = sipProvider.getNewCallId();
            MaxForwardsHeader max_forwards = headerFactory.createMaxForwardsHeader(70);

            Request request = messageFactory.createRequest(requestUri, Request.INVITE, callId, cseq, from, to, viaList, max_forwards);
            /**
             * add some extra headers to the request header
             */
            ContentTypeHeader content_type = headerFactory.createContentTypeHeader("application","sdp");
            ContentLengthHeader content_length = headerFactory.createContentLengthHeader(this.sdp.length());

            ContactHeader contact = createContactHeader(this.callerName,this.proxyIp, this.transport, this.callerUuid);

            request.addHeader(contact);
            request.addHeader(content_type);
            request.addHeader(content_length);
            request.setContent(this.sdp.getBytes(), content_type);
            inviteTransactionId = sipProvider.getNewClientTransaction(request);
            inviteTransactionId.sendRequest();
            dialog = inviteTransactionId.getDialog();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private FromHeader createFromHeader(String callerAlias, String callerName, String callerDomain) {
        FromHeader from = null;
        try {
            SipURI fromUri = addressFactory.createSipURI(callerName, callerDomain);
            Address fromAddress = addressFactory.createAddress(fromUri);
            if(callerAlias != null) {
                fromAddress.setDisplayName(callerAlias);
            }
            String tag = Utils.getInstance().generateTag();
            from = headerFactory.createFromHeader(fromAddress, tag);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return from;
    }

    private ToHeader createToHeader(String calleeAlias, String calleeName, String calleeIp) {
        ToHeader to = null;
        try {
            SipURI toUri = addressFactory.createSipURI(calleeName, calleeIp);
            Address toAddress = addressFactory.createAddress(toUri);
            if(calleeAlias != null) {
                toAddress.setDisplayName(calleeAlias);
            }
            String tag = Utils.getInstance().generateTag();
            to = headerFactory.createToHeader(toAddress,null);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return to;
    }


    private ArrayList<ViaHeader> createViaListHeader(String transport, int rport) {
        ArrayList viaHeaders = new ArrayList();
        ViaHeader viaHeader = null;
        String ip = listeningPoint.getIPAddress();
        int port = sipProvider.getListeningPoint(transport).getPort();
        try {
            viaHeader = headerFactory.createViaHeader(ip, port, transport, Utils.getInstance().generateBranchId());
            if (rport > 0 && rport < 65535) {
                viaHeader.setParameter("rport", rport+"");
            }
            viaHeaders.add(viaHeader);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        }
        return viaHeaders;
    }

    private ContactHeader createContactHeader(String callerName, String proxyIp, String transport, String uuid) {
        Contact contactHeader = null;

        SipURI contactUri = null;
        try {
            contactUri = addressFactory.createSipURI(callerName, proxyIp);
            contactUri.setPort(listeningPoint.getPort());
            Address contactAddress = addressFactory.createAddress(contactUri);
            contactHeader = new Contact();
            contactHeader.setAddress(contactAddress);
            if(uuid != null) {
                contactHeader.setSipInstanceParam(uuid);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return contactHeader;
    }



    //if server send me the bye message
    @Override
    public void processRequest(RequestEvent requestEvent) {
        Request request = requestEvent.getRequest();
        ServerTransaction serverTransactionId = requestEvent
                .getServerTransaction();

        log.info("\n\nRequest " + request.getMethod()
                + " received at " + sipStack.getStackName()
                + " with server transaction id " + serverTransactionId);

        // We are the UAC so the only request we get is the BYE.
        if (request.getMethod().equals(Request.BYE))
            processBye(request, serverTransactionId);
        else {
            try {
                serverTransactionId.sendResponse( messageFactory.createResponse(202,request) );
            } catch (SipException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvalidArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    public void processBye(Request request,
                           ServerTransaction serverTransactionId) {
        try {
            System.out.println("shootist:  got a bye .");
            if (serverTransactionId == null) {
                System.out.println("shootist:  null TID.");
                return;
            }
            Dialog dialog = serverTransactionId.getDialog();
            System.out.println("Dialog State = " + dialog.getState());
            Response response = messageFactory.createResponse(200, request);
            serverTransactionId.sendResponse(response);
            System.out.println("shootist:  Sending OK.");
            System.out.println("Dialog State = " + dialog.getState());

        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(0);

        }
    }
    //client side
    @Override
    public void processResponse(ResponseEvent responseEvent) {
        log.info("Got a response:");
        Response response = (Response) responseEvent.getResponse();
        ClientTransaction tid = responseEvent.getClientTransaction();
        CSeqHeader cseq = (CSeqHeader) response.getHeader(CSeqHeader.NAME);

        log.info("Response received : Status Code = "
                + response.getStatusCode() + " " + cseq);


        if (tid == null) {
            // RFC3261: MUST respond to every 2xx
            if (ackRequest!=null && dialog!=null) {
                System.out.println("re-sending ACK");
                try {
                    dialog.sendAck(ackRequest);
                } catch (SipException se) {
                    se.printStackTrace();
                }
            }
            return;
        }
        // If the caller is supposed to send the bye

//        if (!byeTaskRunning) {
//            byeTaskRunning = true;
//            new Timer().schedule(new ByeTask(dialog), 50000) ;
//        }

        log.info("transaction state is " + tid.getState());
        log.info("Dialog = " + tid.getDialog());
        log.info("Dialog State is " + tid.getDialog().getState());

        try {

            if (response.getStatusCode() == Response.OK) {
                if (cseq.getMethod().equals(Request.INVITE)) {

                    log.info("Dialog after 200 OK  " + dialog);
                    log.info("Dialog State after 200 OK  " + dialog.getState());
                    ackRequest = dialog.createAck( ((CSeqHeader) response.getHeader(CSeqHeader.NAME)).getSeqNumber() );
                    log.info("Sending ACK");
                    dialog.sendAck(ackRequest);
                    // disposal the sdp message  to get the video port and audio port

                    int videoPort = 0;
                    int audioPort = 0;
                    byte[] responseContent = response.getRawContent();
                    String contentString = new String(responseContent);
                    SdpFactory sdpFactory = SdpFactory.getInstance();
                    SessionDescription sd = sdpFactory.createSessionDescription(contentString);
                    Vector medias = sd.getMediaDescriptions(true);
                    for(int i = 0; i < medias.size(); i++) {
                        MediaDescription md = (MediaDescription)medias.elementAt(i);
                        Media media = md.getMedia();
                        String protocol = media.getProtocol();
                        String type = media.getMediaType();
                        int port = media.getMediaPort();
                        if(type.toLowerCase().equals("video")) {
                            System.out.println("video udp port:"+ port);
                            videoPort = port;

                        } else if (type.toLowerCase().equals("audio")) {
                            System.out.println("audio udp port:"+ port);
                            audioPort = port;
                        } else if (type.toLowerCase().equals("")) {

                        }
                    }

                    if(sr != null) {

                        sr.setSuccessful(true);
                        sr.setResponseData("{\"videoPort\":" + videoPort + "}", "utf-8");
                    }
                    System.out.println("============");
                    sipProvider.removeSipListener(this);
                    sipStack.deleteSipProvider(sipProvider);
                    sipStack.stop();
                    return;
                    // JvB: test REFER, reported bug in tag handling
                    // dialog.sendRequest(  sipProvider.getNewClientTransaction( dialog.createRequest("REFER") ));

                } else if (cseq.getMethod().equals(Request.CANCEL)) {
                    if (dialog.getState() == DialogState.CONFIRMED) {
                        // oops cancel went in too late. Need to hang up the
                        // dialog.
                        System.out
                                .println("Sending BYE -- cancel went in too late !!");
                        Request byeRequest = dialog.createRequest(Request.BYE);
                        ClientTransaction ct = sipProvider
                                .getNewClientTransaction(byeRequest);
                        dialog.sendRequest(ct);

                    }

                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(0);
        }
    }

    @Override
    public void processTimeout(TimeoutEvent timeoutEvent) {
        log.error("Transaction Time out");
    }

    @Override
    public void processIOException(IOExceptionEvent ioExceptionEvent) {
        log.error("IOException happens");
    }

    @Override
    public void processTransactionTerminated(TransactionTerminatedEvent transactionTerminatedEvent) {
        log.info("Transaction terminated event recieved");
    }

    @Override
    public void processDialogTerminated(DialogTerminatedEvent dialogTerminatedEvent) {
        log.info("Dialog Terminated");
    }
}
