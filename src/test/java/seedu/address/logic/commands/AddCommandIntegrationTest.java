package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandResult.CommandType.ADDSTUDENT;
import static seedu.address.logic.commands.CommandResult.CommandType.ADDTUTOR;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStudents.getTypicalStudentsAddressBook;
import static seedu.address.testutil.TypicalTuitionClasses.getTypicalTuitionClassesAddressBook;
import static seedu.address.testutil.TypicalTutors.getTypicalTutorsAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.tutor.Tutor;
import seedu.address.model.tuitionclass.TuitionClass;
import seedu.address.testutil.StudentBuilder;
import seedu.address.testutil.TuitionClassBuilder;
import seedu.address.testutil.TutorBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest { //todo

    @Test
    public void execute_newPerson_success() {
        Model model = new ModelManager(getTypicalStudentsAddressBook(), new UserPrefs());
        Student validStudent = new StudentBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(validStudent);

        assertCommandSuccess(AddCommand.of(validStudent), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validStudent), expectedModel, ADDSTUDENT);

        model = new ModelManager(getTypicalTutorsAddressBook(), new UserPrefs());
        Tutor validTutor = new TutorBuilder().build();

        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(validTutor);

        assertCommandSuccess(AddCommand.of(validTutor), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validTutor), expectedModel, ADDTUTOR);
    }

    @Test
    public void execute_newClass_success() {
        Model model = new ModelManager(getTypicalTuitionClassesAddressBook(), new UserPrefs());
        TuitionClass validClass = new TuitionClassBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addTuitionClass(validClass);

        assertCommandSuccess(AddCommand.of(validClass), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validClass), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Model model = new ModelManager(getTypicalStudentsAddressBook(), new UserPrefs());

        Student studentInList = model.getAddressBook().getStudentList().get(0);
        assertCommandFailure(AddCommand.of(studentInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);

        model = new ModelManager(getTypicalTutorsAddressBook(), new UserPrefs());

        Tutor tutorInList = model.getAddressBook().getTutorList().get(0);
        assertCommandFailure(AddCommand.of(tutorInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicateClass_throwsCommandException() {
        Model model = new ModelManager(getTypicalTuitionClassesAddressBook(), new UserPrefs());

        TuitionClass classInList = model.getAddressBook().getTuitionClassList().get(0);
        assertCommandFailure(AddCommand.of(classInList), model, AddCommand.MESSAGE_DUPLICATE_CLASS);
    }

}
