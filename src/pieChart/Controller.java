package pieChart;

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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

    //Ethernet Table
    @FXML
    public TableView ethernet_table = new TableView();
    @FXML
    public TableColumn<TableEntries, String> ethernet_destination;
    @FXML
    public TableColumn<TableEntries, String> ethernet_source;
    @FXML
    public TableColumn<TableEntries, String> ethernet_type;

    //TCP Table
    @FXML
    public TableView tcp_table = new TableView();
    @FXML
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Ethernet
        ethernet_destination.setCellValueFactory(cellData -> cellData.getValue().destination_ethernetProperty());
        ethernet_source.setCellValueFactory(cellData -> cellData.getValue().source_ethernetProperty());
        ethernet_type.setCellValueFactory(cellData -> cellData.getValue().type_ethernetProperty());

        //TCP
        tcp_destination.setCellValueFactory(cellData -> cellData.getValue().destination_tcpProperty());
        tcp_source.setCellValueFactory(cellData -> cellData.getValue().source_tcpProperty());
        tcp_seq.setCellValueFactory(cellData -> cellData.getValue().sequence_tcpProperty());
        tcp_ack.setCellValueFactory(cellData -> cellData.getValue().ack_tcpProperty());
        tcp_header.setCellValueFactory(cellData -> cellData.getValue().headerLength_tcpProperty());
        tcp_checksum.setCellValueFactory(cellData -> cellData.getValue().checksum_tcpProperty());

        //UDP
        udp_destination.setCellValueFactory(cellData -> cellData.getValue().destination_udpProperty());
        udp_source.setCellValueFactory(cellData -> cellData.getValue().source_udpProperty());
        udp_header.setCellValueFactory(cellData -> cellData.getValue().headerLength_udpProperty());
        udp_checksum.setCellValueFactory(cellData -> cellData.getValue().checksum_udpProperty());

        //IP4
        ip4_version.setCellValueFactory(cellData -> cellData.getValue().version_ip4Property());
        ip4_headerLength.setCellValueFactory(cellData -> cellData.getValue().headerLength_ip4Property());
        ip4_length.setCellValueFactory(cellData -> cellData.getValue().length_ip4Property());
        ip4_ttl.setCellValueFactory(cellData -> cellData.getValue().ttl_ip4Property());
        ip4_type.setCellValueFactory(cellData -> cellData.getValue().type_ip4Property());
        ip4_source.setCellValueFactory(cellData -> cellData.getValue().source_ip4Property());
        ip4_destination.setCellValueFactory(cellData -> cellData.getValue().destination_ip4Property());
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

        //
        ArrayList<TableEntries> table_entries4;
        table_entries4 = pcapReader.testingC.getIP4TableEntries();
        ObservableList<TableEntries> ip4_columns = FXCollections.observableArrayList();
        ip4_columns.setAll(table_entries4);
        ip4_table.setItems(ip4_columns);

    }

    @FXML
    public void openFile() {
        file = new FileChooser();
        file.setTitle("Open Resource File");
        file.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PCAP files (*.pcap)", "*.pcap"));

        File theFile = file.showOpenDialog(new Stage());

        String fileN = theFile.getAbsolutePath();
        pcapReader.testingC.setFilename(fileN);

        pcapReader.testingC.runFile();

        //This is just for testing to make sure the file was read
        System.out.println("TCP count is: " + pcapReader.testingC.getCount_tcp());
        System.out.println("UDP count is: " + pcapReader.testingC.getCount_udp());
        System.out.println("ICMP count is: " + pcapReader.testingC.getCount_icmp());

        System.out.println("IP4 count is: " + pcapReader.testingC.getCount_ip4());
        System.out.println("IP6 count is: " + pcapReader.testingC.getCount_ip6());
        System.out.println("ARP count is: " + pcapReader.testingC.getCount_arp());

        //Pie Chart1
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("TCP Count: " + pcapReader.testingC.getCount_tcp(), pcapReader.testingC.getCount_tcp()),
                        new PieChart.Data("UDP Count: " + pcapReader.testingC.getCount_udp(), pcapReader.testingC.getCount_udp()),
                        new PieChart.Data("ICMP Count: " + pcapReader.testingC.getCount_icmp(), pcapReader.testingC.getCount_icmp()));
        pieChart.setData(pieChartData);
        pieChart.setLabelLineLength(10);
        pieChart.setLegendSide(Side.BOTTOM);
        pieChart.setTitle("TCP/UDP/ICMP");

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

        //Ethernet Table
        setTableData();


        }


}


