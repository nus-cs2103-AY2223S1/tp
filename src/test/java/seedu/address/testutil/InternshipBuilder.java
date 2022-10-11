package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.internship.Address;
import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.Email;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.Company;
import seedu.address.model.internship.Link;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Internship objects.
 */
public class InternshipBuilder {

    public static final String DEFAULT_NAME = "Google";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "careers@google.com";
    public static final ApplicationStatus DEFAULT_APPLICATION_STATUS = ApplicationStatus.Applied;
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Company company;
    private Link link;
    private Email email;
    private ApplicationStatus applicationStatus;
    private Address address;
    private Set<Tag> tags;

    /**
     * Creates a {@code InternshipBuilder} with the default details.
     */
    public InternshipBuilder() {
        company = new Company(DEFAULT_NAME);
        link = new Link(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        applicationStatus = DEFAULT_APPLICATION_STATUS;
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the InternshipBuilder with the data of {@code internshipToCopy}.
     */
    public InternshipBuilder(Internship internshipToCopy) {
        company = internshipToCopy.getCompany();
        link = internshipToCopy.getLink();
        email = internshipToCopy.getEmail();
        applicationStatus = internshipToCopy.getApplicationStatus();
        address = internshipToCopy.getAddress();
        tags = new HashSet<>(internshipToCopy.getTags());
    }

    /**
     * Sets the {@code Company} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withName(String name) {
        this.company = new Company(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Internship} that we are building.
     */
    public InternshipBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Link} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withPhone(String phone) {
        this.link = new Link(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code ApplicationStatus} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withApplicationStatus(ApplicationStatus applicationStatus) {
        this.applicationStatus = applicationStatus;
        return this;
    }

    public Internship build() {
        return new Internship(company, link, email, applicationStatus, address, tags);
    }

}
