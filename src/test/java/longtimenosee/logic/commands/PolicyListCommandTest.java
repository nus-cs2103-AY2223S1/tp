package longtimenosee.logic.commands;

import static longtimenosee.logic.commands.CommandTestUtil.assertCommandSuccess;
import static longtimenosee.logic.commands.CommandTestUtil.showPolicyAtIndex;
import static longtimenosee.testutil.TypicalIndexes.INDEX_FIRST_POLICY;
import static longtimenosee.testutil.TypicalPolicies.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import longtimenosee.model.Model;
import longtimenosee.model.ModelManager;
import longtimenosee.model.UserPrefs;


/**
 * Contains integration tests (interaction with the Model) and unit tests for PolicyListCommand.
 */
public class PolicyListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new PolicyListCommand(), model, PolicyListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPolicyAtIndex(model, INDEX_FIRST_POLICY);
        assertCommandSuccess(new PolicyListCommand(), model, PolicyListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
