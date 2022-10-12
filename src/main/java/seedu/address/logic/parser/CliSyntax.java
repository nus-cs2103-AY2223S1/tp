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
    public static final Prefix PREFIX_MODULE_CODE = new Prefix("m/");
    public static final Prefix PREFIX_MODULE_TITLE = new Prefix("t/");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("d/");
    public static final Prefix PREFIX_MODULE_LINK = new Prefix("l/");
    public static final Prefix PREFIX_TASK_DESCRIPTION = new Prefix("td/");
    public static final Prefix PREFIX_TASK_NUMBER = new Prefix("tn/");
}
