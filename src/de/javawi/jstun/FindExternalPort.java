package de.javawi.jstun;

import de.javawi.jstun.test.DiscoveryInfo;
import de.javawi.jstun.test.DiscoveryTest;

import java.net.BindException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class FindExternalPort {

    static IPandPort stunServerList[] = {
            new IPandPort("stun.ekiga.net"),
            new IPandPort("stunserver.org"),
            new IPandPort("stun.l.google.com", 19305)
    };

    public static IPandPort findExternal(int port) {
        IPandPort ret = new IPandPort();
        while (true) {
            for (int i = 0; i < stunServerList.length; i++) {
                IPandPort stunServer = stunServerList[i];


                try {
                    DiscoveryTest test = new DiscoveryTest(InetAddress.getByName("0.0.0.0"), port, stunServer.sip, stunServer.port);
                    DiscoveryInfo di = test.quickTest();
                    System.out.print(di.getPublicIP().toString() + ":" + di.getPublicPort() + di.getLocalIP() + ":" + port);
                    ret.ip = di.getPublicIP();
                    ret.port = di.getPublicPort();
                } catch (BindException be) {
                    System.out.println("0.0.0.0: " + be.getMessage());
                } catch (NullPointerException e) {
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }


                /*
                try {
                    Enumeration<NetworkInterface> ifaces = NetworkInterface.getNetworkInterfaces();
                    while (ifaces.hasMoreElements()) {
                        NetworkInterface iface = ifaces.nextElement();
                        Enumeration<InetAddress> iaddresses = iface.getInetAddresses();
                        while (iaddresses.hasMoreElements()) {
                            InetAddress iaddress = iaddresses.nextElement();
                            if (Class.forName("java.net.Inet4Address").isInstance(iaddress)) {
                                if ((!iaddress.isLoopbackAddress()) && (!iaddress.isLinkLocalAddress())) {
                                    try {
                                        DiscoveryTest test = new DiscoveryTest(iaddress, port, stunServer.sip, stunServer.port);
                                        //DiscoveryTest test = new DiscoveryTest(iaddress, port, "jstun.javawi.de", 3478);
                                        //DiscoveryTest test = new DiscoveryTest(iaddress, "stun.sipgate.net", 10000);
                                        // iphone-stun.freenet.de:3478
                                        // larry.gloo.net:3478
                                        // stun.xten.net:3478
                                        // stun.sipgate.net:10000
                                        DiscoveryInfo di = test.quickTest();
                                        System.out.print(di.getPublicIP().toString() + ":" + di.getPublicPort() + di.getLocalIP() + ":" + port);
                                        ret.ip = di.getPublicIP();
                                        ret.port = di.getPublicPort();
                                    } catch (BindException be) {
                                        System.out.println(iaddress.toString() + ": " + be.getMessage());
                                    } catch (NullPointerException e) {
                                        System.out.println(e.getMessage());
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                */





                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                if (ret.ip != null) break;
            }
            if (ret.ip != null) break;
        }
        return ret;
    }

    public static void main(String args[]) {
        int port;
        try {
            port = Integer.parseInt(args[1]);
        } catch (Exception e) { port = 60000; }
        System.out.println("Using port " + port);

        System.out.println(findExternal(port));

    }
}
