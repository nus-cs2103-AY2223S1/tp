package seedu.rc4hdb.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.rc4hdb.commons.exceptions.IllegalValueException;
import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.resident.fields.Email;
import seedu.rc4hdb.model.resident.fields.Gender;
import seedu.rc4hdb.model.resident.fields.House;
import seedu.rc4hdb.model.resident.fields.MatricNumber;
import seedu.rc4hdb.model.resident.fields.Name;
import seedu.rc4hdb.model.resident.fields.Phone;
import seedu.rc4hdb.model.resident.fields.Room;
import seedu.rc4hdb.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Resident}.
 */
public class JsonAdaptedResident {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Resident's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String room;
    private final String gender;
    private final String house;
    private final String matricNumber;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedResident} with the given resident details.
     */
    @JsonCreator
    public JsonAdaptedResident(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("room") String room,
                             @JsonProperty("gender") String gender, @JsonProperty("house") String house,
                             @JsonProperty("matricNumber") String matricNumber,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.room = room;
        this.gender = gender;
        this.house = house;
        this.matricNumber = matricNumber;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Resident} into this class for Jackson use.
     */
    public JsonAdaptedResident(Resident source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        room = source.getRoom().room;
        gender = source.getGender().gender;
        house = source.getHouse().house;
        matricNumber = source.getMatricNumber().matricNumber;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted resident object into the model's {@code Resident} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted resident.
     */
    public Resident toModelType() throws IllegalValueException {
        final List<Tag> residentTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            residentTags.add(tag.toModelType());
        }

        throwIfNullField(name, Name.class);
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        throwIfNullField(phone, Phone.class);
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        throwIfNullField(email, Email.class);
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        throwIfNullField(room, Room.class);
        if (!Room.isValidRoom(room)) {
            throw new IllegalValueException(Room.MESSAGE_CONSTRAINTS);
        }
        final Room modelRoom = new Room(room);

        throwIfNullField(gender, Gender.class);
        if (!Gender.isValidGender(gender)) {
            throw new IllegalValueException(Gender.MESSAGE_CONSTRAINTS);
        }
        final Gender modelGender = new Gender(gender);

        throwIfNullField(house, House.class);
        if (!House.isValidHouse(house)) {
            throw new IllegalValueException(House.MESSAGE_CONSTRAINTS);
        }
        final House modelHouse = new House(house);

        throwIfNullField(matricNumber, MatricNumber.class);
        if (!MatricNumber.isValidMatricNumber(matricNumber)) {
            throw new IllegalValueException(MatricNumber.MESSAGE_CONSTRAINTS);
        }
        final MatricNumber modelMatricNumber = new MatricNumber(matricNumber);

        final Set<Tag> modelTags = new HashSet<>(residentTags);
        return new Resident(modelName, modelPhone, modelEmail, modelRoom, modelGender, modelHouse, modelMatricNumber,
                modelTags);
    }

    /**
     * Throws an {@code IllegalValueException} if field is null.
     * @param field The field to check if null.
     * @param fieldClass The {@code Class} that field is supposed to be converted into.
     * @throws IllegalValueException if field is null.
     */
    private void throwIfNullField(String field, Class<?> fieldClass) throws IllegalValueException {
        if (field == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, fieldClass.getSimpleName()));
        }
    }

}
