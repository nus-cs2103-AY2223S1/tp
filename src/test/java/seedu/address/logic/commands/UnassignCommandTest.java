package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalStudents.STUDENT3;
import static seedu.address.testutil.TypicalTuitionClasses.TUITIONCLASS1;
import static seedu.address.testutil.TypicalTuitionClasses.TUITIONCLASS2;
import static seedu.address.testutil.TypicalTuitionClasses.TUITIONCLASS3;
import static seedu.address.testutil.TypicalTutors.TUTOR3;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tuitionclass.TuitionClass;

/**
 * Contains integration tests (interaction with the Model) and unit tests for UnassignCommand.
 */
public class UnassignCommandTest {

    @Test
    public void executeForStudent_validIndexAndTuitionClass_success() {
        Model model = new ModelManager(new AddressBook(), new UserPrefs());
        model.addPerson(STUDENT3);
        model.addTuitionClass(TUITIONCLASS2);
        List<TuitionClass> expectedTuitionClasses = STUDENT3.getTuitionClasses();
        expectedTuitionClasses.remove(TUITIONCLASS2);
        try {
            UnassignCommand unassignCommand = new UnassignCommand(INDEX_FIRST_PERSON, TUITIONCLASS2.getName());
            CommandResult commandResult = unassignCommand.execute(model);
            assertEquals(commandResult, String.format(UnassignCommand.MESSAGE_UNASSIGNED_STUDENT_SUCCESS, STUDENT3));
            assertEquals(expectedTuitionClasses, STUDENT3.getTuitionClasses());
        } catch (CommandException e) {
            return;
        }
    }

    @Test
    public void executeForTutor_validIndexAndTuitionClass_success() {
        Model model = new ModelManager(new AddressBook(), new UserPrefs());
        model.addPerson(TUTOR3);
        model.addTuitionClass(TUITIONCLASS1);
        List<TuitionClass> expectedTuitionClasses = TUTOR3.getTuitionClasses();
        expectedTuitionClasses.remove(TUITIONCLASS1);
        try {
            UnassignCommand unassignCommand = new UnassignCommand(INDEX_FIRST_PERSON, TUITIONCLASS1.getName());
            CommandResult commandResult = unassignCommand.execute(model);
            assertEquals(commandResult, String.format(UnassignCommand.MESSAGE_UNASSIGNED_TUTOR_SUCCESS, TUTOR3));
            assertEquals(expectedTuitionClasses, TUTOR3.getTuitionClasses());
        } catch (CommandException e) {
            return;
        }
    }

    @Test
    public void executeForStudent_invalidCurrentList_throwsCommandException() {
        Model model = new ModelManager(new AddressBook(), new UserPrefs());
        model.addPerson(STUDENT3);
        model.addTuitionClass(TUITIONCLASS2);
        model.updateCurrentListType(Model.ListType.TUITIONCLASS_LIST);
        UnassignCommand unassignCommand = new UnassignCommand(INDEX_FIRST_PERSON, TUITIONCLASS2.getName());

        assertCommandFailure(unassignCommand, model, AssignCommand.MESSAGE_INVALID_CURRENT_LIST);
    }

    @Test
    public void executeForTutor_invalidCurrentList_throwsCommandException() {
        Model model = new ModelManager(new AddressBook(), new UserPrefs());
        model.addPerson(TUTOR3);
        model.addTuitionClass(TUITIONCLASS1);
        model.updateCurrentListType(Model.ListType.TUITIONCLASS_LIST);
        UnassignCommand unassignCommand = new UnassignCommand(INDEX_FIRST_PERSON, TUITIONCLASS1.getName());

        assertCommandFailure(unassignCommand, model, AssignCommand.MESSAGE_INVALID_CURRENT_LIST);
    }

    @Test
    public void executeForStudent_invalidIndexFilteredList_throwsCommandException() {

        Model model = new ModelManager(new AddressBook(), new UserPrefs());
        model.addPerson(STUDENT3);
        model.addTuitionClass(TUITIONCLASS2);
        model.updateCurrentListType(Model.ListType.STUDENT_LIST);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        UnassignCommand unassignCommand = new UnassignCommand(outOfBoundIndex, TUITIONCLASS2.getName());

        assertCommandFailure(unassignCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void executeForTutor_invalidIndexFilteredList_throwsCommandException() {

        Model model = new ModelManager(new AddressBook(), new UserPrefs());
        model.updateCurrentListType(Model.ListType.TUTOR_LIST);
        model.addPerson(TUTOR3);
        model.addTuitionClass(TUITIONCLASS1);
        model.updateCurrentListType(Model.ListType.TUTOR_LIST);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        UnassignCommand unassignCommand = new UnassignCommand(outOfBoundIndex, TUITIONCLASS1.getName());

        assertCommandFailure(unassignCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void executeForStudent_invalidUnassignedClass_throwsCommandException() {
        Model model = new ModelManager(new AddressBook(), new UserPrefs());
        model.updateCurrentListType(Model.ListType.STUDENT_LIST);
        model.addPerson(STUDENT3);
        model.addTuitionClass(TUITIONCLASS3);
        UnassignCommand unassignCommand = new UnassignCommand(INDEX_FIRST_PERSON, TUITIONCLASS3.getName());

        assertCommandFailure(unassignCommand, model, UnassignCommand.MESSAGE_INVALID_UNASSIGNED_STUDENT);
    }

    @Test
    public void executeForTutor_invalidUnassignedClass_throwsCommandException() {
        Model model = new ModelManager(new AddressBook(), new UserPrefs());
        model.updateCurrentListType(Model.ListType.TUTOR_LIST);
        model.addPerson(TUTOR3);
        model.addTuitionClass(TUITIONCLASS3);
        UnassignCommand unassignCommand = new UnassignCommand(INDEX_FIRST_PERSON, TUITIONCLASS3.getName());

        assertCommandFailure(unassignCommand, model, UnassignCommand.MESSAGE_INVALID_UNASSIGNED_TUTOR);
    }
}
