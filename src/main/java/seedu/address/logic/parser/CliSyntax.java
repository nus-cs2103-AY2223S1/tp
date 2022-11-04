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
    public static final Prefix PREFIX_ID = new Prefix("i/");
    public static final Prefix PREFIX_HANDLE = new Prefix("h/");
    public static final Prefix PREFIX_MODULE_CODE = new Prefix("c/");
    public static final Prefix PREFIX_MODULE_DESCRIPTION = new Prefix("d/");
    public static final Prefix PREFIX_STUDENT_TA = new Prefix("ta/");

    public static final Prefix PREFIX_WEEKDAY = new Prefix("w/");
    public static final Prefix PREFIX_MODULE_OF_SCHEDULE = new Prefix("c/");
    public static final Prefix PREFIX_CLASS_TIME = new Prefix("ct/");
    public static final Prefix PREFIX_CLASS_CATEGORY = new Prefix("cc/");
    public static final Prefix PREFIX_CLASS_VENUE = new Prefix("cv/");
    public static final Prefix PREFIX_CLASS_GROUP = new Prefix("cg/");

}
