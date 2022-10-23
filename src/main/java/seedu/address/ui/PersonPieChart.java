package seedu.address.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.sun.javafx.beans.event.AbstractNotifyListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import seedu.address.model.person.Person;

import javax.print.attribute.IntegerSyntax;


/**
 * A Ui component responsible for displaying the piechart used to visualise the spread of the different contacts in the
 * list.
 */
public class PersonPieChart extends UiPart<Region> {

    private static final String FXML = "PieChart.fxml";
    @FXML
    private PieChart pieChart;
    private ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
            new PieChart.Data("Student", 0),
            new PieChart.Data("Professor", 0),
            new PieChart.Data("TA", 0)
    );

    /**
     * Creates a pie chart with the given {@code ObservableList}.
     */
    public PersonPieChart(ObservableList<Person> personList) {
        super(FXML);
        pieChart.setData(pieChartData);
        updatePieChart(personList);
        pieChart.setLegendVisible(false);
        pieChart.setTitle("Your Contacts");
    }

    /**
     * Updates the pie chart with the given {@code ObservableList}.
     */
    public void updatePieChart(ObservableList<Person> personList) {

        List<Integer> studentProfTaCount = personListToCount(personList);
        updatePieChartData(studentProfTaCount);
        setLabelNames(studentProfTaCount);
        addCountToLabels();

        if (isCountZero(studentProfTaCount)) {
            pieChart.setVisible(false);
        } else {
            pieChart.setVisible(true);
        }
    }

    /**
     * Updates pieChartData of the pieChart with the given count list of student, professor and TA.
     */
    public void updatePieChartData(List<Integer> studentProfTaCount) {
        for (int i = 0; i < 3; i++) {
            pieChartData.get(i).setPieValue(studentProfTaCount.get(i));
        }
    }

    /**
     * Counts and returns a list of the number of Students, Professors and TAs in the given {@code ObservableList}.
     */
    private List<Integer> personListToCount(ObservableList<Person> personList) {
        int studentCount = 0;
        int profCount = 0;
        int taCount = 0;
        List<Integer> result = new ArrayList<>();

        for (Person person: personList) {
            String personType = person.getFullTypeString();
            if (personType.equals("Student")) {
                studentCount++;
            } else if (personType.equals("Professor")) {
                profCount++;
            } else {
                taCount++;
            }
        }

        result.add(studentCount);
        result.add(profCount);
        result.add(taCount);

        return result;
    }

    /**
     * Sets the names of the labels based on the count of student, professor and TA.
     */
    public void setLabelNames(List<Integer> studentProfTaCount) {

        StringBuilder others = new StringBuilder();

        PieChart.Data studentData = pieChartData.get(0);
        PieChart.Data profData = pieChartData.get(1);
        PieChart.Data taData = pieChartData.get(2);

        studentData.setName(studentProfTaCount.get(0) != 0 ? "Student" : "Others");
        profData.setName(studentProfTaCount.get(1) != 0 ? "Professor" : "Others");
        taData.setName(studentProfTaCount.get(2) != 0 ? "TA" : "Others");

    }

    /**
     * Returns true if student, professor and TA count are all 0.
     */
    public boolean isCountZero(List<Integer> studentProfTaCount) {
        boolean isCountZero = true;
        for (Integer count : studentProfTaCount) {
            if (count != 0) {
                isCountZero = false;
            }
        }
        return isCountZero;
    }

    /**
     * Adds the count of each contact type to corresponding labels in the pie chart.
     */
    public void addCountToLabels() {
        //@@author normkoh-reused
        //Reused from https://stackoverflow.com/questions/35479375
        // with minor modifications
        pieChart.getData().forEach(d -> {
            Optional<Node> opTextNode = pieChart.lookupAll(".chart-pie-label")
                    .stream()
                    .filter(n -> n instanceof Text && ((Text) n).getText().contains(d.getName()))
                    .findAny();
            if (opTextNode.isPresent()) {
                Text text = (Text) opTextNode.get();
                text.setText(d.getName() + " (" + (int) d.getPieValue() + ")");
            }
        });
    }


}
