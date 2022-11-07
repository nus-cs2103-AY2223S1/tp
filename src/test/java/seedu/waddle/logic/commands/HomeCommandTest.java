package seedu.waddle.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.waddle.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.waddle.logic.StageManager;
import seedu.waddle.model.Model;
import seedu.waddle.model.ModelManager;
import seedu.waddle.model.itinerary.Itinerary;
import seedu.waddle.testutil.ItineraryBuilder;

public class HomeCommandTest {

    private final Model model = new ModelManager();
    private final Model expectedModel = new ModelManager();

    @Test
    public void execute_atHomeStage_success() {
        HomeCommand homeCommand = new HomeCommand();

        String expectedMessage = HomeCommand.MESSAGE_ALREADY_HOME_SUCCESS;

        assertCommandSuccess(homeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_notAtHomeStage_success() {
        HomeCommand homeCommand = new HomeCommand();

        String expectedMessage = HomeCommand.MESSAGE_HOME_SUCCESS;

        Itinerary itinerary = new ItineraryBuilder().build();
        StageManager.getInstance().setWishStage(itinerary);

        assertCommandSuccess(homeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        HomeCommand homeCommand = new HomeCommand();
        HomeCommand homeCommandOther = new HomeCommand();

        // same object -> returns true
        assertTrue(homeCommand.equals(homeCommand));

        // another home command -> returns true
        assertTrue(homeCommand.equals(homeCommandOther));

        // different types -> returns false
        assertFalse(homeCommand.equals(1));

        // null -> returns false
        assertFalse(homeCommand.equals(null));

    }
}
