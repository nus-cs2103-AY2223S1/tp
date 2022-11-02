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
import coydir.model.person.Department;
import coydir.model.person.Email;
import coydir.model.person.EmployeeId;
import coydir.model.person.Leave;
import coydir.model.person.Name;
import coydir.model.person.Person;
import coydir.model.person.Phone;
import coydir.model.person.Position;
import coydir.model.person.Rating;
import coydir.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";
    public static final String LEAVES_FIELD = "Number of Leaves";

    private final String name;
    private final String employeeId;
    private final String phone;
    private final String email;
    private final String position;
    private final String department;
    private final String address;
    private final String leave;
    private final String leaveLeft;
    private final String rating;

    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedLeave> leaveTaken = new ArrayList<>();
    private final List<JsonAdaptedRating> performanceHistory = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("employeeId") String employeeId,
            @JsonProperty("phone") String phone, @JsonProperty("email") String email,
            @JsonProperty("position") String position, @JsonProperty("department") String department,
            @JsonProperty("address") String address, @JsonProperty("leave") String leave,
            @JsonProperty("leaveLeft") String leaveLeft, @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
            @JsonProperty("leaveTaken") List<JsonAdaptedLeave> leaveTaken,
            @JsonProperty("rating") String rating,
            @JsonProperty("performanceHistory") List<JsonAdaptedRating> performanceHistory) {

        this.name = name;
        this.employeeId = employeeId;
        this.phone = phone;
        this.email = email;
        this.position = position;
        this.department = department;
        this.address = address;
        this.leave = leave;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.leaveLeft = leaveLeft;
        if (leaveTaken != null) {
            this.leaveTaken.addAll(leaveTaken);
        }
        this.rating = rating;
        if (performanceHistory != null) {
            this.performanceHistory.addAll(performanceHistory);
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
        department = source.getDepartment().value;
        address = source.getAddress().value;
        leave = String.valueOf(source.getTotalNumberOfLeaves());
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        leaveLeft = String.valueOf(source.getLeavesLeft());
        leaveTaken.addAll(source.getLeaves().stream()
                .map(JsonAdaptedLeave::new)
                .collect(Collectors.toList()));
        rating = String.valueOf(source.getRating());
        performanceHistory.addAll(source.getRatingHistory().stream()
                .map(JsonAdaptedRating::new)
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

        final List<Rating> personRatings = new ArrayList<>();
        for (JsonAdaptedRating rating : performanceHistory) {
            personRatings.add(rating.toModelType());
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

        final Phone modelPhone;
        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        } else if (phone.equals("N/A")) {
            modelPhone = new Phone();
        } else if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        } else {
            modelPhone = new Phone(phone);
        }

        final Email modelEmail;
        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        } else if (email.equals("N/A")) {
            modelEmail = new Email();
        } else if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        } else {
            modelEmail = new Email(email);
        }

        if (position == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Position.class.getSimpleName()));
        }
        if (!Position.isValidPosition(position)) {
            throw new IllegalValueException(Position.MESSAGE_CONSTRAINTS);
        }
        final Position modelPosition = new Position(position);

        if (department == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Department.class.getSimpleName()));
        }
        if (!Department.isValidDepartment(department)) {
            throw new IllegalValueException(Department.MESSAGE_CONSTRAINTS);
        }
        final Department modelDepartment = new Department(department);

        final Address modelAddress;
        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Address.class.getSimpleName()));
        } else if (address.equals("N/A")) {
            modelAddress = new Address();
        } else if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        } else {
            modelAddress = new Address(address);
        }

        final int modelLeave;
        final int leaveLeftTransfer;
        if (leave == null || leaveLeft == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, LEAVES_FIELD));
        } else {
            try {
                modelLeave = Integer.valueOf(leave);
                leaveLeftTransfer = Integer.valueOf(leaveLeft);
                if (leaveLeftTransfer > modelLeave) {
                    throw new IllegalValueException("Leave left cannot be more than total leave");
                }
            } catch (NumberFormatException nfe) {
                throw new IllegalValueException(String.format("%s is not a valid integer", leave));
            }
        }

        final Set<Tag> modelTags = new HashSet<>(personTags);

        final Rating modelRating;
        if (rating == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Rating.class.getSimpleName()));
        } else if (rating.equals("N/A")) {
            modelRating = new Rating();
        } else if (!Rating.isValidRating(rating)) {
            throw new IllegalValueException(Rating.MESSAGE_CONSTRAINTS);
        } else {
            modelRating = new Rating(rating);
        }

        // Create Person object
        Person p = new Person(modelName, modelEmployeeId, modelPhone, modelEmail,
                modelPosition, modelDepartment, modelAddress, modelTags, modelLeave,
                modelRating);

        // Add related data
        p.setLeavesLeft(leaveLeftTransfer);
        for (Leave l : personLeaves) {
            p.addLeave(l);
        }

        for (Rating r : personRatings) {
            p.addRating(r);
        }

        return p;
    }
}
