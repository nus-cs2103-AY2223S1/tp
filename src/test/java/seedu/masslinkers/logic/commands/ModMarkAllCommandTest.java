package seedu.masslinkers.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.masslinkers.testutil.TypicalStudents.AMY;
import static seedu.masslinkers.testutil.TypicalStudents.BOB;
import static seedu.masslinkers.testutil.TypicalStudents.getTypicalMassLinkers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.masslinkers.logic.commands.exceptions.CommandException;
import seedu.masslinkers.model.Model;
import seedu.masslinkers.model.ModelManager;
import seedu.masslinkers.model.UserPrefs;
import seedu.masslinkers.model.student.Mod;
import seedu.masslinkers.model.student.Student;
import seedu.masslinkers.testutil.StudentBuilder;

//@@author carriezhengjr
/**
 * Test class for ModMarkCommand.
 */
public class ModMarkAllCommandTest {

    private static final Mod VALID_MOD_CS2100_TAKING = new Mod("CS2100", false);
    private static final Mod VALID_MOD_CS2101_TAKING = new Mod("CS2101", false);
    private static final Mod VALID_MOD_CS2103_TAKING = new Mod("CS2103", false);
    private static Model model;

    /**
     * Sets up the model before each test.
     */
    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalMassLinkers(), new UserPrefs());
    }

    /**
     * Tests the behaviour of ModMarkAllCommand when the student wants to mark all mods of every batchmate,
     * where all mods initially have "is taking" as mod status.
     *
     * @throws CommandException If an error occurs during execution of ModMarkAllCommand.
     */
    @Test
    public void execute_markAllTaking_success() throws CommandException {

        Student batchmate1 = new StudentBuilder(AMY)
                .withMods(
                        VALID_MOD_CS2100_TAKING.getModName(),
                        VALID_MOD_CS2101_TAKING.getModName())
                .build();
        Student batchmate2 = new StudentBuilder(BOB)
                .withMods(
                        VALID_MOD_CS2100_TAKING.getModName(),
                        VALID_MOD_CS2103_TAKING.getModName())
                .build();
        model.addStudent(batchmate1);
        model.addStudent(batchmate2);

        ModMarkAllCommand commandToExecute = new ModMarkAllCommand();
        CommandResult commandResult = commandToExecute.execute(model);

        ObservableList<Mod> mods1 = batchmate1.getMods();
        for (Mod mod : mods1) {
            boolean expectedModStatus1 = true;
            boolean actualModStatus1 = mod.getModStatus();
            assertEquals(expectedModStatus1, actualModStatus1);
        }

        ObservableList<Mod> mods2 = batchmate2.getMods();
        for (Mod mod : mods2) {
            boolean expectedModStatus2 = true;
            boolean actualModStatus2 = mod.getModStatus();
            assertEquals(expectedModStatus2, actualModStatus2);
        }

        String expectedMsg = String.format(ModMarkAllCommand.MESSAGE_SUCCESS);
        String actualMsg = commandResult.getFeedbackToUser();
        assertEquals(expectedMsg, actualMsg);
    }

}
