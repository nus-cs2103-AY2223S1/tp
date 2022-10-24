package seedu.foodrem.logic.commands.generalcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.model.Model;
import seedu.foodrem.model.ModelManager;
import seedu.foodrem.model.UserPrefs;
import seedu.foodrem.testutil.TypicalFoodRem;

class ExitCommandTest {

    private final Model model = new ModelManager(TypicalFoodRem.getTypicalFoodRem(), new UserPrefs());

    @Test
    void execute() {
        CommandResult commandResult = new ExitCommand().execute(model);

        assertFalse(commandResult.isShowHelp());
        assertTrue(commandResult.isExit());
        assertEquals(commandResult.getFeedbackToUser(), "Exiting FoodRem as requested ...");
    }
}
