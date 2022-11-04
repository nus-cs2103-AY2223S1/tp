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

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Client's %s field is missing!";

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

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }

        if (name.isEmpty()) {
            return Client.EmptyClient.EMPTY_CLIENT;
        }

        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        ClientMobile modelMobile;

        if (mobile == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ClientMobile.class.getSimpleName()));
        }

        if (mobile.isEmpty()) {
            modelMobile = ClientMobile.EmptyClientMobile.EMPTY_MOBILE;
        } else {
            if (!ClientMobile.isValidClientMobile(mobile)) {
                throw new IllegalValueException(ClientMobile.MESSAGE_CONSTRAINTS);
            }
            modelMobile = new ClientMobile(mobile);
        }

        ClientEmail modelEmail;

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ClientEmail.class.getSimpleName()));
        }

        if (email.isEmpty()) {
            modelEmail = ClientEmail.EmptyEmail.EMPTY_EMAIL;
        } else {
            if (!ClientEmail.isValidEmail(email)) {
                throw new IllegalValueException(ClientEmail.MESSAGE_CONSTRAINTS);
            }
            modelEmail = new ClientEmail(email);
        }

        Pin modelPin;
        if (pin == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Pin.class.getSimpleName()));
        }
        if (!Pin.isValidPin(pin)) {
            throw new IllegalValueException(Pin.MESSAGE_CONSTRAINTS);
        }
        modelPin = new Pin(Boolean.parseBoolean(pin));

        if (clientId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ClientId.class.getSimpleName()));
        }

        if (!ClientId.isValidClientId(clientId)) {
            throw new IllegalValueException(ClientId.MESSAGE_CONSTRAINTS);
        }

        final ClientId modelClientId = new ClientId(Integer.parseInt(clientId));

        assert modelClientId.getIdInt() >= 0 : "Client ID should be positive";

        return new Client(modelName, modelMobile, modelEmail, clientProjects, modelClientId, modelPin);
    }

}
