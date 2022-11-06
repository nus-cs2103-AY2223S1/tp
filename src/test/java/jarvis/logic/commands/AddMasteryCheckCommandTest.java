package jarvis.logic.commands;

import static jarvis.testutil.Assert.assertThrows;
import static jarvis.testutil.LessonBuilder.DEFAULT_LESSON_DESC;
import static jarvis.testutil.LessonBuilder.DEFAULT_MASTERY_CHECK_DESC;
import static jarvis.testutil.LessonBuilder.DEFAULT_TIME_PERIOD;
import static jarvis.testutil.TypicalLessons.DT3;
import static jarvis.testutil.TypicalLessons.DT4;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import jarvis.commons.core.index.Index;
import jarvis.logic.commands.exceptions.CommandException;
import jarvis.model.MasteryCheck;
import jarvis.model.TimePeriod;
import jarvis.testutil.LessonBuilder;

public class AddMasteryCheckCommandTest extends AddLessonCommandTest {

    @Test
    public void constructor_nullTimePeriod_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new AddMasteryCheckCommand(null, null, VALID_STUDENT_INDEX));
    }

    @Test
    public void constructor_nullStudentIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new AddMasteryCheckCommand(null, VALID_TIME_PERIOD, null));
    }

    @Test
    public void execute_masteryCheckAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingLessonAdded modelStub = new ModelStubAcceptingLessonAdded();

        CommandResult commandResult = new AddMasteryCheckCommand(DEFAULT_MASTERY_CHECK_DESC,
                DEFAULT_TIME_PERIOD, VALID_STUDENT_INDEX).execute(modelStub);

        MasteryCheck validMasteryCheck = new LessonBuilder().withDesc(DEFAULT_MASTERY_CHECK_DESC)
                .withTimePeriod(DEFAULT_TIME_PERIOD).withStudents(getStudentsInLesson()).buildMasteryCheck();

        assertEquals(String.format(AddMasteryCheckCommand.MESSAGE_SUCCESS, validMasteryCheck),
                commandResult.getFeedbackToUser());

        assertEquals(Arrays.asList(validMasteryCheck), modelStub.lessonsAdded);
    }

    @Test
    public void execute_duplicateMasteryCheck_throwsCommandException() {
        MasteryCheck validMasteryCheck = new LessonBuilder().withDesc(DEFAULT_MASTERY_CHECK_DESC)
                .withTimePeriod(DEFAULT_TIME_PERIOD).withStudents(getStudentsInLesson()).buildMasteryCheck();

        AddMasteryCheckCommand addMasteryCheckCommand =
                new AddMasteryCheckCommand(DEFAULT_MASTERY_CHECK_DESC, DEFAULT_TIME_PERIOD, VALID_STUDENT_INDEX);

        ModelStubWithLesson modelStub = new ModelStubWithLesson(validMasteryCheck);

        assertThrows(CommandException.class, AddMasteryCheckCommand.MESSAGE_DUPLICATE_MASTERY_CHECK, () ->
                addMasteryCheckCommand.execute(modelStub));
    }

    @Test
    public void execute_clashTimePeriod_throwsCommandException() {
        //valid masteryCheck with diff desc but same time period
        MasteryCheck validMasteryCheck = new LessonBuilder().withDesc(DEFAULT_LESSON_DESC)
                .withTimePeriod(DEFAULT_TIME_PERIOD).withStudents(getStudentsInLesson()).buildMasteryCheck();

        AddMasteryCheckCommand addMasteryCheckCommand =
                new AddMasteryCheckCommand(DEFAULT_MASTERY_CHECK_DESC, DEFAULT_TIME_PERIOD, VALID_STUDENT_INDEX);

        ModelStubWithLesson modelStub = new ModelStubWithLesson(validMasteryCheck);

        assertThrows(CommandException.class, AddMasteryCheckCommand.MESSAGE_TIME_PERIOD_CLASH, () ->
                addMasteryCheckCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        AddMasteryCheckCommand addMasteryCheck1Command =
                new AddMasteryCheckCommand(DEFAULT_MASTERY_CHECK_DESC, DEFAULT_TIME_PERIOD, VALID_STUDENT_INDEX);

        // same object -> returns true
        assertTrue(addMasteryCheck1Command.equals(addMasteryCheck1Command));

        // same values -> returns true
        AddMasteryCheckCommand addMasteryCheck1CommandCopy =
                new AddMasteryCheckCommand(DEFAULT_MASTERY_CHECK_DESC, DEFAULT_TIME_PERIOD, VALID_STUDENT_INDEX);
        assertTrue(addMasteryCheck1Command.equals(addMasteryCheck1CommandCopy));

        // different types -> returns false
        assertFalse(addMasteryCheck1Command.equals(1));

        // null -> returns false
        assertFalse(addMasteryCheck1Command.equals(null));

        // different desc -> returns false
        AddMasteryCheckCommand addMasteryCheckNullDescCommand =
                new AddMasteryCheckCommand(DEFAULT_LESSON_DESC, DEFAULT_TIME_PERIOD, VALID_STUDENT_INDEX);
        assertFalse(addMasteryCheck1Command.equals(addMasteryCheckNullDescCommand));

        // different time period ->returns false
        AddMasteryCheckCommand addMasteryCheck1DiffTimeCommand =
                new AddMasteryCheckCommand(DEFAULT_MASTERY_CHECK_DESC, new TimePeriod(DT3, DT4), VALID_STUDENT_INDEX);
        assertFalse(addMasteryCheck1Command.equals(addMasteryCheck1DiffTimeCommand));

        // different studentIndex
        Set<Index> diffStudentIndex = new HashSet<>();
        diffStudentIndex.add(Index.fromOneBased(Integer.parseInt("1")));
        diffStudentIndex.add(Index.fromOneBased(Integer.parseInt("2")));
        AddMasteryCheckCommand addMasteryCheck1DiffStudentCommand =
                new AddMasteryCheckCommand(DEFAULT_MASTERY_CHECK_DESC, DEFAULT_TIME_PERIOD, diffStudentIndex);
        assertFalse(addMasteryCheck1Command.equals(addMasteryCheck1DiffStudentCommand));
    }
}
