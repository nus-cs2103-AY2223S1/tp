package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandResult.CommandType.NOK;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalNextOfKins.AMY_NEXTOFKIN;
import static seedu.address.testutil.TypicalNextOfKins.BOB_NEXTOFKIN;
import static seedu.address.testutil.TypicalStudents.getTypicalStudentsAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.nextofkin.NextOfKin;
import seedu.address.model.person.student.Student;
import seedu.address.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for NextOfKinCommand.
 */
public class NextOfKinCommandTest {

    //todo: maybe change to model stub? not sure

    @Test
    public void constructor_nullEntity_throwsNullPointerException() {
        NextOfKin nextOfKin = null;
        Index index = null;

        //index null
        assertThrows(NullPointerException.class, () -> new NextOfKinCommand(index));

        //both null
        assertThrows(NullPointerException.class, () -> new NextOfKinCommand(index, nextOfKin));

        //index valid, next of kin null
        Index validIndex = INDEX_FIRST_PERSON;
        assertThrows(NullPointerException.class, () -> new NextOfKinCommand(validIndex, nextOfKin));

        //index null, next of kin valid
        NextOfKin validNextOfKin = AMY_NEXTOFKIN;
        assertThrows(NullPointerException.class, () -> new NextOfKinCommand(index, validNextOfKin));
    }

    @Test
    public void execute_studentListNewNextOfKin_addSuccessful() {
        Model model = new ModelManager(getTypicalStudentsAddressBook(), new UserPrefs());
        model.updateCurrentListType(Model.ListType.STUDENT_LIST);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.updateCurrentListType(Model.ListType.STUDENT_LIST);
        Student editedStudent = new StudentBuilder(model.getFilteredStudentList().get(0))
                .withNextOfKin(AMY_NEXTOFKIN).build();
        expectedModel.setPerson(model.getFilteredStudentList().get(0), editedStudent);

        NextOfKinCommand nextOfKinCommand = new NextOfKinCommand(INDEX_FIRST_PERSON, AMY_NEXTOFKIN);

        String expectedMessage =
                String.format(NextOfKinCommand.MESSAGE_ADD_NEXTOFKIN_SUCCESS, AMY_NEXTOFKIN, editedStudent);

        assertCommandSuccess(nextOfKinCommand, model, expectedMessage, expectedModel,
                NOK, INDEX_FIRST_PERSON.getZeroBased());
    }

    @Test
    public void execute_studentListUpdateNextOfKin_addSuccessful() {
        Model model = new ModelManager(getTypicalStudentsAddressBook(), new UserPrefs());
        model.updateCurrentListType(Model.ListType.STUDENT_LIST);
        Student editedStudent = new StudentBuilder(model.getFilteredStudentList().get(0))
                .withNextOfKin(AMY_NEXTOFKIN).build();
        model.setPerson(model.getFilteredStudentList().get(0), editedStudent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.updateCurrentListType(Model.ListType.STUDENT_LIST);
        editedStudent = new StudentBuilder(model.getFilteredStudentList().get(0))
                .withNextOfKin(BOB_NEXTOFKIN).build();
        expectedModel.setPerson(model.getFilteredStudentList().get(0), editedStudent);

        NextOfKinCommand nextOfKinCommand = new NextOfKinCommand(INDEX_FIRST_PERSON, BOB_NEXTOFKIN);

        String expectedMessage =
                String.format(NextOfKinCommand.MESSAGE_ADD_NEXTOFKIN_SUCCESS, BOB_NEXTOFKIN, editedStudent);

        assertCommandSuccess(nextOfKinCommand, model, expectedMessage, expectedModel,
                NOK, INDEX_FIRST_PERSON.getZeroBased());
    }

    @Test
    public void execute_studentListRemoveNextOfKin_removeSuccessful() {
        //existing next of kin
        Model model = new ModelManager(getTypicalStudentsAddressBook(), new UserPrefs());
        model.updateCurrentListType(Model.ListType.STUDENT_LIST);
        Student editedStudent = new StudentBuilder(model.getFilteredStudentList().get(0))
                .withNextOfKin(AMY_NEXTOFKIN).build();
        model.setPerson(model.getFilteredStudentList().get(0), editedStudent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.updateCurrentListType(Model.ListType.STUDENT_LIST);
        editedStudent = new StudentBuilder(model.getFilteredStudentList().get(0))
                .withNextOfKin(null).build();
        expectedModel.setPerson(model.getFilteredStudentList().get(0), editedStudent);

        NextOfKinCommand nextOfKinCommand = new NextOfKinCommand(INDEX_FIRST_PERSON);

        String expectedMessage =
                String.format(NextOfKinCommand.MESSAGE_REMOVE_NEXTOFKIN_SUCCESS, editedStudent);

        assertCommandSuccess(nextOfKinCommand, model, expectedMessage, expectedModel,
                NOK, INDEX_FIRST_PERSON.getZeroBased());

        //no existing next of kin
        model = new ModelManager(getTypicalStudentsAddressBook(), new UserPrefs());
        model.updateCurrentListType(Model.ListType.STUDENT_LIST);

        expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.updateCurrentListType(Model.ListType.STUDENT_LIST);

        nextOfKinCommand = new NextOfKinCommand(INDEX_FIRST_PERSON);

        expectedMessage =
                String.format(NextOfKinCommand.MESSAGE_REMOVE_NEXTOFKIN_SUCCESS, editedStudent);

        assertCommandSuccess(nextOfKinCommand, model, expectedMessage, expectedModel,
                NOK, INDEX_FIRST_PERSON.getZeroBased());
    }

    @Test
    public void execute_wrongList_throwsCommandException() {
        Model modelForTutor = new ModelManager(getTypicalStudentsAddressBook(), new UserPrefs());
        modelForTutor.updateCurrentListType(Model.ListType.TUTOR_LIST);

        NextOfKinCommand nextOfKinCommandForTutor = new NextOfKinCommand(INDEX_FIRST_PERSON);

        assertThrows(CommandException.class,
                Messages.MESSAGE_WRONG_LIST_FOR_COMMAND_USAGE, () -> nextOfKinCommandForTutor.execute(modelForTutor));

        Model modelForTuitionClass = new ModelManager(getTypicalStudentsAddressBook(), new UserPrefs());
        modelForTuitionClass.updateCurrentListType(Model.ListType.TUITIONCLASS_LIST);

        NextOfKinCommand nextOfKinCommandForTuitionClass = new NextOfKinCommand(INDEX_FIRST_PERSON);

        assertThrows(CommandException.class,
                Messages.MESSAGE_WRONG_LIST_FOR_COMMAND_USAGE, () -> nextOfKinCommandForTuitionClass
                        .execute(modelForTuitionClass));
    }

    @Test
    public void execute_studentListDuplicateNextOfKin_throwsCommandException() {
        Model model = new ModelManager(getTypicalStudentsAddressBook(), new UserPrefs());
        model.updateCurrentListType(Model.ListType.STUDENT_LIST);
        Student editedStudent = new StudentBuilder(model.getFilteredStudentList().get(0))
                .withNextOfKin(AMY_NEXTOFKIN).build();
        model.setPerson(model.getFilteredStudentList().get(0), editedStudent);

        NextOfKinCommand nextOfKinCommand = new NextOfKinCommand(INDEX_FIRST_PERSON, AMY_NEXTOFKIN);

        assertThrows(CommandException.class,
                NextOfKinCommand.MESSAGE_DUPLICATE_NEXTOFKIN, () -> nextOfKinCommand.execute(model));
    }
}
