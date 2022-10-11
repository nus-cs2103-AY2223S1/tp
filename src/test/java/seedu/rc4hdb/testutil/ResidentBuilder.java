package seedu.rc4hdb.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.resident.fields.Email;
import seedu.rc4hdb.model.resident.fields.Gender;
import seedu.rc4hdb.model.resident.fields.House;
import seedu.rc4hdb.model.resident.fields.MatricNumber;
import seedu.rc4hdb.model.resident.fields.Name;
import seedu.rc4hdb.model.resident.fields.Phone;
import seedu.rc4hdb.model.resident.fields.Room;
import seedu.rc4hdb.model.tag.Tag;
import seedu.rc4hdb.model.util.SampleDataUtil;

/**
 * A utility class to help with building Resident objects.
 */
public class ResidentBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ROOM = "03-10";
    public static final String DEFAULT_GENDER = "F";
    public static final String DEFAULT_HOUSE = "D";
    public static final String DEFAULT_MATRIC_NUMBER = "A0123456U";

    private Name name;
    private Phone phone;
    private Email email;
    private Room room;
    private Gender gender;
    private House house;
    private MatricNumber matricNumber;
    private Set<Tag> tags;

    /**
     * Creates a {@code ResidentBuilder} with the default details.
     */
    public ResidentBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        room = new Room(DEFAULT_ROOM);
        gender = new Gender(DEFAULT_GENDER);
        house = new House(DEFAULT_HOUSE);
        matricNumber = new MatricNumber(DEFAULT_MATRIC_NUMBER);
        tags = new HashSet<>();
    }

    /**
     * Initializes the ResidentBuilder with the data of {@code residentToCopy}.
     */
    public ResidentBuilder(Resident residentToCopy) {
        name = residentToCopy.getName();
        phone = residentToCopy.getPhone();
        email = residentToCopy.getEmail();
        room = residentToCopy.getRoom();
        gender = residentToCopy.getGender();
        house = residentToCopy.getHouse();
        matricNumber = residentToCopy.getMatricNumber();
        tags = residentToCopy.getTags();
    }

    /**
     * Sets the {@code Name} of the {@code Resident} that we are building.
     */
    public ResidentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Resident} that we are building.
     */
    public ResidentBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Resident} that we are building.
     */
    public ResidentBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Room} of the {@code Resident} that we are building.
     */
    public ResidentBuilder withRoom(String room) {
        this.room = new Room(room);
        return this;
    }

    /**
     * Sets the {@code Gender} of the {@code Resident} that we are building.
     */
    public ResidentBuilder withGender(String gender) {
        this.gender = new Gender(gender);
        return this;
    }

    /**
     * Sets the {@code House} of the {@code Resident} that we are building.
     */
    public ResidentBuilder withHouse(String house) {
        this.house = new House(house);
        return this;
    }

    /**
     * Sets the {@code MatricNumber} of the {@code Resident} that we are building.
     */
    public ResidentBuilder withMatricNumber(String matricNumber) {
        this.matricNumber = new MatricNumber(matricNumber);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Resident} that we are building.
     */
    public ResidentBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    public Resident build() {
        return new Resident(name, phone, email, room, gender, house, matricNumber, tags);
    }

}
