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
import seedu.address.model.meeting.Meeting;
import seedu.address.model.product.Product;

/**
 * An Immutable MyInsuRec that is serializable to JSON format.
 */

@JsonRootName(value = "myinsurec")
class JsonSerializableMyInsuRec {

    public static final String MESSAGE_DUPLICATE_CLIENT = "Clients list contains duplicate client(s).";
    public static final String MESSAGE_DUPLICATE_MEETING = "Meetings list contains duplicate meeting(s)";
    public static final String MESSAGE_DUPLICATE_PRODUCT = "Products list contains duplicate product(s)";

    private final List<JsonAdaptedClient> clients = new ArrayList<>();
    private final List<JsonAdaptedProduct> products = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableMyInsuRec} with the given clients.
     */
    @JsonCreator
    public JsonSerializableMyInsuRec(@JsonProperty("clients") List<JsonAdaptedClient> clients,
                                     @JsonProperty("products") List<JsonAdaptedProduct> products) {
        this.clients.addAll(clients);
        this.products.addAll(products);
    }

    /**
     * Converts a given {@code ReadOnlyMyInsuRec} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableMyInsuRec}.
     */
    public JsonSerializableMyInsuRec(ReadOnlyMyInsuRec source) {
        clients.addAll(source.getClientList().stream().map(JsonAdaptedClient::new).collect(Collectors.toList()));
        products.addAll(source.getProductList().stream().map(JsonAdaptedProduct::new).collect(Collectors.toList()));
    }

    /**
     * Converts this MyInsuRec into the model's {@code MyInsuRec} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public MyInsuRec toModelType() throws IllegalValueException {
        MyInsuRec myInsuRec = new MyInsuRec();
        for (JsonAdaptedClient jsonAdaptedClient : clients) {
            Client client = jsonAdaptedClient.toModelType();
            addClientToModel(client, myInsuRec);
            for (Meeting meeting : client.getMeetings()) {
                addMeetingToModel(meeting, myInsuRec);
            }
        }
        for (JsonAdaptedProduct jsonAdaptedProduct : products) {
            Product product = jsonAdaptedProduct.toModelType();
            addProductToModel(product, myInsuRec);
        }
        return myInsuRec;
    }

    private void addMeetingToModel(Meeting meeting, MyInsuRec mir) throws IllegalValueException {
        if (mir.hasMeeting(meeting)) {
            throw new IllegalValueException(MESSAGE_DUPLICATE_MEETING);
        }
        mir.addMeeting(meeting);
    }

    private void addClientToModel(Client client, MyInsuRec mir) throws IllegalValueException {
        if (mir.hasClient(client)) {
            throw new IllegalValueException(MESSAGE_DUPLICATE_CLIENT);
        }
        mir.addClient(client);
    }

    private void addProductToModel(Product product, MyInsuRec mir) throws IllegalValueException {
        if (mir.hasProduct(product)) {
            throw new IllegalValueException(MESSAGE_DUPLICATE_PRODUCT);
        }
        mir.addProduct(product);
    }

}
