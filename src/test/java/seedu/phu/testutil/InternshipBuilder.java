package seedu.phu.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.phu.model.internship.ApplicationProcess;
import seedu.phu.model.internship.Date;
import seedu.phu.model.internship.Email;
import seedu.phu.model.internship.Internship;
import seedu.phu.model.internship.Name;
import seedu.phu.model.internship.Phone;
import seedu.phu.model.internship.Position;
import seedu.phu.model.internship.Remark;
import seedu.phu.model.internship.Website;
import seedu.phu.model.tag.Tag;
import seedu.phu.model.util.SampleDataUtil;

/**
 * A utility class to help with building Internship objects.
 */
public class InternshipBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_INTERNSHIP = "winter internship '22";
    public static final String DEFAULT_POSITION = "Software Intern";
    public static final String DEFAULT_APPLICATION_PROCESS = "interview";
    public static final String DEFAULT_DATE = "01-12-2022";
    public static final String DEFAULT_WEBSITE = "https://defaultweb.com/careers";


    private Name name;
    private Phone phone;
    private Email email;
    private Remark remark;
    private Set<Tag> tags;
    private Position position;
    private ApplicationProcess applicationProcess;
    private Date date;
    private Website website;

    /**
     * Creates a {@code InternshipBuilder} with the default details.
     */
    public InternshipBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        remark = new Remark(DEFAULT_INTERNSHIP);
        tags = new HashSet<>();
        position = new Position(DEFAULT_POSITION);
        applicationProcess = new ApplicationProcess(DEFAULT_APPLICATION_PROCESS);
        date = new Date(DEFAULT_DATE);
        website = new Website(DEFAULT_WEBSITE);
    }

    /**
     * Initializes the InternshipBuilder with the data of {@code internshipToCopy}.
     */
    public InternshipBuilder(Internship internshipToCopy) {
        name = internshipToCopy.getName();
        phone = internshipToCopy.getPhone();
        email = internshipToCopy.getEmail();
        remark = internshipToCopy.getRemark();
        tags = new HashSet<>(internshipToCopy.getTags());
        position = internshipToCopy.getPosition();
        applicationProcess = internshipToCopy.getApplicationProcess();
        date = internshipToCopy.getDate();
        website = internshipToCopy.getWebsite();
    }

    /**
     * Sets the {@code Name} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withName(String name) {
        this.name = new Name(name);
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
     * Sets the {@code Remark} of the {@code Remark} that we are building.
     */
    public InternshipBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
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
     * Sets the {@code Position} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withPosition(String position) {
        this.position = new Position(position);
        return this;
    }

    /**
     * Sets the {@code ApplicationProcess} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withApplicationProcess(String applicationProcess) {
        this.applicationProcess = new ApplicationProcess(applicationProcess);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withDate(String date) {
        this.date = new Date(date);
        return this;
    }

    /**
     * Sets the {@code Website} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withWebsite(String website) {
        this.website = new Website(website);
        return this;
    }

    public Internship build() {
        return new Internship(name, phone, email, remark, position, applicationProcess, date, website, tags);
    }

}
