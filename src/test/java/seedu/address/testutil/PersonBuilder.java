package seedu.address.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.category.Category;
import seedu.address.model.person.Address;
import seedu.address.model.person.Date;
import seedu.address.model.person.DateSlot;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.HomeVisit;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nurse;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Physician;
import seedu.address.model.person.Uid;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {
    public static final String DEFAULT_CATEGORY = "P";
    public static final String DEFAULT_UID = "1";
    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_GENDER = "F";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_DATE_AND_SLOT = "2022-06-14,4";
    public static final String DEFAULT_VISIT_STATUS = "false";
    public static final String DEFAULT_HOME_VISITS = "2022-11-10,4:2";
    public static final String DEFAULT_CONTACT_NAME = "John Doe";
    public static final String DEFAULT_CONTACT_EMAIL = "johndoe@example.com";
    public static final String DEFAULT_CONTACT_PHONE = "81234567";

    private Category category;
    private Uid uid;
    private Name name;
    private Gender gender;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;
    private List<DateSlot> dateSlotList;
    private List<HomeVisit> homeVisitList;
    private Physician physician;
    private List<Date> unavailableDateList;
    private List<Date> fullyScheduledDateList;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        category = new Category(DEFAULT_CATEGORY);
        uid = new Uid(DEFAULT_UID);
        name = new Name(DEFAULT_NAME);
        gender = new Gender(DEFAULT_GENDER);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        dateSlotList = new ArrayList<>();
        homeVisitList = new ArrayList<>();
        physician = new Physician(new Name(DEFAULT_CONTACT_NAME), new Phone(DEFAULT_CONTACT_PHONE),
                new Email(DEFAULT_CONTACT_EMAIL));
        unavailableDateList = new ArrayList<>();
        fullyScheduledDateList = new ArrayList<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        category = personToCopy.getCategory();
        uid = personToCopy.getUid();
        name = personToCopy.getName();
        gender = personToCopy.getGender();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        tags = new HashSet<>(personToCopy.getTags());
        if (personToCopy.getCategory().isPatient()) {
            Patient patientToCopy = (Patient) personToCopy;
            dateSlotList = new ArrayList<>(patientToCopy.getDatesSlots());
            Physician[] physicianArr = new Physician[] { null };
            patientToCopy.getAttendingPhysician().ifPresent(
                    x -> physicianArr[0] = new Physician(x.getName(), x.getPhone(), x.getEmail()));
            physician = physicianArr[0];
        } else {
            homeVisitList = new ArrayList<>(((Nurse) personToCopy).getHomeVisits());
            physician = null;
            homeVisitList = new ArrayList<>(((Nurse) personToCopy).getHomeVisits());
            unavailableDateList = new ArrayList<>(((Nurse) personToCopy).getUnavailableDates());
            fullyScheduledDateList = new ArrayList<>(((Nurse) personToCopy).getFullyScheduledDates());
        }
    }

    /**
     * Sets the {@code Id} of the {@code Person} that we are building.
     */
    public PersonBuilder withUid(String id) {
        this.uid = new Uid(id);
        return this;
    }

    /**
     * Sets the {@code Category} of the {@code Person} that we are building.
     */
    public PersonBuilder withCategory(String category) {
        this.category = new Category(category);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Gender} of the {@code Person} that we are building.
     */
    public PersonBuilder withGender(String gender) {
        this.gender = new Gender(gender);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the
     * {@code Person} that we are building.
     */
    public PersonBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Parses the {@code datesSlots} into a {@code List<DateSlot>} and
     * set it to the {@code Person} that we are building.
     * Applies only to Patient.
     */
    public PersonBuilder withDatesSlots(String... datesSlots) {
        this.dateSlotList = SampleDataUtil.getDatesSlotsList(datesSlots);
        return this;
    }

    /**
     * Parses the {@code homeVisit} into a {@code List<HomeVisit>} and
     * set it to the {@code Person} that we are building.
     * Applies only to Nurse.
     */
    public PersonBuilder withHomeVisits(String... homeVisit) {
        this.homeVisitList = SampleDataUtil.getHomeVisitsList(homeVisit);
        return this;
    }

    /**
     * Sets the {@code Uid} of the {@code Person} that we are building to the
     * universal Uid.
     */
    public PersonBuilder withUniversalUid() {
        this.uid = Uid.generateUniversalUid();
        return this;
    }

    /**
     * Sets the {@code Uid} of the {@code Person} that we are building to the
     * universal Uid.
     */
    public PersonBuilder withAttendingPhysician(String n, String p, String e) {
        physician = new Physician(new Name(n), new Phone(p), new Email(e));
        return this;
    }

    /**
     * Parses the {@code unavailableDate} into a {@code List<Date>} and
     * set it to the {@code Person} that we are building.
     * Applies only to Nurse.
     */
    public PersonBuilder withUnavailableDateList(String... unavailableDates) {
        this.unavailableDateList = SampleDataUtil.getUnavailableDateList(unavailableDates);
        return this;
    }

    /**
     * Parses the {@code fullyScheduledDate} into a {@code List<Date>} and
     * set it to the {@code Person} that we are building.
     * Applies only to Nurse.
     */
    public PersonBuilder withFullyScheduledDateList(String... fullyScheduledDate) {
        this.fullyScheduledDateList = SampleDataUtil.getFullyScheduledDateList(fullyScheduledDate);
        return this;
    }

    /**
     * Build a person for test.
     */
    public Person build() {
        if (category.isNurse()) {
            return new Nurse(uid, name, gender, phone, email, address, tags,
                    unavailableDateList, homeVisitList, fullyScheduledDateList);
        } else if (category.isPatient()) {
            return new Patient(uid, name, gender, phone, email, address, tags, dateSlotList, physician, null);
        }
        return new Person(uid, name, gender, phone, email, address, tags);
    }

}
