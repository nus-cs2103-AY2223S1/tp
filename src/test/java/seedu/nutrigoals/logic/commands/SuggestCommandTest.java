package seedu.nutrigoals.logic.commands;

import static seedu.nutrigoals.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.nutrigoals.testutil.TypicalFoods.getTypicalNutriGoals;

import org.junit.jupiter.api.Test;

import seedu.nutrigoals.model.Model;
import seedu.nutrigoals.model.ModelManager;
import seedu.nutrigoals.model.UserPrefs;
import seedu.nutrigoals.model.user.User;
import seedu.nutrigoals.testutil.UserBuilder;


public class SuggestCommandTest {

    private static final User DEFAULT_USER = new UserBuilder().build();
    private final Model model = new ModelManager(getTypicalNutriGoals(), new UserPrefs());

    @Test
    public void execute_suggestCommand_success() {
        SuggestCommand suggestCommand = new SuggestCommand();
        String suggestedCalorie = "1928 calories";
        model.setUserDetails(DEFAULT_USER);
        String expectedMessage = String.format(SuggestCommand.MESSAGE_SUGGEST_DETAILS, suggestedCalorie);
        assertCommandSuccess(suggestCommand, model, expectedMessage, model);
    }

    @Test
    public void execute_suggestCommandF_failure() {
        SuggestCommand suggestCommand = new SuggestCommand();
        String expectedMessage = SuggestCommand.MESSAGE_NO_PROFILE_CREATED;
        assertCommandFailure(suggestCommand, model, expectedMessage);
    }
}
