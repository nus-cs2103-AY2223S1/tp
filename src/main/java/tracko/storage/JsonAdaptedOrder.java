package tracko.storage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import tracko.commons.exceptions.IllegalValueException;
import tracko.model.item.InventoryList;
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
    private final LocalDateTime timeCreated;
    private final List<JsonAdaptedItemQuantityPair> itemList = new ArrayList<>();
    private final boolean isPaid;
    private final boolean isDelivered;

    /**
     * Constructs a {@code JsonAdaptedOrder} with the given order details.
     */
    @JsonCreator
    public JsonAdaptedOrder(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                            @JsonProperty("email") String email, @JsonProperty("address") String address,
                            @JsonProperty("timeCreated") LocalDateTime timeCreated,
                            @JsonProperty("itemList") List<JsonAdaptedItemQuantityPair> itemList,
                            @JsonProperty("isPaid") boolean isPaid, @JsonProperty("isDelivered") boolean isDelivered) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.timeCreated = timeCreated;
        if (itemList != null) {
            this.itemList.addAll(itemList);
        }
        this.isPaid = isPaid;
        this.isDelivered = isDelivered;
    }

    /**
     * Converts a given {@code Order} into this class for Jackson use.
     */
    public JsonAdaptedOrder(Order source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        timeCreated = source.getTimeCreated();
        source.getItemList().stream()
            .forEach(item -> itemList.add(new JsonAdaptedItemQuantityPair(item)));
        this.isPaid = source.getPaidStatus();
        this.isDelivered = source.getDeliveryStatus();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Order} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted order.
     */
    public Order toModelType(InventoryList inventoryList) throws IllegalValueException {

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

        if (timeCreated == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LocalDateTime.class.getSimpleName()));
        }

        if (itemList == null || itemList.isEmpty()) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Item List"));
        }

        List<ItemQuantityPair> itemQuantityPairs = new ArrayList<>();
        if (isPaid && isDelivered) {
            for (JsonAdaptedItemQuantityPair pair : itemList) {
                itemQuantityPairs.add(pair.toModelType());
            }
        } else {
            for (JsonAdaptedItemQuantityPair pair : itemList) {
                itemQuantityPairs.add(pair.toModelType(inventoryList));
            }
        }

        return new Order(modelName, modelPhone, modelEmail, modelAddress, timeCreated, itemQuantityPairs,
            isPaid, isDelivered);
    }
}
