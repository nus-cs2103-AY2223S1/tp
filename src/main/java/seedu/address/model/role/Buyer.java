package seedu.address.model.role;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Encapsulates all Buyer attributes for a particular Buyer.
 */
public class Buyer {

    public final PriceRange priceRange;
    public final DesiredCharacteristics desiredCharacteristics;

    /**
     * Every field must be present for a Buyer to be correctly initialised.
     */
    public Buyer(PriceRange priceRange, DesiredCharacteristics desiredCharacteristics) {
        requireAllNonNull(priceRange, desiredCharacteristics);

        this.priceRange = priceRange;
        this.desiredCharacteristics = desiredCharacteristics;
    }

    public PriceRange getPriceRange() {
        return this.priceRange;
    }

    public DesiredCharacteristics getDesiredCharacteristics() {
        return this.desiredCharacteristics;
    }

    /**
     * Returns true if both buyers have the same price range and characteristics.
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
                && otherBuyer.getPriceRange().equals(this.priceRange)
                && otherBuyer.getDesiredCharacteristics().equals(this.desiredCharacteristics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.priceRange, this.desiredCharacteristics);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Buyer that has price range of: ")
                .append(priceRange)
                .append("; and desired characteristics: ")
                .append(desiredCharacteristics);
        return sb.toString();
    }
}
