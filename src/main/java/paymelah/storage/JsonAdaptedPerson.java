package paymelah.storage;

import static paymelah.model.person.Address.EMPTY_ADDRESS_STRING;
import static paymelah.model.person.Phone.EMPTY_PHONE_STRING;
import static paymelah.model.person.Telegram.EMPTY_TELEGRAM_STRING;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import paymelah.commons.exceptions.IllegalValueException;
import paymelah.model.debt.Debt;
import paymelah.model.debt.DebtList;
import paymelah.model.person.Address;
import paymelah.model.person.Name;
import paymelah.model.person.Person;
import paymelah.model.person.Phone;
import paymelah.model.person.Telegram;
import paymelah.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String telegram;
    private final String address;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedDebt> debts = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("telegram") String telegram, @JsonProperty("address") String address,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
            @JsonProperty("debts") List<JsonAdaptedDebt> debts) {
        this.name = name;
        this.phone = phone;
        this.telegram = telegram;
        this.address = address;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (debts != null) {
            this.debts.addAll(debts);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        telegram = source.getTelegram().value;
        address = source.getAddress().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        debts.addAll(source.getDebts().asList().stream()
                .map(JsonAdaptedDebt::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        final List<Debt> personDebts = new ArrayList<>();
        for (JsonAdaptedDebt debt : debts) {
            personDebts.add(debt.toModelType());
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
        final Phone modelPhone;
        if (phone.equals(EMPTY_PHONE_STRING)) {
            modelPhone = Phone.EMPTY_PHONE;
        } else if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        } else {
            modelPhone = new Phone(phone);
        }

        if (telegram == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Telegram.class.getSimpleName()));
        }
        final Telegram modelTelegram;
        if (telegram.equals(EMPTY_TELEGRAM_STRING)) {
            modelTelegram = Telegram.EMPTY_TELEGRAM;
        } else if (!Telegram.isValidHandle(telegram)) {
            throw new IllegalValueException(Telegram.MESSAGE_CONSTRAINTS);
        } else {
            modelTelegram = new Telegram(telegram);
        }

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        final Address modelAddress;
        if (address.equals(EMPTY_ADDRESS_STRING)) {
            modelAddress = Address.EMPTY_ADDRESS;
        } else if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        } else {
            modelAddress = new Address(address);
        }

        final Set<Tag> modelTags = new HashSet<>(personTags);

        final DebtList modelDebts = DebtList.fromList(personDebts);

        return new Person(modelName, modelPhone, modelTelegram, modelAddress, modelTags, modelDebts);
    }

}
