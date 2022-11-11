package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions (Person) */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_PERSON = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_COMPANY = new Prefix("c/");


    /* Prefix definitions (Internship) */
    public static final Prefix PREFIX_COMPANY_NAME = new Prefix("c/");
    public static final Prefix PREFIX_INTERNSHIP = new Prefix("i/");
    public static final Prefix PREFIX_INTERNSHIP_ROLE = new Prefix("r/");
    public static final Prefix PREFIX_INTERNSHIP_STATUS = new Prefix("s/");
    public static final Prefix PREFIX_INTERVIEW_DATE = new Prefix("d/");

    /* Prefix definitions (Common) */
    public static final Prefix PREFIX_LINK_INDEX = new Prefix("l/");
}
