package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.teammate.Teammate;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_TEAMMATE = "Teammates list contains duplicate teammate(s).";

    private final List<JsonAdaptedTeammate> teammates = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given teammates.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("teammates") List<JsonAdaptedTeammate> teammates) {
        this.teammates.addAll(teammates);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        teammates.addAll(source.getTeammateList().stream().map(JsonAdaptedTeammate::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedTeammate jsonAdaptedTeammate : teammates) {
            Teammate teammate = jsonAdaptedTeammate.toModelType();
            if (addressBook.hasTeammate(teammate)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TEAMMATE);
            }
            addressBook.addTeammate(teammate);
        }
        return addressBook;
    }

}
