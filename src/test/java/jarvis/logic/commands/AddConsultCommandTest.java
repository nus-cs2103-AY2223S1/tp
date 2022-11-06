package jarvis.logic.commands;

import static jarvis.testutil.Assert.assertThrows;
import static jarvis.testutil.LessonBuilder.DEFAULT_CONSULT_DESC;
import static jarvis.testutil.LessonBuilder.DEFAULT_LESSON_DESC;
import static jarvis.testutil.LessonBuilder.DEFAULT_TIME_PERIOD;
import static jarvis.testutil.TypicalLessons.TP3;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import jarvis.commons.core.index.Index;
import jarvis.logic.commands.exceptions.CommandException;
import jarvis.model.Consult;
import jarvis.model.TimePeriod;
import jarvis.testutil.LessonBuilder;

public class AddConsultCommandTest extends AddLessonCommandTest {

    @Test
    public void constructor_nullTimePeriod_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new AddConsultCommand(null, null, VALID_STUDENT_INDEX));
    }

    @Test
    public void constructor_nullStudentIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new AddConsultCommand(null, VALID_TIME_PERIOD, null));
    }

    @Test
    public void execute_consultAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingLessonAdded modelStub = new ModelStubAcceptingLessonAdded();

        CommandResult commandResult = new AddConsultCommand(DEFAULT_CONSULT_DESC, DEFAULT_TIME_PERIOD,
                VALID_STUDENT_INDEX).execute(modelStub);

        Consult validConsult = new LessonBuilder().withDesc(DEFAULT_CONSULT_DESC).withTimePeriod(DEFAULT_TIME_PERIOD)
                .withStudents(getStudentsInLesson()).buildConsult();

        assertEquals(String.format(AddConsultCommand.MESSAGE_SUCCESS, validConsult),
                commandResult.getFeedbackToUser());

        assertEquals(Arrays.asList(validConsult), modelStub.lessonsAdded);
    }

    @Test
    public void execute_duplicateConsult_throwsCommandException() {
        Consult validConsult = new LessonBuilder().withDesc(DEFAULT_CONSULT_DESC).withTimePeriod(DEFAULT_TIME_PERIOD)
                .withStudents(getStudentsInLesson()).buildConsult();

        AddConsultCommand addConsultCommand =
                new AddConsultCommand(DEFAULT_CONSULT_DESC, DEFAULT_TIME_PERIOD, VALID_STUDENT_INDEX);

        ModelStubWithLesson modelStub = new ModelStubWithLesson(validConsult);

        assertThrows(CommandException.class,
                AddConsultCommand.MESSAGE_DUPLICATE_CONSULT, () -> addConsultCommand.execute(modelStub));
    }

    @Test
    public void execute_clashTimePeriod_throwsCommandException() {
        //valid consult with diff desc but same time period
        Consult validConsult = new LessonBuilder().withDesc(DEFAULT_LESSON_DESC).withTimePeriod(DEFAULT_TIME_PERIOD)
                .withStudents(getStudentsInLesson()).buildConsult();

        AddConsultCommand addConsultCommand =
                new AddConsultCommand(DEFAULT_CONSULT_DESC, DEFAULT_TIME_PERIOD, VALID_STUDENT_INDEX);

        ModelStubWithLesson modelStub = new ModelStubWithLesson(validConsult);

        assertThrows(CommandException.class,
                AddConsultCommand.MESSAGE_TIME_PERIOD_CLASH, () -> addConsultCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        AddConsultCommand addConsultRecursionCommand =
                new AddConsultCommand(DEFAULT_CONSULT_DESC, DEFAULT_TIME_PERIOD, VALID_STUDENT_INDEX);

        // same object -> returns true
        assertTrue(addConsultRecursionCommand.equals(addConsultRecursionCommand));

        // same values -> returns true
        AddConsultCommand addConsultRecursionCommandCopy =
                new AddConsultCommand(DEFAULT_CONSULT_DESC, DEFAULT_TIME_PERIOD, VALID_STUDENT_INDEX);
        assertTrue(addConsultRecursionCommand.equals(addConsultRecursionCommandCopy));

        // different types -> returns false
        assertFalse(addConsultRecursionCommand.equals(1));

        // null -> returns false
        assertFalse(addConsultRecursionCommand.equals(null));

        // different desc -> returns false
        AddConsultCommand addConsultNullDescCommand =
                new AddConsultCommand(DEFAULT_LESSON_DESC, DEFAULT_TIME_PERIOD, VALID_STUDENT_INDEX);
        assertFalse(addConsultRecursionCommand.equals(addConsultNullDescCommand));

        // different time period ->returns false
        AddConsultCommand addConsultRecursionDiffTimeCommand =
                new AddConsultCommand(DEFAULT_CONSULT_DESC, TP3, VALID_STUDENT_INDEX);
        assertFalse(addConsultRecursionCommand.equals(addConsultRecursionDiffTimeCommand));

        // different studentIndex
        Set<Index> diffStudentIndex = new HashSet<>();
        diffStudentIndex.add(Index.fromOneBased(Integer.parseInt("1")));
        diffStudentIndex.add(Index.fromOneBased(Integer.parseInt("2")));
        AddConsultCommand addConsultRecursionDiffStudentCommand =
                new AddConsultCommand(DEFAULT_CONSULT_DESC, DEFAULT_TIME_PERIOD, diffStudentIndex);
        assertFalse(addConsultRecursionCommand.equals(addConsultRecursionDiffStudentCommand));
    }
}
