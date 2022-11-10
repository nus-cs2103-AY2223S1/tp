package seedu.address.logic.commands.util;

/**
 * Provides utils for some Command classes.
 */
public class CommandUtil {
    public static final String[] ACCEPTABLE_BUYER_PARAMETER = new String[]{"BUYERS", "BUYER", "B"};

    public static final String[] ACCEPTABLE_DELIVERER_PARAMETER = new String[]{
        "DELIVERERS", "DELIVERER", "D"};

    public static final String[] ACCEPTABLE_SUPPLIER_PARAMETER = new String[]{"SUPPLIERS", "SUPPLIER", "S"};

    public static final String[] ACCEPTABLE_ORDER_PARAMETER = new String[]{"ORDERS", "ORDER", "O"};

    public static final String[] ACCEPTABLE_PET_PARAMETER = new String[]{"PETS", "PET", "P"};
    public static final String[] ACCEPTABLE_ALL_PARAMETER = new String[]{"ALL", "A"};

    public static final String[] ACCEPTABLE_SORT_ORDER_SIZE_PARAMETER = new String[]{
        "ORDERS", "ORDER"};

    public static final String[] ACCEPTABLE_SORT_PET_LIST_SIZE_PARAMETER = new String[]{"PETS", "PET"};

    public static final String[] ACCEPTABLE_SORT_ADDRESS_PARAMETER = new String[]{"ADDRESS", "ADDR", "A"};

    public static final String[] ACCEPTABLE_SORT_EMAIL_PARAMETER = new String[]{"EMAIL", "EMA", "EM", "E"};

    public static final String[] ACCEPTABLE_SORT_NAME_PARAMETER = new String[]{"NAME", "NA", "N"};

    public static final String[] ACCEPTABLE_SORT_LOCATION_PARAMETER = new String[]{"LOCATION", "LOC", "L"};

    public static final String[] ACCEPTABLE_SORT_PHONE_PARAMETER = new String[]{"PHONE", "PH"};

    public static final String[] ACCEPTABLE_SORT_PRICE_RANGE_PARAMETER = new String[]{"PRICERANGE", "PRANGE", "PRICER",
        "PR"};
    public static final String[] ACCEPTABLE_SORT_DUE_DATE_PARAMETER = new String[]{"DUEDATE", "DUE", "BY", "DATE",
        "BYDATE", "D"};
    public static final String[] ACCEPTABLE_SORT_PRICE_PARAMETER = new String[]{"PRICE", "P"};
    public static final String[] ACCEPTABLE_SORT_STATUS_PARAMETER = new String[]{"ORDERSTATUS", "STATUS", "OS", "S"};
    public static final String[] ACCEPTABLE_SORT_COLOR_PARAMETER = new String[]{"COLOR", "C"};
    public static final String[] ACCEPTABLE_SORT_COLOR_PATTERN_PARAMETER = new String[]{"COLORPATTERN", "CPATTERN",
        "COLORP", "CP"};
    public static final String[] ACCEPTABLE_SORT_BIRTH_DATE_PARAMETER = new String[]{"BIRTHDATE", "BDATE", "BIRTHD",
        "DATE", "BD"};
    public static final String[] ACCEPTABLE_SORT_SPECIES_PARAMETER = new String[]{"SPECIES", "S"};
    public static final String[] ACCEPTABLE_SORT_HEIGHT_PARAMETER = new String[]{"HEIGHT", "H"};
    public static final String[] ACCEPTABLE_SORT_WEIGHT_PARAMETER = new String[]{"WEIGHT", "W"};

    /**
     * Checks if a given parameter matches any parameter in a given acceptable parameters array.
     * @param acceptableParameters The given parameter array.
     * @param parameter The given parameter.
     * @return The boolean value.
     */
    public static boolean isValidParameter(String[] acceptableParameters, String parameter) {
        parameter = parameter.toUpperCase();
        for (String para : acceptableParameters) {
            if (parameter.equals(para)) {
                return true;
            }
        }
        return false;
    }
}
