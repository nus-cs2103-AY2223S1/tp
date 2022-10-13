package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY_CLASS;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY_STUDENT;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY_TUTOR;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB_CLASS;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB_STUDENT;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB_TUTOR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DAY_CLASS1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CLASS1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUALIFICATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHOOL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUBJECT_CLASS1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.address.logic.commands.CommandTestUtil.showTuitionClassAtIndex;
import static seedu.address.logic.commands.CommandTestUtil.showTutorAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalStudents.getTypicalStudentsAddressBook;
import static seedu.address.testutil.TypicalTuitionClasses.getTypicalTuitionClassesAddressBook;
import static seedu.address.testutil.TypicalTutors.getTypicalTutorsAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.address.logic.commands.EditCommand.EditTuitionClassDescriptor;
import seedu.address.logic.commands.EditCommand.EditTutorDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.tutor.Tutor;
import seedu.address.model.tuitionclass.TuitionClass;
import seedu.address.testutil.EditStudentDescriptorBuilder;
import seedu.address.testutil.EditTuitionClassDescriptorBuilder;
import seedu.address.testutil.EditTutorDescriptorBuilder;
import seedu.address.testutil.StudentBuilder;
import seedu.address.testutil.TuitionClassBuilder;
import seedu.address.testutil.TutorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Model model = new ModelManager(getTypicalStudentsAddressBook(), new UserPrefs());
        model.updateCurrentListType(Model.ListType.STUDENT_LIST);

        Student editedStudent = new StudentBuilder().build();
        EditStudentDescriptor studentDescriptor = new EditStudentDescriptorBuilder(editedStudent).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, studentDescriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.updateCurrentListType(Model.ListType.STUDENT_LIST);
        expectedModel.setPerson(model.getFilteredStudentList().get(0), editedStudent);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);

        model = new ModelManager(getTypicalTutorsAddressBook(), new UserPrefs());
        model.updateCurrentListType(Model.ListType.TUTOR_LIST);

        Tutor editedTutor = new TutorBuilder().build();
        EditTutorDescriptor tutorDescriptor = new EditTutorDescriptorBuilder(editedTutor).build();
        editCommand = new EditCommand(INDEX_FIRST_PERSON, tutorDescriptor);

        expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedTutor);

        expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.updateCurrentListType(Model.ListType.TUTOR_LIST);
        expectedModel.setPerson(model.getFilteredTutorList().get(0), editedTutor);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);

        //cant test this now coz edit does not account for list of students and tutors fields of class
        //
        //model = new ModelManager(getTypicalTuitionClassesAddressBook(), new UserPrefs());
        //model.updateCurrentListType(Model.ListType.TUITIONCLASS_LIST);
        //
        //TuitionClass editedClass = new TuitionClassBuilder().build();
        //EditTuitionClassDescriptor classDescriptor = new EditTuitionClassDescriptorBuilder(editedClass).build();
        //editCommand = new EditCommand(INDEX_FIRST_PERSON, classDescriptor);
        //
        //expectedMessage = String.format(EditCommand.MESSAGE_EDIT_CLASS_SUCCESS, editedClass);
        //
        //expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        //expectedModel.updateCurrentListType(Model.ListType.TUITIONCLASS_LIST);
        //expectedModel.setTuitionClass(model.getFilteredTuitionClassList().get(0), editedClass);
        //
        //assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        // student
        Model model = new ModelManager(getTypicalStudentsAddressBook(), new UserPrefs());
        model.updateCurrentListType(Model.ListType.STUDENT_LIST);

        Index indexLastStudent = Index.fromOneBased(model.getFilteredStudentList().size());
        Student lastStudent = model.getFilteredStudentList().get(indexLastStudent.getZeroBased());

        StudentBuilder studentInList = new StudentBuilder(lastStudent);
        Student editedStudent = studentInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).withSchool(VALID_SCHOOL_BOB).build();

        EditStudentDescriptor studentDescriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).withSchool(VALID_SCHOOL_BOB).build();
        EditCommand editCommand = new EditCommand(indexLastStudent, studentDescriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.updateCurrentListType(Model.ListType.STUDENT_LIST);
        expectedModel.setPerson(lastStudent, editedStudent);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);

        // tutor
        model = new ModelManager(getTypicalTutorsAddressBook(), new UserPrefs());
        model.updateCurrentListType(Model.ListType.TUTOR_LIST);

        Index indexLastTutor = Index.fromOneBased(model.getFilteredTutorList().size());
        Tutor lastTutor = model.getFilteredTutorList().get(indexLastTutor.getZeroBased());

        TutorBuilder tutorInList = new TutorBuilder(lastTutor);
        Tutor editedTutor = tutorInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).withQualification(VALID_QUALIFICATION_BOB).build();

        EditTutorDescriptor tutorDescriptor = new EditTutorDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).withQualification(VALID_QUALIFICATION_BOB)
                .build();
        editCommand = new EditCommand(indexLastTutor, tutorDescriptor);

        expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedTutor);

        expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.updateCurrentListType(Model.ListType.TUTOR_LIST);
        expectedModel.setPerson(lastTutor, editedTutor);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);

        // tuition class
        model = new ModelManager(getTypicalTuitionClassesAddressBook(), new UserPrefs());
        model.updateCurrentListType(Model.ListType.TUITIONCLASS_LIST);

        Index indexLastClass = Index.fromOneBased(model.getFilteredTuitionClassList().size());
        TuitionClass lastClass = model.getFilteredTuitionClassList().get(indexLastClass.getZeroBased());

        TuitionClassBuilder classInList = new TuitionClassBuilder(lastClass);
        TuitionClass editedClass = classInList.withName(VALID_NAME_CLASS1).withSubject(VALID_SUBJECT_CLASS1)
                .withTags(VALID_TAG_HUSBAND).withDay(VALID_DAY_CLASS1).build();

        EditTuitionClassDescriptor classDescriptor = new EditTuitionClassDescriptorBuilder().withName(VALID_NAME_CLASS1)
                .withSubject(VALID_SUBJECT_CLASS1).withTags(VALID_TAG_HUSBAND).withDay(VALID_DAY_CLASS1).build();
        editCommand = new EditCommand(indexLastClass, classDescriptor);

        expectedMessage = String.format(EditCommand.MESSAGE_EDIT_CLASS_SUCCESS, editedClass);

        expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.updateCurrentListType(Model.ListType.TUITIONCLASS_LIST);
        expectedModel.setTuitionClass(lastClass, editedClass);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        // student
        Model model = new ModelManager(getTypicalStudentsAddressBook(), new UserPrefs());
        model.updateCurrentListType(Model.ListType.STUDENT_LIST);

        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, new EditStudentDescriptor());
        Student editedStudent = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.updateCurrentListType(Model.ListType.STUDENT_LIST);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);

        // tutor
        model = new ModelManager(getTypicalTutorsAddressBook(), new UserPrefs());
        model.updateCurrentListType(Model.ListType.TUTOR_LIST);

        editCommand = new EditCommand(INDEX_FIRST_PERSON, new EditTutorDescriptor());
        Tutor editedTutor = model.getFilteredTutorList().get(INDEX_FIRST_PERSON.getZeroBased());

        expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedTutor);

        expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.updateCurrentListType(Model.ListType.TUTOR_LIST);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);

        // class
        model = new ModelManager(getTypicalTuitionClassesAddressBook(), new UserPrefs());
        model.updateCurrentListType(Model.ListType.TUITIONCLASS_LIST);

        editCommand = new EditCommand(INDEX_FIRST_PERSON, new EditTuitionClassDescriptor());
        TuitionClass editedClass = model.getFilteredTuitionClassList().get(INDEX_FIRST_PERSON.getZeroBased());

        expectedMessage = String.format(EditCommand.MESSAGE_EDIT_CLASS_SUCCESS, editedClass);

        expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.updateCurrentListType(Model.ListType.TUITIONCLASS_LIST);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        // student
        Model model = new ModelManager(getTypicalStudentsAddressBook(), new UserPrefs());
        model.updateCurrentListType(Model.ListType.STUDENT_LIST);
        showStudentAtIndex(model, INDEX_FIRST_PERSON);

        Student studentInFilteredList = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Student editedStudent = new StudentBuilder(studentInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
                new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.updateCurrentListType(Model.ListType.STUDENT_LIST);
        expectedModel.setPerson(model.getFilteredStudentList().get(0), editedStudent);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);

        // tutor
        model = new ModelManager(getTypicalTutorsAddressBook(), new UserPrefs());
        model.updateCurrentListType(Model.ListType.TUTOR_LIST);
        showTutorAtIndex(model, INDEX_FIRST_PERSON);

        Tutor tutorInFilteredList = model.getFilteredTutorList().get(INDEX_FIRST_PERSON.getZeroBased());
        Tutor editedTutor = new TutorBuilder(tutorInFilteredList).withName(VALID_NAME_BOB).build();
        editCommand = new EditCommand(INDEX_FIRST_PERSON,
                new EditTutorDescriptorBuilder().withName(VALID_NAME_BOB).build());

        expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedTutor);

        expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.updateCurrentListType(Model.ListType.TUTOR_LIST);
        expectedModel.setPerson(model.getFilteredTutorList().get(0), editedTutor);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);

        // class
        model = new ModelManager(getTypicalTuitionClassesAddressBook(), new UserPrefs());
        model.updateCurrentListType(Model.ListType.TUITIONCLASS_LIST);
        showTuitionClassAtIndex(model, INDEX_FIRST_PERSON);

        TuitionClass classInFilteredList = model.getFilteredTuitionClassList().get(INDEX_FIRST_PERSON.getZeroBased());
        TuitionClass editedClass = new TuitionClassBuilder(classInFilteredList).withName(VALID_NAME_BOB).build();
        editCommand = new EditCommand(INDEX_FIRST_PERSON,
                new EditTuitionClassDescriptorBuilder().withName(VALID_NAME_BOB).build());

        expectedMessage = String.format(EditCommand.MESSAGE_EDIT_CLASS_SUCCESS, editedClass);

        expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.updateCurrentListType(Model.ListType.TUITIONCLASS_LIST);
        expectedModel.setTuitionClass(model.getFilteredTuitionClassList().get(0), editedClass);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        // student
        Model model = new ModelManager(getTypicalStudentsAddressBook(), new UserPrefs());
        model.updateCurrentListType(Model.ListType.STUDENT_LIST);

        Student firstStudent = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditStudentDescriptor studentDescriptor = new EditStudentDescriptorBuilder(firstStudent).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_PERSON, studentDescriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);

        // tutor
        model = new ModelManager(getTypicalTutorsAddressBook(), new UserPrefs());
        model.updateCurrentListType(Model.ListType.TUTOR_LIST);

        Tutor firstTutor = model.getFilteredTutorList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditTutorDescriptor tutorDescriptor = new EditTutorDescriptorBuilder(firstTutor).build();
        editCommand = new EditCommand(INDEX_SECOND_PERSON, tutorDescriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);

        // class
        model = new ModelManager(getTypicalTuitionClassesAddressBook(), new UserPrefs());
        model.updateCurrentListType(Model.ListType.TUITIONCLASS_LIST);

        TuitionClass firstClass = model.getFilteredTuitionClassList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditTuitionClassDescriptor classDescriptor = new EditTuitionClassDescriptorBuilder(firstClass).build();
        editCommand = new EditCommand(INDEX_SECOND_PERSON, classDescriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_CLASS);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        Model model = new ModelManager(getTypicalStudentsAddressBook(), new UserPrefs());
        model.updateCurrentListType(Model.ListType.STUDENT_LIST);

        showStudentAtIndex(model, INDEX_FIRST_PERSON);

        Student studentInList = model.getAddressBook().getStudentList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
                new EditStudentDescriptorBuilder(studentInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);

        // tutor
        model = new ModelManager(getTypicalTutorsAddressBook(), new UserPrefs());
        model.updateCurrentListType(Model.ListType.TUTOR_LIST);

        showTutorAtIndex(model, INDEX_FIRST_PERSON);

        Tutor tutorInList = model.getAddressBook().getTutorList().get(INDEX_SECOND_PERSON.getZeroBased());
        editCommand = new EditCommand(INDEX_FIRST_PERSON,
                new EditTutorDescriptorBuilder(tutorInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);

        // class
        model = new ModelManager(getTypicalTuitionClassesAddressBook(), new UserPrefs());
        model.updateCurrentListType(Model.ListType.TUITIONCLASS_LIST);

        showTuitionClassAtIndex(model, INDEX_FIRST_PERSON);

        TuitionClass classInList = model.getAddressBook().getTuitionClassList().get(INDEX_SECOND_PERSON.getZeroBased());
        editCommand = new EditCommand(INDEX_FIRST_PERSON,
                new EditTuitionClassDescriptorBuilder(classInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_CLASS);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Model model = new ModelManager(getTypicalStudentsAddressBook(), new UserPrefs());
        model.updateCurrentListType(Model.ListType.STUDENT_LIST);
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        EditStudentDescriptor studentDescriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, studentDescriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);

        model = new ModelManager(getTypicalTutorsAddressBook(), new UserPrefs());
        model.updateCurrentListType(Model.ListType.TUTOR_LIST);
        outOfBoundIndex = Index.fromOneBased(model.getFilteredTutorList().size() + 1);
        EditTutorDescriptor tutorDescriptor = new EditTutorDescriptorBuilder().withName(VALID_NAME_BOB).build();
        editCommand = new EditCommand(outOfBoundIndex, tutorDescriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);

        model = new ModelManager(getTypicalTuitionClassesAddressBook(), new UserPrefs());
        model.updateCurrentListType(Model.ListType.TUITIONCLASS_LIST);
        outOfBoundIndex = Index.fromOneBased(model.getFilteredTuitionClassList().size() + 1);
        EditTuitionClassDescriptor classDescriptor =
                new EditTuitionClassDescriptorBuilder().withName(VALID_NAME_BOB).build();
        editCommand = new EditCommand(outOfBoundIndex, classDescriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        Model model = new ModelManager(getTypicalStudentsAddressBook(), new UserPrefs());
        model.updateCurrentListType(Model.ListType.STUDENT_LIST);
        showStudentAtIndex(model, INDEX_FIRST_PERSON);
        final Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getStudentList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);

        model = new ModelManager(getTypicalTutorsAddressBook(), new UserPrefs());
        model.updateCurrentListType(Model.ListType.TUTOR_LIST);
        showTutorAtIndex(model, INDEX_FIRST_PERSON);
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getTutorList().size());

        editCommand = new EditCommand(outOfBoundIndex,
                new EditTutorDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);

        model = new ModelManager(getTypicalTuitionClassesAddressBook(), new UserPrefs());
        model.updateCurrentListType(Model.ListType.TUITIONCLASS_LIST);
        showTuitionClassAtIndex(model, INDEX_FIRST_PERSON);
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getTuitionClassList().size());

        editCommand = new EditCommand(outOfBoundIndex,
                new EditTuitionClassDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        EditCommand standardCommand = new EditCommand(INDEX_FIRST_PERSON, DESC_AMY_STUDENT);

        // same values -> returns true
        EditStudentDescriptor copyStudentDescriptor = new EditStudentDescriptor(DESC_AMY_STUDENT);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_PERSON, copyStudentDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_PERSON, DESC_AMY_STUDENT)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_PERSON, DESC_BOB_STUDENT)));

        standardCommand = new EditCommand(INDEX_FIRST_PERSON, DESC_AMY_TUTOR);

        // same values -> returns true
        EditTutorDescriptor copyTutorDescriptor = new EditTutorDescriptor(DESC_AMY_TUTOR);
        commandWithSameValues = new EditCommand(INDEX_FIRST_PERSON, copyTutorDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_PERSON, DESC_AMY_TUTOR)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_PERSON, DESC_BOB_TUTOR)));

        standardCommand = new EditCommand(INDEX_FIRST_PERSON, DESC_AMY_CLASS);

        // same values -> returns true
        EditTuitionClassDescriptor copyClassDescriptor = new EditTuitionClassDescriptor(DESC_AMY_CLASS);
        commandWithSameValues = new EditCommand(INDEX_FIRST_PERSON, copyClassDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_PERSON, DESC_AMY_CLASS)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_PERSON, DESC_BOB_CLASS)));

        // between student edit and tutor edit
        standardCommand = new EditCommand(INDEX_FIRST_PERSON, DESC_AMY_STUDENT);
        commandWithSameValues = new EditCommand(INDEX_FIRST_PERSON, copyTutorDescriptor);
        assertFalse(standardCommand.equals(commandWithSameValues));

        // between student edit and class edit
        standardCommand = new EditCommand(INDEX_FIRST_PERSON, DESC_AMY_STUDENT);
        commandWithSameValues = new EditCommand(INDEX_FIRST_PERSON, copyClassDescriptor);
        assertFalse(standardCommand.equals(commandWithSameValues));

        // between tutor edit and student edit
        standardCommand = new EditCommand(INDEX_FIRST_PERSON, DESC_AMY_TUTOR);
        commandWithSameValues = new EditCommand(INDEX_FIRST_PERSON, copyStudentDescriptor);
        assertFalse(standardCommand.equals(commandWithSameValues));

        // between tutor edit and class edit
        standardCommand = new EditCommand(INDEX_FIRST_PERSON, DESC_AMY_TUTOR);
        commandWithSameValues = new EditCommand(INDEX_FIRST_PERSON, copyClassDescriptor);
        assertFalse(standardCommand.equals(commandWithSameValues));

        // between class edit and student edit
        standardCommand = new EditCommand(INDEX_FIRST_PERSON, DESC_AMY_CLASS);
        commandWithSameValues = new EditCommand(INDEX_FIRST_PERSON, copyStudentDescriptor);
        assertFalse(standardCommand.equals(commandWithSameValues));

        // between class edit and tutor edit
        standardCommand = new EditCommand(INDEX_FIRST_PERSON, DESC_AMY_CLASS);
        commandWithSameValues = new EditCommand(INDEX_FIRST_PERSON, copyTutorDescriptor);
        assertFalse(standardCommand.equals(commandWithSameValues));


    }

}
