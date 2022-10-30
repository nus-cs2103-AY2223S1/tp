package seedu.boba.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.boba.commons.exceptions.IllegalValueException;
import seedu.boba.model.BobaBot;
import seedu.boba.model.ReadOnlyBobaBot;
import seedu.boba.model.customer.Customer;

/**
 * An Immutable BobaBot that is serializable to JSON format.
 */
@JsonRootName(value = "bobabot")
class JsonSerializableBobaBot {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate customer(s).";

    private final List<JsonAdaptedCustomer> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableBobaBot} with the given persons.
     */
    @JsonCreator
    public JsonSerializableBobaBot(@JsonProperty("persons") List<JsonAdaptedCustomer> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyBobaBot} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableBobaBot}.
     */
    public JsonSerializableBobaBot(ReadOnlyBobaBot source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedCustomer::new).collect(Collectors.toList()));
    }

    /**
     * Converts this bobabot into the bobaBotModel's {@code BobaBot} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public BobaBot toModelType() throws IllegalValueException {
        BobaBot bobaBot = new BobaBot();
        for (JsonAdaptedCustomer jsonAdaptedCustomer : persons) {
            Customer customer = jsonAdaptedCustomer.toModelType();
            if (bobaBot.hasPerson(customer)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            bobaBot.addPerson(customer);
        }
        return bobaBot;
    }

}
