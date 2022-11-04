package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Loan;
import seedu.address.model.person.LoanHistory;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String birthday;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final String loan;
    private final List<JsonAdaptedLoanHistory> history;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
            @JsonProperty("birthday") String birthday,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
            @JsonProperty("loan") String loan, @JsonProperty("history") List<JsonAdaptedLoanHistory> history) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.loan = loan;
        this.history = history;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        birthday = source.getBirthday().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        loan = source.getLoan().toString();
        history = source.getHistory().stream().map(JsonAdaptedLoanHistory::new).collect(Collectors.toList());

    }


    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     * @param addressBookTagList the list of tags that exist in the addressBook to be assigned to
     *                           the model's {@code Person} object
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType(List<Tag> addressBookTagList) throws IllegalValueException {
        final List<Tag> convertedTags = new ArrayList<>();
        for (JsonAdaptedTag adaptedTag : tagged) {
            convertedTags.add(adaptedTag.toModelType());
        }

        final Set<Tag> modelTags = addressBookTagList.stream()
                .filter(convertedTags::contains)
                .collect(Collectors.toSet());

        final List<LoanHistory> modelHistory = new ArrayList<>();
        for (JsonAdaptedLoanHistory adaptedLoan : history) {
            modelHistory.add(adaptedLoan.toModelType());
        }
        // We could really use some abstraction here -- Rui Han

        // Name validity check
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        // Phone validity check
        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        // Email validity check
        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);


        // Address validity check
        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        // Birthday validity check
        if (birthday == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Birthday.class.getSimpleName()));
        }
        if (!Birthday.isValidBirthday(birthday)) {
            throw new IllegalValueException(Birthday.MESSAGE_CONSTRAINTS);
        }
        final Birthday modelBirthday = new Birthday(birthday);

        // Loan validity check
        if (loan == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Loan.class.getSimpleName()));
        }
        if (!Loan.isValidLoan(loan)) {
            throw new IllegalValueException(Loan.MESSAGE_CONSTRAINTS);
        }
        final Loan modelLoan = new Loan(loan);

        return new Person(modelName, modelPhone, modelEmail, modelAddress, modelBirthday,
                          modelTags, modelLoan, modelHistory);
    }

}
