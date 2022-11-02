package seedu.masslinkers.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.masslinkers.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.masslinkers.testutil.Assert.assertThrows;
import static seedu.masslinkers.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.masslinkers.testutil.TypicalStudents.BOB;
import static seedu.masslinkers.testutil.TypicalStudents.getTypicalMassLinkers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import seedu.masslinkers.commons.core.Messages;
import seedu.masslinkers.commons.core.index.Index;
import seedu.masslinkers.logic.commands.exceptions.CommandException;
import seedu.masslinkers.model.Model;
import seedu.masslinkers.model.ModelManager;
import seedu.masslinkers.model.UserPrefs;
import seedu.masslinkers.model.student.Mod;
import seedu.masslinkers.model.student.Student;
import seedu.masslinkers.testutil.StudentBuilder;
//@author jonasgwt
public class ModAddCommandTest {

    private static final Mod VALID_MOD_CS2100 = new Mod("CS2100", false);
    private static final Mod VALID_MOD_CS2101 = new Mod("CS2101", false);
    private static Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalMassLinkers(), new UserPrefs());
    }

    /**
     * Test constructor to throw an exception when null index is provided.
     */
    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModAddCommand(null, FXCollections.observableArrayList()));
    }

    /**
     * Test constructor to throw an exception when null mods is provided.
     */
    @Test
    public void constructor_nullMods_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModAddCommand(INDEX_FIRST_STUDENT, null));
    }

    /**
     * Test the {@code execute} method to add mods to a student.
     */
    @Test
    public void execute_saveToModels_success() throws CommandException {

        // adds a test student to model
        Student toAdd = new StudentBuilder(BOB).withMods(VALID_MOD_CS2100.getModName()).build();
        model.addStudent(toAdd);

        // execute ModAddCommand on the test student
        Index indexLastStudent = Index.fromOneBased(model.getFilteredStudentList().size());
        ModAddCommand commandToExecute = new ModAddCommand(indexLastStudent,
                FXCollections.singletonObservableList(VALID_MOD_CS2101));
        CommandResult commandResult = commandToExecute.execute(model);
        // get the edited student from ModAddCommand
        Student editedStudent = model.getFilteredStudentList().get(indexLastStudent.getZeroBased());

        // expected edited student
        Student editedStudentExpected = new StudentBuilder(BOB)
                .withMods(VALID_MOD_CS2100.getModName(), VALID_MOD_CS2101.getModName())
                .build();

        assertEquals(String.format(ModAddCommand.MESSAGE_SUCCESS, editedStudent), commandResult.getFeedbackToUser());
        assertEquals(editedStudent, editedStudentExpected);
    }

    /**
     * Test the {@code execute} method to add a pre-existing mod.
     */
    @Test
    public void execute_saveDuplicateMods_success() throws CommandException {

        // adds a test student to model
        Student toAdd = new StudentBuilder(BOB).withMods(VALID_MOD_CS2100.getModName()).build();
        model.addStudent(toAdd);

        // execute ModAddCommand on the test student with existing mod
        Index indexLastStudent = Index.fromOneBased(model.getFilteredStudentList().size());
        ModAddCommand commandToExecute = new ModAddCommand(indexLastStudent,
                FXCollections.singletonObservableList(VALID_MOD_CS2100));
        CommandResult commandResult = commandToExecute.execute(model);
        // get the edited student from ModAddCommand
        Student editedStudent = model.getFilteredStudentList().get(indexLastStudent.getZeroBased());

        // expected edited student
        Student editedStudentExpected = new StudentBuilder(BOB)
                .withMods(VALID_MOD_CS2100.getModName())
                .build();

        assertEquals(String.format(ModAddCommand.MESSAGE_SUCCESS, editedStudent), commandResult.getFeedbackToUser());
        assertEquals(editedStudent, editedStudentExpected);
    }

    /**
     * Test the {@code execute} method to throw an exception when index provided
     * is out of bounds.
     */
    @Test
    public void execute_indexOutOfBounds_throwsCommandException() {
        Index indexOutOfBounds = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        ModAddCommand invalidCommand = new ModAddCommand(indexOutOfBounds,
                FXCollections.singletonObservableList(VALID_MOD_CS2100));
        assertCommandFailure(invalidCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

}
