package seedu.address.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Region;

/**
 * The UI component that is responsible for display grade's distribution.
 */
public class GradeChart extends UiPart<Region> {
    private static final String FXML = "GradeChart.fxml";

    @FXML
    private PieChart pieChart;

    /**
     * Creates a {@code PieChart}.
     */
//    public GradeChart(ObservableList<PieChart.Data> pieChartData) {
     public GradeChart() {
        super(FXML);
        ObservableList<PieChart.Data> chartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Grapefruit", 12),
                        new PieChart.Data("Oranges", 24),
                        new PieChart.Data("Plums", 9),
                        new PieChart.Data("Pears", 21),
                        new PieChart.Data("Apples", 29));
        pieChart.setData(chartData);
        pieChart.setTitle("Grade chart");
    }
}
