package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.meeting.MeetingDate;
import seedu.address.model.meeting.MeetingLocation;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Income;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.portfolio.Note;
import seedu.address.model.portfolio.Plan;
import seedu.address.model.portfolio.Portfolio;
import seedu.address.model.portfolio.Risk;
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
    private final String income;
    private final String meetingDate;
    private final String meetingLocation;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final String risk;
    private final List<JsonAdaptedPlan> planned = new ArrayList<>();
    private final List<JsonAdaptedNote> noted = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("address") String address,
                             @JsonProperty("income") String income,
                             @JsonProperty("meetingDate") String meetingDate,
                             @JsonProperty("meetingLocation") String meetingLocation,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                             @JsonProperty("risk") String risk,
                             @JsonProperty("plan") List<JsonAdaptedPlan> planned,
                             @JsonProperty("note") List<JsonAdaptedNote> noted) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.income = income;
        if (meetingDate != null) {
            this.meetingDate = meetingDate;
        } else {
            this.meetingDate = "";
        }
        if (meetingLocation != null) {
            this.meetingLocation = meetingLocation;
        } else {
            this.meetingLocation = "";
        }
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (risk != null) {
            this.risk = risk;
        } else {
            this.risk = "";
        }
        if (planned != null) {
            this.planned.addAll(planned);
        }
        if (noted != null) {
            this.noted.addAll(noted);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        Portfolio portfolio = source.getPortfolio();
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        income = source.getIncome().value;
        meetingDate = source.getMeeting().getMeetingDate().get();
        meetingLocation = source.getMeeting().getMeetingLocation().get();
        tagged.addAll(source.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList()));
        risk = portfolio.getRisk().get();
        planned.addAll(portfolio.getPlans().stream()
            .map(JsonAdaptedPlan::new)
            .collect(Collectors.toList()));
        noted.addAll(portfolio.getNotes().stream()
            .map(JsonAdaptedNote::new)
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

        final List<Plan> personPlans = new ArrayList<>();
        for (JsonAdaptedPlan plan : planned) {
            personPlans.add(plan.toModelType());
        }

        final List<Note> personNotes = new ArrayList<>();
        for (JsonAdaptedNote note : noted) {
            personNotes.add(note.toModelType());
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

        if (income == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Income.class.getSimpleName()));
        }
        if (!Income.isValidIncome(income)) {
            throw new IllegalValueException(Income.MESSAGE_CONSTRAINTS);
        }
        final Income modelIncome = new Income(income);

        if (meetingDate != null && !MeetingDate.isValidMeetingDate(meetingDate)) {
            throw new IllegalValueException(MeetingDate.MESSAGE_CONSTRAINTS);
        }
        final MeetingDate modelMeetingDate;

        if (meetingDate != null) {
            modelMeetingDate = new MeetingDate(meetingDate);
        } else {
            modelMeetingDate = new MeetingDate("");
        }

        if (meetingLocation != null && !MeetingLocation.isValidMeetingLocation(meetingLocation)) {
            throw new IllegalValueException(MeetingLocation.MESSAGE_CONSTRAINTS);
        }
        final MeetingLocation modelMeetingLocation;

        if (meetingLocation != null) {
            modelMeetingLocation = new MeetingLocation(meetingLocation);
        } else {
            modelMeetingLocation = new MeetingLocation("");
        }

        if (risk != null && !Risk.isValidRisk(risk)) {
            throw new IllegalValueException(Risk.MESSAGE_CONSTRAINTS);
        }
        final Risk modelRisk;

        if (risk != null) {
            modelRisk = new Risk(risk);
        } else {
            modelRisk = new Risk("");
        }


        final Set<Plan> modelPlans = new HashSet<>(personPlans);

        final Set<Tag> modelTags = new HashSet<>(personTags);

        final Set<Note> modelNotes = new HashSet<>(personNotes);

        return new Person(modelName, modelPhone, modelEmail, modelAddress, modelIncome, modelMeetingDate,
            modelMeetingLocation,
            modelTags, modelRisk, modelPlans, modelNotes);
    }

}
