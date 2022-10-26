package seedu.foodrem.testutil;

/**
 * A utility class for validating messages to users.
 */
public class MessageToUser {
    // Item name validation
    public static final String MESSAGE_FOR_NAME_IS_BLANK =
            "The item name should not be blank.";
    public static final String MESSAGE_FOR_NAME_TOO_LONG =
            "The item name should not exceed 200 characters";
    public static final String MESSAGE_FOR_INVALID_CHARACTERS_IN_NAME =
            "The item name should only contain alphanumeric characters and spaces. It should not start with a blank "
                    + "space.";

    // Item quantity validation
    public static final String MESSAGE_FOR_QUANTITY_NOT_A_NUMBER =
            "The item quantity should be a number.";
    public static final String MESSAGE_FOR_QUANTITY_IS_NEGATIVE =
            "The item quantity should not be negative.";
    public static final String MESSAGE_FOR_QUANTITY_PRECISION_TOO_HIGH =
            "The item quantity should not have more than 4 decimal places.";
    public static final String MESSAGE_FOR_QUANTITY_TOO_LARGE =
            "The item quantity should not be more than 1,000,000.";
    public static final String MESSAGE_FOR_FINAL_QUANTITY_TOO_LARGE =
            "(Final Quantity) " + MESSAGE_FOR_QUANTITY_TOO_LARGE;
    public static final String MESSAGE_FOR_FINAL_QUANTITY_IS_NEGATIVE =
            "(Final Quantity) " + MESSAGE_FOR_QUANTITY_IS_NEGATIVE;

    // Item unit validation
    public static final String MESSAGE_FOR_INVALID_CHARACTERS_IN_UNIT =
            "The item unit should only contain alphanumeric characters and spaces.";
    public static final String MESSAGE_FOR_UNIT_TOO_LONG =
            "The item unit should not exceed 10 characters.";

    // Bought date
    public static final String MESSAGE_FOR_UNABLE_TO_PARSE_BOUGHT_DATE =
            "The item bought date must follow the format dd-mm-yyyy.";
    public static final String MESSAGE_FOR_BOUGHT_DATE_YEAR_TOO_SMALL =
            "The year for item bought date should be larger than or equal to 1900.";
    public static final String MESSAGE_FOR_BOUGHT_DATE_YEAR_TOO_LARGE =
            "The year for item bought date should be less than or equal to 2300.";

    // Expiry date
    public static final String MESSAGE_FOR_UNABLE_TO_PARSE_EXPIRY_DATE =
            "The item expiry date must follow the format dd-mm-yyyy.";
    public static final String MESSAGE_FOR_EXPIRY_DATE_YEAR_TOO_SMALL =
            "The year for item expiry date should be larger than or equal to 1900.";
    public static final String MESSAGE_FOR_EXPIRY_DATE_YEAR_TOO_LARGE =
            "The year for item expiry date should be less than or equal to 2300.";

    // Price
    public static final String MESSAGE_FOR_UNABLE_TO_PARSE_PRICE =
            "The item price should be a number.";

    // Remarks
    public static final String MESSAGE_FOR_INVALID_CHARACTERS_IN_REMARKS =
            "The item remark should only contain alphanumeric characters and spaces. "
                    + "It should not start with a blank space.";
    private static final String MESSAGE_FOR_REMARKS_TOO_LONG =
            "The item remark should not exceed 1000 characters";
}
