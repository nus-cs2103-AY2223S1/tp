package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Email;
import seedu.address.model.person.IncomeLevel;
import seedu.address.model.person.Monthly;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.NormalTag;
import seedu.address.model.tag.PlanTag;
import seedu.address.model.tag.RiskTag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String riskTag;
    private final String planTag;
    private final String income;
    private final String monthly;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedAppointment> appointments = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
            @JsonProperty("income") String income,
            @JsonProperty("monthly") String monthly,
            @JsonProperty("riskTag") String riskTag,
            @JsonProperty("planTag") String planTag,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
            @JsonProperty("appointments") List<JsonAdaptedAppointment> appointments) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.riskTag = riskTag;
        this.planTag = planTag;
        this.income = income;
        this.monthly = monthly;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (appointments != null) {
            this.appointments.addAll(appointments);
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
        riskTag = source.getRiskTag().tagName;
        planTag = source.getPlanTag().tagName;
        income = source.getIncome().value;
        monthly = source.getMonthly().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        appointments.addAll(source.getAppointments().stream()
                .map(JsonAdaptedAppointment::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<NormalTag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        final List<Appointment> personAppointments = new ArrayList<>();
        for (JsonAdaptedAppointment jsonAdaptedAppointment : appointments) {
            personAppointments.add(jsonAdaptedAppointment.toModelType());
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
        if (!IncomeLevel.isValidIncome(income)) {
            throw new IllegalValueException(IncomeLevel.MESSAGE_CONSTRAINTS);
        }
        final IncomeLevel modelIncome = new IncomeLevel(income);

        if (!Monthly.isValidMonthly(monthly)) {
            throw new IllegalValueException(Monthly.MESSAGE_CONSTRAINTS);
        }
        final Monthly modelMonthly = new Monthly(monthly);

        if (riskTag == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, RiskTag.class.getSimpleName()));
        }
        if (!RiskTag.isValidRiskTagName(riskTag)) {
            throw new IllegalValueException(RiskTag.MESSAGE_CONSTRAINTS);
        }
        final RiskTag modelRiskTag = new RiskTag(riskTag);

        if (planTag == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, PlanTag.class.getSimpleName()));
        }
        if (!PlanTag.isValidPlanTagName(planTag)) {
            throw new IllegalValueException(PlanTag.MESSAGE_CONSTRAINTS);
        }
        final PlanTag modelPlanTag = new PlanTag(planTag);

        final Set<NormalTag> modelTags = new HashSet<>(personTags);
        final Set<Appointment> modelAppointments = new HashSet<>(personAppointments);
        Person newPerson = new Person(modelName, modelPhone, modelEmail, modelAddress, modelIncome,
                modelMonthly, modelRiskTag, modelPlanTag, modelTags);
        newPerson.setAppointments(modelAppointments);
        return newPerson;
    }

}
