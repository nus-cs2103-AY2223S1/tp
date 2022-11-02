package seedu.masslinkers.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
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
//@author carriezhengjr
/**
 * Test class for ModMarkCommand.
 */
public class ModMarkCommandTest {

    private static final Mod VALID_MOD_CS2100 = new Mod("CS2100", false);
    private static final Mod VALID_MOD_CS2101 = new Mod("CS2101", false);
    private static final Mod VALID_MOD_CS2103 = new Mod("CS2103", false);
    private static final Mod MOD_NOT_FOUND_CS2105 = new Mod("CS2105", false);
    private static Model model;

    /**
     * Sets up the model before each test.
     */
    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalMassLinkers(), new UserPrefs());
    }

    /**
     * Tests the behaviour of ModMarkCommand when index is not entered.
     */
    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModMarkCommand(null, FXCollections.observableArrayList()));
    }

    /**
     * Tests the behaviour of ModMarkCommand when mod is not entered.
     */
    @Test
    public void constructor_nullMods_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModMarkCommand(INDEX_FIRST_STUDENT, null));
    }

    /**
     * Tests the behaviour of ModMarkCommand when the student wants to mark 1 existing mod
     * in the list of modules of a batchmate.
     *
     * @throws CommandException If an error occurs during execution of ModMarkCommand.
     */
    @Test
    public void execute_markOneMod_success() throws CommandException {

        Student batchmate = new StudentBuilder(BOB).withMods(VALID_MOD_CS2100.getModName()).build();
        model.addStudent(batchmate);

        Index indexLastStudent = Index.fromOneBased(model.getFilteredStudentList().size());
        ModMarkCommand commandToExecute = new ModMarkCommand(indexLastStudent,
                FXCollections.singletonObservableList(VALID_MOD_CS2100));
        CommandResult commandResult = commandToExecute.execute(model);

        String actualModStatus = batchmate.getMods().get(0).toString();
        String expectedModStatus = "[CS2100: true]";

        assertEquals(expectedModStatus, actualModStatus);
        assertEquals(ModMarkCommand.MESSAGE_SUCCESS, commandResult.getFeedbackToUser());
    }

    /**
     * Tests the behaviour of ModMarkCommand when the student wants to mark 2 existing mods
     * in the list of modules of a batchmate.
     *
     * @throws CommandException If an error occurs during execution of ModMarkCommand.
     */
    @Test
    public void execute_markMultipleMod_success() throws CommandException {

        Student batchmate = new StudentBuilder(BOB).withMods(
                VALID_MOD_CS2100.getModName(),
                VALID_MOD_CS2103.getModName(),
                VALID_MOD_CS2101.getModName())
                .build();
        model.addStudent(batchmate);

        Index indexLastStudent = Index.fromOneBased(model.getFilteredStudentList().size());
        ModMarkCommand commandToExecute = new ModMarkCommand(indexLastStudent,
                FXCollections.observableArrayList(VALID_MOD_CS2101, VALID_MOD_CS2100));
        CommandResult commandResult = commandToExecute.execute(model);

        String actualFirstModStatus = batchmate.getMods().get(0).toString();
        String actualSecondModStatus = batchmate.getMods().get(1).toString();
        String actualThirdModStatus = batchmate.getMods().get(2).toString();
        String actualModStatus = actualFirstModStatus + actualSecondModStatus + actualThirdModStatus;

        String expectedModStatus = "[CS2103: false]" + "[CS2100: true]" + "[CS2101: true]";

        assertEquals(expectedModStatus, actualModStatus);
        assertEquals(ModMarkCommand.MESSAGE_SUCCESS, commandResult.getFeedbackToUser());
    }

    /**
     * Tests the behaviour of ModMarkCommand when the student wants to mark 1 non-existing mod
     * in the list of modules of a batchmate.
     */
    @Test
    public void execute_markNonExistingMod1_throwsCommandException() {

        Student batchmate = new StudentBuilder(BOB).withMods(
                        VALID_MOD_CS2100.getModName(),
                        VALID_MOD_CS2103.getModName(),
                        VALID_MOD_CS2101.getModName())
                .build();
        model.addStudent(batchmate);

        Index indexLastStudent = Index.fromOneBased(model.getFilteredStudentList().size());

        try {
            ModMarkCommand commandToExecute = new ModMarkCommand(indexLastStudent,
                    FXCollections.observableArrayList(MOD_NOT_FOUND_CS2105));
            commandToExecute.execute(model);
            fail(); // Test should not reach this line.
        } catch (CommandException e) {
            assertEquals(ModMarkCommand.MESSAGE_INVALID_MOD, e.getMessage());
        }

    }

    /**
     * Tests the behaviour of ModMarkCommand when the student wants to mark multiple mods containing
     * 1 non-existing mod in the list of modules of a batchmate.
     */
    @Test
    public void execute_markNonExistingMod2_throwsCommandException() {

        Student batchmate = new StudentBuilder(BOB).withMods(
                        VALID_MOD_CS2100.getModName(),
                        VALID_MOD_CS2103.getModName(),
                        VALID_MOD_CS2101.getModName())
                .build();
        model.addStudent(batchmate);

        Index indexLastStudent = Index.fromOneBased(model.getFilteredStudentList().size());

        try {
            ModMarkCommand commandToExecute = new ModMarkCommand(indexLastStudent,
                    FXCollections.observableArrayList(VALID_MOD_CS2101, VALID_MOD_CS2103, MOD_NOT_FOUND_CS2105));
            commandToExecute.execute(model);
            fail(); // Test should not reach this line.
        } catch (CommandException e) {
            assertEquals(ModMarkCommand.MESSAGE_INVALID_MOD, e.getMessage());
        }

    }

    /**
     * Tests the behaviour of ModMarkCommand when index is out of range.
     *
     * @throws CommandException If an error occurs during execution of ModMarkCommand.
     */
    @Test
    public void execute_indexOutOfBounds_throwsCommandException() throws CommandException {
        Index indexOutOfBounds = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        ModMarkCommand invalidCommand = new ModMarkCommand(indexOutOfBounds,
                FXCollections.singletonObservableList(VALID_MOD_CS2100));
        assertCommandFailure(invalidCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

}
