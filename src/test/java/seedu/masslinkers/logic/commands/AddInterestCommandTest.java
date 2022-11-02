package seedu.masslinkers.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.masslinkers.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.masslinkers.testutil.Assert.assertThrows;
import static seedu.masslinkers.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.masslinkers.testutil.TypicalStudents.BOB;
import static seedu.masslinkers.testutil.TypicalStudents.getTypicalMassLinkers;

import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.masslinkers.commons.core.Messages;
import seedu.masslinkers.commons.core.index.Index;
import seedu.masslinkers.logic.commands.exceptions.CommandException;
import seedu.masslinkers.model.Model;
import seedu.masslinkers.model.ModelManager;
import seedu.masslinkers.model.UserPrefs;
import seedu.masslinkers.model.interest.Interest;
import seedu.masslinkers.model.student.Student;
import seedu.masslinkers.testutil.StudentBuilder;
//@@author sltsheryl
public class AddInterestCommandTest {
    private static final Interest VALID_INTEREST_AI = new Interest("AI");
    private static final Interest VALID_INTEREST_SWE = new Interest("SWE");
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
        assertThrows(NullPointerException.class, () -> new AddInterestCommand(null,
                new HashSet<>()));
    }

    /**
     * Test constructor to throw an exception when null interest is provided.
     */
    @Test
    public void constructor_nullMods_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddInterestCommand(INDEX_FIRST_STUDENT, null));
    }

    /**
     * Test the {@code execute} method to add interests to a student.
     */
    @Test
    public void execute_saveToModels_success() throws CommandException {

        // adds a test student to model
        Student toAdd = new StudentBuilder(BOB).withInterests(VALID_INTEREST_AI.interestName).build();
        model.addStudent(toAdd);

        // execute AddInterestCommand on the test student
        Index indexLastStudent = Index.fromOneBased(model.getFilteredStudentList().size());
        HashSet<Interest> currentInterestSet = new HashSet<>();
        currentInterestSet.add(VALID_INTEREST_AI);
        AddInterestCommand commandToExecute = new AddInterestCommand(indexLastStudent,
                currentInterestSet);
        CommandResult commandResult = commandToExecute.execute(model);
        // get the edited student from AddInterestCommand
        Student editedStudent = model.getFilteredStudentList().get(indexLastStudent.getZeroBased());

        // expected edited student
        Student editedStudentExpected = new StudentBuilder(BOB)
                .withInterests(VALID_INTEREST_AI.interestName, VALID_INTEREST_AI.interestName)
                .build();

        assertEquals(String.format(AddInterestCommand.MESSAGE_SUCCESS, editedStudent),
                commandResult.getFeedbackToUser());
        assertEquals(editedStudent, editedStudentExpected);
    }

    /**
     * Test the {@code execute} method to add a pre-existing interest.
     */
    @Test
    public void execute_saveDuplicateInterests_success() throws CommandException {

        // adds a test student to model
        Student toAdd = new StudentBuilder(BOB).withInterests(VALID_INTEREST_SWE.interestName).build();
        model.addStudent(toAdd);

        // execute AddInterestCommand on the test student with existing mod
        Index indexLastStudent = Index.fromOneBased(model.getFilteredStudentList().size());
        HashSet<Interest> currentInterestSet = new HashSet<>();
        currentInterestSet.add(VALID_INTEREST_SWE);
        AddInterestCommand commandToExecute = new AddInterestCommand(indexLastStudent,
                currentInterestSet);
        CommandResult commandResult = commandToExecute.execute(model);
        // get the edited student from AddInterestCommand
        Student editedStudent = model.getFilteredStudentList().get(indexLastStudent.getZeroBased());

        // expected edited student
        Student editedStudentExpected = new StudentBuilder(BOB)
                .withInterests(VALID_INTEREST_SWE.interestName)
                .build();

        assertEquals(String.format(AddInterestCommand.MESSAGE_SUCCESS, editedStudent),
                commandResult.getFeedbackToUser());
        assertEquals(editedStudent, editedStudentExpected);
    }

    /**
     * Test the {@code execute} method to throw an exception when index provided
     * is out of bounds.
     */
    @Test
    public void execute_indexOutOfBounds_throwsCommandException() {
        Index indexOutOfBounds = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        HashSet<Interest> currentInterestSet = new HashSet<>();
        currentInterestSet.add(VALID_INTEREST_SWE);
        AddInterestCommand invalidCommand = new AddInterestCommand(indexOutOfBounds,
                currentInterestSet);
        assertCommandFailure(invalidCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

}
