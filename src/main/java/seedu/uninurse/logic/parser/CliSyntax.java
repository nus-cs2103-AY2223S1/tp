package seedu.uninurse.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TASK_DESCRIPTION = new Prefix("d/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_CONDITION = new Prefix("c/");
    public static final Prefix PREFIX_MEDICATION = new Prefix("m/");
    public static final Prefix PREFIX_REMARK = new Prefix("r/");
    public static final Prefix PREFIX_OPTION_PATIENT_INDEX = new Prefix("-p");
    public static final Prefix PREFIX_OPTION_TASK_INDEX = new Prefix("-d");
    public static final Prefix PREFIX_OPTION_TAG_INDEX = new Prefix("-t");
    public static final Prefix PREFIX_OPTION_CONDITION_INDEX = new Prefix("-c");
    public static final Prefix PREFIX_OPTION_MEDICATION_INDEX = new Prefix("-m");
    public static final Prefix PREFIX_OPTION_REMARK_INDEX = new Prefix("-r");

    public static final String SPECIAL_CHARACTER_ALL = "--all";
    public static final String SPECIAL_CHARACTER_TODAY = "--today";

    public static final Prefix[] PREFIXES_PATIENT_ALL = {
        PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
        PREFIX_TASK_DESCRIPTION, PREFIX_TAG, PREFIX_CONDITION, PREFIX_MEDICATION, PREFIX_REMARK
    };

    public static final Prefix[] PREFIXES_OPTION_ALL = {
        PREFIX_OPTION_PATIENT_INDEX, PREFIX_OPTION_TAG_INDEX, PREFIX_OPTION_TASK_INDEX,
        PREFIX_OPTION_CONDITION_INDEX, PREFIX_OPTION_MEDICATION_INDEX, PREFIX_OPTION_REMARK_INDEX
    };
}
