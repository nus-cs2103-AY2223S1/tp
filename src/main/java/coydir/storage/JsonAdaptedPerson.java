package coydir.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import coydir.commons.exceptions.IllegalValueException;
import coydir.model.person.Address;
import coydir.model.person.Email;
import coydir.model.person.EmployeeId;
import coydir.model.person.Leave;
import coydir.model.person.Name;
import coydir.model.person.Person;
import coydir.model.person.Phone;
import coydir.model.person.Position;
import coydir.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String employeeId;
    private final String phone;
    private final String email;
    private final String position;
    private final String address;
    private final String leave;
    private final String leaveLeft;

    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedLeave> leaveTaken = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("employeeId") String employeeId,
            @JsonProperty("phone") String phone, @JsonProperty("email") String email,
            @JsonProperty("position") String position, @JsonProperty("address") String address,
            @JsonProperty("leave") String leave, @JsonProperty("leaveLeft") String leaveLeft,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
            @JsonProperty("leaveTaken") List<JsonAdaptedLeave> leaveTaken) {
        this.name = name;
        this.employeeId = employeeId;
        this.phone = phone;
        this.email = email;
        this.position = position;
        this.address = address;
        this.leave = leave;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.leaveLeft = leaveLeft;
        if (leaveTaken != null) {
            this.leaveTaken.addAll(leaveTaken);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        employeeId = source.getEmployeeId().value;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        position = source.getPosition().value;
        address = source.getAddress().value;
        leave = String.valueOf(source.getTotalNumberOfLeaves());
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        leaveLeft = String.valueOf(source.getLeavesLeft());
        leaveTaken.addAll(source.getLeaves().stream()
                .map(JsonAdaptedLeave::new)
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

        final List<Leave> personLeaves = new ArrayList<>();
        for (JsonAdaptedLeave leave : leaveTaken) {
            personLeaves.add(leave.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (employeeId == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, EmployeeId.class.getSimpleName()));
        }
        if (!EmployeeId.isValidEmployeeId(employeeId)) {
            throw new IllegalValueException(EmployeeId.MESSAGE_CONSTRAINTS);
        }
        final EmployeeId modelEmployeeId = new EmployeeId(employeeId);

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

        if (position == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                        Position.class.getSimpleName()));
        }
        if (!Position.isValidPosition(position)) {
            throw new IllegalValueException(Position.MESSAGE_CONSTRAINTS);
        }
        final Position modelPosition = new Position(position);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (leave == null) {
            throw new IllegalValueException("FAIL");
        }
        final int modelLeave = Integer.valueOf(leave);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        final Set<Leave> modelLeaveTaken = new HashSet<>(personLeaves);
        Person p = new Person(modelName, modelEmployeeId, modelPhone, modelEmail,
                modelPosition, modelAddress, modelTags, modelLeave);
        p.setLeavesLeft(Integer.valueOf(leaveLeft));
        for (Leave l : modelLeaveTaken) {
            p.addLeave(l);
        }
        return p;
    }

}
