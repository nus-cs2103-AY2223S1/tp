package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.record.RecordContainsKeywordsPredicate;
import seedu.address.testutil.TestUtil;

/**
 * Contains integration tests (interaction with the Model) for {@code BackCommand}.
 */
public class BackCommandTest {
    private final String testString = "benson covid-19";
    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new BackCommand(), model, BackCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyFilteredPersonList_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.updateFilteredPersonList(prepareNamePredicate());

        assertCommandSuccess(new BackCommand(), model, BackCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyFilteredRecordList_success() {
        Model model = TestUtil.prepareModel();
        Model expectedModel = TestUtil.prepareModel();
        model.updateFilteredRecordList(prepareRecordPredicate());

        assertCommandSuccess(new BackCommand(), model, BackCommand.MESSAGE_SUCCESS, expectedModel);
    }

    /**
     * Returns a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate prepareNamePredicate() {
        return new NameContainsKeywordsPredicate(Arrays.asList(testString.split("\\s+")));
    }

    /**
     * Returns a {@code RecordContainsKeywordsPredicate}.
     */
    private RecordContainsKeywordsPredicate prepareRecordPredicate() {
        return new RecordContainsKeywordsPredicate(Arrays.asList(testString.split("\\s+")));
    }
}
