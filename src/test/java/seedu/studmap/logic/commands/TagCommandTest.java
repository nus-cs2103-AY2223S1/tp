package seedu.studmap.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.studmap.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.studmap.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.studmap.logic.commands.CommandTestUtil.VALID_TAG_STUDENT;
import static seedu.studmap.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.studmap.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.studmap.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.studmap.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.studmap.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.studmap.testutil.TypicalStudents.getTypicalStudMap;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import seedu.studmap.commons.core.Messages;
import seedu.studmap.commons.core.index.AllIndexGenerator;
import seedu.studmap.commons.core.index.Index;
import seedu.studmap.commons.core.index.SingleIndexGenerator;
import seedu.studmap.commons.util.CollectionUtil;
import seedu.studmap.model.Model;
import seedu.studmap.model.ModelManager;
import seedu.studmap.model.StudMap;
import seedu.studmap.model.UserPrefs;
import seedu.studmap.model.student.Student;
import seedu.studmap.model.tag.Tag;
import seedu.studmap.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * EditCommand.
 */
public class TagCommandTest {

    private Model model = new ModelManager(getTypicalStudMap(), new UserPrefs());

    private Set<Tag> singleTagSet = Stream.of(VALID_TAG_FRIEND).map(Tag::new).collect(Collectors.toSet());
    private TagCommand.TagCommandStudentEditor singleTagAdder =
            new TagCommand.TagCommandStudentEditor(singleTagSet);

    private Set<Tag> multiTagSet =
            Stream.of(VALID_TAG_FRIEND, VALID_TAG_STUDENT).map(Tag::new).collect(Collectors.toSet());

    private TagCommand.TagCommandStudentEditor multiTagAdder =
            new TagCommand.TagCommandStudentEditor(multiTagSet);

    private Set<Tag> multiTagSetReversed =
            Stream.of(VALID_TAG_STUDENT, VALID_TAG_FRIEND).map(Tag::new).collect(Collectors.toSet());
    private TagCommand.TagCommandStudentEditor multiTagAdderReversed =
            new TagCommand.TagCommandStudentEditor(multiTagSetReversed);

    public static void main(String[] args) {
        TagCommandTest test = new TagCommandTest();
        test.execute_invalidstudentIndexFilteredList_failure();
    }

    @Test
    public void execute_addSingleTagUnfilteredList_success() {
        Index indexLaststudent = Index.fromOneBased(model.getFilteredStudentList().size());
        Student lastStudent = model.getFilteredStudentList().get(indexLaststudent.getZeroBased());

        StudentBuilder studentInList = new StudentBuilder(lastStudent);
        Student editedStudentBase = studentInList.withTags(VALID_TAG_HUSBAND).build();
        Student editedStudentExpected = studentInList.withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

        Model baseModel = new ModelManager(new StudMap(model.getStudMap()), new UserPrefs());
        baseModel.setStudent(lastStudent, editedStudentBase);
        TagCommand tagCommand = new TagCommand(new SingleIndexGenerator(indexLaststudent),
                singleTagAdder);

        Model expectedModel = new ModelManager(new StudMap(model.getStudMap()), new UserPrefs());
        expectedModel.setStudent(lastStudent, editedStudentExpected);
        String expectedMessage = String.format(
                TagCommand.MESSAGE_SINGLE_ADD_TAGS_SUCCESS,
                CollectionUtil.collectionToString(singleTagSet),
                editedStudentBase.getName());

        assertCommandSuccess(tagCommand, baseModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addMultiTagUnfilteredList_success() {
        Index indexLaststudent = Index.fromOneBased(model.getFilteredStudentList().size());
        Student lastStudent = model.getFilteredStudentList().get(indexLaststudent.getZeroBased());

        StudentBuilder studentInList = new StudentBuilder(lastStudent);
        Student editedStudentBase = studentInList.withTags(VALID_TAG_HUSBAND).build();
        Student editedStudentExpected = studentInList.withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND, VALID_TAG_STUDENT)
                .build();

        Model baseModel = new ModelManager(new StudMap(model.getStudMap()), new UserPrefs());
        baseModel.setStudent(lastStudent, editedStudentBase);
        TagCommand tagCommand = new TagCommand(new SingleIndexGenerator(indexLaststudent),
                multiTagAdder);

        Model expectedModel = new ModelManager(new StudMap(model.getStudMap()), new UserPrefs());
        expectedModel.setStudent(lastStudent, editedStudentExpected);
        String expectedMessage = String.format(
                TagCommand.MESSAGE_SINGLE_ADD_TAGS_SUCCESS,
                CollectionUtil.collectionToString(multiTagSet),
                editedStudentBase.getName());

        assertCommandSuccess(tagCommand, baseModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addSingleTagAllUnfilteredList_success() {
        StudentBuilder studentInList;
        Student editedStudentBase;
        Student editedStudentExpected;

        Model baseModel = new ModelManager(new StudMap(model.getStudMap()), new UserPrefs());
        Model expectedModel = new ModelManager(new StudMap(model.getStudMap()), new UserPrefs());

        String expectedMessage = String.format(
                TagCommand.MESSAGE_MULTI_ADD_TAGS_SUCCESS,
                CollectionUtil.collectionToString(singleTagSet),
                model.getFilteredStudentList().size());

        for (Student student : model.getFilteredStudentList()) {
            studentInList = new StudentBuilder(student);
            editedStudentBase = studentInList.withTags(VALID_TAG_HUSBAND).build();
            baseModel.setStudent(student, editedStudentBase);
            editedStudentExpected = studentInList.withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
            expectedModel.setStudent(student, editedStudentExpected);
        }

        TagCommand tagCommand = new TagCommand(new AllIndexGenerator(), singleTagAdder);
        assertCommandSuccess(tagCommand, baseModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addMultiTagAllUnfilteredList_success() {
        StudentBuilder studentInList;
        Student editedStudentBase;
        Student editedStudentExpected;

        Model baseModel = new ModelManager(new StudMap(model.getStudMap()), new UserPrefs());
        Model expectedModel = new ModelManager(new StudMap(model.getStudMap()), new UserPrefs());

        String expectedMessage = String.format(
                TagCommand.MESSAGE_MULTI_ADD_TAGS_SUCCESS,
                CollectionUtil.collectionToString(multiTagSet),
                model.getFilteredStudentList().size());

        for (Student student : model.getFilteredStudentList()) {
            studentInList = new StudentBuilder(student);
            editedStudentBase = studentInList.withTags(VALID_TAG_HUSBAND).build();
            baseModel.setStudent(student, editedStudentBase);
            editedStudentExpected = studentInList.withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND, VALID_TAG_STUDENT)
                    .build();
            expectedModel.setStudent(student, editedStudentExpected);
        }

        TagCommand tagCommand = new TagCommand(new AllIndexGenerator(), multiTagAdder);
        assertCommandSuccess(tagCommand, baseModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addDuplicateTagUnfilteredList_success() {
        Index indexLaststudent = Index.fromOneBased(model.getFilteredStudentList().size());
        Student lastStudent = model.getFilteredStudentList().get(indexLaststudent.getZeroBased());

        StudentBuilder studentInList = new StudentBuilder(lastStudent);
        Student editedStudentBase = studentInList.withTags(VALID_TAG_FRIEND).build();
        Student editedStudentModel = studentInList.withTags(VALID_TAG_FRIEND, VALID_TAG_STUDENT).build();

        Model baseModel = new ModelManager(new StudMap(model.getStudMap()), new UserPrefs());
        baseModel.setStudent(lastStudent, editedStudentBase);
        TagCommand tagCommand = new TagCommand(new SingleIndexGenerator(indexLaststudent),
                multiTagAdder);

        Model expectedModel = new ModelManager(new StudMap(model.getStudMap()), new UserPrefs());
        expectedModel.setStudent(lastStudent, editedStudentModel);
        String expectedMessage = String.format(
                TagCommand.MESSAGE_SINGLE_ADD_TAGS_SUCCESS,
                CollectionUtil.collectionToString(multiTagSet),
                editedStudentBase.getName());

        assertCommandSuccess(tagCommand, baseModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addToNewSingleTagUnfilteredList_success() {
        Index indexLaststudent = Index.fromOneBased(model.getFilteredStudentList().size());
        Student lastStudent = model.getFilteredStudentList().get(indexLaststudent.getZeroBased());

        StudentBuilder studentInList = new StudentBuilder(lastStudent);
        Student editedStudentBase = studentInList.withTags().build();
        Student editedStudentModel = studentInList.withTags(VALID_TAG_FRIEND).build();

        Model baseModel = new ModelManager(new StudMap(model.getStudMap()), new UserPrefs());
        baseModel.setStudent(lastStudent, editedStudentBase);
        TagCommand tagCommand = new TagCommand(new SingleIndexGenerator(indexLaststudent),
                singleTagAdder);

        Model expectedModel = new ModelManager(new StudMap(model.getStudMap()), new UserPrefs());
        expectedModel.setStudent(lastStudent, editedStudentModel);
        String expectedMessage = String.format(
                TagCommand.MESSAGE_SINGLE_ADD_TAGS_SUCCESS,
                CollectionUtil.collectionToString(singleTagSet),
                editedStudentBase.getName());

        assertCommandSuccess(tagCommand, baseModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addSingleTagAllFilteredList_success() {
        showStudentAtIndex(model, INDEX_SECOND_STUDENT);
        StudentBuilder studentInList;
        Student editedStudentBase;
        Student editedStudentExpected;

        Model baseModel = new ModelManager(new StudMap(model.getStudMap()), new UserPrefs());
        Model expectedModel = new ModelManager(new StudMap(model.getStudMap()), new UserPrefs());

        String expectedMessage = String.format(
                TagCommand.MESSAGE_SINGLE_ADD_TAGS_SUCCESS,
                CollectionUtil.collectionToString(singleTagSet),
                model.getFilteredStudentList().get(0).getName());

        for (Student student : model.getFilteredStudentList()) {
            studentInList = new StudentBuilder(student);
            editedStudentBase = studentInList.withTags(VALID_TAG_HUSBAND).build();
            baseModel.setStudent(student, editedStudentBase);
            editedStudentExpected = studentInList.withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
            expectedModel.setStudent(student, editedStudentExpected);
        }

        showStudentAtIndex(baseModel, INDEX_SECOND_STUDENT);
        showStudentAtIndex(expectedModel, INDEX_SECOND_STUDENT);
        TagCommand tagCommand = new TagCommand(new AllIndexGenerator(), singleTagAdder);
        assertCommandSuccess(tagCommand, baseModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addMultiTagFilteredList_success() {
        showStudentAtIndex(model, INDEX_SECOND_STUDENT);
        Index indexLaststudent = Index.fromOneBased(model.getFilteredStudentList().size());
        Student lastStudent = model.getFilteredStudentList().get(indexLaststudent.getZeroBased());

        StudentBuilder studentInList = new StudentBuilder(lastStudent);
        Student editedStudentBase = studentInList.withTags(VALID_TAG_HUSBAND).build();
        Student editedStudentExpected = studentInList.withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

        Model baseModel = new ModelManager(new StudMap(model.getStudMap()), new UserPrefs());
        baseModel.setStudent(lastStudent, editedStudentBase);

        Model expectedModel = new ModelManager(new StudMap(model.getStudMap()), new UserPrefs());
        expectedModel.setStudent(lastStudent, editedStudentExpected);
        String expectedMessage = String.format(
                TagCommand.MESSAGE_SINGLE_ADD_TAGS_SUCCESS,
                CollectionUtil.collectionToString(singleTagSet),
                editedStudentBase.getName());

        showStudentAtIndex(baseModel, INDEX_SECOND_STUDENT);
        showStudentAtIndex(expectedModel, INDEX_SECOND_STUDENT);
        TagCommand tagCommand = new TagCommand(new SingleIndexGenerator(indexLaststudent),
                singleTagAdder);

        assertCommandSuccess(tagCommand, baseModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidstudentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        TagCommand tagCommand = new TagCommand(new SingleIndexGenerator(outOfBoundIndex),
                singleTagAdder);

        assertCommandFailure(tagCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of student map
     */
    @Test
    public void execute_invalidstudentIndexFilteredList_failure() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
        Index outOfBoundIndex = INDEX_SECOND_STUDENT;
        // ensures that outOfBoundIndex is still in bounds of student map list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getStudMap().getStudentList().size());

        TagCommand tagCommand = new TagCommand(new SingleIndexGenerator(outOfBoundIndex),
                singleTagAdder);

        assertCommandFailure(tagCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final TagCommand standardSingleCommand =
                new TagCommand(new SingleIndexGenerator(INDEX_FIRST_STUDENT), singleTagAdder);
        final TagCommand standardMultiCommand =
                new TagCommand(new SingleIndexGenerator(INDEX_FIRST_STUDENT), multiTagAdderReversed);

        // same values -> returns true
        TagCommand commandWithSameValues =
                new TagCommand(new SingleIndexGenerator(INDEX_FIRST_STUDENT), singleTagAdder);
        assertTrue(standardSingleCommand.equals(commandWithSameValues));

        // same values but different order -> returns true
        TagCommand commandWithSameMultiValues =
                new TagCommand(new SingleIndexGenerator(INDEX_FIRST_STUDENT), multiTagAdderReversed);
        assertTrue(standardMultiCommand.equals(commandWithSameMultiValues));

        // same object -> returns true
        assertTrue(standardSingleCommand.equals(standardSingleCommand));

        // null -> returns false
        assertFalse(standardSingleCommand.equals(null));

        // different types -> returns false
        assertFalse(standardSingleCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardSingleCommand.equals(
                new TagCommand(new SingleIndexGenerator(INDEX_SECOND_STUDENT), singleTagAdder)));

        // different descriptor -> returns false
        assertFalse(standardSingleCommand.equals(
                new TagCommand(new SingleIndexGenerator(INDEX_FIRST_STUDENT), multiTagAdder)));
    }
}
