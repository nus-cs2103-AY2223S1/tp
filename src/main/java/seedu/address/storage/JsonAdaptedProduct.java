package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.product.Product;

/**
 * Jackson-friendly version of {@link Product}.
 */
public class JsonAdaptedProduct {

    private final String productName;

    /**
     * Constructs a {@code JsonAdaptedProduct} with the given {@code productName}.
     */
    @JsonCreator
    public JsonAdaptedProduct(String productName) {
        this.productName = productName;
    }

    /**
     * Converts a given {@code Product} into this class for Jackson use.
     */
    public JsonAdaptedProduct(Product source) {
        productName = source.productName;
    }

    @JsonValue
    public String getProductName() {
        return productName;
    }

    /**
     * Converts this Jackson-friendly adapted product object into the model's {@code Product} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted product.
     */
    public Product toModelType() throws IllegalValueException {
        if (!Product.isValidProductName(productName)) {
            throw new IllegalValueException(Product.MESSAGE_CONSTRAINTS);
        }
        return new Product(productName);
    }
}
