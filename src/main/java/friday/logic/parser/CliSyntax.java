package friday.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_TELEGRAMHANDLE = new Prefix("t/");
    public static final Prefix PREFIX_CONSULTATION = new Prefix("c/");
    public static final Prefix PREFIX_MASTERYCHECK = new Prefix("m/");
    public static final Prefix PREFIX_REMARK = new Prefix("r/");
    public static final Prefix PREFIX_TAG = new Prefix("tag/");
    public static final Prefix PREFIX_ALIAS = new Prefix("a/");
    public static final Prefix PREFIX_RESERVED_KEYWORD = new Prefix("k/");

    /* Order definitions */
    public static final Order ORDER_ASCENDING = new Order("asc");
    public static final Order ORDER_DESCENDING = new Order("desc");

}
