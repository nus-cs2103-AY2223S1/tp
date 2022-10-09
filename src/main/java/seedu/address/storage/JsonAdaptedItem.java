package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.item.Item;
import seedu.address.model.item.ItemBoughtDate;
import seedu.address.model.item.ItemExpiryDate;
import seedu.address.model.item.ItemName;
import seedu.address.model.item.ItemQuantity;
import seedu.address.model.item.ItemUnit;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

/**
 * Jackson-friendly version of {@link Item}.
 */
class JsonAdaptedItem {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Item's %s field is missing!";

    private final String name;
    private final String quantity;
    private final String unit;
    private final String boughtDate;
    private final String expiryDate;
    // private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedItem} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedItem(@JsonProperty("name") String name,
                           @JsonProperty("quantity") String quantity,
                           @JsonProperty("unit") String unit,
                           @JsonProperty("bought") String boughtDate,
                           @JsonProperty("expiry") String expiryDate) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.boughtDate = boughtDate;
        this.expiryDate = expiryDate;
    }

    /**
     * Converts a given {@code Item} into this class for Jackson use.
     */
    public JsonAdaptedItem(Item source) {
        name = String.valueOf(source.getName());
        quantity = String.valueOf(source.getQuantity());
        unit = String.valueOf(source.getUnit());
        boughtDate = String.valueOf(source.getBoughtDate());
        expiryDate = String.valueOf(source.getExpiryDate());
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Item} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Item toModelType() throws IllegalValueException {
        //final List<Tag> personTags = new ArrayList<>();
        //for (JsonAdaptedTag tag : tagged) {
        //    personTags.add(tag.toModelType());
        //}

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        // TODO: Change Validation
        //if (!Name.isValidName(name)) {
        //    throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        //}
        final ItemName modelItemName = new ItemName(name);

        if (quantity == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        // TODO: Change Validation
        //if (!Phone.isValidPhone(quantity)) {
        //    throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        //}
        final ItemQuantity modelItemQuantity = new ItemQuantity(quantity);

        if (unit == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        // TODO: Change Validation
        //if (!Email.isValidEmail(unit)) {
        //    throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        //}
        final ItemUnit modelItemUnit = new ItemUnit(unit);

        if (boughtDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        // TODO: Change Validation
        //if (!Address.isValidAddress(boughtDate)) {
        //    throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        //}
        final ItemBoughtDate modelItemBoughtDate = new ItemBoughtDate(boughtDate);

        if (expiryDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        // TODO: Change Validation
        //if (!Address.isValidAddress(expiryDate)) {
        //    throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        //}
        final ItemExpiryDate modelItemExpiryDate = new ItemExpiryDate(expiryDate);

        //final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Item(modelItemName, modelItemQuantity, modelItemUnit, modelItemBoughtDate, modelItemExpiryDate);
    }

}
