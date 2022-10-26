package seedu.masslinkers.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.masslinkers.testutil.Assert.assertThrows;
import static seedu.masslinkers.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.masslinkers.testutil.TypicalStudents.BOB;
import static seedu.masslinkers.testutil.TypicalStudents.BOB_WITHOUT_INTERESTS;
import static seedu.masslinkers.testutil.TypicalStudents.BOB_WITHOUT_TENNIS;
import static seedu.masslinkers.testutil.TypicalStudents.getTypicalMassLinkers;

import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.masslinkers.commons.core.index.Index;
import seedu.masslinkers.logic.commands.exceptions.CommandException;
import seedu.masslinkers.model.Model;
import seedu.masslinkers.model.ModelManager;
import seedu.masslinkers.model.UserPrefs;
import seedu.masslinkers.model.interest.Interest;
import seedu.masslinkers.model.student.Student;
import seedu.masslinkers.testutil.StudentBuilder;

public class DeleteInterestCommandTest {
    private static final Interest INTEREST_TENNIS = new Interest("tennis");
    private static final Interest INTEREST_NETFLIX = new Interest("netflix");
    private static final Interest INTEREST_ANIME = new Interest("anime");

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
        assertThrows(NullPointerException.class, () -> new DeleteInterestCommand(null,
                new HashSet<>()));
    }

    /**
     * Test constructor to throw an exception when null interest is provided.
     */
    @Test
    public void constructor_nullMods_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteInterestCommand(INDEX_FIRST_STUDENT, null));
    }

    /**
     * Tests the behaviour of DeleteInterestCommand when the student wants to delete 1 existing interest
     * from a batchmate.
     */
    @Test
    public void execute_deleteOneInterest_success() throws CommandException {

        // adds a test student to model (Bob has interests: tennis, netflix)
        Student toAdd = new StudentBuilder(BOB).build();
        model.addStudent(toAdd);

        // execute DeleteInterestCommand on the test student
        Index indexLastStudent = Index.fromOneBased(model.getFilteredStudentList().size());
        HashSet<Interest> interestsToBeDeleted = new HashSet<>();
        interestsToBeDeleted.add(INTEREST_TENNIS);
        DeleteInterestCommand commandToExecute = new DeleteInterestCommand(indexLastStudent,
                interestsToBeDeleted);
        CommandResult commandResult = commandToExecute.execute(model);
        // get the edited student from the model after executing DeleteInterestCommand
        Student editedStudent = model.getFilteredStudentList().get(indexLastStudent.getZeroBased());

        // expected edited student
        Student editedStudentExpected = new StudentBuilder(BOB_WITHOUT_TENNIS).build();

        assertEquals(String.format(DeleteInterestCommand.MESSAGE_SUCCESS, editedStudent),
                commandResult.getFeedbackToUser());
        assertEquals(editedStudent, editedStudentExpected);
    }

    /**
     * Tests the behaviour of DeleteInterestCommand when the student wants to delete multiple existing interests
     * from a batchmate.
     */
    @Test
    public void execute_deleteMultipleInterests_success() throws CommandException {

        // adds a test student to model (Bob has interests: tennis, netflix)
        Student toAdd = new StudentBuilder(BOB).build();
        model.addStudent(toAdd);

        // execute DeleteInterestCommand on the test student
        Index indexLastStudent = Index.fromOneBased(model.getFilteredStudentList().size());
        HashSet<Interest> interestsToBeDeleted = new HashSet<>();
        interestsToBeDeleted.add(INTEREST_TENNIS);
        interestsToBeDeleted.add(INTEREST_NETFLIX);

        DeleteInterestCommand commandToExecute = new DeleteInterestCommand(indexLastStudent,
                interestsToBeDeleted);
        CommandResult commandResult = commandToExecute.execute(model);
        // get the edited student from the model after executing DeleteInterestCommand
        Student editedStudent = model.getFilteredStudentList().get(indexLastStudent.getZeroBased());

        // expected edited student
        Student editedStudentExpected = new StudentBuilder(BOB_WITHOUT_INTERESTS).build();

        assertEquals(String.format(DeleteInterestCommand.MESSAGE_SUCCESS, editedStudent),
                commandResult.getFeedbackToUser());
        assertEquals(editedStudent, editedStudentExpected);
    }

    /**
     * Tests the behaviour of DeleteInterestCommand when the student wants to delete 1 non-existing interest
     * from a batchmate.
     */
    @Test
    public void execute_delete1NonExistingInterest_throwsCommandException() {

        // adds a test student to model (Bob has interests: tennis, netflix)
        Student toAdd = new StudentBuilder(BOB).build();
        model.addStudent(toAdd);

        // execute DeleteInterestCommand on the test student
        Index indexLastStudent = Index.fromOneBased(model.getFilteredStudentList().size());
        HashSet<Interest> interestsToBeDeleted = new HashSet<>();
        interestsToBeDeleted.add(INTEREST_ANIME);

        try {
            DeleteInterestCommand commandToExecute = new DeleteInterestCommand(indexLastStudent,
                    interestsToBeDeleted);
            commandToExecute.execute(model); // Should jump to catch block
            fail(); // Test should not reach this line
        } catch (CommandException e) {
            assertEquals(DeleteInterestCommand.MESSAGE_INVALID_INTEREST, e.getMessage());
        }
    }

    /**
     * Tests the behaviour of DeleteInterestCommand when the student wants to delete multiple interests containing
     * a mix of existing and non-existing interests from a batchmate.
     */
    @Test
    public void execute_deleteMixedInterests_throwsCommandException() {

        // adds a test student to model (Bob has interests: tennis, netflix)
        Student toAdd = new StudentBuilder(BOB).build();
        model.addStudent(toAdd);

        // execute DeleteInterestCommand on the test student
        Index indexLastStudent = Index.fromOneBased(model.getFilteredStudentList().size());
        HashSet<Interest> interestsToBeDeleted = new HashSet<>();
        interestsToBeDeleted.add(INTEREST_TENNIS);
        interestsToBeDeleted.add(INTEREST_ANIME);

        try {
            DeleteInterestCommand commandToExecute = new DeleteInterestCommand(indexLastStudent,
                    interestsToBeDeleted);
            commandToExecute.execute(model); // Should jump to catch block
            fail(); // Test should not reach this line
        } catch (CommandException e) {
            assertEquals(DeleteInterestCommand.MESSAGE_INVALID_INTEREST, e.getMessage());
        }
    }

}
