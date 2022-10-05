package tracko.storage;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import tracko.commons.exceptions.IllegalValueException;
import tracko.model.order.Address;
import tracko.model.order.Email;
import tracko.model.order.ItemQuantityPair;
import tracko.model.order.Name;
import tracko.model.order.Order;
import tracko.model.order.Phone;



/**
 * Jackson-friendly version of {@link Order}.
 */
public class JsonAdaptedOrder {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Orders's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final List<JsonAdaptedItemQuantityPair> itemList = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedOrder} with the given order details.
     */
    @JsonCreator
    public JsonAdaptedOrder(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                            @JsonProperty("email") String email, @JsonProperty("address") String address,
                            @JsonProperty("itemList") List<JsonAdaptedItemQuantityPair> itemList) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.itemList.addAll(itemList);
    }

    /**
     * Converts a given {@code Order} into this class for Jackson use.
     */
    public JsonAdaptedOrder(Order source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        source.getItemList().stream()
            .forEach(item -> itemList.add(new JsonAdaptedItemQuantityPair(item)));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Order toModelType() throws IllegalValueException {

        List<ItemQuantityPair> itemQuantityPairs = new ArrayList<>();
        for (JsonAdaptedItemQuantityPair pair : itemList) {
            itemQuantityPairs.add(pair.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        return new Order(modelName, modelPhone, modelEmail, modelAddress, itemQuantityPairs);
    }
}
