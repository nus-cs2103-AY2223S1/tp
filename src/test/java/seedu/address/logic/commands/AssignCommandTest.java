package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.student.Student;
import seedu.address.model.tuitionclass.TuitionClass;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AssignCommand.
 */
public class AssignCommandTest {

    @Test
    public void executeForStudent_validIndexUnfilteredList_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.updateCurrentListType(Model.ListType.STUDENT_LIST);

        Student studentToAssign = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        String expectedMessage = String.format(AssignCommand.MESSAGE_ASSIGN_STUDENT_SUCCESS, studentToAssign);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateCurrentListType(Model.ListType.STUDENT_LIST);
        TuitionClass tuitionClassToBeAssigned = expectedModel.getFilteredTuitionClassList()
                .get(INDEX_SECOND_PERSON.getZeroBased());
        tuitionClassToBeAssigned.addStudentToClass(studentToAssign);

        AssignCommand assignCommand = new AssignCommand(INDEX_FIRST_PERSON, tuitionClassToBeAssigned.getName());

        //assertCommandSuccess(assignCommand, model, expectedMessage, expectedModel);
    }
}
