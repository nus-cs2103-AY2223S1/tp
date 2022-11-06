package jarvis.logic.commands;

import static jarvis.logic.commands.CommandTestUtil.assertCommandFailure;
import static jarvis.logic.commands.CommandTestUtil.assertCommandSuccess;
import static jarvis.testutil.TypicalLessons.CONSULT_1;
import static jarvis.testutil.TypicalLessons.CONSULT_DESCRIPTION_1;
import static jarvis.testutil.TypicalLessons.MASTERY_CHECK_DESCRIPTION_1;
import static jarvis.testutil.TypicalLessons.MC_1;
import static jarvis.testutil.TypicalLessons.STUDIO_1;
import static jarvis.testutil.TypicalLessons.STUDIO_DESCRIPTION_1;
import static jarvis.testutil.TypicalLessons.STUDIO_DESCRIPTION_2;
import static jarvis.testutil.TypicalLessons.TP1;
import static jarvis.testutil.TypicalLessons.getTypicalLessonBook;
import static jarvis.testutil.TypicalStudents.getTypicalStudentBook;
import static jarvis.testutil.TypicalStudents.getTypicalStudents;
import static jarvis.testutil.TypicalTasks.getTypicalTaskBook;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jarvis.commons.core.index.Index;
import jarvis.model.Consult;
import jarvis.model.MasteryCheck;
import jarvis.model.Model;
import jarvis.model.ModelManager;
import jarvis.model.Student;
import jarvis.model.Studio;
import jarvis.model.TimePeriod;
import jarvis.model.UserPrefs;
import jarvis.testutil.LessonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddStudioCommand}.
 */
public class AddLessonCommandIntegrationTest {

    private Model model;
    private LocalDateTime newDT1 = LocalDateTime.of(2022, 9, 9, 9, 0);
    private LocalDateTime newDT2 = LocalDateTime.of(2022, 9, 9, 10, 0);
    private TimePeriod noClashTimePeriod = new TimePeriod(newDT1, newDT2);
    private List<Student> studentsInLesson =  Arrays.asList(getTypicalStudents().get(0), getTypicalStudents().get(1));
    private Set<Index> studentIndexesForLesson =
            new HashSet<>(Arrays.asList(Index.fromOneBased(1), Index.fromOneBased(2)));

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalStudentBook(), getTypicalTaskBook(),
                getTypicalLessonBook(), new UserPrefs());
    }

    @Test
    public void execute_newStudio_success() {
        Studio validStudio = new LessonBuilder(STUDIO_1).withStudents(getTypicalStudents())
                .withTimePeriod(noClashTimePeriod).buildStudio();

        Model expectedModel =
                new ModelManager(model.getStudentBook(), model.getTaskBook(), model.getLessonBook(), new UserPrefs());
        expectedModel.addLesson(validStudio);

        assertCommandSuccess(new AddStudioCommand(STUDIO_DESCRIPTION_1, noClashTimePeriod), model,
                String.format(AddStudioCommand.MESSAGE_SUCCESS, validStudio), expectedModel);
    }

    @Test
    public void execute_newMasteryCheck_success() {
        // first 2 student in student list used for mastery check
        List<Student> studentsInMasteryCheck = studentsInLesson;
        Set<Index> studentIndexesForMasteryCheck = studentIndexesForLesson;

        MasteryCheck validMasteryCheck = new LessonBuilder(MC_1).withStudents(studentsInMasteryCheck)
                .withTimePeriod(noClashTimePeriod).buildMasteryCheck();

        Model expectedModel =
                new ModelManager(model.getStudentBook(), model.getTaskBook(), model.getLessonBook(), new UserPrefs());
        expectedModel.addLesson(validMasteryCheck);

        assertCommandSuccess(new AddMasteryCheckCommand(MASTERY_CHECK_DESCRIPTION_1, noClashTimePeriod,
                        studentIndexesForMasteryCheck), model,
                String.format(AddMasteryCheckCommand.MESSAGE_SUCCESS, validMasteryCheck), expectedModel);
    }

    @Test
    public void execute_newConsult_success() {
        // first 2 student in student list used for consult
        List<Student> studentsInConsult = studentsInLesson;
        Set<Index> studentIndexesForConsult = studentIndexesForLesson;

        Consult validConsult = new LessonBuilder(CONSULT_1).withStudents(studentsInConsult)
                .withTimePeriod(noClashTimePeriod).buildConsult();

        Model expectedModel =
                new ModelManager(model.getStudentBook(), model.getTaskBook(), model.getLessonBook(), new UserPrefs());
        expectedModel.addLesson(validConsult);

        assertCommandSuccess(new AddConsultCommand(CONSULT_DESCRIPTION_1, noClashTimePeriod, studentIndexesForConsult),
                model, String.format(AddConsultCommand.MESSAGE_SUCCESS, validConsult), expectedModel);
    }

    @Test
    public void execute_duplicateLesson_throwsCommandException() {
        // one of the lesson in lesson list is Mastery Check 1 with 1st and 2nd student in student list
        Set<Index> studentIndexesForMasteryCheck = studentIndexesForLesson;
        assertCommandFailure(new AddMasteryCheckCommand(MASTERY_CHECK_DESCRIPTION_1,
                TP1, studentIndexesForMasteryCheck), model,
                AddMasteryCheckCommand.MESSAGE_DUPLICATE_MASTERY_CHECK);
    }

    @Test
    public void execute_lessonTimeClash_throwsCommandException() {
        // Studio with same timing as one of the lesson in lesson list
        assertCommandFailure(new AddStudioCommand(STUDIO_DESCRIPTION_2, TP1), model,
                AddStudioCommand.MESSAGE_TIME_PERIOD_CLASH);
    }
}
