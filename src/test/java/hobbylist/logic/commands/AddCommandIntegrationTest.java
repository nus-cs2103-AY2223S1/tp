package hobbylist.logic.commands;

import static hobbylist.logic.commands.CommandTestUtil.assertCommandFailure;
import static hobbylist.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hobbylist.model.Model;
import hobbylist.model.ModelManager;
import hobbylist.model.UserPrefs;
import hobbylist.model.activity.Activity;
import hobbylist.testutil.ActivityBuilder;
import hobbylist.testutil.TypicalActivities;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalActivities.getTypicalHobbyList(), new UserPrefs());
    }

    @Test
    public void execute_newActivity_success() {
        Activity validActivity = new ActivityBuilder().build();

        Model expectedModel = new ModelManager(model.getHobbyList(), new UserPrefs());
        expectedModel.addActivity(validActivity);

        assertCommandSuccess(new AddCommand(validActivity), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validActivity), expectedModel);
    }

    @Test
    public void execute_duplicateActivity_throwsCommandException() {
        Activity activityInList = model.getHobbyList().getActivityList().get(0);
        assertCommandFailure(new AddCommand(activityInList), model, AddCommand.MESSAGE_DUPLICATE_ACTIVITY);
    }

}
