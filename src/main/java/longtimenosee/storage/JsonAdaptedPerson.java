package longtimenosee.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import longtimenosee.commons.exceptions.IllegalValueException;
import longtimenosee.model.person.Address;
import longtimenosee.model.person.Birthday;
import longtimenosee.model.person.Email;
import longtimenosee.model.person.Income;
import longtimenosee.model.person.Name;
import longtimenosee.model.person.Person;
import longtimenosee.model.person.Phone;
import longtimenosee.model.person.RiskAppetite;
import longtimenosee.model.policy.AssignedPolicy;
import longtimenosee.model.tag.Tag;

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
    private final String income;
    private final String riskAppetite;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedAssignedPolicy> assignedPolicies = new ArrayList<>();
    private final boolean pinned;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged, @JsonProperty("birthday") String birthday,
                             @JsonProperty("income") String income, @JsonProperty("riskAppetite") String riskAppetite,
                                 @JsonProperty("pinned") boolean pinned,
                             @JsonProperty("assignedPolicies") List<JsonAdaptedAssignedPolicy> assignedPolicies) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.pinned = pinned;
        this.birthday = birthday;
        this.income = income;
        this.riskAppetite = riskAppetite;
        if (assignedPolicies != null) {
            this.assignedPolicies.addAll(assignedPolicies);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        pinned = source.getPin();
        birthday = source.getBirthday().value;
        income = source.getIncome().value;
        riskAppetite = source.getRiskAppetite().value;
        assignedPolicies.addAll(source.getAssignedPolicies().stream()
                .map(JsonAdaptedAssignedPolicy::new)
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

        final List<AssignedPolicy> personPolicies = new ArrayList<>();
        for (JsonAdaptedAssignedPolicy assignedPolicy : assignedPolicies) {
            personPolicies.add(assignedPolicy.toModelType());
        }
        final Set<AssignedPolicy> modelPolicies = new HashSet<>(personPolicies);

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        if (!Name.isValidLength(name)) {
            throw new IllegalValueException(Name.LENGTH_CONSTRAINTS);
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

        final Set<Tag> modelTags = new HashSet<>(personTags);
        if (birthday == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Birthday.class.getSimpleName()));
        }
        //Check for valid YYYY-MM-DD format
        if (!Birthday.isValidFormat(birthday)) {
            throw new IllegalValueException(String.format(Birthday.MESSAGE_FORMAT_CONSTRAINTS,
                    Birthday.class.getSimpleName()));
        }
        //Check for whether the date is a valid Date
        if (!Birthday.isValidDate(birthday)) {
            throw new IllegalValueException(String.format(Birthday.RANGE_FORMAT_CONSTRAINTS,
                    Birthday.class.getSimpleName()));
        }
        //Check whether the date (specifically the year) is reasonable
        if (!Birthday.isReasonableBirthday(birthday)) {
            throw new IllegalValueException(String.format(Birthday.MESSAGE_DATE_CONSTRAINTS,
                    Birthday.class.getSimpleName()));
        }
        final Birthday modelBirthday = new Birthday(birthday);

        if (income == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Income.class.getSimpleName()));
        }
        if (!Income.isValidFormat(income)) {
            throw new IllegalValueException(Income.MESSAGE_FORMAT_CONSTRAINTS);
        }

        if (!Income.isPositiveIncome(income)) {
            throw new IllegalValueException(Income.VALUE_FORMAT_CONSTRAINTS);
        }

        final Income modelIncome = new Income(income);

        if (riskAppetite == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    RiskAppetite.class.getSimpleName()));
        }
        if (!RiskAppetite.isValidFormat(riskAppetite)) {
            throw new IllegalValueException(RiskAppetite.MESSAGE_FORMAT_CONSTRAINTS);
        }

        final RiskAppetite modelRiskAppetite = new RiskAppetite(riskAppetite);


        return new Person(modelName, modelPhone, modelEmail, modelAddress, modelTags,
                modelBirthday, modelIncome, modelRiskAppetite, modelPolicies, pinned);
    }

}
