package swift.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/", "name");
    public static final Prefix PREFIX_PHONE = new Prefix("p/", "phone");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/", "email");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/", "address");
    public static final Prefix PREFIX_TAG = new Prefix("t/", "tag");
    public static final Prefix PREFIX_TASK = new Prefix("t/", "task_index");
    public static final Prefix PREFIX_CONTACT = new Prefix("c/", "contact_index");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("d/", "description");
    public static final Prefix PREFIX_DEADLINE = new Prefix("dl/", "deadline");
    // PREFIX_KEYWORD is not a prefix, but a placeholder for the keyword argument
    public static final Prefix PREFIX_KEYWORD = new Prefix("keyword", "keyword");
    public static final Prefix PREFIX_CONTACT_INDEX = new Prefix("", "contact_index");
    public static final Prefix PREFIX_TASK_INDEX = new Prefix("", "task_index");
}
