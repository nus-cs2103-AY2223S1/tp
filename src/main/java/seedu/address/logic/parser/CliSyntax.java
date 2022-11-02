package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    // Default start of prefix
    public static final Prefix DEFAULT_PREFIX_DELIMITER = new Prefix("-");

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("-n");
    public static final Prefix PREFIX_MODULE = new Prefix("-m");
    public static final Prefix PREFIX_DEADLINE = new Prefix("-d");
    public static final Prefix PREFIX_TAG = new Prefix("-t");

    public static final Prefix PREFIX_LIST_ALL = new Prefix("-a");
    public static final Prefix PREFIX_LIST_UNMARKED = new Prefix("-u");
    public static final Prefix PREFIX_LIST_MARKED = new Prefix("-m");
    public static final Prefix PREFIX_LIST_MODULE = new Prefix("--module");
    public static final Prefix PREFIX_LIST_TAG = new Prefix("-t");
    public static final Prefix PREFIX_LIST_DEADLINE = new Prefix("-d");
    public static final Prefix PREFIX_LIST_NAME = new Prefix("-n");

    /* String definitions */
    public static final String LIST_ALL_STRING = "-a";
    public static final String LIST_UNMARKED_STRING = "-u";
    public static final String LIST_MARKED_STRING = "-m";
    public static final String LIST_MODULE_STRING = "--module";
    public static final String LIST_TAG_STRING = "-t";
    public static final String LIST_DEADLINE_STRING = "-d";
}
