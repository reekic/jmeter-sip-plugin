package com.polycom.sampler;

import gov.nist.javax.sip.Utils;
import javax.sip.*;
import javax.sip.address.*;
import javax.sip.header.*;
import javax.sip.message.*;

import java.text.ParseException;
import java.util.*;

public class SipClient implements SipListener{

    private SipProvider sipProvider;
    private SipStack sipStack;

    private AddressFactory addressFactory;
    private MessageFactory messageFactory;
    private HeaderFactory headerFactory;
    private ContactHeader contactHeader;

    private Dialog dialog;
    private Request ackRequest;


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


    public SipClient() {

    }



    //server side
    @Override
    public void processRequest(RequestEvent requestEvent) {

    }

    //client side
    @Override
    public void processResponse(ResponseEvent responseEvent) {
        System.out.println("Got a response:");
        Response response = (Response) responseEvent.getResponse();
        ClientTransaction tid = responseEvent.getClientTransaction();
        CSeqHeader cseq = (CSeqHeader) response.getHeader(CSeqHeader.NAME);

        System.out.println("Response received : Status Code = "
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

//        if ( examples.simplecallsetup.Shootme.callerSendsBye && !byeTaskRunning) {
//            byeTaskRunning = true;
//            new Timer().schedule(new ByeTask(dialog), 40000) ;
//        }

        System.out.println("transaction state is " + tid.getState());
        System.out.println("Dialog = " + tid.getDialog());
        System.out.println("Dialog State is " + tid.getDialog().getState());

        try {
            if (response.getStatusCode() == Response.OK) {
                if (cseq.getMethod().equals(Request.INVITE)) {
                    responseEvent.getBytes();
                    System.out.println("Dialog after 200 OK  " + dialog);
                    System.out.println("Dialog State after 200 OK  " + dialog.getState());
                    ackRequest = dialog.createAck( ((CSeqHeader) response.getHeader(CSeqHeader.NAME)).getSeqNumber() );
                    System.out.println("Sending ACK");
                    dialog.sendAck(ackRequest);

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

    }

    @Override
    public void processIOException(IOExceptionEvent ioExceptionEvent) {

    }

    @Override
    public void processTransactionTerminated(TransactionTerminatedEvent transactionTerminatedEvent) {

    }

    @Override
    public void processDialogTerminated(DialogTerminatedEvent dialogTerminatedEvent) {

    }
}
