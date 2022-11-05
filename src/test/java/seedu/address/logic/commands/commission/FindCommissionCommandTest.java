package seedu.address.logic.commands.commission;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalCustomers.getTypicalAddressBook;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommissionCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.commission.CompositeCommissionPredicate;
import seedu.address.model.tag.Tag;

public class FindCommissionCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        CompositeCommissionPredicate firstPredicate =
                new CompositeCommissionPredicate(Collections.singleton("first"),
                        Collections.singleton(new Tag("inking")),
                        Collections.singleton(new Tag("traditional")));
        CompositeCommissionPredicate secondPredicate =
                new CompositeCommissionPredicate(Collections.singleton("second"),
                        Collections.singleton(new Tag("big")),
                        Collections.singleton(new Tag("acrylic")));

        FindCommissionCommand findFirstCommand = new FindCommissionCommand(firstPredicate);
        FindCommissionCommand findSecondCommand = new FindCommissionCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommissionCommand findFirstCommandCopy = new FindCommissionCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different  -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }
}
