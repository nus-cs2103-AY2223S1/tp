package seedu.foodrem.logic.commands.generalcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(new ExitCommand().execute(model),
                     new CommandResult("Exiting Address Book as requested ...", false, true));
    }
}
