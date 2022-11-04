package seedu.hrpro.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_BUDGET = new Prefix("pb/");
    public static final Prefix PREFIX_DEADLINE = new Prefix("pd/");
    public static final Prefix PREFIX_PROJECT_NAME = new Prefix("pn/");
    public static final Prefix PREFIX_STAFF_NAME = new Prefix("sn/");
    public static final Prefix PREFIX_STAFF_LEAVE = new Prefix("sl/");
    public static final Prefix PREFIX_STAFF_DEPARTMENT = new Prefix("sd/");
    public static final Prefix PREFIX_STAFF_TITLE = new Prefix("st/");
    public static final Prefix PREFIX_STAFF_CONTACT = new Prefix("sp/");
    public static final Prefix PREFIX_TASK_DESCRIPTION = new Prefix("tdesc/");
    public static final Prefix PREFIX_TASK_DEADLINE = new Prefix("td/");
    public static final Prefix PREFIX_TASK_MARK = new Prefix("tm/");
}
