package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class GithubCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    @Test
    public void executeGithubCommand_noGithubUsernameAssociated_failure() {
        GithubCommand githubCommand = new GithubCommand(INDEX_FIRST_PERSON);
        String expectedMessage = GithubCommand.MESSAGE_NO_GITHUB_ERROR;
        assertCommandFailure(githubCommand, model, expectedMessage);
    }

}
