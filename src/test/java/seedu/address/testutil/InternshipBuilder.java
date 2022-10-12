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

    public static final String DEFAULT_COMPANY = "Google";
    public static final String DEFAULT_LINK = "https://careers.google.com/jobs/results/97935383573996230/";
    public static final String DEFAULT_DESCRIPTION = "Software Engineering Intern, BS, Summer 2023";
    public static final ApplicationStatus DEFAULT_APPLICATION_STATUS = ApplicationStatus.Applied;
    public static final String DEFAULT_APPLIED_DATE = "25 Oct 2022";

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
        company = new Company(DEFAULT_COMPANY);
        link = new Link(DEFAULT_LINK);
        description = new Description(DEFAULT_DESCRIPTION);
        applicationStatus = DEFAULT_APPLICATION_STATUS;
        appliedDate = new AppliedDate(DEFAULT_APPLIED_DATE);
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
    public InternshipBuilder withCompany(String company) {
        this.company = new Company(company);
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
    public InternshipBuilder withAppliedDate(String appliedDate) {
        System.out.println(appliedDate);
        this.appliedDate = new AppliedDate(appliedDate);
        return this;
    }

    /**
     * Sets the {@code Link} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withLink(String link) {
        this.link = new Link(link);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withDescription(String description) {
        this.description = new Description(description);
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
