package tuthub.ui;

import java.util.Comparator;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import tuthub.commons.core.LogsCenter;
import tuthub.logic.commands.exceptions.CommandException;
import tuthub.logic.parser.exceptions.ParseException;
import tuthub.model.tutor.Tutor;

/**
 * An UI component that displays information of a {@code Tutor}.
 */
public class TutorListCard extends UiPart<Region> {

    private static final String FXML = "TutorListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/tuthub-level4/issues/336">The issue on Tuthub level 4</a>
     */

    public final Tutor tutor;
    private final String studentId;
    private final int displayedIndex;
    private final CommandExecutor commandExecutor;
    private final Logger logger = LogsCenter.getLogger(TutorListCard.class);

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private FlowPane modules;
    @FXML
    private FlowPane tags;


    /**
     * Creates a {@code TutorCode} with the given {@code Tutor} and index to display.
     */
    public TutorListCard(CommandExecutor commandExecutor, Tutor tutor, int displayedIndex) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        this.tutor = tutor;
        this.displayedIndex = displayedIndex - 1;
        this.studentId = tutor.getStudentId().value;

        id.setText(displayedIndex + ". ");
        name.setText(tutor.getName().fullName);
        tutor.getModules().stream()
            .sorted(Comparator.comparing(module -> module.value))
            .forEach(module -> modules.getChildren().add(new Label(module.value)));
        tutor.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    /**
     * Handles the tutor list card pressed event.
     */
    @FXML
    private void handleViewDetails() {
        int indexToView = displayedIndex + 1;
        String commandText = "view " + indexToView;
        try {
            commandExecutor.execute(commandText);
        } catch (ParseException | CommandException e) {
            logger.info("This block should not be executed");
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TutorListCard)) {
            return false;
        }

        // state check
        TutorListCard card = (TutorListCard) other;
        return studentId.equals(card.studentId)
                && tutor.equals(card.tutor);
    }
}
