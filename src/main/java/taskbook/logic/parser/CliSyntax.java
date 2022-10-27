package taskbook.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("#/");
    public static final Prefix PREFIX_INDEX = new Prefix("i/");
    public static final Prefix PREFIX_ASSIGN_TO = new Prefix("o/");
    public static final Prefix PREFIX_ASSIGN_FROM = new Prefix("m/");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("d/");
    public static final Prefix PREFIX_DATE = new Prefix("t/");
    public static final Prefix PREFIX_SORT_TYPE = new Prefix("s/");
    public static final Prefix PREFIX_HELP_COMMAND = new Prefix("c/");
    public static final Prefix PREFIX_QUERY = new Prefix("q/");
    public static final Prefix PREFIX_ASSIGNMENT = new Prefix("a/");
    public static final Prefix PREFIX_DONE = new Prefix("x/");
}
