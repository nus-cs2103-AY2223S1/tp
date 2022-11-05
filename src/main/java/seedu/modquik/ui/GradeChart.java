package seedu.modquik.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Side;
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
    public GradeChart(ObservableList<PieChart.Data> pieChartData) {
        super(FXML);
        pieChart.setData(pieChartData);
        pieChart.setLegendSide(Side.LEFT);
        pieChart.setLabelsVisible(false);
    }
}
