package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.exam.Exam;

/**
 * A utility class containing a list of {@code Exam} objects to be used in tests.
 */
public class TypicalExams {

    public static final Exam MIDTERM_EXAM = new ExamBuilder().withModule("cs2040")
        .withDescription("Midterm Exam")
        .withDate("29-12-2022")
        .build();
    public static final Exam FINAL_EXAM = new ExamBuilder().withModule("cs2030")
        .withDescription("Final Exam")
        .withDate("28-12-2022")
        .build();

    public static List<Exam> getTypicalExams() {
        return new ArrayList<>(Arrays.asList(MIDTERM_EXAM, FINAL_EXAM));
    }
}
