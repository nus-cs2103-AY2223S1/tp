package foodwhere.logic.commands;

import static foodwhere.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import foodwhere.model.Model;
import foodwhere.model.ModelManager;
import foodwhere.model.UserPrefs;
import foodwhere.model.stall.comparator.StallsComparatorList;
import foodwhere.testutil.TypicalStalls;

/**
 * Contains integration tests (interaction with the Model) and unit tests for SSortCommand.
 */
public class SSortCommandTest {

    private Model model = new ModelManager(TypicalStalls.getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

    @Test
    public void execute_nameCriterion_showsSortedListByName() {
        StallsComparatorList comparator = prepareComparator("NAME");
        expectedModel.sortStalls(comparator.getComparator());
        String expectedMessage = String.format(SSortCommand.MESSAGE_SUCCESS, comparator.getCriteria());
        assertCommandSuccess(new SSortCommand(comparator), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_reversedNameCriterion_showsSortedListByReversedName() {
        StallsComparatorList comparator = prepareComparator("REVERSEDNAME");
        expectedModel.sortStalls(comparator.getComparator());
        String expectedMessage = String.format(SSortCommand.MESSAGE_SUCCESS, comparator.getCriteria());
        assertCommandSuccess(new SSortCommand(comparator), model, expectedMessage, expectedModel);
    }

    /**
     * Parses {@code userInput} into a {@code StallsComparatorList}.
     */
    private StallsComparatorList prepareComparator(String userInput) {
        return StallsComparatorList.valueOf(userInput);
    }
}
