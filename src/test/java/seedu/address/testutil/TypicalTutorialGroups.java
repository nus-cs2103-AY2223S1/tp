package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.student.TutorialGroup;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalTutorialGroups {
    public static final TutorialGroup TUT_1 = new TutorialGroupBuilder().withName("T01").build();

    public static final TutorialGroup TUT_2 = new TutorialGroupBuilder().withName("T02").build();

    public static final TutorialGroup TUT_3 = new TutorialGroupBuilder().withName("T03").build();


    private TypicalTutorialGroups() {} // prevents instantiation


    public static List<TutorialGroup> getTypicalTutorialGroups() {
        return new ArrayList<>(Arrays.asList(TUT_1, TUT_2, TUT_3));
    }
}
