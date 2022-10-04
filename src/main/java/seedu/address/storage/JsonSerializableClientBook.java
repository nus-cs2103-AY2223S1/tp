package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ClientBook;
import seedu.address.model.ReadOnlyClientBook;
import seedu.address.model.client.Client;

/**
 * An Immutable ClientBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableClientBook {

    public static final String MESSAGE_DUPLICATE_CLIENT = "Persons list contains duplicate client(s).";

    private final List<JsonAdaptedClient> clients = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableClientBook} with the given clients.
     */
    @JsonCreator
    public JsonSerializableClientBook(@JsonProperty("persons") List<JsonAdaptedClient> clients) {
        this.clients.addAll(clients);
    }

    /**
     * Converts a given {@code ReadOnlyClientBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableClientBook}.
     */
    public JsonSerializableClientBook(ReadOnlyClientBook source) {
        clients.addAll(source.getClientList().stream().map(JsonAdaptedClient::new).collect(Collectors.toList()));
    }

    /**
     * Converts this client book into the model's {@code ClientBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ClientBook toModelType() throws IllegalValueException {
        ClientBook clientBook = new ClientBook();
        for (JsonAdaptedClient jsonAdaptedPerson : clients) {
            Client person = jsonAdaptedPerson.toModelType();
            if (clientBook.hasClient(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CLIENT);
            }
            clientBook.addClient(person);
        }
        return clientBook;
    }

}
