package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_ADDITIONAL_REQUESTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_COLOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_COLOR_PATTERN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_PRICE_RANGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_REQUESTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_SPECIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_STATUS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import java.util.List;

import seedu.address.model.order.Order;
import seedu.address.model.order.PriceRange;
import seedu.address.model.order.Request;

/**
 * A utility class for Order.
 */
public class OrderUtil {

    /**
     * Returns the part of command string for the given {@code Order}'s details.
     */
    public static String getOrderBody(Order order) {
        StringBuilder sb = new StringBuilder();
        sb.append(INDEX_FIRST.getOneBased() + " ");
        sb.append(PREFIX_ORDER_STATUS + order.getOrderStatus().getStatus() + " ");
        sb.append(PREFIX_ORDER_REQUESTS + "add-r " + getRequestBody(order.getRequest()) + " ");
        sb.append(PREFIX_ORDER_PRICE + Double.toString(order.getSettledPrice().getPrice()) + " ");
        sb.append(PREFIX_ORDER_PRICE_RANGE + getPriceRange(order.getRequestedPriceRange()) + " ");
        sb.append(PREFIX_ORDER_DATE + order.getByDate().toString() + " ");
        List<String> additionalRequestsList = order.getAdditionalRequests().getAdditionalRequestsToString();
        for (String additionalRequests : additionalRequestsList) {
            sb.append(PREFIX_ORDER_ADDITIONAL_REQUESTS + additionalRequests + " ");
        }
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code Request}'s details.
     */
    public static String getRequestBody(Request request) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_ORDER_AGE + Integer.toString(request.getRequestedAge().getValue()) + " ");
        sb.append(PREFIX_ORDER_SPECIES + request.getRequestedSpecies().getValue() + " ");
        sb.append(PREFIX_ORDER_COLOR + request.getRequestedColor().getValue() + " ");
        sb.append(PREFIX_ORDER_COLOR_PATTERN + request.getRequestedColorPattern().getValue() + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code PriceRange}'s details.
     */
    public static String getPriceRange(PriceRange priceRange) {
        return priceRange.getLowerBound().getPrice() + "," + priceRange.getUpperBound().getPrice();
    }

}
