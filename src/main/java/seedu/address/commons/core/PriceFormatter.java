package seedu.address.commons.core;

import java.text.DecimalFormat;

/**
 * Formats price to a more user-friendly format.
 */
public class PriceFormatter {

    /**
     * Formats price to a more user-friendly format.
     * @param price price.
     * @return A user-friendly version of price.
     */
    public static String formatPrice(double price) {
        DecimalFormat df = new DecimalFormat("##,###");
        // Net Transaction Amount has decimals
        if (price % 1 != 0) {
            df = new DecimalFormat("##,###.00");
        }

        if (Math.abs(price) < 1) {
            df = new DecimalFormat("0.00");
        }

        return price < 0
                ? "-$" + df.format(Math.abs(price))
                : "$" + df.format(price);
    }
}
