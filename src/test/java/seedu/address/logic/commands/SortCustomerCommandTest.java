package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.commons.core.Messages.MESSAGE_CUSTOMERS_SORTED;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.Model.CUSTOMER_NAME_COMPARATOR;
import static seedu.address.model.Model.CUSTOMER_REVENUE_COMPARATOR;
import static seedu.address.testutil.TypicalCustomers.ALICE;
import static seedu.address.testutil.TypicalCustomers.BENSON;
import static seedu.address.testutil.TypicalCustomers.CARL;
import static seedu.address.testutil.TypicalCustomers.DANIEL;
import static seedu.address.testutil.TypicalCustomers.ELLE;
import static seedu.address.testutil.TypicalCustomers.FIONA;
import static seedu.address.testutil.TypicalCustomers.GEORGE;
import static seedu.address.testutil.TypicalCustomers.MONA;
import static seedu.address.testutil.TypicalCustomers.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.util.SortDirection;

/**
 * Contains integration tests (interaction with the Model) for {@code SortCustomerCommand}.
 */
public class SortCustomerCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }


    @Test
    public void execute_nameSort_sortsProperly() {
        String expectedMessage = String.format(MESSAGE_CUSTOMERS_SORTED, 8, SortDirection.DECREASING, "description");
        SortCustomerCommand command = new SortCustomerCommand(
                CUSTOMER_NAME_COMPARATOR.reversed(), "description", SortDirection.DECREASING);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(MONA, GEORGE, FIONA, ELLE, DANIEL, CARL, BENSON, ALICE),
                model.getSortedFilteredCustomerList());
    }

    @Test
    public void equals() {
        SortCustomerCommand nameSort = new SortCustomerCommand(CUSTOMER_NAME_COMPARATOR, "name",
                SortDirection.INCREASING);
        SortCustomerCommand nameSort1 = new SortCustomerCommand(CUSTOMER_NAME_COMPARATOR, "name",
            SortDirection.INCREASING);
        SortCustomerCommand nameSortDecreasing = new SortCustomerCommand(CUSTOMER_NAME_COMPARATOR.reversed(), "name",
            SortDirection.DECREASING);
        SortCustomerCommand revenueSort = new SortCustomerCommand(CUSTOMER_REVENUE_COMPARATOR.reversed(), "revenue",
            SortDirection.DECREASING);
        assertEquals(nameSort, nameSort1);
        assertNotEquals(nameSort, nameSortDecreasing);
        assertNotEquals(nameSort, revenueSort);
    }
}
