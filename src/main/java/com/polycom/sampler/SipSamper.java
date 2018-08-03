package com.polycom.sampler;

import com.polycom.gui.SipSamplerGui;
import org.apache.jmeter.samplers.AbstractSampler;
import org.apache.jmeter.samplers.Entry;
import org.apache.jmeter.samplers.SampleResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SipSamper extends AbstractSampler {
    private static final Logger log = LoggerFactory.getLogger(SipSamper.class);


    public SipSamper() {
        super();
        setName("SIP Sampler");
    }
    @Override
    public SampleResult sample(Entry entry) {
        SampleResult sr = new SampleResult();


        log.info("=====================================");
        log.info("remote name:" + getRemoteName());
        log.info("remote address:" + getRemoteAddress());
        log.info("remote port:"+getRemotePort());
        log.info("remote transport:"+getTransport());


        log.info("local alias:"+getLocalUserAlias());
        log.info("local name:"+getLocalName());
        log.info("local domain:"+getLocalNameDomain());

        log.info("proxy address:"+getLocalAddress());
        log.info("proxy port:"+getLocalPort());

        log.info("UUID:"+getUuid());
        log.info("SDP:"+getSdp());
        log.info("SDP length:"+getSdp().length());


        log.info("=====================================");

        SipClient sc = new SipClient();

        sc.setCalleeIp(getRemoteAddress());
        sc.setCalleeName(getRemoteName());
        sc.setCalleePort(Integer.parseInt(getRemotePort()));
        sc.setCallerAlias(getLocalUserAlias());
        sc.setCallerName(getLocalName());
        sc.setTransport(getTransport());
        sc.setCallerDomain(getLocalNameDomain());
        sc.setProxyIp(getLocalAddress());
        sc.setProxyPort(Integer.parseInt(getLocalPort()));
        sc.setCallerUuid(getUuid());
        sc.setSdp(SdpSample.sdp);
        sc.setSr(sr);
        sr.setSampleLabel("SIP Sampler-invite");
        sc.startInvite();
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return sr;
    }


    public String getRemoteName() {
        return this.getPropertyAsString("SIPSAMPLER.REMOTENAME");
    }

    public void setRemoteName(String remoteName) {
        this.setProperty("SIPSAMPLER.REMOTENAME", remoteName);
    }

    public String getRemoteAddress() {
        return this.getPropertyAsString("SIPSAMPLER.REMOTEADDRESS");
    }

    public void setRemoteAddress(String remoteAddress) {
        this.setProperty("SIPSAMPLER.REMOTEADDRESS", remoteAddress);
    }

    public String getRemotePort() {
        return this.getPropertyAsString("SIPSAMPLER.REMOTEPORT");
    }

    public void setRemotePort(String remotePort) {
        this.setProperty("SIPSAMPLER.REMOTEPORT", remotePort);
    }

    public String getTransport() {
        return this.getPropertyAsString("SIPSAMPLER.TRANSPORT");
    }

    public void setTransport(String transport) {
        this.setProperty("SIPSAMPLER.TRANSPORT", transport);
    }

    public String getLocalUserAlias() {
        return this.getPropertyAsString("SIPSAMPLER.LOCALUSERALIAS");
    }

    public void setLocalUserAlias(String localUserAlias) {
        this.setProperty("SIPSAMPLER.LOCALUSERALIAS", localUserAlias);
    }
    public String getLocalName() {
        return this.getPropertyAsString("SIPSAMPLER.LOCALNAME");
    }

    public void setLocalName(String localName) {
        this.setProperty("SIPSAMPLER.LOCALNAME", localName);
    }

    public String getLocalNameDomain() {
        return this.getPropertyAsString("SIPSAMPLER.LOCALNAMEDOMAIN");
    }

    public void setLocalNameDomain(String localNameDomain) {
        this.setProperty("SIPSAMPLER.LOCALNAMEDOMAIN", localNameDomain);
    }

    public String getLocalAddress() {
        return this.getPropertyAsString("SIPSAMPLER.LOCALADDRESS");
    }

    public void setLocalAddress(String localAddress) {
        this.setProperty("SIPSAMPLER.LOCALADDRESS", localAddress);
    }

    public String getLocalPort() {
        return this.getPropertyAsString("SIPSAMPLER.LOCALPORT");
    }

    public void setLocalPort(String localPort) {
        this.setProperty("SIPSAMPLER.LOCALPORT", localPort);
    }

    public String getUuid() {
        return this.getPropertyAsString("SIPSAMPLER.UUID");
    }

    public void setUuid(String uuid) {
        this.setProperty("SIPSAMPLER.UUID", uuid);
    }

    public String getSdp() {
        return this.getPropertyAsString("SIPSAMPLER.SDP");
    }

    public void setSdp(String sdp) {
        this.setProperty("SIPSAMPLER.SDP", sdp);
    }
}
