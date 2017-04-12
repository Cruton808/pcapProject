package pieChart;

import IPConverter.IPConverter;
import IPConverter.TopDest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.TableEntries;
import org.jnetpcap.protocol.lan.Ethernet;

import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public FileChooser file;

    @FXML
    public PieChart pieChart = new PieChart();

    @FXML
    public PieChart pieChart2 = new PieChart();

    //dest table
    @FXML
    public TableView dest_table = new TableView();
    @FXML
    public PieChart pieChart3 = new PieChart();

    //Ethernet Table
    @FXML
    public TableView ethernet_table = new TableView();
    @FXML
    public TableColumn<TableEntries, String> ethernet_destination;
    @FXML
    public TableColumn<TableEntries, String> ethernet_source;
    @FXML
    public TableColumn<TableEntries, String> ethernet_type;
    @FXML
    public TableColumn<TableEntries, String> ethernet_date;
    @FXML
    public TableColumn<TableEntries, String> ethernet_caplen;
    @FXML
    public TableColumn<TableEntries, String> ethernet_len;
    @FXML
    public TableColumn<TableEntries, String> ethernet_frame_no;

    //TCP Table

    @FXML
    public TableView tcp_table = new TableView();
    public TableColumn<TableEntries, String> tcp_destination;
    @FXML
    public TableColumn<TableEntries, String> tcp_source;
    @FXML
    public TableColumn<TableEntries, String> tcp_seq;
    @FXML
    public TableColumn<TableEntries, String> tcp_ack;
    @FXML
    public TableColumn<TableEntries, String> tcp_header;
    @FXML
    public TableColumn<TableEntries, String> tcp_checksum;
    @FXML
    public TableColumn<TableEntries, String> tcp_checksum_c;
    @FXML
    public TableColumn<TableEntries, String> tcp_seglength;

    //UDP Table
    @FXML
    public TableView udp_table = new TableView();
    @FXML
    public TableColumn<TableEntries, String> udp_destination;
    @FXML
    public TableColumn<TableEntries, String> udp_source;
    @FXML
    public TableColumn<TableEntries, String> udp_header;
    @FXML
    public TableColumn<TableEntries, String> udp_checksum;
    @FXML
    public TableColumn<TableEntries, String> udp_checksum_c;

    //IP4 Table
    @FXML
    public TableView ip4_table = new TableView();
    @FXML
    public TableColumn<TableEntries, String> ip4_version;
    @FXML
    public TableColumn<TableEntries, String> ip4_headerLength;
    @FXML
    public TableColumn<TableEntries, String> ip4_length;
    @FXML
    public TableColumn<TableEntries, String> ip4_ttl;
    @FXML
    public TableColumn<TableEntries, String> ip4_type;
    @FXML
    public TableColumn<TableEntries, String> ip4_source;
    @FXML
    public TableColumn<TableEntries, String> ip4_destination;
    @FXML
    public TableColumn<TableEntries, String> ip4_source_name;
    @FXML
    public TableColumn<TableEntries, String> ip4_dest_name;

    //IP6 Table
    @FXML
    public TableView ip6_table = new TableView();
    @FXML
    public TableColumn<TableEntries, String> ip6_version;
    @FXML
    public TableColumn<TableEntries, String> ip6_hdrlength;
    @FXML
    public TableColumn<TableEntries, String> ip6_hoplimit;
    @FXML
    public TableColumn<TableEntries,String> ip6_source;
    @FXML
    public TableColumn<TableEntries, String> ip6_dest;
    @FXML
    public TableColumn<TableEntries, String> ip6_nexthdr;
    @FXML
    public TableColumn<TableEntries, String> ip6_length;

    //ARP Table
    @FXML
    public TableView arp_table = new TableView();
    @FXML
    public TableColumn<TableEntries, String> arp_hardware;
    @FXML
    public TableColumn<TableEntries, String> arp_protocol;
    @FXML
    public TableColumn<TableEntries, String> arp_operation;
    @FXML
    public TableColumn<TableEntries,String> arp_headerLength;

    //TOP 10 List
    @FXML
    public ListView<String> topTenList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {



        //Ethernet
        ethernet_destination.setCellValueFactory(cellData -> cellData.getValue().destination_ethernetProperty());
        ethernet_source.setCellValueFactory(cellData -> cellData.getValue().source_ethernetProperty());
        ethernet_type.setCellValueFactory(cellData -> cellData.getValue().type_ethernetProperty());
        ethernet_date.setCellValueFactory(cellData -> cellData.getValue().ethernet_dateProperty());
        ethernet_caplen.setCellValueFactory(cellData -> cellData.getValue().ethernet_caplenProperty());
        ethernet_len.setCellValueFactory(cellData -> cellData.getValue().ethernet_lenProperty());
        ethernet_frame_no.setCellValueFactory(cellData -> cellData.getValue().ethernet_frame_noProperty());

        //TCP
        tcp_destination.setCellValueFactory(cellData -> cellData.getValue().destination_tcpProperty());
        tcp_source.setCellValueFactory(cellData -> cellData.getValue().source_tcpProperty());
        tcp_seq.setCellValueFactory(cellData -> cellData.getValue().sequence_tcpProperty());
        tcp_ack.setCellValueFactory(cellData -> cellData.getValue().ack_tcpProperty());
        tcp_header.setCellValueFactory(cellData -> cellData.getValue().headerLength_tcpProperty());
        tcp_checksum.setCellValueFactory(cellData -> cellData.getValue().checksum_tcpProperty());
        tcp_checksum_c.setCellValueFactory(cellData -> cellData.getValue().checksum_tcp_cProperty());
        tcp_seglength.setCellValueFactory(cellData -> cellData.getValue().tcp_seglengthProperty());

        //UDP
        udp_destination.setCellValueFactory(cellData -> cellData.getValue().destination_udpProperty());
        udp_source.setCellValueFactory(cellData -> cellData.getValue().source_udpProperty());
        udp_header.setCellValueFactory(cellData -> cellData.getValue().headerLength_udpProperty());
        udp_checksum.setCellValueFactory(cellData -> cellData.getValue().checksum_udpProperty());
        udp_checksum_c.setCellValueFactory(cellData -> cellData.getValue().checksum_udp_cProperty());

        //IP4
        ip4_version.setCellValueFactory(cellData -> cellData.getValue().version_ip4Property());
        ip4_headerLength.setCellValueFactory(cellData -> cellData.getValue().headerLength_ip4Property());
        ip4_length.setCellValueFactory(cellData -> cellData.getValue().length_ip4Property());
        ip4_ttl.setCellValueFactory(cellData -> cellData.getValue().ttl_ip4Property());
        ip4_type.setCellValueFactory(cellData -> cellData.getValue().type_ip4Property());
        ip4_source.setCellValueFactory(cellData -> cellData.getValue().source_ip4Property());
        ip4_destination.setCellValueFactory(cellData -> cellData.getValue().destination_ip4Property());
        ip4_source_name.setCellValueFactory(cellData -> cellData.getValue().source_name_ip4Property());
        ip4_dest_name.setCellValueFactory(cellData -> cellData.getValue().dest_name_ip4Property());

        //IP6
        ip6_version.setCellValueFactory(cellData -> cellData.getValue().version_ip6Property());
        ip6_hdrlength.setCellValueFactory(cellData -> cellData.getValue().payload_lengthProperty());
        ip6_hoplimit.setCellValueFactory(cellData -> cellData.getValue().hop_limitProperty());
        ip6_source.setCellValueFactory(cellData -> cellData.getValue().source_ip6Property());
        ip6_dest.setCellValueFactory(cellData -> cellData.getValue().destination_ip6Property());
        ip6_nexthdr.setCellValueFactory(cellData -> cellData.getValue().next_headerProperty());
        ip6_length.setCellValueFactory(cellData -> cellData.getValue().length_ip6Property());

        //ARP
        arp_hardware.setCellValueFactory(cellData -> cellData.getValue().hardware_arpProperty());
        arp_protocol.setCellValueFactory(cellData -> cellData.getValue().protocol_arpProperty());
        arp_operation.setCellValueFactory(cellData -> cellData.getValue().operation_arpProperty());
        arp_headerLength.setCellValueFactory(cellData -> cellData.getValue().headerLength_arpProperty());

    }

    public void setTableData(){
        //Ethernet
        ArrayList<TableEntries> table_entries;
        table_entries = pcapReader.testingC.getEthernetTableEntries();
        ObservableList<TableEntries> ether_dests = FXCollections.observableArrayList();
        ether_dests.setAll(table_entries);
        ethernet_table.setItems(ether_dests);

        //TCP
        ArrayList<TableEntries> table_entries2;
        table_entries2 = pcapReader.testingC.getTCPTableEntries();
        ObservableList<TableEntries> tcp_columns = FXCollections.observableArrayList();
        tcp_columns.setAll(table_entries2);
        tcp_table.setItems(tcp_columns);

        //UDP
        ArrayList<TableEntries> table_entries3;
        table_entries3 = pcapReader.testingC.getUDPTableEntries();
        ObservableList<TableEntries> udp_columns = FXCollections.observableArrayList();
        udp_columns.setAll(table_entries3);
        udp_table.setItems(udp_columns);

        //IP4
        ArrayList<TableEntries> table_entries4;
        table_entries4 = pcapReader.testingC.getIP4TableEntries();
        ObservableList<TableEntries> ip4_columns = FXCollections.observableArrayList();
        ip4_columns.setAll(table_entries4);
        ip4_table.setItems(ip4_columns);

        //IP6
        ArrayList<TableEntries> table_entries5;
        table_entries5 = pcapReader.testingC.getIP6TableEntries();
        ObservableList<TableEntries> ip6_columns = FXCollections.observableArrayList();
        ip6_columns.setAll(table_entries5);
        ip6_table.setItems(ip6_columns);

        //ARP
        ArrayList<TableEntries> table_entries6;
        table_entries6 = pcapReader.testingC.getARPTableEntries();
        ObservableList<TableEntries> arp_columns = FXCollections.observableArrayList();
        arp_columns.setAll(table_entries6);
        arp_table.setItems(arp_columns);

    }

    public void setTopTenList() {
        ObservableList<String> ips = FXCollections
                .observableArrayList();

        ips.addAll(pcapReader.testingC.getTopTenList());
        topTenList.setItems(ips);
    }

    @FXML
    public void openFile() {
        file = new FileChooser();
        file.setTitle("Open Resource File");
        file.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PCAP files (*.pcap)", "*.pcap"));

        File theFile = file.showOpenDialog(new Stage());

        try {
            String fileN = theFile.getAbsolutePath();
            pcapReader.testingC.setFilename(fileN);

            pcapReader.testingC.runFile();

            //This is just for testing to make sure the file was read
            System.out.println("TCP count is: " + pcapReader.testingC.getCount_tcp());
            System.out.println("UDP count is: " + pcapReader.testingC.getCount_udp());

            System.out.println("IP4 count is: " + pcapReader.testingC.getCount_ip4());
            System.out.println("IP6 count is: " + pcapReader.testingC.getCount_ip6());
            System.out.println("ARP count is: " + pcapReader.testingC.getCount_arp());

            //Pie Chart1
            ObservableList<PieChart.Data> pieChartData =
                    FXCollections.observableArrayList(
                            new PieChart.Data("TCP Count: " + pcapReader.testingC.getCount_tcp(), pcapReader.testingC.getCount_tcp()),
                            new PieChart.Data("UDP Count: " + pcapReader.testingC.getCount_udp(), pcapReader.testingC.getCount_udp()));
            pieChart.setData(pieChartData);
            pieChart.setLabelLineLength(10);
            pieChart.setLegendSide(Side.BOTTOM);
            pieChart.setTitle("TCP/UDP");

            //Pie Chart2
            ObservableList<PieChart.Data> pieChartData2 =
                    FXCollections.observableArrayList(
                            new PieChart.Data("IP4 Count: " + pcapReader.testingC.getCount_ip4(), pcapReader.testingC.getCount_ip4()),
                            new PieChart.Data("IP6 Count: " + pcapReader.testingC.getCount_ip6(), pcapReader.testingC.getCount_ip6()),
                            new PieChart.Data("ARP Count: " + pcapReader.testingC.getCount_arp(), pcapReader.testingC.getCount_arp()));
            pieChart2.setData(pieChartData2);
            pieChart2.setLabelLineLength(10);
            pieChart2.setLegendSide(Side.BOTTOM);
            pieChart2.setTitle("IP4/IP6/ARP");

//            //PieChart3
//            ObservableList<PieChart.Data> pieChartData3 = FXCollections.observableArrayList(
//                    new PieChart.Data("name:", IPConverter.TopDest.)
//
//            );

            //Ethernet Table
            setTableData();

            //top ten list
            setTopTenList();

        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText("File reading error!");
            alert.setContentText("No file was selected.");

            alert.showAndWait();
        }
    }
}


