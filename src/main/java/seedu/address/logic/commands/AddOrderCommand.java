package seedu.address.logic.commands;

import seedu.address.logic.parser.Prefix;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

public class AddOrderCommand {


    public static final Prefix PREFIX_ORDER_STATUS = new Prefix("o_s/");
    public static final Prefix PREFIX_ORDER_REQUESTS = new Prefix("o_r/");
    public static final Prefix PREFIX_ORDER_PRICE = new Prefix("o_p/");
    public static final Prefix PREFIX_ORDER_PRICE_RANGE = new Prefix("o_pr/");
    public static final Prefix PREFIX_ORDER_ADDITIONAL_REQUESTS = new Prefix("o_ar/");
    public static final Prefix PREFIX_ORDER_DATE = new Prefix("o_d/");
    public static final Prefix PREFIX_ORDER_PET = new Prefix("o_pt/");

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
            + PREFIX_ORDER_REQUESTS + "It should be a chihuahua. "
            + PREFIX_ORDER_PRICE + "6.8 "
            + PREFIX_ORDER_PRICE_RANGE + "5.4-8.0 "
            + PREFIX_ORDER_DATE + "2022-09-30 "
            + PREFIX_ORDER_PET + "(...Pet fields) "
            + PREFIX_ORDER_ADDITIONAL_REQUESTS + "Free delivery"
            + PREFIX_ORDER_ADDITIONAL_REQUESTS + "Vaccination certified";
}
