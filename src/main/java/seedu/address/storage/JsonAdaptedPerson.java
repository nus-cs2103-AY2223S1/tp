package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.person.AdditionalNotes;
import seedu.address.model.person.Address;
import seedu.address.model.person.Class;
import seedu.address.model.person.Email;
import seedu.address.model.person.Money;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String classDateTime;
    private final Integer moneyOwed;
    private final Integer moneyPaid;
    private final String additionalNotes;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("address") String address,
                             @JsonProperty("classDateTime") String classDateTime,
                             @JsonProperty("moneyOwed") Integer moneyOwed,
                             @JsonProperty("moneyPaid") Integer moneyPaid,
                             @JsonProperty("additionalNotes") String additionalNotes) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.classDateTime = classDateTime;
        this.moneyOwed = moneyOwed;
        this.moneyPaid = moneyPaid;
        this.additionalNotes = additionalNotes;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        classDateTime = source.getAClass().classDateTime;
        moneyOwed = source.getMoneyOwed().value;
        moneyPaid = source.getMoneyPaid().value;
        additionalNotes = source.getAdditionalNotes().notes;
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {

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

        final Class modelClassDateTime;
        if (classDateTime != null && !classDateTime.equals("")) {
            if (!Class.isValidClassString(classDateTime)) {
                throw new IllegalValueException(Class.MESSAGE_CONSTRAINTS);
            }
            modelClassDateTime = ParserUtil.parseClass(classDateTime);
        } else {
            modelClassDateTime = new Class();
        }

        final Money modelMoneyOwed;
        if (moneyOwed != null) {
            if (!Money.isValidMoney(moneyOwed)) {
                throw new IllegalValueException(Money.MESSAGE_CONSTRAINTS);
            }
            modelMoneyOwed = new Money(moneyOwed);
        } else {
            modelMoneyOwed = new Money(0);
        }

        final Money modelMoneyPaid;
        if (moneyPaid != null) {
            if (!Money.isValidMoney(moneyPaid)) {
                throw new IllegalValueException(Money.MESSAGE_CONSTRAINTS);
            }
            modelMoneyPaid = new Money(moneyPaid);
        } else {
            modelMoneyPaid = new Money(0);
        }

        final AdditionalNotes modelAdditionalNotes;
        if (additionalNotes != null) {
            modelAdditionalNotes = new AdditionalNotes(additionalNotes);
        } else {
            modelAdditionalNotes = new AdditionalNotes("");
        }

        return new Person(modelName, modelPhone, modelEmail, modelAddress, modelClassDateTime,
                modelMoneyOwed, modelMoneyPaid, modelAdditionalNotes);
    }

}
