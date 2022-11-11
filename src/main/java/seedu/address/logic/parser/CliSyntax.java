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
    public static final Prefix PREFIX_CONTACT = new Prefix("@");
    public static final Prefix PREFIX_ADD_CONTACT = new Prefix("+@");
    public static final Prefix PREFIX_DELETE_CONTACT = new Prefix("-@");
    public static final Prefix PREFIX_DEADLINE = new Prefix("by/");
    public static final Prefix PREFIX_BEFORE = new Prefix("before/");
    public static final Prefix PREFIX_AFTER = new Prefix("after/");
    public static final Prefix PREFIX_TITLE = new Prefix("ti/");
    public static final Prefix PREFIX_PROJECT = new Prefix("#");
    public static final Prefix PREFIX_FLAG = new Prefix("-");
}
