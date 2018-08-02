package com.polycom.gui;
import com.polycom.sampler.SipSamper;
import org.apache.jmeter.samplers.gui.AbstractSamplerGui;
import org.apache.jmeter.testelement.TestElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

public class SipSamplerGui extends AbstractSamplerGui {
    private static final Logger log = LoggerFactory.getLogger(SipSamplerGui.class);
    private SipPanel sipPanel = new SipPanel();

    public SipSamplerGui(){
        super();
        setLayout(new BorderLayout(1, 5));
        setBorder(makeBorder());

        this.add(makeTitlePanel(), BorderLayout.NORTH);
        this.add(sipPanel, BorderLayout.CENTER);
    }

    @Override
    public String getLabelResource() {
        return null;
    }

    @Override
    public TestElement createTestElement() {
        SipSamper ss = new SipSamper();
        this.modifyTestElement(ss);
        return ss;
    }

    @Override
    public void modifyTestElement(TestElement testElement) {
        SipSamper ss = (SipSamper)testElement;
        ss.setRemoteName(this.sipPanel.getRemoteName());
        ss.setRemoteAddress(this.sipPanel.getRemoteAddress());
        ss.setRemotePort(this.sipPanel.getRemotePort());
        ss.setTransport(this.sipPanel.getTransport());
        ss.setLocalUserAlias(this.sipPanel.getLocalUserAlias());
        ss.setLocalName(this.sipPanel.getLocalName());
        ss.setLocalNameDomain(this.sipPanel.getLocalNameDomain());
        ss.setLocalAddress(this.sipPanel.getLocalAddress());
        ss.setLocalPort(this.sipPanel.getLocalPort());
        ss.setUuid(this.sipPanel.getUuid());
        ss.setSdp(this.sipPanel.getSdp());
    }

    @Override
    public void configure(TestElement element) {
        super.configure(element);
        SipSamper sampler = (SipSamper)element;
        this.sipPanel.setRemoteName(sampler.getRemoteName());
        this.sipPanel.setRemoteAddress(sampler.getRemoteAddress());
        this.sipPanel.setRemotePort(sampler.getRemotePort());
        this.sipPanel.setLocalUserAlias(sampler.getLocalUserAlias());
        this.sipPanel.setLocalName(sampler.getLocalName());
        this.sipPanel.setLocalNameDomain(sampler.getLocalNameDomain());
        this.sipPanel.setLocalPort(sampler.getLocalPort());
        this.sipPanel.setLocalAddress(sampler.getLocalAddress());
        this.sipPanel.setUuid(sampler.getUuid());
        this.sipPanel.setSdp(sampler.getSdp());
    }
}
