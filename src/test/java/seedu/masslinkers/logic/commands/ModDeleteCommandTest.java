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
//@@author ElijahS67
/**
 * Test class for ModDeleteCommand.
 */
public class ModDeleteCommandTest {

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
     * Tests the behaviour of ModDeleteCommand when index is not entered.
     */
    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModDeleteCommand(null, FXCollections.observableArrayList()));
    }

    /**
     * Tests the behaviour of ModDeleteCommand when mod is not entered.
     */
    @Test
    public void constructor_nullMods_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModDeleteCommand(INDEX_FIRST_STUDENT, null));
    }

    /**
     * Tests the behaviour of ModDeleteCommand when the student wants to delete 1 existing mod
     * from the list of modules of a batchmate.
     *
     * @throws CommandException If an error occurs during execution of ModDeleteCommand.
     */
    @Test
    public void execute_deleteOneMod_success() throws CommandException {

        Student batchmate = new StudentBuilder(BOB)
                .withMods(VALID_MOD_CS2100.getModName(), VALID_MOD_CS2101.getModName())
                .build();
        model.addStudent(batchmate);

        Index indexLastStudent = Index.fromOneBased(model.getFilteredStudentList().size());
        ModDeleteCommand commandToExecute = new ModDeleteCommand(indexLastStudent,
                FXCollections.singletonObservableList(VALID_MOD_CS2100));
        CommandResult commandResult = commandToExecute.execute(model);

        String actualModList = batchmate.getMods().toString();
        String expectedModList = "[[CS2101: false]]";

        assertEquals(actualModList, expectedModList);
        assertEquals(ModDeleteCommand.MESSAGE_SUCCESS, commandResult.getFeedbackToUser());
    }

    /**
     * Tests the behaviour of ModDeleteCommand when the student wants to delete 2 existing mods
     * from the list of modules of a batchmate.
     *
     * @throws CommandException If an error occurs during execution of ModDeleteCommand.
     */
    @Test
    public void execute_deleteMultipleMods_success() throws CommandException {

        Student batchmate = new StudentBuilder(BOB).withMods(
                VALID_MOD_CS2100.getModName(),
                VALID_MOD_CS2103.getModName(),
                VALID_MOD_CS2101.getModName())
                .build();
        model.addStudent(batchmate);

        Index indexLastStudent = Index.fromOneBased(model.getFilteredStudentList().size());
        ModDeleteCommand commandToExecute = new ModDeleteCommand(indexLastStudent,
                FXCollections.observableArrayList(VALID_MOD_CS2101, VALID_MOD_CS2100));
        CommandResult commandResult = commandToExecute.execute(model);

        String actualModList = batchmate.getMods().toString();
        String expectedModList = "[[CS2103: false]]";

        assertEquals(actualModList, expectedModList);
        assertEquals(ModDeleteCommand.MESSAGE_SUCCESS, commandResult.getFeedbackToUser());
    }

    /**
     * Tests the behaviour of ModDeleteCommand when the student wants to delete 1 non-existing mod
     * from the list of modules of a batchmate.
     */
    @Test
    public void execute_delete1NonExistingMod_throwsCommandException() {

        Student batchmate = new StudentBuilder(BOB).withMods(
                        VALID_MOD_CS2100.getModName(),
                        VALID_MOD_CS2103.getModName(),
                        VALID_MOD_CS2101.getModName())
                .build();
        model.addStudent(batchmate);

        Index indexLastStudent = Index.fromOneBased(model.getFilteredStudentList().size());

        try {
            ModDeleteCommand commandToExecute = new ModDeleteCommand(indexLastStudent,
                    FXCollections.observableArrayList(MOD_NOT_FOUND_CS2105));
            commandToExecute.execute(model);
            fail(); // Test should not reach this line.
        } catch (CommandException e) {
            assertEquals(ModDeleteCommand.MESSAGE_INVALID_MOD, e.getMessage());
        }

    }

    /**
     * Tests the behaviour of ModDeleteCommand when the student wants to delete multiple mods containing
     * a mix of existing and non-existing mods from the list of modules of a batchmate.
     */
    @Test
    public void execute_deleteMixExistingAndNonExistingMods_throwsCommandException() {

        Student batchmate = new StudentBuilder(BOB).withMods(
                        VALID_MOD_CS2100.getModName(),
                        VALID_MOD_CS2103.getModName(),
                        VALID_MOD_CS2101.getModName())
                .build();
        model.addStudent(batchmate);

        Index indexLastStudent = Index.fromOneBased(model.getFilteredStudentList().size());

        try {
            ModDeleteCommand commandToExecute = new ModDeleteCommand(indexLastStudent,
                    FXCollections.observableArrayList(VALID_MOD_CS2101, VALID_MOD_CS2103, MOD_NOT_FOUND_CS2105));
            commandToExecute.execute(model);
            fail(); // Test should not reach this line. Goes to Catch block.
        } catch (CommandException e) {
            assertEquals(ModDeleteCommand.MESSAGE_INVALID_MOD, e.getMessage());
        }

    }

    /**
     * Tests the behaviour of ModDeleteCommand when index is out of range.
     *
     * @throws CommandException If an error occurs during execution of ModDeleteCommand.
     */
    @Test
    public void execute_indexOutOfBounds_throwsCommandException() throws CommandException {
        Index indexOutOfBounds = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        ModDeleteCommand invalidCommand = new ModDeleteCommand(indexOutOfBounds,
                FXCollections.singletonObservableList(VALID_MOD_CS2100));
        assertCommandFailure(invalidCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

}
