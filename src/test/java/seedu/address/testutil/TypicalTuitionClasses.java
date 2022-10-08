package seedu.address.testutil;

import seedu.address.model.tuitionclass.TuitionClass;

/**
 * A utility class containing a list of {@code TuitionClass} objects to be used in tests.
 */
public class TypicalTuitionClasses {

    public static final TuitionClass TUITIONCLASS1 =
            new TuitionClassBuilder().withName("P2MATH")
            .withSubject("MATHEMATICS")
            .withLevel("PRIMARY2")
            .withDay("MONDAY")
            .withTime("10:00", "12:00")
            .withTags("tough")
            .build();

    public static final TuitionClass TUITIONCLASS2 =
            new TuitionClassBuilder().withName("P5ENG")
            .withSubject("ENGLISH")
            .withLevel("PRIMARY5")
            .withDay("FRIDAY")
            .withTime("18:00", "20:00")
            .withTags("easy")
            .build();

    private TypicalTuitionClasses() {} // prevents instantiation
}
