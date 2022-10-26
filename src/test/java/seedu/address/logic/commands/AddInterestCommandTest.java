package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalStudents.BOB;
import static seedu.address.testutil.TypicalStudents.getTypicalMassLinkers;

import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.interest.Interest;
import seedu.address.model.student.Student;
import seedu.address.testutil.StudentBuilder;

public class AddInterestCommandTest {
    private static final Interest VALID_INTEREST_TENNIS = new Interest("tennis");
    private static final Interest VALID_INTEREST_ANIME = new Interest("anime");
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
        assertThrows(NullPointerException.class, () -> new AddInterestCommand(INDEX_FIRST_PERSON, null));
    }

    /**
     * Test the {@code execute} method to add interests to a student.
     */
    @Test
    public void execute_saveToModels_success() throws CommandException {

        // adds a test student to model
        Student toAdd = new StudentBuilder(BOB).withInterests(VALID_INTEREST_TENNIS.interestName).build();
        model.addStudent(toAdd);

        // execute AddInterestCommand on the test student
        Index indexLastStudent = Index.fromOneBased(model.getFilteredStudentList().size());
        HashSet<Interest> currentInterestSet = new HashSet<>();
        currentInterestSet.add(VALID_INTEREST_TENNIS);
        AddInterestCommand commandToExecute = new AddInterestCommand(indexLastStudent,
                currentInterestSet);
        CommandResult commandResult = commandToExecute.execute(model);
        // get the edited student from AddInterestCommand
        Student editedStudent = model.getFilteredStudentList().get(indexLastStudent.getZeroBased());

        // expected edited student
        Student editedStudentExpected = new StudentBuilder(BOB)
                .withInterests(VALID_INTEREST_TENNIS.interestName, VALID_INTEREST_TENNIS.interestName)
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
        Student toAdd = new StudentBuilder(BOB).withInterests(VALID_INTEREST_ANIME.interestName).build();
        model.addStudent(toAdd);

        // execute AddInterestCommand on the test student with existing mod
        Index indexLastStudent = Index.fromOneBased(model.getFilteredStudentList().size());
        HashSet<Interest> currentInterestSet = new HashSet<>();
        currentInterestSet.add(VALID_INTEREST_ANIME);
        AddInterestCommand commandToExecute = new AddInterestCommand(indexLastStudent,
                currentInterestSet);
        CommandResult commandResult = commandToExecute.execute(model);
        // get the edited student from AddInterestCommand
        Student editedStudent = model.getFilteredStudentList().get(indexLastStudent.getZeroBased());

        // expected edited student
        Student editedStudentExpected = new StudentBuilder(BOB)
                .withInterests(VALID_INTEREST_ANIME.interestName)
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
        currentInterestSet.add(VALID_INTEREST_ANIME);
        AddInterestCommand invalidCommand = new AddInterestCommand(indexOutOfBounds,
                currentInterestSet);
        assertCommandFailure(invalidCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

}
