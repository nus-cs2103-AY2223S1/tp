package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.IDA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.MUSAB_WITH_NO_APPT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.sort.SortByAppointment;
import seedu.address.logic.parser.sort.SortByName;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Person;

public class SortCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setModel() {
        this.model = new ModelManager();
        Person[] persons = {ELLE, IDA, GEORGE, MUSAB_WITH_NO_APPT};
        for (Person p: persons) {
            model.addPerson(p);
        }
    }


    @Test
    public void equals() {
        SortByName sortByName = new SortByName("asc");
        SortCommand sortByNameCommand = new SortCommand(sortByName, "name");
        SortByName sortByNameDesc = new SortByName("desc");
        SortCommand sortByNameDescCommand = new SortCommand(sortByNameDesc, "name");
        SortByAppointment sortByAppointment = new SortByAppointment("asc");
        SortCommand sortByAppointmentCommand = new SortCommand(sortByAppointment, "appt");

        // same object -> returns true
        assertTrue(sortByNameCommand.equals(sortByNameCommand));

        // null -> returns false
        assertFalse(sortByAppointment.equals(null));

        // same values -> return true
        SortCommand sortByNameCommandClone = new SortCommand(sortByName, "name");
        assertTrue(sortByNameCommand.equals(sortByNameCommandClone));

        SortCommand sortByNameDescCommandClone = new SortCommand(sortByNameDesc, "name");
        assertTrue(sortByNameDescCommand.equals(sortByNameDescCommandClone));

        // different types -> returns false
        assertFalse(sortByNameCommand.equals(sortByNameDescCommand));


    }

    @Test
    public void execute_sortByName_success() {
        this.expectedModel = new ModelManager();
        Person[] persons = {ELLE, GEORGE, IDA, MUSAB_WITH_NO_APPT};
        for (Person p: persons) {
            expectedModel.addPerson(p);
        }

        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, "name");
        SortCommand sortCommand = new SortCommand(new SortByName("asc"), "name");
        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortByNameDesc_success() {
        this.expectedModel = new ModelManager();
        Person[] persons = {MUSAB_WITH_NO_APPT, IDA, GEORGE, ELLE};
        for (Person p: persons) {
            expectedModel.addPerson(p);
        }

        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, "name");
        SortCommand sortCommand = new SortCommand(new SortByName("desc"), "name");
        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortByAppt_success() {
        this.expectedModel = new ModelManager();
        Person[] persons = {GEORGE, ELLE, IDA, MUSAB_WITH_NO_APPT};
        for (Person p: persons) {
            expectedModel.addPerson(p);
        }

        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, "appt");
        SortCommand sortCommand = new SortCommand(new SortByAppointment("asc"), "appt");
        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortByApptDesc_success() {
        this.expectedModel = new ModelManager();
        Person[] persons = {IDA, ELLE, GEORGE, MUSAB_WITH_NO_APPT};
        for (Person p: persons) {
            expectedModel.addPerson(p);
        }

        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, "appt");
        SortCommand sortCommand = new SortCommand(new SortByAppointment("desc"), "appt");
        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }
}
