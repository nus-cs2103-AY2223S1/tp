package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_STUDENT;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * EditCommand.
 */
public class AddTagCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Set<Tag> singleTagSet = Stream.of(VALID_TAG_FRIEND).map(Tag::new).collect(Collectors.toSet());
    private Set<Tag> multiTagSet = Stream.of(VALID_TAG_FRIEND, VALID_TAG_STUDENT).map(Tag::new)
            .collect(Collectors.toSet());
    private Set<Tag> multiTagSetReversed = Stream.of(VALID_TAG_STUDENT, VALID_TAG_FRIEND).map(Tag::new)
            .collect(Collectors.toSet());

    @Test
    public void execute_addSingleTagUnfilteredList_success() {
        Index indexLaststudent = Index.fromOneBased(model.getFilteredStudentList().size());
        Student lastStudent = model.getFilteredStudentList().get(indexLaststudent.getZeroBased());

        StudentBuilder studentInList = new StudentBuilder(lastStudent);
        Student editedStudentBase = studentInList.withTags(VALID_TAG_HUSBAND).build();
        Student editedStudentExpected = studentInList.withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

        Model baseModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        baseModel.setStudent(lastStudent, editedStudentBase);
        AddTagCommand addTagCommand = new AddTagCommand(indexLaststudent, singleTagSet);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(lastStudent, editedStudentExpected);
        String expectedMessage = String.format(
                AddTagCommand.MESSAGE_SINGLE_ADD_TAGS_SUCCESS,
                AddTagCommand.tagSetToSting(singleTagSet),
                editedStudentBase.getName());

        assertCommandSuccess(addTagCommand, baseModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addMultiTagUnfilteredList_success() {
        Index indexLaststudent = Index.fromOneBased(model.getFilteredStudentList().size());
        Student lastStudent = model.getFilteredStudentList().get(indexLaststudent.getZeroBased());

        StudentBuilder studentInList = new StudentBuilder(lastStudent);
        Student editedStudentBase = studentInList.withTags(VALID_TAG_HUSBAND).build();
        Student editedStudentExpected = studentInList.withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND, VALID_TAG_STUDENT)
                .build();

        Model baseModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        baseModel.setStudent(lastStudent, editedStudentBase);
        AddTagCommand addTagCommand = new AddTagCommand(indexLaststudent, multiTagSet);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(lastStudent, editedStudentExpected);
        String expectedMessage = String.format(
                AddTagCommand.MESSAGE_SINGLE_ADD_TAGS_SUCCESS,
                AddTagCommand.tagSetToSting(multiTagSet),
                editedStudentBase.getName());

        assertCommandSuccess(addTagCommand, baseModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addSingleTagAllUnfilteredList_success() {
        StudentBuilder studentInList;
        Student editedStudentBase;
        Student editedStudentExpected;

        Model baseModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        String expectedMessage = String.format(
                AddTagCommand.MESSAGE_MULTI_ADD_TAGS_SUCCESS,
                AddTagCommand.tagSetToSting(singleTagSet),
                model.getFilteredStudentList().size());

        for (Student student : model.getFilteredStudentList()) {
            studentInList = new StudentBuilder(student);
            editedStudentBase = studentInList.withTags(VALID_TAG_HUSBAND).build();
            baseModel.setStudent(student, editedStudentBase);
            editedStudentExpected = studentInList.withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
            expectedModel.setStudent(student, editedStudentExpected);
        }

        AddTagCommand addTagCommand = new AddTagCommand(singleTagSet);
        assertCommandSuccess(addTagCommand, baseModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addMultiTagAllUnfilteredList_success() {
        StudentBuilder studentInList;
        Student editedStudentBase;
        Student editedStudentExpected;

        Model baseModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        String expectedMessage = String.format(
                AddTagCommand.MESSAGE_MULTI_ADD_TAGS_SUCCESS,
                AddTagCommand.tagSetToSting(multiTagSet),
                model.getFilteredStudentList().size());

        for (Student student : model.getFilteredStudentList()) {
            studentInList = new StudentBuilder(student);
            editedStudentBase = studentInList.withTags(VALID_TAG_HUSBAND).build();
            baseModel.setStudent(student, editedStudentBase);
            editedStudentExpected = studentInList.withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND, VALID_TAG_STUDENT)
                    .build();
            expectedModel.setStudent(student, editedStudentExpected);
        }

        AddTagCommand addTagCommand = new AddTagCommand(multiTagSet);
        assertCommandSuccess(addTagCommand, baseModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addDuplicateTagUnfilteredList_success() {
        Index indexLaststudent = Index.fromOneBased(model.getFilteredStudentList().size());
        Student lastStudent = model.getFilteredStudentList().get(indexLaststudent.getZeroBased());

        StudentBuilder studentInList = new StudentBuilder(lastStudent);
        Student editedStudentBase = studentInList.withTags(VALID_TAG_FRIEND).build();
        Student editedStudentModel = studentInList.withTags(VALID_TAG_FRIEND, VALID_TAG_STUDENT).build();

        Model baseModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        baseModel.setStudent(lastStudent, editedStudentBase);
        AddTagCommand addTagCommand = new AddTagCommand(indexLaststudent, multiTagSet);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(lastStudent, editedStudentModel);
        String expectedMessage = String.format(
                AddTagCommand.MESSAGE_SINGLE_ADD_TAGS_SUCCESS,
                AddTagCommand.tagSetToSting(multiTagSet),
                editedStudentBase.getName());

        assertCommandSuccess(addTagCommand, baseModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addToNewSingleTagUnfilteredList_success() {
        Index indexLaststudent = Index.fromOneBased(model.getFilteredStudentList().size());
        Student lastStudent = model.getFilteredStudentList().get(indexLaststudent.getZeroBased());

        StudentBuilder studentInList = new StudentBuilder(lastStudent);
        Student editedStudentBase = studentInList.withTags().build();
        Student editedStudentModel = studentInList.withTags(VALID_TAG_FRIEND).build();

        Model baseModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        baseModel.setStudent(lastStudent, editedStudentBase);
        AddTagCommand addTagCommand = new AddTagCommand(indexLaststudent, singleTagSet);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(lastStudent, editedStudentModel);
        String expectedMessage = String.format(
                AddTagCommand.MESSAGE_SINGLE_ADD_TAGS_SUCCESS,
                AddTagCommand.tagSetToSting(singleTagSet),
                editedStudentBase.getName());

        assertCommandSuccess(addTagCommand, baseModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addSingleTagAllFilteredList_success() {
        showStudentAtIndex(model, INDEX_SECOND_STUDENT);
        StudentBuilder studentInList;
        Student editedStudentBase;
        Student editedStudentExpected;

        Model baseModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        String expectedMessage = String.format(
                AddTagCommand.MESSAGE_MULTI_ADD_TAGS_SUCCESS,
                AddTagCommand.tagSetToSting(singleTagSet),
                model.getFilteredStudentList().size());

        for (Student student : model.getFilteredStudentList()) {
            studentInList = new StudentBuilder(student);
            editedStudentBase = studentInList.withTags(VALID_TAG_HUSBAND).build();
            baseModel.setStudent(student, editedStudentBase);
            editedStudentExpected = studentInList.withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
            expectedModel.setStudent(student, editedStudentExpected);
        }

        showStudentAtIndex(baseModel, INDEX_SECOND_STUDENT);
        AddTagCommand addTagCommand = new AddTagCommand(singleTagSet);
        assertCommandSuccess(addTagCommand, baseModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addMultiTagFilteredList_success() {
        showStudentAtIndex(model, INDEX_SECOND_STUDENT);
        Index indexLaststudent = Index.fromOneBased(model.getFilteredStudentList().size());
        Student lastStudent = model.getFilteredStudentList().get(indexLaststudent.getZeroBased());

        StudentBuilder studentInList = new StudentBuilder(lastStudent);
        Student editedStudentBase = studentInList.withTags(VALID_TAG_HUSBAND).build();
        Student editedStudentExpected = studentInList.withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

        Model baseModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        baseModel.setStudent(lastStudent, editedStudentBase);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(lastStudent, editedStudentExpected);
        String expectedMessage = String.format(
                AddTagCommand.MESSAGE_SINGLE_ADD_TAGS_SUCCESS,
                AddTagCommand.tagSetToSting(singleTagSet),
                editedStudentBase.getName());

        showStudentAtIndex(baseModel, INDEX_SECOND_STUDENT);
        AddTagCommand addTagCommand = new AddTagCommand(indexLaststudent, singleTagSet);

        assertCommandSuccess(addTagCommand, baseModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidstudentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        AddTagCommand addTagCommand = new AddTagCommand(outOfBoundIndex, singleTagSet);

        assertCommandFailure(addTagCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidstudentIndexFilteredList_failure() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
        Index outOfBoundIndex = INDEX_SECOND_STUDENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getStudentList().size());

        AddTagCommand addTagCommand = new AddTagCommand(outOfBoundIndex, singleTagSet);

        assertCommandFailure(addTagCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final AddTagCommand standardSingleCommand = new AddTagCommand(INDEX_FIRST_STUDENT, singleTagSet);
        final AddTagCommand standardMultiCommand = new AddTagCommand(INDEX_FIRST_STUDENT, multiTagSetReversed);

        // same values -> returns true
        AddTagCommand commandWithSameValues = new AddTagCommand(INDEX_FIRST_STUDENT, singleTagSet);
        assertTrue(standardSingleCommand.equals(commandWithSameValues));

        // same values but different order -> returns true
        AddTagCommand commandWithSameMultiValues = new AddTagCommand(INDEX_FIRST_STUDENT, multiTagSetReversed);
        assertTrue(standardMultiCommand.equals(commandWithSameMultiValues));

        // same object -> returns true
        assertTrue(standardSingleCommand.equals(standardSingleCommand));

        // null -> returns false
        assertFalse(standardSingleCommand.equals(null));

        // different types -> returns false
        assertFalse(standardSingleCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardSingleCommand.equals(new AddTagCommand(INDEX_SECOND_STUDENT, singleTagSet)));

        // different descriptor -> returns false
        assertFalse(standardSingleCommand.equals(new AddTagCommand(INDEX_FIRST_STUDENT, multiTagSet)));
    }

    public static void main(String[] args) {
        AddTagCommandTest test = new AddTagCommandTest();
        test.execute_invalidstudentIndexFilteredList_failure();
    }
}
