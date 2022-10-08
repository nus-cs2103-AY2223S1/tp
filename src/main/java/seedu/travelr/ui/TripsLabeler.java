package seedu.travelr.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import seedu.travelr.logic.Logic;
import seedu.travelr.logic.commands.CommandResult;
import seedu.travelr.logic.commands.exceptions.CommandException;
import seedu.travelr.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class TripsLabeler extends UiPart<Region> {

    private static final String FXML = "TripsLabeler.fxml";


    public TripsLabeler() {
        super(FXML);
    }



}
