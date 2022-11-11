package tracko.logic.parser;

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
    public static final Prefix PREFIX_ITEM = new Prefix("i/");
    public static final Prefix PREFIX_QUANTITY = new Prefix("q/");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("d/");
    public static final Prefix PREFIX_COST_PRICE = new Prefix("cp/");
    public static final Prefix PREFIX_SELL_PRICE = new Prefix("sp/");
    public static final Flag FLAG_PAID = new Flag("-p");
    public static final Flag FLAG_DELIVERED = new Flag("-d");
    public static final Flag FLAG_UNPAID = new Flag("-P");
    public static final Flag FLAG_UNDELIVERED = new Flag("-D");
}
