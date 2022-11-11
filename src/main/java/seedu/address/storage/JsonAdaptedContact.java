package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.Contact;

/**
 * Jackson-friendly version of {@link Contact}.
 */
class JsonAdaptedContact {

    private final String contactName;

    /**
     * Constructs a {@code JsonAdaptedContact} with the given {@code contactName}.
     */
    @JsonCreator
    public JsonAdaptedContact(String contactName) {
        this.contactName = contactName;
    }

    /**
     * Converts a given {@code Contact} into this class for Jackson use.
     */
    public JsonAdaptedContact(Contact source) {
        contactName = source.contactName;
    }

    @JsonValue
    public String getContactName() {
        return contactName;
    }

    /**
     * Converts this Jackson-friendly adapted contact object into the model's {@code Contact} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted contact.
     */
    public Contact toModelType() throws IllegalValueException {
        if (!Contact.isValidContactName(contactName)) {
            throw new IllegalValueException(Contact.MESSAGE_CONSTRAINTS);
        }
        return new Contact(contactName);
    }

}
