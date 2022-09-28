package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalProfiles.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.profile.Profile;
import seedu.address.testutil.ProfileBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

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

        assertCommandSuccess(new AddCommand(validProfile), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validProfile), expectedModel);
    }

    @Test
    public void execute_duplicateProfile_throwsCommandException() {
        Profile profileInList = model.getAddressBook().getProfileList().get(0);
        assertCommandFailure(new AddCommand(profileInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
