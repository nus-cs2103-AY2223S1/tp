package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getSortedAddressBookAscending;
import static seedu.address.testutil.TypicalPersons.getSortedAddressBookDescending;
import static seedu.address.testutil.TypicalPersons.getSortedProfessorsAscending;
import static seedu.address.testutil.TypicalPersons.getSortedStudentsAscending;
import static seedu.address.testutil.TypicalPersons.getUnsortedAddressBook;
import static seedu.address.testutil.TypicalPersons.getUnsortedProfessors;
import static seedu.address.testutil.TypicalPersons.getUnsortedStudents;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.order.Order;
import seedu.address.logic.commands.SortCommand.SortPersonListDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

class SortCommandTest {
    private static Order ascendingOrder = Order.lexicographicalOrder("A-Z");
    private static Order descendingOrder = Order.lexicographicalOrder("Z-A");
    private static SortPersonListDescriptor sortBy = new SortPersonListDescriptor();
    private Model model = new ModelManager(
            getUnsortedAddressBook(), new UserPrefs());

    @Test
    void execute_sortUnfilteredListByNameAscending_success() {
        sortBy.setName(true);
        SortCommand sort = new SortCommand(ascendingOrder, sortBy);
        Model expectedModel = new ModelManager(
                getSortedAddressBookAscending(), new UserPrefs());
        String expectedMessage = SortCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(sort, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_sortUnfilteredListByNameDescending_success() {
        sortBy.setName(true);
        SortCommand sort = new SortCommand(descendingOrder, sortBy);
        Model expectedModel = new ModelManager(
                getSortedAddressBookDescending(), new UserPrefs());
        String expectedMessage = SortCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(sort, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_sortProfessorsListByNameAscending_success() {
        sortBy.setName(true);
        SortCommand sort = new SortCommand(ascendingOrder, sortBy);
        Model model = new ModelManager(getUnsortedProfessors(), new UserPrefs());
        Model expectedModel = new ModelManager(getSortedProfessorsAscending(), new UserPrefs());
        String expectedMessage = SortCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(sort, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_sortStudentsList_success() {
        sortBy.setName(true);
        SortCommand sort = new SortCommand(ascendingOrder, sortBy);
        Model model = new ModelManager(getUnsortedStudents(), new UserPrefs());
        Model expectedModel = new ModelManager(getSortedStudentsAscending(), new UserPrefs());
        String expectedMessage = SortCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(sort, model, expectedMessage, expectedModel);
    }
}
