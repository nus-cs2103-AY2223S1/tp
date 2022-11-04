package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_PREAMBLE = new Prefix("");
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_INCOME = new Prefix("i/");
    public static final Prefix PREFIX_MEETING_DATE = new Prefix("m/");
    public static final Prefix PREFIX_MEETING_LOCATION = new Prefix("ml/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_RISK = new Prefix("r/");
    public static final Prefix PREFIX_PLAN = new Prefix("pl/");
    public static final Prefix PREFIX_NOTE = new Prefix("note/");
    public static final Prefix PREFIX_OLD_PASSWORD = new Prefix("old/");
    public static final Prefix PREFIX_NEW_PASSWORD = new Prefix("new/");
}
