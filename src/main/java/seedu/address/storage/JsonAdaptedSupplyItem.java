package seedu.address.storage;

import static seedu.address.commons.util.IntegerUtil.getStringFromInt;
import static seedu.address.commons.util.StringUtil.getIntFromString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.item.SupplyItem;
import seedu.address.model.person.Address;
import seedu.address.model.person.Item;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Price;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link SupplyItem}
 */
public class JsonAdaptedSupplyItem {
    private final String name;
    private final String currentStock;
    private final String minStock;
    private final String supplierName;
    private final String price;
    private final String phone;
    private final String personItem;
    private final String address;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedSupplyItem} with the given supply item details.
     */
    @JsonCreator
    public JsonAdaptedSupplyItem(@JsonProperty("name") String name, @JsonProperty("currentStock") String currentStock,
                           @JsonProperty("minStock") String minStock,
                           @JsonProperty("supplier") String supplierName,
                           @JsonProperty("phone") String phone,
                           @JsonProperty("price") String price, @JsonProperty("item") String personItem ,
                           @JsonProperty("address") String address,
                           @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.currentStock = currentStock;
        this.minStock = minStock;
        this.supplierName = supplierName;
        this.phone = phone;
        this.price = price;
        this.personItem = personItem;
        this.address = address;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code SupplyItem} into this class for Jackson use.
     */
    public JsonAdaptedSupplyItem(SupplyItem source) {
        name = source.getName();
        currentStock = getStringFromInt(source.getCurrentStock());
        minStock = getStringFromInt(source.getMinStock());
        supplierName = source.getSupplier().getName().fullName;
        price = source.getSupplier().getPrice().value;
        phone = source.getSupplier().getPhone().value;
        personItem = source.getSupplier().getItem().value;
        address = source.getSupplier().getAddress().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted supply item object into the model's {@code SupplyItem} object.
     *
     * @throws IllegalValueException if there are any data constraints violated in the adapted supply item.
     */
    public SupplyItem toModelType() throws IllegalValueException {
        final List<Tag> supplyItemTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            supplyItemTags.add(tag.toModelType());
        }

        // TODO: Handle exceptions more gracefully
        if (name == null) {
            throw new IllegalValueException("Supply Item name is missing !");
        }

        final String modelName = this.name;

        if (currentStock == null) {
            throw new IllegalValueException("Supply Item currentStock is missing !");
        }

        final int modelCurrentStock = getIntFromString(this.currentStock);

        if (minStock == null) {
            throw new IllegalValueException("Supply Item minStock is missing !");
        }

        final int modelMinStock = getIntFromString(this.minStock);

        if (supplierName == null) {
            throw new IllegalValueException("Supply Item supplier is missing !");
        }

        final Name modelSupplierName = new Name(supplierName);

        if (price == null) {
            throw new IllegalValueException("Supply Item price is missing !");
        }

        final Price modelPrice = new Price(price);

        if (phone == null) {
            throw new IllegalValueException("Supply Item phone is missing !");
        }

        final Phone modelPhone = new Phone(phone);

        if (personItem == null) {
            throw new IllegalValueException("Supply Item personItem is missing !");
        }

        final Item modelPersonItem = new Item(personItem);

        if (address == null) {
            throw new IllegalValueException("Supply Item address is missing !");
        }

        final Address modelAddress = new Address(address);

        final Set<Tag> modelTags = new HashSet<>(supplyItemTags);
        final Person modelPerson = new Person(modelSupplierName,
                modelPhone, modelPrice, modelPersonItem, modelAddress, modelTags);
        return new SupplyItem(modelName, modelCurrentStock, modelMinStock, modelPerson, modelTags);
    }
}

