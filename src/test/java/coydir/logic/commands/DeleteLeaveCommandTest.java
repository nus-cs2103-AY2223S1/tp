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
import coydir.model.person.Leave;
import coydir.model.person.Person;
import coydir.testutil.PersonBuilder;

public class DeleteLeaveCommandTest {

    @Test
    public void execute_validAllFields_success() {
    Model model = new ModelManager(new Database(), new UserPrefs());
    Leave leave = new Leave("02-01-2022", "02-01-2022");
    Person personToDeleteLeave = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPosition("Software Engineer").withDepartment("Information Technology")
            .withPhone("94351253").withRating("3").withTags("friends").withTotalLeave(15)
            .withLeavePeriod(leave).withEmployeeId("1").build();
    Person expectedPerson = new PersonBuilder().withName("Alice Pauline")
    .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
    .withPosition("Software Engineer").withDepartment("Information Technology")
    .withPhone("94351253").withRating("3").withTags("friends").withTotalLeave(15)
    .withEmployeeId("1").build();
    personToDeleteLeave.setLeavesLeft(14);
    model.addPerson(personToDeleteLeave);
    DeleteLeaveCommand deleteLeaveCommand = new DeleteLeaveCommand(ID_FIRST_EMPLOYEE, 0);
    String expectedMessage =  String.format(DeleteLeaveCommand.MESSAGE_LEAVE_REMOVE_SUCCESS, personToDeleteLeave.getName());
    ModelManager expectedModel = new ModelManager(new Database(), new UserPrefs());
    expectedModel.addPerson(expectedPerson);
    expectedPerson.setLeavesLeft(15);
    assertCommandSuccess(deleteLeaveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidID_throwsCommandException() {
        DeleteLeaveCommand deleteLeaveCommand = new DeleteLeaveCommand(ID_SECOND_EMPLOYEE, 0);
        Model model = new ModelManager(new Database(), new UserPrefs());
        Person person = new PersonBuilder().withName("Alice Pauline")
        .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
        .withPosition("Software Engineer").withDepartment("Information Technology")
        .withPhone("94351253").withRating("3").withTags("friends").withTotalLeave(0)
        .withLeavePeriod(new Leave("01-01-2022", "01-01-2022")).withEmployeeId("1").build();
        model.addPerson(person);
        assertCommandFailure(deleteLeaveCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        DeleteLeaveCommand deleteLeaveCommand = new DeleteLeaveCommand(ID_FIRST_EMPLOYEE, 100);
        Model model = new ModelManager(new Database(), new UserPrefs());
        Person person = new PersonBuilder().withName("Alice Pauline")
        .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
        .withPosition("Software Engineer").withDepartment("Information Technology")
        .withPhone("94351253").withRating("3").withTags("friends").withTotalLeave(0)
        .withLeavePeriod(new Leave("01-01-2022", "01-01-2022")).withEmployeeId("1").build();
        model.addPerson(person);
        assertCommandFailure(deleteLeaveCommand, model, DeleteLeaveCommand.MESSAGE_INVALID_INDEX);
    }


}
