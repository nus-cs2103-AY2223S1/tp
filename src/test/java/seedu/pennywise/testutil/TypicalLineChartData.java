package seedu.pennywise.testutil;

import static seedu.pennywise.logic.commands.CommandTestUtil.VALID_AMT_DINNER;
import static seedu.pennywise.logic.commands.CommandTestUtil.VALID_AMT_LUNCH;
import static seedu.pennywise.logic.commands.CommandTestUtil.VALID_AMT_MOVIE;
import static seedu.pennywise.logic.commands.CommandTestUtil.VALID_ISO_DATE_DINNER;
import static seedu.pennywise.logic.commands.CommandTestUtil.VALID_ISO_DATE_LUNCH;
import static seedu.pennywise.logic.commands.CommandTestUtil.VALID_ISO_DATE_MOVIE;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

/**
 * A utility class containing a sample line graph data to be used in tests.
 */
public class TypicalLineChartData {
    private ObservableList<XYChart.Data<String, Number>> expenditureLineChartData =
            FXCollections.observableArrayList();

    /**
     * Constructs a TypicalLineChartData object.
     */
    public TypicalLineChartData() {
        LocalDate date = LocalDate.of(2022, 8, 1);
        int noOfDays = date.lengthOfMonth();
        for (int i = 0; i < noOfDays; i++) {
            String xValue = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
            Number yValue = xValue.equals(VALID_ISO_DATE_LUNCH)
                    ? Double.parseDouble(VALID_AMT_LUNCH)
                    : xValue.equals(VALID_ISO_DATE_DINNER)
                    ? Double.parseDouble(VALID_AMT_DINNER)
                    : xValue.equals(VALID_ISO_DATE_MOVIE)
                    ? Double.parseDouble(VALID_AMT_MOVIE)
                    : 0.00;
            expenditureLineChartData.add(new XYChart.Data<>(xValue, yValue));
            date = date.plusDays(1);
        }
    }

    public ObservableList<XYChart.Data<String, Number>> getExpenditureLineChartData() {
        return this.expenditureLineChartData;
    }
}
