package jarvis.model.util;

import java.time.LocalDateTime;
import java.util.List;

import jarvis.model.Consult;
import jarvis.model.Lesson;
import jarvis.model.LessonBook;
import jarvis.model.LessonDesc;
import jarvis.model.MasteryCheck;
import jarvis.model.ReadOnlyLessonBook;
import jarvis.model.TimePeriod;

/**
 * Contains utility methods for populating {@code LessonBook} with sample data.
 */
public class SampleLessonUtil {
    public static Lesson[] getSampleLessons() {
        return new Lesson[] {
            new Consult(new LessonDesc("Midterms clarification"),
                    new TimePeriod(LocalDateTime.of(2022, 10, 10, 12, 0),
                            LocalDateTime.of(2022, 10, 10, 14, 0)),
                    List.of(SampleStudentUtil.getSampleStudents())),
            new MasteryCheck(new LessonDesc("Mastery Check 1"),
                    new TimePeriod(LocalDateTime.of(2022, 11, 10, 12, 0),
                            LocalDateTime.of(2022, 11, 10, 14, 0)),
                    List.of(SampleStudentUtil.getSampleStudents())),
        };
    }
    public static ReadOnlyLessonBook getSampleLessonBook() {
        LessonBook sampleLessonBook = new LessonBook();
        for (Lesson sampleLesson : getSampleLessons()) {
            sampleLessonBook.addLesson(sampleLesson);
        }
        return sampleLessonBook;
    }
}
