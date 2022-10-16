package seedu.address.logic.commands.profile;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalProfiles.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.profile.AddProfileCommand;
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
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newProfile_success() {
        Profile validProfile = new ProfileBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addProfile(validProfile);

        assertCommandSuccess(new AddProfileCommand(validProfile), model,
                String.format(AddProfileCommand.MESSAGE_SUCCESS, validProfile), expectedModel);
    }

    @Test
    public void execute_duplicateProfile_throwsCommandException() {
        Profile profileInList = model.getAddressBook().getProfileList().get(0);
        assertCommandFailure(
                new AddProfileCommand(profileInList),
                model,
                AddProfileCommand.MESSAGE_DUPLICATE_PROFILE
        );
    }

}
