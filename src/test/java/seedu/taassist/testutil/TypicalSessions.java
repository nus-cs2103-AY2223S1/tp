package seedu.taassist.testutil;

import seedu.taassist.model.session.Session;

/**
 * A utility class containing a list of {@code Session} objects to be used in tests.
 */
public class TypicalSessions {
    public static final Session ASSIGNMENT_1 = new SessionBuilder().withName("Assignment 1")
            .withDate("2022-01-01").build();
    public static final Session LAB_1 = new SessionBuilder().withName("Lab 1")
            .withDate("2022-10-12").build();
    public static final Session TUTORIAL_1 = new SessionBuilder().withName("Tutorial 1")
            .withDate("2022-02-28").build();
}
