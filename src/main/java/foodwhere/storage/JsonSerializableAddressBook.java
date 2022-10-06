package foodwhere.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import foodwhere.commons.exceptions.IllegalValueException;
import foodwhere.model.AddressBook;
import foodwhere.model.ReadOnlyAddressBook;
import foodwhere.model.stall.Stall;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_STALL = "Stalls list contains duplicate stall(s).";

    private final List<JsonAdaptedStall> stalls = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given stalls.
     */
    @JsonCreator public JsonSerializableAddressBook(@JsonProperty("stalls") List<JsonAdaptedStall> stalls) {
        this.stalls.addAll(stalls);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        stalls.addAll(source.getStallList().stream().map(JsonAdaptedStall::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedStall jsonAdaptedStall : stalls) {
            Stall stall = jsonAdaptedStall.toModelType();
            if (addressBook.hasStall(stall)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STALL);
            }
            addressBook.addStall(stall);
        }
        return addressBook;
    }

}
