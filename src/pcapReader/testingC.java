package pcapReader;

import java.util.*;
import IPConverter.IPConverter;
import models.TableEntries;
import org.jnetpcap.Pcap;
import org.jnetpcap.packet.JPacket;
import org.jnetpcap.packet.JPacketHandler;
import org.jnetpcap.packet.format.FormatUtils;
import org.jnetpcap.protocol.lan.Ethernet;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.jnetpcap.protocol.tcpip.Udp;
import org.jnetpcap.protocol.network.Arp;
import org.jnetpcap.protocol.network.Ip6;


/**
 * Created by Mark Skerl, Creighton Lee, and Amrit Gill on 2017-03-30.
 */
public class testingC {
    private static String FILENAME;
    private static int[] tcp_count = {0};
    private static int[] udp_count = {0};
    private static int[] arp_count = {0};
    private static int[] ip4_count = {0};
    private static int[] ip6_count = {0};

    private static ArrayList<TableEntries> ethernetTableValues = new ArrayList<>();
    private static ArrayList<TableEntries> tcpTableValues = new ArrayList<>();
    private static ArrayList<TableEntries> udpTableValues = new ArrayList<>();
    private static ArrayList<TableEntries> ip4TableValues = new ArrayList<>();
    private static ArrayList<TableEntries> ip6TableValues = new ArrayList<>();
    private static ArrayList<TableEntries> arpTableValues = new ArrayList<>();

    public static void main(String[] args) {
        //For Testing purposes
        String fileN = "tests/new_test.pcap";
        setFilename(fileN);

        runFile();
        System.out.println("TCP count is: " + getCount_tcp());
        System.out.println("UDP count is: " + getCount_udp());
        System.out.println("ARP count is: " + getCount_arp());
        System.out.println("IP4 count is: " + getCount_ip4());
        System.out.println("IP6 count is: " + getCount_ip6());

    }

    public static void setFilename(String filename){
        FILENAME = filename;
    }

    public static String getFilename(){
        return FILENAME;
    }

    public static void runFile(){
        String FILENAME = getFilename();
        final StringBuilder errbuf = new StringBuilder();

        final Pcap pcap = Pcap.openOffline(FILENAME, errbuf);
        if (pcap == null) {
            System.err.println(errbuf);
            return;
        }

        //TODO we can either reset the cache or just add ip address to it
        IPConverter.resetCache();

        pcap.loop(Pcap.LOOP_INFINITE, new JPacketHandler<StringBuilder>() {
            final Tcp tcp = new Tcp();
            final Udp udp = new Udp();
            final Arp arp = new Arp();
            final Ip4 ip4 = new Ip4();
            final Ip6 ip6 = new Ip6();
            final Ethernet ethernet = new Ethernet();

            public void nextPacket(JPacket packet, StringBuilder errbuf) {
                TableEntries te = new TableEntries();
                TableEntries TCP_table = new TableEntries();
                TableEntries UDP_table = new TableEntries();
                TableEntries IP4_tables = new TableEntries();
                TableEntries IP6_tables = new TableEntries();
                TableEntries ARP_tables = new TableEntries();

                if (packet.hasHeader(tcp)) {
                    tcp_count[0] = tcp_count[0] + 1;
                    TCP_table.setDestination_tcp(String.valueOf(tcp.destination()));
                    TCP_table.setSource_tcp(String.valueOf(tcp.source()));
                    TCP_table.setSequence_tcp(String.valueOf(tcp.seq()));
                    TCP_table.setAck_tcp(String.valueOf(tcp.ack()));
                    TCP_table.setHeaderLength_tcp(String.valueOf(tcp.getHeaderLength()));
                    TCP_table.setChecksum_tcp(String.valueOf(tcp.checksum()));
                    TCP_table.setChecksum_tcp_c(tcp.checksumDescription());
                    TCP_table.setTcp_seglength(String.valueOf(tcp.getPayloadLength()));
                    tcpTableValues.add(TCP_table);
                }
                if (packet.hasHeader(udp)) {
                    udp_count[0] = udp_count[0] + 1;
                    UDP_table.setDestination_udp(String.valueOf(udp.destination()));
                    UDP_table.setSource_udp(String.valueOf(udp.source()));
                    UDP_table.setHeaderLength_udp(String.valueOf(udp.getHeaderLength()));
                    UDP_table.setChecksum_udp(String.valueOf(udp.checksum()));
                    UDP_table.setChecksum_udp_c(udp.checksumDescription());
                    udpTableValues.add(UDP_table);

                }
                if (packet.hasHeader(arp)) {
                    arp_count[0] = arp_count[0] + 1;
                    ARP_tables.setHardware_arp(arp.hardwareTypeDescription());
                    ARP_tables.setProtocol_arp(arp.protocolTypeDescription());
                    ARP_tables.setOperation_arp(arp.operationDescription());
                    ARP_tables.setHeaderLength_arp(String.valueOf(arp.getHeaderLength()));
                    arpTableValues.add(ARP_tables);
                }

                if (packet.hasHeader(ip4)) {
                    ip4_count[0] = ip4_count[0] + 1;

                    String ip4Source = FormatUtils.ip(ip4.source());
                    String ip4Dest = FormatUtils.ip(ip4.destination());

                    IPConverter.add(ip4Source);
                    IPConverter.add(ip4Dest);
                    IP4_tables.setVersion_ip4(String.valueOf(ip4.version()));
                    IP4_tables.setHeaderLength_ip4(String.valueOf(ip4.getHeaderLength()));
                    IP4_tables.setLength_ip4(String.valueOf(ip4.getLength()));
                    IP4_tables.setTtl_ip4(String.valueOf(ip4.ttl()));
                    IP4_tables.setType_ip4(String.valueOf(ip4.typeEnum()));
                    IP4_tables.setSource_ip4(ip4Source);
                    IP4_tables.setDestination_ip4(ip4Dest);
//                    try {
//                        IP4_tables.setSource_name_ip4(InetAddress.getByName(ip4Source).getHostName());
//                    } catch (UnknownHostException e) {
//                        e.printStackTrace();
//                    };
                    IP4_tables.setSource_name_ip4(IPConverter.getHostname(ip4Source));
                    IP4_tables.setDest_name_ip4(IPConverter.getHostname(ip4Dest));
                    ip4TableValues.add(IP4_tables);

                }
                if (packet.hasHeader(ip6)) {
                    ip6_count[0] = ip6_count[0] + 1;
                    IP6_tables.setVersion_ip6(String.valueOf(ip6.version()));
                    IP6_tables.setPayload_length(String.valueOf(ip6.length()));
                    IP6_tables.setHop_limit(String.valueOf(ip6.hopLimit()));
                    IP6_tables.setSource_ip6(FormatUtils.ip(ip6.source()));
                    IP6_tables.setDestination_ip6((FormatUtils.ip(ip6.destination())));
                    IP6_tables.setNext_header(String.valueOf(ip6.next()));
                    IP6_tables.setLength_ip6(String.valueOf(ip6.getNextHeaderId()));
                    ip6TableValues.add(IP6_tables);

                }

                if(packet.hasHeader(ethernet)){
                    te.setType_ethernet(String.valueOf(ethernet.typeEnum()));
                    if(packet.hasHeader(arp)){
                        te.setType_ethernet("ARP");
                    }
                    te.setDestination_ethernet(FormatUtils.mac(ethernet.destination()));
                    te.setSource_ethernet(FormatUtils.mac(ethernet.source()));
                    te.setEthernet_date(new Date(packet.getCaptureHeader().timestampInMillis()));
                    te.setEthernet_caplen(packet.getCaptureHeader().caplen());
                    te.setEthernet_len(packet.getCaptureHeader().wirelen());
                    te.setEthernet_frame_no(packet.getFrameNumber());
                    ethernetTableValues.add(te);

                }

            }
        }, errbuf);
        setTCP_count(tcp_count);
        setUDP_count(udp_count);
        setARP_count(arp_count);
        setIP4_count(ip4_count);
        setIP6_count(ip6_count);

        pcap.close();
    }
    //TCP
    public static void setTCP_count(int[] count){
        tcp_count = count;
    }

    public static int getCount_tcp(){
        return tcp_count[0];
    }

    //UDP
    public static void setUDP_count(int[] count){
        udp_count = count;
    }

    public static int getCount_udp(){
        return udp_count[0];
    }

    //ARP
    public static void setARP_count(int[] count){
        arp_count = count;
    }

    public static int getCount_arp(){
        return arp_count[0];
    }

    //IP4
    public static void setIP4_count(int[] count){
        ip4_count = count;
    }

    public static int getCount_ip4(){
        return ip4_count[0];
    }

    //IP6
    public static void setIP6_count(int[] count){
        ip6_count = count;
    }

    public static int getCount_ip6(){
        return ip6_count[0];
    }

    //Ethernet table entries
    public static ArrayList<TableEntries> getEthernetTableEntries(){
        return ethernetTableValues;
    }

    //TCP Table Entries
    public static ArrayList<TableEntries> getTCPTableEntries(){
        return tcpTableValues;
    }

    //UDP Table Entries
    public static ArrayList<TableEntries> getUDPTableEntries(){
        return udpTableValues;
    }

    //IP4 Table Entries
    public static ArrayList<TableEntries> getIP4TableEntries(){
        return ip4TableValues;
    }

    //IP6 Table Entries
    public static ArrayList<TableEntries> getIP6TableEntries(){
        return ip6TableValues;
    }

    //ARP Table Entries
    public static ArrayList<TableEntries> getARPTableEntries(){
        return arpTableValues;
    }

}
