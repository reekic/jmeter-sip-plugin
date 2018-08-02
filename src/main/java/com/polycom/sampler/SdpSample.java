package com.polycom.sampler;

public class SdpSample {
    public static String sdp = "v=0\r\n" +
            "o=- 1532330471 1532330471 IN IP4 10.250.54.130\r\n" +
            "s=MRD=MRE MRC-V=1.0.1\r\n" +
            "c=IN IP4 10.250.54.130\r\n" +
            "b=AS:896\r\n" +
            "t=0 0\r\n" +
            "a=sendrecv\r\n" +
            "a=vnd.polycom.MBA.p2p:v=1.0.1\r\n" +
            "m=audio 1024 RTP/SAVP 118 115 114 9 102 113 101 103 0 8 15 18 119\r\n" +
            "a=sendrecv\r\n" +
            "a=crypto:1 AES_CM_128_HMAC_SHA1_80 inline:/zGEzmmZ+bwyADxwH8UyTCqwURw2INvVdzyQ/egu|2^31\r\n" +
            "a=rtpmap:118 SIRENLPR/48000/1\r\n" +
            "a=fmtp:118 bitrate=64000\r\n" +
            "a=rtpmap:115 G7221/32000\r\n" +
            "a=fmtp:115 bitrate=48000\r\n" +
            "a=rtpmap:114 G7221/32000\r\n" +
            "a=fmtp:114 bitrate=32000\r\n" +
            "a=rtpmap:9 G722/8000\r\n" +
            "a=fmtp:9 bitrate=64000\r\n" +
            "a=rtpmap:102 G7221/16000\r\n" +
            "a=fmtp:102 bitrate=32000\r\n" +
            "a=rtpmap:113 G7221/32000\r\n" +
            "a=fmtp:113 bitrate=24000\r\n" +
            "a=rtpmap:101 G7221/16000\r\n" +
            "a=fmtp:101 bitrate=24000\r\n" +
            "a=rtpmap:103 G7221/16000\r\n" +
            "a=fmtp:103 bitrate=16000\r\n" +
            "a=rtpmap:0 PCMU/8000\r\n" +
            "a=rtpmap:8 PCMA/8000\r\n" +
            "a=rtpmap:15 G728/8000\r\n" +
            "a=rtpmap:18 G729/8000\r\n" +
            "a=fmtp:18 annexb=no\r\n" +
            "a=rtpmap:119 telephone-event/8000\r\n" +
            "a=fmtp:119 0-15\r\n" +
            "m=video 1026 RTP/SAVP 111 109 110 96 34 31 106 105 116\r\n" +
            "a=sendrecv\r\n" +
            "a=crypto:1 AES_CM_128_HMAC_SHA1_80 inline:pVT+no/08DvSLYFOE+eTYaZv8LtPRLyKru4Adrk7|2^31\r\n" +
            "a=rtcp-fb:* nack pli\r\n" +
            "a=rtcp-fb:* ccm fir\r\n" +
            "a=rtcp-fb:* ccm tmmbr\r\n" +
            "a=vnd.polycom.forceVideoMode:9\r\n" +
            "a=rtpmap:111 H264/90000\r\n" +
            "a=fmtp:111 profile-level-id=64001f; packetization-mode=1; max-br=20010; sar=13\r\n" +
            "a=vnd.polycom.avcplus.p2p:111 max-temp-layer-p2p=3\r\n" +
            "a=rtpmap:109 H264/90000\r\n" +
            "a=fmtp:109 profile-level-id=42801f; max-br=20010; sar=13\r\n" +
            "a=vnd.polycom.avcplus.p2p:109 max-temp-layer-p2p=3\r\n" +
            "a=rtpmap:110 H264/90000\r\n" +
            "a=fmtp:110 profile-level-id=42801f; packetization-mode=1; max-br=20010; sar=13\r\n" +
            "a=vnd.polycom.avcplus.p2p:110 max-temp-layer-p2p=3\r\n" +
            "a=rtpmap:96 H263-1998/90000\r\n" +
            "a=fmtp:96 CIF=1;QCIF=1;SQCIF=1;CUSTOM=352,240,1;CUSTOM=640,480,1;T\r\n" +
            "a=rtpmap:34 H263/90000\r\n" +
            "a=fmtp:34 CIF=1;QCIF=1;SQCIF=1\r\n" +
            "a=rtpmap:31 H261/90000\r\n" +
            "a=fmtp:31 CIF=1;QCIF=1\r\n" +
            "a=rtpmap:106 H264-SVC/90000\r\n" +
            "a=fmtp:106 profile-level-id=56001f; packetization-mode=1; max-br=20010; sar=13\r\n" +
            "a=rtpmap:105 H264-SVC/90000\r\n" +
            "a=fmtp:105 profile-level-id=53e01f; packetization-mode=1; max-br=20010; sar=13\r\n" +
            "a=rtpmap:116 vnd.polycom.lpr/9000\r\n" +
            "a=fmtp:116 V=2;minPP=0;PP=150;RS=52;RP=10;PS=1400\r\n" +
            "m=application 1032 UDP/BFCP *\r\n" +
            "a=sendrecv\r\n" +
            "a=setup:actpass\r\n" +
            "a=connection:new\r\n" +
            "a=floorctrl:c-s\r\n" +
            "m=application 1030 RTP/SAVP 100\r\n" +
            "a=sendrecv\r\n" +
            "a=crypto:1 AES_CM_128_HMAC_SHA1_80 inline:HMdOev0pvwTkN2rEfvIhncsdS1lUD8sOOntQ2Wl2|2^31\r\n" +
            "a=rtpmap:100 H224/4800\r\n";

}
