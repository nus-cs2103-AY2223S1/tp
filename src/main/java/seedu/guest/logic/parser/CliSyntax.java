package seedu.guest.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_DATE_RANGE = new Prefix("dr/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_NUMBER_OF_GUESTS = new Prefix("ng/");
    public static final Prefix PREFIX_IS_ROOM_CLEAN = new Prefix("rc/");
    public static final Prefix PREFIX_ROOM = new Prefix("rm/");
}
