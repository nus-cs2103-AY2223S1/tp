package seedu.address.ui;


import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.portfolio.Portfolio;


/**
 * An UI component that displays information of a {@code Person}.
 */
public class PortfolioWindow extends UiPart<Region> {

    private static final String FXML = "Portfolio.fxml";
    private static final String RISK_LEVEL = "Risk Level: ";
    private static final String NO_NOTE = "no notes yet";
    private static final String NO_PLAN = "no plans yet";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    @FXML
    private Label risk;
    @FXML
    private VBox plans;
    @FXML
    private VBox notes;
    @FXML
    private VBox portfolioVBoxEmpty;
    @FXML
    private ScrollPane scroller;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PortfolioWindow(Portfolio portfolio) {
        super(FXML);
        //portfolioVBoxEmpty.setVisible(true);
        scroller.setFitToWidth(true);
        portfolioVBoxEmpty.setVisible(false);

        if (portfolio != null) {
            risk.setText(RISK_LEVEL + portfolio.getRisk().get());

            if (portfolio.getPlans().isEmpty()) {
                plans.getChildren().add(new Label(NO_PLAN));
            } else {
                portfolio.getPlans().stream()
                    .sorted(Comparator.comparing(plan -> plan.value))
                    .forEach(plan -> plans.getChildren().add(new Label(plan.value)));
            }

            if (portfolio.getNotes().isEmpty()) {
                notes.getChildren().add(new Label(NO_NOTE));
            } else {
                portfolio.getNotes().stream()
                    .sorted(Comparator.comparing(note -> note.value))
                    .forEach(note -> notes.getChildren().add(new Label(note.value)));
            }
        } else {
            //show empty portfolio
            scroller.setVisible(false);
            portfolioVBoxEmpty.setVisible(true);
        }

    }

}
