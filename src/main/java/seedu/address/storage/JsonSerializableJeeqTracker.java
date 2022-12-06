package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.JeeqTracker;
import seedu.address.model.ReadOnlyJeeqTracker;
import seedu.address.model.client.Client;

/**
 * An Immutable JeeqTracker that is serializable to JSON format.
 */
@JsonRootName(value = "jeeqtracker")
class JsonSerializableJeeqTracker {

    public static final String MESSAGE_DUPLICATE_CLIENT = "Clients list contains duplicate client(s).";

    private final List<JsonAdaptedClient> clients = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableJeeqTracker} with the given clients.
     */
    @JsonCreator
    public JsonSerializableJeeqTracker(@JsonProperty("clients") List<JsonAdaptedClient> clients) {
        this.clients.addAll(clients);
    }

    /**
     * Converts a given {@code ReadOnlyJeeqTracker} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableJeeqTracker}.
     */
    public JsonSerializableJeeqTracker(ReadOnlyJeeqTracker source) {
        clients.addAll(source.getClientList().stream().map(JsonAdaptedClient::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code JeeqTracker} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public JeeqTracker toModelType() throws IllegalValueException {
        JeeqTracker jeeqTracker = new JeeqTracker();
        for (JsonAdaptedClient jsonAdaptedClient : clients) {
            Client client = jsonAdaptedClient.toModelType();
            if (jeeqTracker.hasClient(client)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CLIENT);
            }
            jeeqTracker.addClient(client);
        }
        return jeeqTracker;
    }

}
