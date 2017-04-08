package models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Mark-Laptop on 2017-04-07.
 */
public class TableEntries {
    //Ethernet
    private StringProperty destination_ethernet = new SimpleStringProperty();
    private StringProperty source_ethernet = new SimpleStringProperty();
    private StringProperty type_ethernet = new SimpleStringProperty();

    //TCP
    private StringProperty destination_tcp = new SimpleStringProperty();
    private StringProperty source_tcp = new SimpleStringProperty();
    private StringProperty sequence_tcp = new SimpleStringProperty();
    private StringProperty ack_tcp = new SimpleStringProperty();
    private StringProperty headerLength_tcp = new SimpleStringProperty();
    private StringProperty checksum_tcp = new SimpleStringProperty();

    //UDP
    private StringProperty destination_udp = new SimpleStringProperty();
    private StringProperty source_udp = new SimpleStringProperty();
    private StringProperty headerLength_udp = new SimpleStringProperty();
    private StringProperty checksum_udp = new SimpleStringProperty();

    //IP4
    private StringProperty version_ip4 = new SimpleStringProperty();
    private StringProperty headerLength_ip4 = new SimpleStringProperty();
    private StringProperty length_ip4 = new SimpleStringProperty();
    private StringProperty ttl_ip4 = new SimpleStringProperty();
    private StringProperty type_ip4 = new SimpleStringProperty();
    private StringProperty source_ip4 = new SimpleStringProperty();
    private StringProperty destination_ip4 = new SimpleStringProperty();


    //IP4
    public String getVersion_ip4() {
        return version_ip4.get();
    }

    public StringProperty version_ip4Property() {
        return version_ip4;
    }

    public void setVersion_ip4(String version_ip4) {
        this.version_ip4.set(version_ip4);
    }

    public String getHeaderLength_ip4() {
        return headerLength_ip4.get();
    }

    public StringProperty headerLength_ip4Property() {
        return headerLength_ip4;
    }

    public void setHeaderLength_ip4(String headerLength_ip4) {
        this.headerLength_ip4.set(headerLength_ip4);
    }

    public String getLength_ip4() {
        return length_ip4.get();
    }

    public StringProperty length_ip4Property() {
        return length_ip4;
    }

    public void setLength_ip4(String length_ip4) {
        this.length_ip4.set(length_ip4);
    }

    public String getTtl_ip4() {
        return ttl_ip4.get();
    }

    public StringProperty ttl_ip4Property() {
        return ttl_ip4;
    }

    public void setTtl_ip4(String ttl_ip4) {
        this.ttl_ip4.set(ttl_ip4);
    }

    public String getType_ip4() {
        return type_ip4.get();
    }

    public StringProperty type_ip4Property() {
        return type_ip4;
    }

    public void setType_ip4(String type_ip4) {
        this.type_ip4.set(type_ip4);
    }

    public String getSource_ip4() {
        return source_ip4.get();
    }

    public StringProperty source_ip4Property() {
        return source_ip4;
    }

    public void setSource_ip4(String source_ip4) {
        this.source_ip4.set(source_ip4);
    }

    public String getDestination_ip4() {
        return destination_ip4.get();
    }

    public StringProperty destination_ip4Property() {
        return destination_ip4;
    }

    public void setDestination_ip4(String destination_ip4) {
        this.destination_ip4.set(destination_ip4);
    }



    //UDP
    public String getDestination_udp() {
        return destination_udp.get();
    }

    public StringProperty destination_udpProperty() {
        return destination_udp;
    }

    public void setDestination_udp(String destination_udp) {
        this.destination_udp.set(destination_udp);
    }

    public String getSource_udp() {
        return source_udp.get();
    }

    public StringProperty source_udpProperty() {
        return source_udp;
    }

    public void setSource_udp(String source_udp) {
        this.source_udp.set(source_udp);
    }

    public String getHeaderLength_udp() {
        return headerLength_udp.get();
    }

    public StringProperty headerLength_udpProperty() {
        return headerLength_udp;
    }

    public void setHeaderLength_udp(String headerLength_udp) {
        this.headerLength_udp.set(headerLength_udp);
    }

    public String getChecksum_udp() {
        return checksum_udp.get();
    }

    public StringProperty checksum_udpProperty() {
        return checksum_udp;
    }

    public void setChecksum_udp(String checksum_udp) {
        this.checksum_udp.set(checksum_udp);
    }



    //TCP
    public String getSource_tcp() {
        return source_tcp.get();
    }

    public StringProperty source_tcpProperty() {
        return source_tcp;
    }

    public void setSource_tcp(String source_tcp) {
        this.source_tcp.set(source_tcp);
    }

    public String getSequence_tcp() {
        return sequence_tcp.get();
    }

    public StringProperty sequence_tcpProperty() {
        return sequence_tcp;
    }

    public void setSequence_tcp(String sequence_tcp) {
        this.sequence_tcp.set(sequence_tcp);
    }

    public String getAck_tcp() {
        return ack_tcp.get();
    }

    public StringProperty ack_tcpProperty() {
        return ack_tcp;
    }

    public void setAck_tcp(String ack_tcp) {
        this.ack_tcp.set(ack_tcp);
    }

    public String getHeaderLength_tcp() {
        return headerLength_tcp.get();
    }

    public StringProperty headerLength_tcpProperty() {
        return headerLength_tcp;
    }

    public void setHeaderLength_tcp(String headerLength_tcp) {
        this.headerLength_tcp.set(headerLength_tcp);
    }

    public String getChecksum_tcp() {
        return checksum_tcp.get();
    }

    public StringProperty checksum_tcpProperty() {
        return checksum_tcp;
    }

    public void setChecksum_tcp(String checksum_tcp) {
        this.checksum_tcp.set(checksum_tcp);
    }

    public String getDestination_tcp() {
        return destination_tcp.get();
    }

    public StringProperty destination_tcpProperty() {
        return destination_tcp;
    }

    public void setDestination_tcp(String destination_tcp) {
        this.destination_tcp.set(destination_tcp);
    }



    //ETHERNET
    public String getDestination_ethernet() {
        return destination_ethernet.get();
    }

    public StringProperty destination_ethernetProperty() {
        return destination_ethernet;
    }

    public void setDestination_ethernet(String destination_ethernet) {
        this.destination_ethernet.set(destination_ethernet);
    }

    public String getSource_ethernet() {
        return source_ethernet.get();
    }

    public StringProperty source_ethernetProperty() {
        return source_ethernet;
    }

    public void setSource_ethernet(String source_ethernet) {
        this.source_ethernet.set(source_ethernet);
    }

    public String getType_ethernet() {
        return type_ethernet.get();
    }

    public StringProperty type_ethernetProperty() {
        return type_ethernet;
    }

    public void setType_ethernet(String type_ethernet) {
        this.type_ethernet.set(type_ethernet);
    }

}
