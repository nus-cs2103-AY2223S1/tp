package seedu.waddle.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.waddle.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.waddle.testutil.TypicalItineraries.getTypicalWaddle;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.apache.commons.lang3.SystemUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.waddle.logic.StageManager;
import seedu.waddle.model.Model;
import seedu.waddle.model.ModelManager;
import seedu.waddle.model.UserPrefs;
import seedu.waddle.model.itinerary.Itinerary;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class FreeCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalWaddle(), new UserPrefs());
        expectedModel = new ModelManager(model.getWaddle(), new UserPrefs());
    }

    @Test
    public void execute_correctStage_correctOutput() {
        // select third itinerary
        Itinerary selectedItinerary = model.getFilteredItineraryList().get(2);
        StageManager.getInstance().setWishStage(selectedItinerary);
        String expectedCommandResult = selectedItinerary.getVacantSlots();

        assertCommandSuccess(new FreeCommand(), model, expectedCommandResult, expectedModel);
    }
}
