package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_ADDITIONAL_REQUESTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_PET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_PRICE_RANGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_REQUESTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_STATUS;

/**
 * Adds an order to the
 */
public class AddOrderCommand {

    public static final String COMMAND_WORD = "add-o";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a standalone order or an order affiliated to a buyer. "
            + "Parameters: "
            + PREFIX_ORDER_STATUS + "STATUS "
            + PREFIX_ORDER_REQUESTS + "REQUEST "
            + PREFIX_ORDER_PRICE + "PRICE "
            + PREFIX_ORDER_PRICE_RANGE + "PRICE_RANGE "
            + PREFIX_ORDER_DATE + "DATE "
            + PREFIX_ORDER_PET + "PET "
            + "[" + PREFIX_ORDER_ADDITIONAL_REQUESTS + "ADDITIONAL_REQUEST]...\n"

            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ORDER_STATUS + "Pending "
            + PREFIX_ORDER_REQUESTS + "...(Request fields) "
            + PREFIX_ORDER_PRICE + "6.8 "
            + PREFIX_ORDER_PRICE_RANGE + "5.4,8.0 "
            + PREFIX_ORDER_DATE + "2022-09-30 "
            + PREFIX_ORDER_PET + "(...Pet fields) "
            + PREFIX_ORDER_ADDITIONAL_REQUESTS + "Free delivery "
            + PREFIX_ORDER_ADDITIONAL_REQUESTS + "Vaccination certified";
}
