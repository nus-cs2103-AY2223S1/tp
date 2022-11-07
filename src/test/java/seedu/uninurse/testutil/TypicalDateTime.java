package seedu.uninurse.testutil;

import static seedu.uninurse.testutil.TestUtil.getCurrentDate;
import static seedu.uninurse.testutil.TestUtil.getFutureDate;
import static seedu.uninurse.testutil.TestUtil.getNonCurrentDate;
import static seedu.uninurse.testutil.TestUtil.getPastDate;

import seedu.uninurse.model.task.DateTime;

/**
 * A utility class containing a list of {@code DateTime} objects to be used in tests.
 */
public class TypicalDateTime {

    public static final String DATE_TIME_STRING = "22-10-22 1445";

    public static final DateTime DATE_TIME_ONE = new DateTime("25-10-22 1445");
    public static final DateTime DATE_TIME_TWO = new DateTime("19-11-22 1555");
    public static final DateTime DATE_TIME_TODAY = new DateTime(getCurrentDate());
    public static final DateTime DATE_TIME_NOT_TODAY = new DateTime(getNonCurrentDate());
    public static final DateTime DATE_TIME_PAST = new DateTime(getPastDate());
    public static final DateTime DATE_TIME_FUTURE = new DateTime(getFutureDate());
    public static final DateTime DATE_TIME_THREE = new DateTime("29-10-22 1445"); // 4 days after
    // DATE_TIME_ONE for recurrence tests
}

