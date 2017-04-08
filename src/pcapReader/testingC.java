package pcapReader;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import models.TableEntries;
import org.jnetpcap.Pcap;
import org.jnetpcap.nio.JMemory;
import org.jnetpcap.packet.JFlow;
import org.jnetpcap.packet.JFlowKey;
import org.jnetpcap.packet.JFlowMap;
import org.jnetpcap.packet.JPacket;
import org.jnetpcap.packet.JPacketHandler;
import org.jnetpcap.packet.JScanner;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.format.FormatUtils;
import org.jnetpcap.protocol.lan.Ethernet;
import org.jnetpcap.protocol.network.Icmp;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Http;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.jnetpcap.protocol.tcpip.Udp;
import org.jnetpcap.protocol.network.Arp;
import org.jnetpcap.protocol.network.Ip6;
import sun.nio.cs.StandardCharsets;
import sun.nio.cs.UTF_32;

import static pcapReader.testingC.runFile;

/**
 * Created by Creighton PC on 2017-03-30.
 */
public class testingC {
    private static String FILENAME;
    private static int[] tcp_count = {0};
    private static int[] udp_count = {0};
    private static int[] arp_count = {0};
    private static int[] ip4_count = {0};
    private static int[] ip6_count = {0};
    private static int[] icmp_count = {0};

    private static ArrayList<TableEntries> ethernetTableValues = new ArrayList<>();
    private static ArrayList<TableEntries> tcpTableValues = new ArrayList<>();
    private static ArrayList<TableEntries> udpTableValues = new ArrayList<>();
    private static ArrayList<TableEntries> ip4TableValues = new ArrayList<>();

    public static void main(String[] args) {
        //For Testing purposes
        String fileN = "tests/random_test.pcap";
        setFilename(fileN);

        runFile();
        System.out.println("TCP count is: " + getCount_tcp());
        System.out.println("UDP count is: " + getCount_udp());
        System.out.println("ARP count is: " + getCount_arp());
        System.out.println("IP4 count is: " + getCount_ip4());
        System.out.println("IP6 count is: " + getCount_ip6());
        System.out.println("ICMP count is: " + getCount_icmp());

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

        pcap.loop(Pcap.LOOP_INFINITE, new JPacketHandler<StringBuilder>() {
            final Tcp tcp = new Tcp();
            final Udp udp = new Udp();
            final Arp arp = new Arp();
            final Ip4 ip4 = new Ip4();
            final Ip6 ip6 = new Ip6();
            final Icmp icmp = new Icmp();

            final Ethernet ethernet = new Ethernet();

            public void nextPacket(JPacket packet, StringBuilder errbuf) {
                TableEntries te = new TableEntries();
                TableEntries TCP_table = new TableEntries();
                TableEntries UDP_table = new TableEntries();
                TableEntries IP4_tables = new TableEntries();

                if (packet.hasHeader(tcp)) {
                    tcp_count[0] = tcp_count[0] + 1;

                    TCP_table.setDestination_tcp(String.valueOf(tcp.destination()));
                    TCP_table.setSource_tcp(String.valueOf(tcp.source()));
                    TCP_table.setSequence_tcp(String.valueOf(tcp.seq()));
                    TCP_table.setAck_tcp(String.valueOf(tcp.ack()));
                    TCP_table.setHeaderLength_tcp(String.valueOf(tcp.getHeaderLength()));
                    TCP_table.setChecksum_tcp(tcp.checksumDescription());
                }
                if (packet.hasHeader(udp)) {
                    udp_count[0] = udp_count[0] + 1;

                    UDP_table.setDestination_udp(String.valueOf(udp.destination()));
                    UDP_table.setSource_udp(String.valueOf(udp.source()));
                    UDP_table.setHeaderLength_udp(String.valueOf(udp.getHeaderLength()));
                    UDP_table.setChecksum_udp(udp.checksumDescription());

                }
                if (packet.hasHeader(arp)) {
                    arp_count[0] = arp_count[0] + 1;
                }
                if (packet.hasHeader(ip4)) {
                    ip4_count[0] = ip4_count[0] + 1;

                    IP4_tables.setVersion_ip4(String.valueOf(ip4.version()));
                    IP4_tables.setHeaderLength_ip4(String.valueOf(ip4.getHeaderLength()));
                    IP4_tables.setLength_ip4(String.valueOf(ip4.getLength()));
                    IP4_tables.setTtl_ip4(String.valueOf(ip4.ttl()));
                    IP4_tables.setType_ip4(ip4.typeDescription());
                    IP4_tables.setSource_ip4(FormatUtils.ip(ip4.source()));
                    IP4_tables.setDestination_ip4(FormatUtils.ip(ip4.destination()));
                }
                if (packet.hasHeader(ip6)) {
                    ip6_count[0] = ip6_count[0] + 1;
                }
                if (packet.hasHeader(icmp)) {
                    icmp_count[0] = icmp_count[0] + 1;
                }

                if(packet.hasHeader(ethernet)){
                    te.setType_ethernet(ethernet.typeDescription());
                    if(packet.hasHeader(arp)){
                        te.setType_ethernet("ARP");
                    }
                    te.setDestination_ethernet(FormatUtils.mac(ethernet.destination()));
                    te.setSource_ethernet(FormatUtils.mac(ethernet.source()));
                }
                ethernetTableValues.add(te);
                tcpTableValues.add(TCP_table);
                udpTableValues.add(UDP_table);
                ip4TableValues.add(IP4_tables);
            }
        }, errbuf);
        setTCP_count(tcp_count);
        setUDP_count(udp_count);
        setARP_count(arp_count);
        setIP4_count(ip4_count);
        setIP6_count(ip6_count);
        setICMP_count(icmp_count);

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

    //ICMP
    public static void setICMP_count(int[] count){
        icmp_count = count;
    }

    public static int getCount_icmp(){
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


}
