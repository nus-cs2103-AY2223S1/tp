package seedu.condonery.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.condonery.commons.exceptions.IllegalValueException;
import seedu.condonery.model.client.Client;
import seedu.condonery.model.client.ClientDirectory;
import seedu.condonery.model.client.ReadOnlyClientDirectory;

/**
 * An Immutable ClientDirectory that is serializable to JSON format.
 */
@JsonRootName(value = "clientDirectory")
class JsonSerializableClientDirectory {

    public static final String MESSAGE_DUPLICATE_PROPERTY = "Properties list contains duplicate client(s).";

    private final List<JsonAdaptedClient> clients = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableClientDirectory} with the given clients.
     */
    @JsonCreator
    public JsonSerializableClientDirectory(@JsonProperty("clients") List<JsonAdaptedClient> clients) {
        this.clients.addAll(clients);
    }

    /**
     * Converts a given {@code ReadOnlyClientDirectory} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableClientDirectory}.
     */
    public JsonSerializableClientDirectory(ReadOnlyClientDirectory source) {
        clients.addAll(source.getClientList().stream().map(JsonAdaptedClient::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code ClientDirectory} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ClientDirectory toModelType() throws IllegalValueException {
        ClientDirectory clientDirectory = new ClientDirectory();
        for (JsonAdaptedClient jsonAdaptedClient : clients) {
            Client client = jsonAdaptedClient.toModelType();
            if (clientDirectory.hasClient(client)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PROPERTY);
            }
            clientDirectory.addClient(client);
        }
        return clientDirectory;
    }

}
