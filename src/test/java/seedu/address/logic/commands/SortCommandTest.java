package seedu.address.logic.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.address.logic.parser.sort.SortByName;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.ELLE;

public class SortCommandTest {
    private Model model;

    @BeforeEach
    public static void setModel() {
        Model model = new ModelManager();
        Person[] persons = {ELLE, AMY, BOB};
        for (Person p: persons) {
            model.addPerson(p);
        }
    }

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SortCommand(null, null));
    }

    @Test
    public void equals() {
        SortByName sortByName = new SortByName();
        SortCommand sortByNameCommand = new SortCommand(sortByName, "name");

        // same object -> returns true
        assertTrue(sortByNameCommand.equals(sortByNameCommand));

        // null -> returns false
        assertFalse(sortByNameCommand.equals(null));

        // same values -> return true
        SortCommand sortByNameCommandClone = new SortCommand(sortByName, "name");
        assertTrue(sortByNameCommand.equals(sortByNameCommandClone));

        // different types -> returns false
        assertFalse(sortByNameCommand.equals(new ClearCommand()));


    }

    @Test
    public void execute_sortByName_success() {
        Model expectedModel = new ModelManager();
        Person[] persons = {AMY, BOB, ELLE};
        for (Person p: persons) {
            expectedModel.addPerson(p);
        }

        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, "name");
        SortCommand sortCommand = new SortCommand(new SortByName(), "name");
        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }
}
