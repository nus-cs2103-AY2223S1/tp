package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.MUSAB_WITH_NO_APPT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.sort.SortByAppointment;
import seedu.address.logic.parser.sort.SortByIncome;
import seedu.address.logic.parser.sort.SortByName;
import seedu.address.logic.parser.sort.SortByRiskTag;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Person;

public class SortCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setModel() {
        this.model = new ModelManager();
        Person[] persons = {ELLE, FIONA, GEORGE, ALICE};
        for (Person p: persons) {
            model.addPerson(p);
        }
    }

    @Test
    public void testCompare() {
        // this is to test the result of compare() in SortByAppointment
        SortByAppointment sortByAppointment = new SortByAppointment("asc");
        int result = sortByAppointment.compare(MUSAB_WITH_NO_APPT, ELLE);
        int result2 = sortByAppointment.compare(MUSAB_WITH_NO_APPT, ALICE);
        assertEquals(1, result);
        assertEquals(0, result2);
    }

    @Test
    public void equals() {
        SortByName sortByName = new SortByName("asc");
        SortCommand sortByNameCommand = new SortCommand(sortByName, "name");
        SortByAppointment sortByAppointment = new SortByAppointment("asc");
        SortCommand sortByAppointmentCommand = new SortCommand(sortByAppointment, "appt");
        SortByRiskTag sortByRiskTag = new SortByRiskTag("asc");
        SortCommand sortByRiskTagCommand = new SortCommand(sortByRiskTag, "risk");
        SortByIncome sortByIncome = new SortByIncome("asc");
        SortCommand sortByIncomeCommand = new SortCommand(sortByIncome, "income");

        // same object -> returns true
        assertTrue(sortByName.equals(sortByName));
        assertTrue(sortByAppointment.equals(sortByAppointment));
        assertTrue(sortByRiskTag.equals(sortByRiskTag));
        assertTrue(sortByIncome.equals(sortByIncome));

        // null -> returns false
        assertFalse(sortByAppointment.equals(null));

        // same values -> return true
        SortCommand sortByNameCommandClone = new SortCommand(sortByName, "name");
        assertTrue(sortByNameCommand.equals(sortByNameCommandClone));

        SortCommand sortByAppointmentCommandClone = new SortCommand(sortByAppointment, "appt");
        assertTrue(sortByAppointmentCommand.equals(sortByAppointmentCommandClone));

        SortCommand sortByRiskTagCommandClone = new SortCommand(sortByRiskTag, "risk");
        assertTrue(sortByRiskTagCommand.equals(sortByRiskTagCommandClone));

        SortCommand sortByIncomeCommandClone = new SortCommand(sortByIncome, "income");
        assertTrue(sortByIncomeCommand.equals(sortByIncomeCommandClone));

        // different types -> returns false
        assertFalse(sortByNameCommand.equals(sortByRiskTagCommand));
        assertFalse(sortByIncomeCommand.equals(sortByAppointmentCommand));


    }

    @Test
    public void execute_sortByName_success() {
        this.expectedModel = new ModelManager();
        Person[] persons = {ALICE, ELLE, FIONA, GEORGE};
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
        Person[] persons = {GEORGE, FIONA, ELLE, ALICE};
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
        Person[] persons = {GEORGE, ELLE, FIONA, ALICE};
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
        Person[] persons = {FIONA, ELLE, GEORGE, ALICE};
        for (Person p: persons) {
            expectedModel.addPerson(p);
        }

        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, "appt");
        SortCommand sortCommand = new SortCommand(new SortByAppointment("desc"), "appt");
        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortByRiskTag_success() {
        this.expectedModel = new ModelManager();
        Person[] persons = {GEORGE, ALICE, ELLE, FIONA};
        for (Person p: persons) {
            expectedModel.addPerson(p);
        }

        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, "risk");
        SortCommand sortCommand = new SortCommand(new SortByRiskTag("asc"), "risk");
        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortByRiskTagDesc_success() {
        this.expectedModel = new ModelManager();
        Person[] persons = {ELLE, FIONA, GEORGE, ALICE};
        for (Person p: persons) {
            expectedModel.addPerson(p);
        }

        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, "risk");
        SortCommand sortCommand = new SortCommand(new SortByRiskTag("desc"), "risk");
        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortByIncome_success() {
        this.expectedModel = new ModelManager();
        Person[] persons = {ALICE, FIONA, ELLE, GEORGE};
        for (Person p: persons) {
            expectedModel.addPerson(p);
        }

        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, "income");
        SortCommand sortCommand = new SortCommand(new SortByIncome("asc"), "income");
        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortByIncomeDesc_success() {
        this.expectedModel = new ModelManager();
        Person[] persons = {GEORGE, ELLE, FIONA, ALICE};
        for (Person p: persons) {
            expectedModel.addPerson(p);
        }

        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, "income");
        SortCommand sortCommand = new SortCommand(new SortByIncome("desc"), "income");
        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }
}
