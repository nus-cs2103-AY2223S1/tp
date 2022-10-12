package seedu.address.model.role;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Encapsulates all Seller attributes for a particular Buyer.
 */
public class Seller {

    public final Properties sellerProperties;

    /**
     * Every field must be present for a Buyer to be correctly initialised.
     */
    public Seller(Properties sellerProperties) {
        requireAllNonNull(sellerProperties);

        this.sellerProperties = sellerProperties;

    }

    // Placeholder: Currently just returning an ArrayList
    // TODO: Return properties
    public Properties getSellerProperties() {
        return this.sellerProperties;
    }

    /**
     * Returns true if both sellers have the same list of properties that are being sold.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Buyer)) {
            return false;
        }

        Buyer otherBuyer = (Buyer) other;
        return otherBuyer != null
                && otherBuyer.getPriceRange().equals(this.sellerProperties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.sellerProperties);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Seller that has these properties under their charge: ")
                .append(this.sellerProperties);
        return sb.toString();
    }
}
