package pieChart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Controller {
    @FXML
    public FileChooser file;

    @FXML
    public PieChart pieChart = new PieChart();

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
        System.out.println("TCP count is: " + pcapReader.testingC.getCount_tcp());
        System.out.println("UDP count is: " + pcapReader.testingC.getCount_udp());

        //Pie Chart
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("TCP", pcapReader.testingC.getCount_tcp()),
                        new PieChart.Data("UDP", pcapReader.testingC.getCount_udp()));
        pieChart.setData(pieChartData);
        pieChart.setLabelLineLength(10);
        pieChart.setLegendSide(Side.LEFT);
        pieChart.setTitle("TCP vs UDP");
    }
}

