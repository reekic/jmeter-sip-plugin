package com.polycom.gui;

import org.apache.jmeter.gui.util.HorizontalPanel;
import org.apache.jmeter.gui.util.VerticalPanel;

import javax.swing.*;
import java.awt.*;

public class SipPanel extends JPanel {

    private JLabel remoteNameLabel = new JLabel("Remote Name: ");
    private JTextField remoteName = new JTextField();

    private JLabel remoteAddressLabel = new JLabel("Remote Address: ");
    private JTextField remoteAddress = new JTextField();

    private JLabel remotePortLabel = new JLabel("Remote Port: ");
    private JTextField remotePort = new JTextField();

    private JLabel transportLabel = new JLabel("Transport Protocol: ");
    private JTextField transport = new JTextField();

    private JLabel localUserAliasLabel = new JLabel( "Local User Alias: ");
    private JTextField localUserAlias = new JTextField();

    private JLabel localNameLabel = new JLabel("Local User Name: ");
    private JTextField localName = new JTextField();

    private JLabel localDomainLabel = new JLabel("Local User Domain: ");
    private JTextField localDomain = new JTextField();

    private JLabel localAddressLabel = new JLabel("Proxy Address: ");
    private JTextField localAddress = new JTextField();

    private JLabel localPortLabel = new JLabel("Proxy Port: ");
    private JTextField localPort = new JTextField();

    private JLabel uuidLabel = new JLabel("UUID: ");
    private JTextField uuid = new JTextField();

    private JLabel sdpLabel = new JLabel("SDP Message: ");
    private JTextArea sdp = new JTextArea();


    public SipPanel() {
        setLayout(new BorderLayout(0, 15));


        VerticalPanel mainPanel = new VerticalPanel();
        mainPanel.add(createRemoteInfoPanel());
        mainPanel.add(createCallerInfoPanel());
        mainPanel.add(createProxyInfoPannel());
        mainPanel.add(createUuidPanel());
        mainPanel.add(createSdpPanel());
        add(mainPanel);
    }

    public JPanel createRemoteInfoPanel(){
        HorizontalPanel calleePanel = new HorizontalPanel();

        remoteNameLabel.setLabelFor(remoteName);
        JPanel namePanel = new JPanel(new BorderLayout(5,0));
        namePanel.add(remoteNameLabel,BorderLayout.WEST);
        namePanel.add(remoteName, BorderLayout.CENTER);

        calleePanel.add(namePanel);

        remoteAddressLabel.setLabelFor(remoteAddress);
        JPanel addressPanel = new JPanel(new BorderLayout(5,0));
        addressPanel.add(remoteAddressLabel,BorderLayout.WEST);
        addressPanel.add(remoteAddress, BorderLayout.CENTER);

        calleePanel.add(addressPanel);

        remotePortLabel.setLabelFor(remotePort);
        JPanel portPanel = new JPanel(new BorderLayout(5,0));
        portPanel.add(remotePortLabel,BorderLayout.WEST);
        portPanel.add(remotePort, BorderLayout.CENTER);

        calleePanel.add(portPanel);

        transportLabel.setLabelFor(transport);
        JPanel transportPanel = new JPanel(new BorderLayout(5,0));
        transportPanel.add(transportLabel,BorderLayout.WEST);
        transportPanel.add(transport, BorderLayout.CENTER);

        calleePanel.add(transportPanel);

        return calleePanel;
    }
    public JPanel createCallerInfoPanel(){
        JPanel callerPannel = new HorizontalPanel();
        localUserAliasLabel.setLabelFor(localUserAlias);
        JPanel aliasPanel = new JPanel(new BorderLayout(5, 0));
        aliasPanel.add(localUserAliasLabel,BorderLayout.WEST);
        aliasPanel.add(localUserAlias, BorderLayout.CENTER);

        localNameLabel.setLabelFor(localName);
        JPanel namePanel = new JPanel(new BorderLayout(5, 0));
        namePanel.add(localNameLabel,BorderLayout.WEST);
        namePanel.add(localName, BorderLayout.CENTER);

        localDomainLabel.setLabelFor(localDomain);
        JPanel domainPanel = new JPanel(new BorderLayout(5, 0));
        domainPanel.add(localDomainLabel,BorderLayout.WEST);
        domainPanel.add(localDomain, BorderLayout.CENTER);

        callerPannel.add(aliasPanel);
        callerPannel.add(namePanel);
        callerPannel.add(domainPanel);

        return callerPannel;
    }
    public JPanel createProxyInfoPannel() {
        JPanel proxyPannel = new HorizontalPanel();

        localAddressLabel.setLabelFor(localAddress);
        JPanel addressPanel = new JPanel(new BorderLayout(5, 5));
        addressPanel.add(localAddressLabel,BorderLayout.WEST);
        addressPanel.add(localAddress, BorderLayout.CENTER);


        localPortLabel.setLabelFor(localPort);
        JPanel portPanel = new JPanel(new BorderLayout(5, 5));
        portPanel.add(localPortLabel,BorderLayout.WEST);
        portPanel.add(localPort, BorderLayout.CENTER);

        proxyPannel.add(addressPanel);
        proxyPannel.add(portPanel);

        return proxyPannel;
    }
    public JPanel createUuidPanel() {
        JPanel uuidPanel = new HorizontalPanel();

        uuidLabel.setLabelFor(uuid);
        uuidPanel.add(uuidLabel,BorderLayout.WEST);
        uuidPanel.add(uuid, BorderLayout.CENTER);


        return uuidPanel;
    }
    public JPanel createSdpPanel() {
        JPanel sdpPanel = new HorizontalPanel();

        sdpLabel.setLabelFor(sdp);
        sdpPanel.add(sdpLabel,BorderLayout.WEST);
        sdpPanel.add(sdp, BorderLayout.CENTER);
        return sdpPanel;
    }


    public String getRemoteName() {
        return remoteName.getText();
    }

    public void setRemoteName(String remoteName) {
        this.remoteName.setText(remoteName);
    }

    public String getRemoteAddress() {
        return remoteAddress.getText();
    }

    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress.setText(remoteAddress);
    }

    public String getRemotePort() {
        return remotePort.getText();
    }

    public void setRemotePort(String remotePort) {
        this.remotePort.setText(remotePort);
    }

    public String getTransport() {
        return transport.getText();
    }

    public void setTransport(String transport) {
        this.transport.setText(transport);
    }

    public String getLocalUserAlias() {
        return localUserAlias.getText();
    }

    public void setLocalUserAlias(String localUserAlias) {
        this.localUserAlias.setText(localUserAlias);
    }

    public String getLocalName() {
        return localName.getText();
    }

    public void setLocalName(String localName) {
        this.localName.setText(localName);
    }

    public String getLocalNameDomain() {
        return localDomain.getText();
    }
    public void setLocalNameDomain(String localName) {
        this.localDomain.setText(localName);
    }

    public String getLocalAddress() {
        return localAddress.getText();
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress.setText(localAddress);
    }

    public String getLocalPort() {
        return localPort.getText();
    }

    public void setLocalPort(String localPort) {
        this.localPort.setText(localPort);
    }

    public String getUuid() {
        return uuid.getText();
    }

    public void setUuid(String uuid) {
        this.uuid.setText(uuid);
    }

    public String getSdp() {
        return sdp.getText();
    }

    public void setSdp(String sdp) {
        this.sdp.setText(sdp);
    }
}
