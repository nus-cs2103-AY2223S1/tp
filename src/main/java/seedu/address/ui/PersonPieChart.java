package seedu.address.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import seedu.address.model.person.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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
    }

    /**
     * Updates the pie chart with the given {@code ObservableList}.
     */
    public void updatePieChart(ObservableList<Person> personList) {

        List<Integer> studentProfTaCount = personListToCount(personList);

        ObservableList<PieChart.Data> updatedPieChartData = FXCollections.observableArrayList();
        int i = 0;
        for(Integer count : studentProfTaCount) {
            pieChartData.get(i).setPieValue(count);
            i++;
        }
        addCountToLabels();
    }

    /**
     * Counts and returns a list of the number of Students, Professors and TAs in the given {@code ObservableList}.
     */
    private List<Integer> personListToCount(ObservableList<Person> personList) {
        int studentCount = 0;
        int profCount = 0;
        int TaCount = 0;
        List<Integer> result = new ArrayList<>();

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

        result.add(studentCount);
        result.add(profCount);
        result.add(TaCount);

        return result;
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
                ((Text) opTextNode.get()).setText(d.getName() + " (" + (int) d.getPieValue() + ")");
            }
        });
    }


}
