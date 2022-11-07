package seedu.address.logic.commands.profile;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalNuScheduler.getTypicalNuScheduler;
import static seedu.address.testutil.TypicalProfiles.AMY;
import static seedu.address.testutil.TypicalProfiles.FIRST_INDEX_TELEGRAM;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.profile.Profile;
import seedu.address.testutil.ProfileBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddProfileCommand}.
 */
public class AddProfileCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalNuScheduler(), new UserPrefs());
    }

    @Test
    public void execute_newProfile_success() {
        Profile validProfile = new ProfileBuilder().build();

        Model expectedModel = new ModelManager(model.getNuScheduler(), new UserPrefs());
        expectedModel.addProfile(validProfile);

        assertCommandSuccess(new AddProfileCommand(validProfile), model,
                String.format(AddProfileCommand.MESSAGE_SUCCESS, validProfile), expectedModel);
    }

    @Test
    public void execute_similarEmail_throwsCommandException() {
        Profile profileInList = model.getNuScheduler().getProfileList().get(0);
        Profile newProfile = new ProfileBuilder(AMY).withEmail(profileInList.getEmail().toString())
                        .build();
        assertCommandFailure(
                new AddProfileCommand(newProfile),
                model,
                AddProfileCommand.MESSAGE_SIMILAR_EMAIL
        );
    }

    @Test
    public void execute_similarPhone_throwsCommandException() {
        Profile profileInList = model.getNuScheduler().getProfileList().get(0);
        Profile newProfile = new ProfileBuilder(AMY).withPhone(profileInList.getPhone().toString())
                .build();
        assertCommandFailure(
                new AddProfileCommand(newProfile),
                model,
                AddProfileCommand.MESSAGE_SIMILAR_PHONE
        );
    }

    @Test
    public void execute_similarTelegram_throwsCommandException() {
        Profile newProfile = new ProfileBuilder(AMY).withTelegram(FIRST_INDEX_TELEGRAM)
                .build();
        assertCommandFailure(
                new AddProfileCommand(newProfile),
                model,
                AddProfileCommand.MESSAGE_SIMILAR_TELEGRAM
        );
    }

}
