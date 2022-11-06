package jarvis.logic.commands;

import static jarvis.testutil.Assert.assertThrows;
import static jarvis.testutil.LessonBuilder.DEFAULT_LESSON_DESC;
import static jarvis.testutil.LessonBuilder.DEFAULT_STUDIO_DESC;
import static jarvis.testutil.LessonBuilder.DEFAULT_TIME_PERIOD;
import static jarvis.testutil.TypicalLessons.DT3;
import static jarvis.testutil.TypicalLessons.DT4;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import jarvis.logic.commands.exceptions.CommandException;
import jarvis.model.Studio;
import jarvis.model.TimePeriod;
import jarvis.testutil.LessonBuilder;

public class AddStudioCommandTest extends AddLessonCommandTest {

    @Test
    public void constructor_nullTimePeriod_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddStudioCommand(null, null));
    }

    @Test
    public void execute_studioAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingLessonAdded modelStub = new ModelStubAcceptingLessonAdded();

        CommandResult commandResult = new AddStudioCommand(DEFAULT_STUDIO_DESC, DEFAULT_TIME_PERIOD).execute(modelStub);

        Studio validStudio = new LessonBuilder().withDesc(DEFAULT_STUDIO_DESC)
                .withTimePeriod(DEFAULT_TIME_PERIOD).buildStudio();

        assertEquals(String.format(AddStudioCommand.MESSAGE_SUCCESS, validStudio), commandResult.getFeedbackToUser());

        assertEquals(Arrays.asList(validStudio), modelStub.lessonsAdded);
    }

    @Test
    public void execute_duplicateStudio_throwsCommandException() {
        Studio validStudio = new LessonBuilder().withDesc(DEFAULT_STUDIO_DESC)
                .withTimePeriod(DEFAULT_TIME_PERIOD).buildStudio();

        AddStudioCommand addStudioCommand = new AddStudioCommand(DEFAULT_STUDIO_DESC, DEFAULT_TIME_PERIOD);

        ModelStubWithLesson modelStub = new ModelStubWithLesson(validStudio);

        assertThrows(CommandException.class,
                AddStudioCommand.MESSAGE_DUPLICATE_STUDIO, () -> addStudioCommand.execute(modelStub));
    }

    @Test
    public void execute_clashTimePeriod_throwsCommandException() {
        //valid studio with diff desc but same time period
        Studio validStudio = new LessonBuilder().withDesc(DEFAULT_LESSON_DESC)
                .withTimePeriod(DEFAULT_TIME_PERIOD).buildStudio();

        AddStudioCommand addStudioCommand = new AddStudioCommand(DEFAULT_STUDIO_DESC, DEFAULT_TIME_PERIOD);

        ModelStubWithLesson modelStub = new ModelStubWithLesson(validStudio);

        assertThrows(CommandException.class,
                AddStudioCommand.MESSAGE_TIME_PERIOD_CLASH, () -> addStudioCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        AddStudioCommand addStudio1Command = new AddStudioCommand(DEFAULT_STUDIO_DESC, DEFAULT_TIME_PERIOD);

        // same object -> returns true
        assertTrue(addStudio1Command.equals(addStudio1Command));

        // same values -> returns true
        AddStudioCommand addStudio1CommandCopy = new AddStudioCommand(DEFAULT_STUDIO_DESC, DEFAULT_TIME_PERIOD);
        assertTrue(addStudio1Command.equals(addStudio1CommandCopy));

        // different types -> returns false
        assertFalse(addStudio1Command.equals(1));

        // null -> returns false
        assertFalse(addStudio1Command.equals(null));

        // different desc -> returns false
        AddStudioCommand addStudioNullDescCommand = new AddStudioCommand(DEFAULT_LESSON_DESC, DEFAULT_TIME_PERIOD);
        assertFalse(addStudio1Command.equals(addStudioNullDescCommand));

        // different time period ->returns false
        AddStudioCommand addStudio1DiffTimeCommand =
                new AddStudioCommand(DEFAULT_STUDIO_DESC, new TimePeriod(DT3, DT4));
        assertFalse(addStudio1Command.equals(addStudio1DiffTimeCommand));
    }
}
