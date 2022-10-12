package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    // One prefix can be used for multiple reasons because they are imported by their respective commands.
    // Prefix associated with Person
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_GENDER = new Prefix("g/");
    public static final Prefix PREFIX_DOB = new Prefix("d/");

    // Prefix associated with Event
    public static final Prefix PREFIX_DATE = new Prefix("d/");
    public static final Prefix PREFIX_TIME = new Prefix("t/"); //same as PREFIX_TAG
    public static final Prefix PREFIX_PURPOSE = new Prefix("p/"); //same as PREFIX_PHONE
    public static final Prefix PREFIX_EVENT_TITLE = new Prefix("e/");
}
