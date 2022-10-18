package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.MyInsuRec;
import seedu.address.model.ReadOnlyMyInsuRec;
import seedu.address.model.client.Client;
import seedu.address.model.tag.Tag;

/**
 * An Immutable MyInsuRec that is serializable to JSON format.
 */

@JsonRootName(value = "myinsurec")
class JsonSerializableMyInsuRec {

    public static final String MESSAGE_DUPLICATE_CLIENT = "Clients list contains duplicate client(s).";

    private final List<JsonAdaptedClient> clients = new ArrayList<>();
    private final List<JsonAdaptedTag> products = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableMyInsuRec} with the given clients.
     */
    @JsonCreator
    public JsonSerializableMyInsuRec(@JsonProperty("clients") List<JsonAdaptedClient> clients,
                                     @JsonProperty("products") List<JsonAdaptedTag> tags) {
        this.clients.addAll(clients);
        this.products.addAll(tags);
    }

    /**
     * Converts a given {@code ReadOnlyMyInsuRec} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableMyInsuRec}.
     */
    public JsonSerializableMyInsuRec(ReadOnlyMyInsuRec source) {
        clients.addAll(source.getClientList().stream().map(JsonAdaptedClient::new).collect(Collectors.toList()));
        products.addAll(source.getProductList().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()));
    }

    /**
     * Converts this MyInsuRec into the model's {@code MyInsuRec} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public MyInsuRec toModelType() throws IllegalValueException {
        MyInsuRec myInsuRec = new MyInsuRec();
        for (JsonAdaptedClient jsonAdaptedClient : clients) {
            Client person = jsonAdaptedClient.toModelType();
            if (myInsuRec.hasClient(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CLIENT);
            }
            myInsuRec.addClient(person);
            if (person.hasMeeting()) {
                myInsuRec.addMeeting(person.getMeeting());
            }
        }
        for (JsonAdaptedTag jsonAdaptedTag : products) {
            Tag product = jsonAdaptedTag.toModelType();
            myInsuRec.addProduct(product);
        }
        return myInsuRec;
    }

}
