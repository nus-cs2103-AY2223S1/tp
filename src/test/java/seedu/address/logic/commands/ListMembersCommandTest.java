package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.TeamPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListMembersCommand.
 */
public class ListMembersCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listMembers_success() {
        expectedModel.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW,
            model.getFilteredMemberList().size());
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);

        assertCommandSuccess(new ListMembersCommand(), model, expectedCommandResult, expectedModel);
    }
}
