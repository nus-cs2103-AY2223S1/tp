package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.AppliedDate;
import seedu.address.model.internship.Company;
import seedu.address.model.internship.Description;
import seedu.address.model.internship.Internship;
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
    private Description description;
    private ApplicationStatus applicationStatus;
    private AppliedDate appliedDate;
    private Set<Tag> tags;

    /**
     * Creates a {@code InternshipBuilder} with the default details.
     */
    public InternshipBuilder() {
        company = new Company(DEFAULT_NAME);
        link = new Link(DEFAULT_PHONE);
        description = new Description(DEFAULT_EMAIL);
        applicationStatus = DEFAULT_APPLICATION_STATUS;
        appliedDate = new AppliedDate(DEFAULT_ADDRESS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the InternshipBuilder with the data of {@code internshipToCopy}.
     */
    public InternshipBuilder(Internship internshipToCopy) {
        company = internshipToCopy.getCompany();
        link = internshipToCopy.getLink();
        description = internshipToCopy.getDescription();
        applicationStatus = internshipToCopy.getApplicationStatus();
        appliedDate = internshipToCopy.getAppliedDate();
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
     * Sets the {@code AppliedDate} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withAddress(String address) {
        this.appliedDate = new AppliedDate(address);
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
     * Sets the {@code Description} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withEmail(String email) {
        this.description = new Description(email);
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
        return new Internship(company, link, description, applicationStatus, appliedDate, tags);
    }

}
