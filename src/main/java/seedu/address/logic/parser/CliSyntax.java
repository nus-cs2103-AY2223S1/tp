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

    /* Commission Prefixes */
    public static final Prefix PREFIX_TITLE = new Prefix("n/");
    public static final Prefix PREFIX_FEE = new Prefix("f/");
    public static final Prefix PREFIX_DEADLINE = new Prefix("d/");
    public static final Prefix PREFIX_STATUS = new Prefix("s/");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("p/");

    /* Iteration Prefixes */
    public static final Prefix PREFIX_ITERATION_DATE = new Prefix("d/");
    public static final Prefix PREFIX_ITERATION_DESCRIPTION = new Prefix("n/");
    public static final Prefix PREFIX_ITERATION_IMAGEPATH = new Prefix("p/");
    public static final Prefix PREFIX_ITERATION_FEEDBACK = new Prefix("f/");
}
