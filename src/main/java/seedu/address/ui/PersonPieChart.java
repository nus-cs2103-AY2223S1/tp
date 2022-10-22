package seedu.address.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;


/**
 * A Ui component responsible for displaying the piechart used to visualise the spread of the different contacts in the
 * list.
 */
public class PersonPieChart extends UiPart<Region> {

    private static final String FXML = "PieChart.fxml";

    @FXML
    private PieChart pieChart;

    /**
     * Creates a pie chart with the given {@code ObservableList}.
     */
    public PersonPieChart(ObservableList<Person> personList) {
        super(FXML);
        updatePieChart(personList);
    }

    /**
     * Updates the pie chart with the given {@code ObservableList}.
     */
    public void updatePieChart(ObservableList<Person> personList) {
        int studentCount = 0;
        int profCount = 0;
        int TaCount = 0;

        for (Person person: personList) {
            String personType = person.getFullTypeString();
            if (personType.equals("Student")) {
                studentCount++;
            } else if (personType.equals("Professor")) {
                profCount++;
            } else {
                TaCount++;
            }
        }

        ObservableList<PieChart.Data> result = FXCollections.observableArrayList();
        if (studentCount != 0) {
            result.add(new PieChart.Data("Student", studentCount));
        }
        if (profCount != 0) {
            result.add(new PieChart.Data("Professor", profCount));
        }
        if (TaCount != 0) {
            result.add(new PieChart.Data("TA", TaCount));
        }

        pieChart.setData(result);
    }


}
