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
    public static final Exam EXAMONE = new ExamBuilder()
            .withDescription("Exam one").withModule("CS2030S")
            .withDate("20-08-2023").build();
    public static final Exam EXAMTWO = new ExamBuilder()
            .withDescription("Exam two")
            .withModule("CS2040S").withDate("20-10-2023").build();

    public static final Exam CS2040_FINAL = new ExamBuilder()
            .withModule("cs2040").withDescription("CS2040 Finals")
            .withDate("29-11-2022").build();
    private TypicalExams() {} // prevents instantiation


    public static List<Exam> getTypicalExams() {
        return new ArrayList<>(Arrays.asList(MIDTERM_EXAM, FINAL_EXAM, EXAMONE, EXAMTWO, CS2040_FINAL));
    }

}

