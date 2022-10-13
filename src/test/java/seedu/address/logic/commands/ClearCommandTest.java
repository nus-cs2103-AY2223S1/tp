package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalStudents.getTypicalStudentsAddressBook;
import static seedu.address.testutil.TypicalTuitionClasses.getTypicalTuitionClassesAddressBook;
import static seedu.address.testutil.TypicalTutors.getTypicalTutorsAddressBook;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_emptyStudentList_success() {
        Model model = new ModelManager();
        model.updateCurrentListType(Model.ListType.STUDENT_LIST);
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_emptyTutorList_success() {
        Model model = new ModelManager();
        model.updateCurrentListType(Model.ListType.TUTOR_LIST);
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_emptyTuitionClassList_success() {
        Model model = new ModelManager();
        model.updateCurrentListType(Model.ListType.TUITIONCLASS_LIST);
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBookOnDefault_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.updateCurrentListType(Model.ListType.PERSON_LIST);
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setAddressBook(new AddressBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyStudentList_success() {
        Model model = new ModelManager(getTypicalStudentsAddressBook(), new UserPrefs());
        model.updateCurrentListType(Model.ListType.STUDENT_LIST);
        Model expectedModel = new ModelManager(getTypicalStudentsAddressBook(), new UserPrefs());
        expectedModel.updateCurrentListType(Model.ListType.STUDENT_LIST);
        expectedModel.setStudents(Collections.emptyList());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyTutorList_success() {
        Model model = new ModelManager(getTypicalTutorsAddressBook(), new UserPrefs());
        model.updateCurrentListType(Model.ListType.TUTOR_LIST);
        Model expectedModel = new ModelManager(getTypicalTutorsAddressBook(), new UserPrefs());
        expectedModel.updateCurrentListType(Model.ListType.TUTOR_LIST);
        expectedModel.setTutors(Collections.emptyList());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyTuitionClassList_success() {
        Model model = new ModelManager(getTypicalTuitionClassesAddressBook(), new UserPrefs());
        model.updateCurrentListType(Model.ListType.TUITIONCLASS_LIST);
        Model expectedModel = new ModelManager(getTypicalTuitionClassesAddressBook(), new UserPrefs());
        expectedModel.updateCurrentListType(Model.ListType.TUITIONCLASS_LIST);
        expectedModel.setTuitionClasses(Collections.emptyList());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_undisplayedListsNonEmpty_noChangeToUndisplayedList() {
        // undisplayed is student list
        Model model = new ModelManager(getTypicalStudentsAddressBook(), new UserPrefs());
        model.updateCurrentListType(Model.ListType.TUTOR_LIST);
        Model expectedModel = new ModelManager(getTypicalStudentsAddressBook(), new UserPrefs());
        expectedModel.updateCurrentListType(Model.ListType.TUTOR_LIST);

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);

        model = new ModelManager(getTypicalStudentsAddressBook(), new UserPrefs());
        model.updateCurrentListType(Model.ListType.TUITIONCLASS_LIST);
        expectedModel = new ModelManager(getTypicalStudentsAddressBook(), new UserPrefs());
        expectedModel.updateCurrentListType(Model.ListType.TUITIONCLASS_LIST);

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);

        // undisplayed is tutor list
        model = new ModelManager(getTypicalTutorsAddressBook(), new UserPrefs());
        model.updateCurrentListType(Model.ListType.STUDENT_LIST);
        expectedModel = new ModelManager(getTypicalTutorsAddressBook(), new UserPrefs());
        expectedModel.updateCurrentListType(Model.ListType.STUDENT_LIST);

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);

        model = new ModelManager(getTypicalTutorsAddressBook(), new UserPrefs());
        model.updateCurrentListType(Model.ListType.TUITIONCLASS_LIST);
        expectedModel = new ModelManager(getTypicalTutorsAddressBook(), new UserPrefs());
        expectedModel.updateCurrentListType(Model.ListType.TUITIONCLASS_LIST);

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);

        // undisplayed is tuition class list
        model = new ModelManager(getTypicalTuitionClassesAddressBook(), new UserPrefs());
        model.updateCurrentListType(Model.ListType.STUDENT_LIST);
        expectedModel = new ModelManager(getTypicalTuitionClassesAddressBook(), new UserPrefs());
        expectedModel.updateCurrentListType(Model.ListType.STUDENT_LIST);

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);

        model = new ModelManager(getTypicalTuitionClassesAddressBook(), new UserPrefs());
        model.updateCurrentListType(Model.ListType.TUTOR_LIST);
        expectedModel = new ModelManager(getTypicalTuitionClassesAddressBook(), new UserPrefs());
        expectedModel.updateCurrentListType(Model.ListType.TUTOR_LIST);

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);

    }
}
