package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_BIRTHDAY = new Prefix("b/");
    public static final Prefix PREFIX_HEALTH_INSURANCE = new Prefix("hi/");
    public static final Prefix PREFIX_DISABILITY_INSURANCE = new Prefix("di/");
    public static final Prefix PREFIX_CRITICAL_ILLNESS_INSURANCE = new Prefix("ci/");
    public static final Prefix PREFIX_LIFE_INSURANCE = new Prefix("li/");
    public static final Prefix PREFIX_REMINDER = new Prefix("r/");
    public static final Prefix PREFIX_DATE = new Prefix("d/");
}
