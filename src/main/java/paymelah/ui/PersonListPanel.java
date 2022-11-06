package paymelah.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import paymelah.commons.core.LogsCenter;
import paymelah.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private Accordion personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Person> personList) {
        super(FXML);
        List<TitledPane> titledPanes = new ArrayList<TitledPane>();
        personList.addListener((ListChangeListener<Person>) c -> {
            TitledPane expandedPane = personListView.getExpandedPane();
            String name = getNameFromExpandedPane(expandedPane);
            personListView.getPanes().clear();
            titledPanes.clear();
            TitledPane newExpandedPane = null;
            for (Person person : c.getList()) {
                TitledPane titledPane = createTitledPane(person, c.getList());
                if (expandedPane != null && person.getName().fullName.equals(name)) {
                    newExpandedPane = titledPane;
                }
                titledPanes.add(titledPane);
            }
            personListView.getPanes().addAll(titledPanes);
            personListView.setExpandedPane(newExpandedPane);
        });
        for (Person person : personList) {
            titledPanes.add(createTitledPane(person, personList));
        }
        personListView.getPanes().addAll(titledPanes);
    }

    private String getNameFromExpandedPane(TitledPane expandedPane) {
        if (expandedPane == null) {
            return null;
        }

        HBox hBox = (HBox) expandedPane.getGraphic();
        Label label = (Label) hBox.getChildren().get(0);
        String text = label.getText();
        String name = text.substring(text.indexOf('.') + 2);
        return name;
    }

    private TitledPane createTitledPane(Person person, ObservableList<? extends Person> observableList) {
        int oneBasedIndex = observableList.indexOf(person) + 1;
        if (oneBasedIndex == 0) {
            oneBasedIndex = observableList.size();
        }
        TitledPane titledPane = new TitledPane();
        titledPane.setAlignment(Pos.CENTER);
        titledPane.setMinHeight(200);

        // @@author TheSoggy-reused
        // Adapted from https://stackoverflow.com/a/52458162

        // Create HBox to hold our 2 labels
        HBox contentPane = new HBox();
        contentPane.setAlignment(Pos.CENTER);

        // Set padding on the left to avoid overlapping TitledPane's expand arrow
        contentPane.setPadding(new Insets(0, 10, 0, 25));

        // Now, since the TitledPane's graphic node generally has a fixed size, we need to bind our
        // contentPane's width to match the width of the TitledPane. This will account for resizing as well
        contentPane.minWidthProperty().bind(titledPane.widthProperty());

        // Create a Region to act as a separator for the 2 labels
        HBox region = new HBox();
        region.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(region, Priority.ALWAYS);

        Label name = new Label(oneBasedIndex + ". " + person.getName().fullName);
        name.setMaxWidth(250);

        Label totalAmount = new Label("Total: $" + person.getDebtsAmountAsMoney().toString());
        totalAmount.setMaxWidth(300);

        // Add our nodes to the contentPane
        contentPane.getChildren().addAll(
            name,
            region,
            totalAmount
        );

        // Add the contentPane as the graphic for the TitledPane
        titledPane.setGraphic(contentPane);
        titledPane.setContent(new PersonCard(person).personCardPane);
        // @@author

        return titledPane;
    }
}
