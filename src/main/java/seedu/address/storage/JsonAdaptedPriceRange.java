package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.order.Price;
import seedu.address.model.order.PriceRange;

public class JsonAdaptedPriceRange {

    private Double upperBound;
    private Double lowerBound;

    public JsonAdaptedPriceRange(@JsonProperty("upperBound") Double upperBound,
                                 @JsonProperty("lowerBound") Double lowerBound) {
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
    }
    public JsonAdaptedPriceRange(PriceRange requestedPriceRange) {
        this.upperBound = requestedPriceRange.getUpperBound().getPrice();
        this.lowerBound = requestedPriceRange.getLowerBound().getPrice();
    }

    public PriceRange toModelType() {
        Price modelUpperBound = new Price(upperBound);
        Price modelLowerBound = new Price(lowerBound);
        return new PriceRange(modelUpperBound, modelLowerBound);
    }
}
