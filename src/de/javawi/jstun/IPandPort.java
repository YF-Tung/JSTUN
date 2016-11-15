package de.javawi.jstun;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by yftung on 2016/11/14.
 */

public class IPandPort {
    public InetAddress ip;
    public int port;
    public String sip;

    public IPandPort() {
        this.ip = null;
        this.port = 0;
    }

    public IPandPort (InetAddress ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public IPandPort (String ip_str, int port) {
        sip = ip_str;
        this.port = port;
    }

    // For stun server
    public IPandPort (String ip_str) {
        sip = ip_str;
        this.port = 3478;
    }

    public String toString(){
        return ip.toString() + ":" + port;
    }
}
