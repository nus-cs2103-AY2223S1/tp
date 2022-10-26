package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class IssueCliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_TITLE = new Prefix("t/");
    public static final Prefix PREFIX_DEADLINE = new Prefix("d/");
    public static final Prefix PREFIX_PROJECT_NAME = new Prefix("n/");
    public static final Prefix PREFIX_URGENCY = new Prefix("u/");
    public static final Prefix PREFIX_PROJECT_ID = new Prefix("p/");
    public static final Prefix PREFIX_ISSUE_ID = new Prefix("i/");
    public static final Prefix PREFIX_STATUS = new Prefix("s/");

}
