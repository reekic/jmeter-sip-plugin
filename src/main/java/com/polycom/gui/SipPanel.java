package com.polycom.gui;

import org.apache.jmeter.gui.util.VerticalPanel;

import javax.swing.*;
import java.awt.*;

public class SipPanel extends JPanel {

    private JLabel remoteNameLabel = new JLabel("Remote Name: ");
    private JTextField remoteName = new JTextField();

    private JLabel remoteAddressLabel = new JLabel("Remote Address: ");
    private JTextField remoteAddress;

    private JLabel remotePortLabel = new JLabel("Remote Port: ");
    private JTextField remotePort;

    private JLabel transportLabel = new JLabel("Transport Protocol: ");
    private JTextField transport;

    private JLabel localUserAliasLabel = new JLabel( "Local User Alias: ");
    private JTextField localUserAlias;

    private JLabel localNameLabel = new JLabel("Local User Name: ");
    private JTextField localName;

    private JLabel localAddressLabel = new JLabel("Local Address: ");
    private JTextField localAddress;

    private JLabel localPortLabel = new JLabel("Local Port: ");
    private JTextField localPort;

    private JLabel uuidLabel = new JLabel("UUID: ");
    private JTextField uuid;

    private JLabel sdpLabel = new JLabel("SDP Message: ");
    private JTextArea sdp;


    public SipPanel() {
        setLayout(new BorderLayout(1, 15));

        VerticalPanel mainPanel = new VerticalPanel();
        mainPanel.add(createRemoteInfoPanel());
        mainPanel.add(createLocalInfoPanel());
        mainPanel.add(createUuidPanel());
        mainPanel.add(createSdpPanel());
        add(mainPanel);
    }


    public JPanel createRemoteInfoPanel(){
        return null;
    }
    public JPanel createLocalInfoPanel(){
        return null;
    }
    public JPanel createUuidPanel() {
        return null;
    }

    public JPanel createSdpPanel() {
        return null;
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
