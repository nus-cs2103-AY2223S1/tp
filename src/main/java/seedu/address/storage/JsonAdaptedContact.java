package seedu.address.storage;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.contact.Contact;
import seedu.address.model.person.contact.ContactType;

/**
 * Jackson-friendly version of {@link Contact}
 */
public class JsonAdaptedContact {

    private final ContactType contactType;
    private final String contactValue;

    /**
     * Converts a given {@code Contact} into this class for Jackson use.
     */
    public JsonAdaptedContact(Contact source) {
        contactType = source.getContactType();
        contactValue = source.getValue();
    }

    /**
     * Converts this Jackson-friendly adapted contact object into the model's {@code Contact} object
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted contact
     */
    public Contact toModelType() throws IllegalValueException {
        try {
            return Contact.of(contactType, contactValue);
        } catch (IllegalArgumentException exception) {
            throw new IllegalValueException(exception.getMessage());
        }
    }
}
