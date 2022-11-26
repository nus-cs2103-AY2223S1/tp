package seedu.workbook.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.workbook.model.internship.Company;
import seedu.workbook.model.internship.DateTime;
import seedu.workbook.model.internship.Email;
import seedu.workbook.model.internship.Internship;
import seedu.workbook.model.internship.Role;
import seedu.workbook.model.internship.Stage;
import seedu.workbook.model.tag.Tag;
import seedu.workbook.model.util.SampleDataUtil;

/**
 * A utility class to help with building Internship objects.
 */
public class InternshipBuilder {

    public static final String DEFAULT_COMPANY = "Meta";
    public static final String DEFAULT_ROLE = "God Developer";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_STAGE = "Technical Interview";
    public static final String DEFAULT_DATETIME = "";

    private Company company;
    private Role role;
    private Email email;
    private Stage stage;
    private DateTime dateTime;
    private Set<Tag> languageTags;
    private Set<Tag> tags;

    /**
     * Creates a {@code InternshipBuilder} with the default details.
     */
    public InternshipBuilder() {
        company = new Company(DEFAULT_COMPANY);
        role = new Role(DEFAULT_ROLE);
        email = new Email(DEFAULT_EMAIL);
        stage = new Stage(DEFAULT_STAGE);
        dateTime = new DateTime(DEFAULT_DATETIME);
        languageTags = new HashSet<>();
        tags = new HashSet<>();
    }

    /**
     * Initializes the InternshipBuilder with the data of {@code internshipToCopy}.
     */
    public InternshipBuilder(Internship internshipToCopy) {
        company = internshipToCopy.getCompany();
        role = internshipToCopy.getRole();
        email = internshipToCopy.getEmail();
        stage = internshipToCopy.getStage();
        dateTime = internshipToCopy.getDateTime();
        languageTags = new HashSet<>(internshipToCopy.getLanguageTags());
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
     * Sets the {@code Role} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withRole(String role) {
        this.role = new Role(role);
        return this;
    }

    /**
     * Parses the {@code languageTags} into a {@code Set<Tag>} and set it to the
     * {@code Internship} that we are building.
     */
    public InternshipBuilder withLanguageTags(String... languageTags) {
        this.languageTags = SampleDataUtil.getTagSet(languageTags);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the
     * {@code Internship} that we are building.
     */
    public InternshipBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
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
     * Sets the {@code Stage} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withStage(String stage) {
        this.stage = new Stage(stage);
        return this;
    }

    /**
     * Sets the {@code DateTime} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withDateTime(String dateTime) {
        this.dateTime = new DateTime(dateTime);
        return this;
    }

    public Internship build() {
        return new Internship(company, role, email, stage, dateTime, languageTags, tags);
    }

}
