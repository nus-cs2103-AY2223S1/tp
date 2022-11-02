package longtimenosee.logic.commands;

import static longtimenosee.logic.commands.CommandTestUtil.assertCommandSuccess;
import static longtimenosee.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static longtimenosee.testutil.TypicalPolicies.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import longtimenosee.model.Model;
import longtimenosee.model.ModelManager;
import longtimenosee.model.UserPrefs;
import longtimenosee.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for PolicyListCommand.
 */
public class PolicyAssignedListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() throws Exception {
        Person person = expectedModel.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        CommandResult expectedResult = new PolicyAssignedListCommand(INDEX_FIRST_PERSON).execute(expectedModel);
        assertCommandSuccess(new PolicyAssignedListCommand(INDEX_FIRST_PERSON),
                model, expectedResult, expectedModel);
    }
}
