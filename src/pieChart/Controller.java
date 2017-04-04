package pieChart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.jnetpcap.protocol.lan.Ethernet;

import java.awt.event.MouseEvent;
import java.io.File;

public class Controller {
    @FXML
    public FileChooser file;

    @FXML
    public PieChart pieChart = new PieChart();

    @FXML
    public PieChart pieChart2 = new PieChart();

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
        pieChart.setTitle("TCP vs UDP");

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

        /**
        //show percentage when mouse over pie chart
        Label pieLabel = new Label("");
        pieLabel.setTextFill(Color.DEEPPINK);
        pieLabel.setStyle("fx-font: 24 arial;");
         **/


        }
    }


