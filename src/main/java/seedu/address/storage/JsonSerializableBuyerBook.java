package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.BuyerBook;
import seedu.address.model.ReadOnlyBuyerBook;
import seedu.address.model.buyer.Buyer;

/**
 * An Immutable BuyerBook that is serializable to JSON format.
 */
@JsonRootName(value = "buyerbook")
class JsonSerializableBuyerBook {

    public static final String MESSAGE_DUPLICATE_BUYER = "Buyers list contains duplicate buyer(s).";

    private final List<JsonAdaptedBuyer> buyers = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableBuyerBook} with the given buyers.
     */
    @JsonCreator
    public JsonSerializableBuyerBook(@JsonProperty("buyers") List<JsonAdaptedBuyer> buyers) {
        this.buyers.addAll(buyers);
    }

    /**
     * Converts a given {@code ReadOnlyBuyerBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableBuyerBook}.
     */
    public JsonSerializableBuyerBook(ReadOnlyBuyerBook source) {
        buyers.addAll(source.getBuyerList().stream().map(JsonAdaptedBuyer::new).collect(Collectors.toList()));
    }

    /**
     * Converts this buyer book into the model's {@code BuyerBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public BuyerBook toModelType() throws IllegalValueException {
        BuyerBook buyerBook = new BuyerBook();
        for (JsonAdaptedBuyer jsonAdaptedBuyer : buyers) {
            Buyer buyer = jsonAdaptedBuyer.toModelType();
            if (buyerBook.hasBuyer(buyer)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_BUYER);
            }
            buyerBook.addBuyer(buyer);
        }
        return buyerBook;
    }

}
