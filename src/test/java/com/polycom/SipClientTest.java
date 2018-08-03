package com.polycom;

import com.polycom.sampler.SdpSample;
import com.polycom.sampler.SipClient;
import org.apache.jmeter.samplers.SampleResult;
import org.junit.Test;
import gov.nist.javax.sip.*;
import static org.junit.Assert.assertTrue;

public class SipClientTest {
    @Test
    public void shouldAnswerWithTrue()
    {
        SampleResult sr = new SampleResult();
        SipClient sc = new SipClient();
        sc.setCalleeIp("10.220.207.212");
        sc.setCalleeName("78509");
        sc.setCalleePort(5060);
        sc.setTransport("tcp");
        sc.setCallerAlias("Automation1 Account1");
        sc.setCallerName("auto");
        sc.setCallerDomain("user1.com");
        sc.setProxyIp("10.250.54.130");
        sc.setProxyPort(64444);
        sc.setCallerUuid(null);
        sc.setSdp(SdpSample.sdp);

        sc.setSr(sr);
        sc.startInvite();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(sr.getResponseDataAsString());
    }
}
