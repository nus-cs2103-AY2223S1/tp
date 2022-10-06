package seedu.address.model.role;

/**
 * Placeholder
 */
public class Buyer {
    private PriceRange priceRange;
    private Characteristics characteristics;

    /**
     * Placeholder
     */
    public Buyer(PriceRange priceRange, Characteristics characteristics) {
        this.priceRange = priceRange;
        this.characteristics = characteristics;
    }

    public String getRange() {
        return priceRange == null
                ? ""
                : priceRange.toString();
    }

    public String getCharacteristics() {
        return characteristics.toString();
    }
}
