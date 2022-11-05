package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Name;
import seedu.address.model.Pin;
import seedu.address.model.client.Client;
import seedu.address.model.client.ClientEmail;
import seedu.address.model.client.ClientId;
import seedu.address.model.client.ClientMobile;
import seedu.address.model.project.Project;


/**
 * Jackson-friendly version of {@link Client}.
 */
class JsonAdaptedClient {

    private final String name;
    private final String mobile;
    private final String email;
    private final String clientId;
    private final String pin;

    /**
     * Constructs a {@code JsonAdaptedClient} with the given client details.
     */
    @JsonCreator
    public JsonAdaptedClient(@JsonProperty("name") String name, @JsonProperty("mobile") String mobile,
                             @JsonProperty("email") String email,
                             @JsonProperty("clientId") String clientId,
                             @JsonProperty("pin") String pin) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.clientId = clientId;
        this.pin = pin;
    }

    /**
     * Converts a given {@code Client} into this class for Jackson use.
     */
    public JsonAdaptedClient(Client source) {
        name = source.getClientName().toString();
        mobile = source.getClientMobile().toString();
        email = source.getClientEmail().toString();
        clientId = source.getClientId().toString();
        pin = String.valueOf(source.isPinned());
    }

    /**
     * Converts this Jackson-friendly adapted client object into the model's {@code Client} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted client.
     */
    public Client toModelType() throws IllegalValueException {
        final List<Project> clientProjects = new ArrayList<>();
        final Name modelName = StorageUtil.readNameFromStorage(name, Client.class.getSimpleName());
        final ClientMobile modelMobile = StorageUtil.readMobileFromStorage(mobile);
        final ClientEmail modelEmail = StorageUtil.readEmailFromStorage(email);
        final Pin modelPin = StorageUtil.readPinFromStorage(pin, Client.class.getSimpleName());
        final ClientId modelClientId = StorageUtil.readClientIdFromStorage(clientId);
        return new Client(modelName, modelMobile, modelEmail, clientProjects, modelClientId, modelPin);
    }

}
