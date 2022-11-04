package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions for Patient*/
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_REMARK = new Prefix("r/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_CRITERIA = new Prefix("c/");
    public static final Prefix PREFIX_ORDER = new Prefix("o/");

    /* Prefix definitions for Appointment*/
    public static final Prefix PREFIX_MEDICAL_TEST = new Prefix("t/");
    public static final Prefix PREFIX_DOCTOR = new Prefix("d/");
    public static final Prefix PREFIX_SLOT = new Prefix("s/");

    /* Prefix definitions for Bill*/
    public static final Prefix PREFIX_AMOUNT = new Prefix("a/");
    public static final Prefix PREFIX_BILL_DATE = new Prefix("d/");
    public static final Prefix PREFIX_PAYMENT_STATUS = new Prefix("p/");
}
