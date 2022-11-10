package seedu.travelr.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_TITLE = new Prefix("n/");
    public static final Prefix PREFIX_TRIP = new Prefix("t/");
    public static final Prefix PREFIX_DESC = new Prefix("d/");
    public static final Prefix PREFIX_LOCATION = new Prefix("l/");
    public static final Prefix PREFIX_DATE = new Prefix("D/");
    public static final Prefix PREFIX_EVENT = new Prefix("E/");

    public static final Prefix PREFIX_SORTBY = new Prefix("by/");

    public static final Prefix PREFIX_REVERSE_ORDER = new Prefix("r/");

}
