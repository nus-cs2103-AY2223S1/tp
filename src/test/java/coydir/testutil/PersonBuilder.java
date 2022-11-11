package coydir.testutil;

import static coydir.model.person.Leave.COMPARATOR;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

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
import coydir.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_POSITION = "Software Engineer";
    public static final String DEFAULT_DEPARTMENT = "Information Technology";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final int DEFAULT_LEAVE = 14;
    public static final PriorityQueue<Leave> DEFAULT_LEAVE_PERIOD = null;

    private Name name;
    private EmployeeId employeeId;
    private Phone phone;
    private Email email;
    private Position position;
    private Department department;
    private Address address;
    private Rating rating;
    private int totalLeave;
    private Set<Tag> tags;
    private Queue<Leave> leavePeriod;
    private ArrayList<Rating> performanceHistory;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        employeeId = new EmployeeId();
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        position = new Position(DEFAULT_POSITION);
        department = new Department(DEFAULT_DEPARTMENT);
        address = new Address(DEFAULT_ADDRESS);
        rating = Rating.getNullRating();
        totalLeave = DEFAULT_LEAVE;
        tags = new HashSet<>();
        leavePeriod = new PriorityQueue<Leave>(COMPARATOR);
        performanceHistory = new ArrayList<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        employeeId = personToCopy.getEmployeeId();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        position = personToCopy.getPosition();
        department = personToCopy.getDepartment();
        address = personToCopy.getAddress();
        rating = personToCopy.getRating();
        totalLeave = personToCopy.getTotalNumberOfLeaves();
        tags = new HashSet<>(personToCopy.getTags());
        leavePeriod = personToCopy.getLeaves();
        performanceHistory = personToCopy.getRatingHistory();

    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code EmployeeId} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmployeeId(String employeeId) {
        this.employeeId = new EmployeeId(employeeId);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
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
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmptyAddress() {
        this.address = new Address();
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
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmptyPhone() {
        this.phone = new Phone();
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
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmptyEmail() {
        this.email = new Email();
        return this;
    }

    /**
     * Sets the {@code Position} of the {@code Person} that we are building.
     */
    public PersonBuilder withPosition(String position) {
        this.position = new Position(position);
        return this;
    }

    /**
     * Sets the {@code Department} of the {@code Person} that we are building.
     */
    public PersonBuilder withDepartment(String department) {
        this.department = new Department(department);
        return this;
    }

    /**
     * Sets the {@code Rating} of the {@code Person} that we are building.
     */
    public PersonBuilder withRating(String rating) {
        Rating toAdd = new Rating(rating);
        this.rating = toAdd;
        return this;
    }

    /**
     * Sets the {@code Leave} of the {@code Person} that we are building.
     */
    public PersonBuilder withTotalLeave(int totalLeave) {
        this.totalLeave = totalLeave;
        return this;
    }

    /**
     * Sets the {@code leave period} of the {@code Person} that we are building.
     */
    public PersonBuilder withLeavePeriod(Leave leave) {
        this.leavePeriod.add(leave);
        return this;
    }

    /**
     * Sets the {@code Leave} of the {@code Person} that we are building.
     */
    public Person build() {
        Person person = new Person(name, employeeId, phone, email, position,
            department, address, tags, totalLeave, rating);
        for (Leave leave: leavePeriod) {
            person.addLeave(leave);
        }
        return person;
    }

}
