package seedu.address.logic.commands;

/**
 * Provides a range of acceptable parameters for sort command.
 */
public abstract class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String[] ACCEPTABLE_BUYER_PARAMETER = new String[] {
            "BUYER",
            "B",
            "-B",
            "/B"
    };

    public static final String[] ACCEPTABLE_DELIVERER_PARAMETER = new String[] {
            "DELIVERER",
            "D",
            "-D",
            "/D"
    };

    public static final String[] ACCEPTABLE_SUPPLIER_PARAMETER = new String[] {
            "SUPPLIER",
            "S",
            "-S",
            "/S"
    };

    public static final String[] ACCEPTABLE_ORDER_PARAMETER = new String[] {
            "ORDER",
            "O",
            "-O",
            "/O"
    };

    public static final String[] ACCEPTABLE_PET_PARAMETER = new String[] {
            "PET",
            "P",
            "-P",
            "/P"
    };

    public static final String[] ACCEPTABLE_SORT_ADDRESS_PARAMETER = new String[] {
            "ADDRESS",
            "ADDR",
            "A",
            "-A",
            "/A",
    };

    public static final String[] ACCEPTABLE_SORT_EMAIL_PARAMETER = new String[] {
            "EMAIL",
            "EMA",
            "EM",
            "E",
            "-E",
            "/E",
    };

    public static final String[] ACCEPTABLE_SORT_NAME_PARAMETER = new String[] {
            "NAME",
            "NA",
            "N",
            "-N",
            "/N"
    };

    public static final String[] ACCEPTABLE_SORT_LOCATION_PARAMETER = new String[] {
            "LOCATION",
            "LOC",
            "L",
            "-L",
            "/L"
    };

    public static final String[] ACCEPTABLE_SORT_PHONE_PARAMETER = new String[] {
            "PHONE",
            "PH",
            "P",
            "-P",
            "/P"
    };

    public static boolean isValidParameter(String[] acceptable_parameters, String parameter) {
        parameter = parameter.toUpperCase();
        for (String para: acceptable_parameters) {
            if (parameter.equals(para)) {
                return true;
            }
        }
        return false;
    }

}
