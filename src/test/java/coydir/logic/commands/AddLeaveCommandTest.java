package coydir.logic.commands;

import static coydir.logic.commands.CommandTestUtil.assertCommandFailure;
import static coydir.logic.commands.CommandTestUtil.assertCommandSuccess;
import static coydir.testutil.TypicalIndexes.ID_FIRST_EMPLOYEE;
import static coydir.testutil.TypicalIndexes.ID_SECOND_EMPLOYEE;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import coydir.commons.core.Messages;
import coydir.model.Database;
import coydir.model.Model;
import coydir.model.ModelManager;
import coydir.model.UserPrefs;
import coydir.model.person.EmployeeId;
import coydir.model.person.Leave;
import coydir.model.person.Person;
import coydir.testutil.PersonBuilder;

public class AddLeaveCommandTest {

    @Test
    public void execute_validLeave_success() {
        Model model = new ModelManager(new Database(), new UserPrefs());
        Leave leave = new Leave("02-01-2022", "02-01-2022");
        Leave leave2 = new Leave("01-01-2022", "01-01-2022");
        Person personToAddLeave = new PersonBuilder().withName("Alice Pauline")
                .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                .withPosition("Software Engineer").withDepartment("Information Technology")
                .withPhone("94351253").withRating("3").withTags("friends").withTotalLeave(15)
                .withLeavePeriod(leave2).withEmployeeId("1").build();

        Person personToAddLeaveCopy = new PersonBuilder().withName("Alice Pauline")
                .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                .withPosition("Software Engineer").withDepartment("Information Technology")
                .withPhone("94351253").withRating("3").withTags("friends").withTotalLeave(15)
                .withLeavePeriod(leave2).withEmployeeId("1").build();

        model.addPerson(personToAddLeaveCopy);
        AddLeaveCommand addLeaveCommand = new AddLeaveCommand(ID_FIRST_EMPLOYEE, leave);
        String expectedMessage = String.format(AddLeaveCommand.MESSAGE_LEAVE_ADDED_SUCCESS, personToAddLeave.getName());
        personToAddLeave.addLeave(leave);
        personToAddLeave.setLeavesLeft(14);
        ModelManager expectedModel = new ModelManager(new Database(), new UserPrefs());
        expectedModel.addPerson(personToAddLeave);

        assertCommandSuccess(addLeaveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_insufficientLeave_throwsCommandException() {
        Leave leave = new Leave("02-01-2022", "02-01-2022");
        Model model = new ModelManager(new Database(), new UserPrefs());
        Person personWithNoLeave = new PersonBuilder().withName("Alice Pauline")
                .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                .withPosition("Software Engineer").withDepartment("Information Technology")
                .withPhone("94351253").withRating("3").withTags("friends").withTotalLeave(0)
                .withLeavePeriod(new Leave("01-01-2022", "01-01-2022")).withEmployeeId("1").build();
        model.addPerson(personWithNoLeave);
        AddLeaveCommand addLeaveCommand = new AddLeaveCommand(ID_FIRST_EMPLOYEE, leave);

        assertCommandFailure(addLeaveCommand, model, AddLeaveCommand.MESSAGE_INSUFFICIENT_LEAVES);
    }

    @Test
    public void execute_overlappingLeave_throwsCommandException() {
        Leave leave = new Leave("01-01-2022", "02-01-2022");
        Model model = new ModelManager(new Database(), new UserPrefs());
        Person personWithOverlappingLeave = new PersonBuilder().withName("Alice Pauline")
                .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                .withPosition("Software Engineer").withDepartment("Information Technology")
                .withPhone("94351253").withRating("3").withTags("friends").withTotalLeave(10)
                .withLeavePeriod(new Leave("01-01-2022", "01-01-2022")).withEmployeeId("1").build();
        model.addPerson(personWithOverlappingLeave);
        AddLeaveCommand addLeaveCommand = new AddLeaveCommand(ID_FIRST_EMPLOYEE, leave);

        assertCommandFailure(addLeaveCommand, model, AddLeaveCommand.MESSAGE_OVERLAPPING_LEAVE);
    }

    @Test
    public void execute_duplicateLeave_throwsCommandException() {
        Leave leave = new Leave("01-01-2022", "01-01-2022");
        Model model = new ModelManager(new Database(), new UserPrefs());
        Person personWithDuplicateLeave = new PersonBuilder().withName("Alice Pauline")
                .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                .withPosition("Software Engineer").withDepartment("Information Technology")
                .withPhone("94351253").withRating("3").withTags("friends").withTotalLeave(10)
                .withLeavePeriod(new Leave("01-01-2022", "01-01-2022")).withEmployeeId("1").build();
        model.addPerson(personWithDuplicateLeave);
        AddLeaveCommand addLeaveCommand = new AddLeaveCommand(ID_FIRST_EMPLOYEE, leave);
        assertCommandFailure(addLeaveCommand, model, AddLeaveCommand.MESSAGE_DUPLICATE_LEAVE);
    }

    @Test
    public void execute_invalidID_throwsCommandException() {
        Leave leave = new Leave("01-01-2022", "01-01-2022");
        Model model = new ModelManager(new Database(), new UserPrefs());
        Person personWithDuplicateLeave = new PersonBuilder().withName("Alice Pauline")
                .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                .withPosition("Software Engineer").withDepartment("Information Technology")
                .withPhone("94351253").withRating("3").withTags("friends").withTotalLeave(10)
                .withLeavePeriod(new Leave("01-01-2022", "01-01-2022")).withEmployeeId("1").build();
        model.addPerson(personWithDuplicateLeave);
        AddLeaveCommand addLeaveCommand = new AddLeaveCommand(ID_SECOND_EMPLOYEE, leave);
        assertCommandFailure(addLeaveCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        EmployeeId id = new EmployeeId("1");
        EmployeeId id2 = new EmployeeId("2");
        Leave leave1 = new Leave("02-01-2022", "05-01-2022");
        Leave leave2 = new Leave("03-01-2022", "04-01-2022");
        AddLeaveCommand addLeaveCommand1 = new AddLeaveCommand(id, leave1);
        AddLeaveCommand addLeaveCommand2 = new AddLeaveCommand(id, leave2);
        AddLeaveCommand addLeaveCommand3 = new AddLeaveCommand(id2, leave1);
        // same object -> returns true
        assertTrue(addLeaveCommand1.equals(addLeaveCommand1));

        // same values -> returns true
        AddLeaveCommand addLeaveCommand1Copy = new AddLeaveCommand(id, leave1);
        assertTrue(addLeaveCommand1.equals(addLeaveCommand1Copy));

        // different types -> returns false
        assertFalse(addLeaveCommand1.equals(1));

        // null -> returns false
        assertFalse(addLeaveCommand1.equals(null));

        // different leave periods -> returns false
        assertFalse(addLeaveCommand1.equals(addLeaveCommand2));

        // different ID -> returns false
        assertFalse(addLeaveCommand1.equals(addLeaveCommand3));
    }
}
