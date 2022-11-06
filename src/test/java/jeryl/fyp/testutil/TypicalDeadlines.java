package jeryl.fyp.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jeryl.fyp.model.student.Deadline;
import jeryl.fyp.model.student.Student;
import jeryl.fyp.model.util.SampleDataUtil;

/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalDeadlines {

    public static final Deadline TASK_A = SampleDataUtil.getDeadline("Deadline Example A", "01-01-2023 00:00");
    public static final Deadline TASK_A_COPY = SampleDataUtil.getDeadline("Deadline Example A", "01-01-2023 00:00");
    public static final Deadline TASK_B = SampleDataUtil.getDeadline("Deadline Example B", "10-01-2023 00:00");
    public static final Deadline TASK_B_DIFF_TIME = SampleDataUtil.getDeadline("Deadline Example B", "10-02-2023 00:00");
    public static final Deadline TASK_C = SampleDataUtil.getDeadline("Deadline Example C", "10-01-2023 00:01");
    public static final Deadline TASK_D = SampleDataUtil.getDeadline("Deadline Example D", "09-01-2023 23:59");
    public static final Deadline TASK_E = SampleDataUtil.getDeadline("Deadline Example D", "10-01-2023 00:01");

    private TypicalDeadlines() {} // prevents instantiation

    /**
     * Returns an {@code Student} with all the typical deadlines.
     */
    public static Student getTypicalStudent() {
        Student ab = new StudentBuilder().withStudentName("Sample student")
                .withEmail("sample@example.com").withStudentId("A0123456U")
                .withProjectName("Final Year Project")
                .withProjectStatus("YTS")
                .withTags("friends")
                .withDeadlines(getTypicalDeadlines())
                .build();
        return ab;
    }

    public static List<Deadline> getTypicalDeadlines() {
        return new ArrayList<>(Arrays.asList(TASK_A, TASK_B, TASK_C, TASK_D, TASK_E, TASK_A_COPY, TASK_B_DIFF_TIME));
    }
}
