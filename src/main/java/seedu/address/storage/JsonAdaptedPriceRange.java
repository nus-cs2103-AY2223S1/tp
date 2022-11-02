package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.order.Price;
import seedu.address.model.order.PriceRange;

/**
 * Jackson-friendly version of {@link PriceRange}.
 */
public class JsonAdaptedPriceRange {

    private Double upperBound;
    private Double lowerBound;

    /**
     * Constructs a {@code JsonAdaptedPriceRange} with the given upperbound and lowerbound.
     */
    public JsonAdaptedPriceRange(@JsonProperty("upperBound") Double upperBound,
                                 @JsonProperty("lowerBound") Double lowerBound) {
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
    }

    /**
     * Converts a given {@code PriceRange} into this class for Jackson use.
     */
    public JsonAdaptedPriceRange(PriceRange requestedPriceRange) {
        this.upperBound = requestedPriceRange.getUpperBound().getPrice();
        this.lowerBound = requestedPriceRange.getLowerBound().getPrice();
    }

    /**
     * Converts this Jackson-friendly adapted PriceRange object into the model's {@code PriceRange} object.
     */
    public PriceRange toModelType() throws ParseException {
        if (lowerBound == null || upperBound == null || lowerBound < 0 || upperBound < 0) {
            throw new ParseException(PriceRange.MESSAGE_USAGE);
        }
        Price modelUpperBound = new Price(upperBound);
        Price modelLowerBound = new Price(lowerBound);
        return new PriceRange(modelLowerBound, modelUpperBound);
    }
}
