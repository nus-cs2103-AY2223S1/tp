package longtimenosee.ui;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import longtimenosee.model.policy.FinancialAdvisorIncome;


/**
 * Controller for income chart
 */
public class IncomeChart extends UiPart<Region> {
    private static final String FXML = "LineGraph.fxml";

    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    @FXML
    final LineChart<?, ?> lineChart =
            new LineChart<>(xAxis, yAxis);

    /**
     * Creates an income chart
     */
    @SuppressWarnings("unchecked")
    public IncomeChart(FinancialAdvisorIncome income) {
        super(FXML);
        XYChart.Series series = new XYChart.Series();
        series.setName("3 Year Income");
        xAxis.setLabel("Year");
        yAxis.setLabel("Income");

        if (income == null || income.getYear() == null) {
            series.getData().add(new XYChart.Data<>("2000", 10000));
            series.getData().add(new XYChart.Data<>("2001", 20000));
            series.getData().add(new XYChart.Data<>("2002", 9000));
        } else {
            LocalDate targetYear = income.getYear();
            series.getData().add(new XYChart.Data<>(String.valueOf(targetYear.getYear()), income.getFirstYearIncome()));
            series.getData().add(new XYChart.Data<>(String.valueOf(targetYear.plusYears(1).getYear()),
                    income.getSecondYearIncome()));
            series.getData().add(new XYChart.Data<>(String.valueOf(targetYear.plusYears(2).getYear()),
                    income.getThirdYearIncome()));
        }
        lineChart.getData().add(series);
        lineChart.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
    }

}
