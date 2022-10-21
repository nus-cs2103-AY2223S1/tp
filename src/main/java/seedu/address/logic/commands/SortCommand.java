package seedu.address.logic.commands;

/**
 * Sorts the selected list.
 */
public abstract class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    private static final String[] ACCEPTABLE_SORT_ADDRESS_PARAMETER = new String[]{
            "ADDRESS",
            "ADDR",
            "A",
            "-A",
            "/A",
    };

    private static final String[] ACCEPTABLE_SORT_EMAIL_PARAMETER = new String[] {
            "EMAIL",
            "EMAI",
            "EMA",
            "EM",
            "E",
            "-E",
            "/E",
    };

    private static final String[] ACCEPTABLE_SORT_NAME_PARAMETER = new String[] {
            "NAME",
            "NAM",
            "N",
            "-N",
            "/N"
    };

    private static final String[] ACCEPTABLE_SORT_LOCATION_PARAMETER = new String[] {
            "LOCATION",
            "LOC",
            "L",
            "-L",
            "/L"
    };

    private static final String[] ACCEPTABLE_SORT_PHONE_PARAMETER = new String[] {
            "PHONE",
            "PH",
            "P",
            "-P",
            "/P"
    };



}
