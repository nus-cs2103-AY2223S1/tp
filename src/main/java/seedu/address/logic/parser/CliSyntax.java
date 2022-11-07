package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("name/", "n/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("address/", "a/");
    public static final Prefix PREFIX_TAG = new Prefix("tag/", "t/");
    public static final Prefix PREFIX_EMAIL = new Prefix("email/", "@/");
    public static final Prefix PREFIX_PHONE = new Prefix("phone/", "+/");
    public static final Prefix PREFIX_SLACK = new Prefix("slack/", "sk/");
    public static final Prefix PREFIX_TELEGRAM = new Prefix("telegram/", "tele/");
    public static final Prefix PREFIX_ROLE = new Prefix("role/", "r/");
    public static final Prefix PREFIX_TIMEZONE = new Prefix("timezone/", "tz/");
    public static final Prefix PREFIX_GITHUB = new Prefix("github/", "gh/");

}
