package seedu.intrack.testutil;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.intrack.model.internship.Email;
import seedu.intrack.model.internship.Internship;
import seedu.intrack.model.internship.Name;
import seedu.intrack.model.internship.Phone;
import seedu.intrack.model.internship.Position;
import seedu.intrack.model.internship.Remark;
import seedu.intrack.model.internship.Status;
import seedu.intrack.model.internship.Task;
import seedu.intrack.model.internship.Website;
import seedu.intrack.model.tag.Tag;
import seedu.intrack.model.util.SampleDataUtil;

/**
 * A utility class to help with building Internship objects.
 */
public class InternshipBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_POSITION = "Software Engineer";
    public static final String DEFAULT_STATUS = "Progress";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_WEBSITE = "https://careers.shopee.sg/";
    public static final Task DEFAULT_TASK = new Task("Application submitted",
            LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).format(Task.FORMATTER));
    public static final String DEFAULT_REMARK = "";

    private Name name;
    private Position position;
    private Status status;
    private Phone phone;
    private Email email;
    private Website website;
    private List<Task> tasks;
    private Set<Tag> tags;
    private Remark remark;

    /**
     * Creates a {@code InternshipBuilder} with the default details.
     */
    public InternshipBuilder() {
        name = new Name(DEFAULT_NAME);
        position = new Position(DEFAULT_POSITION);
        status = new Status(DEFAULT_STATUS);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        website = new Website(DEFAULT_WEBSITE);
        tasks = new ArrayList<>();
        tasks.add(DEFAULT_TASK);
        tags = new HashSet<>();
        remark = new Remark(DEFAULT_REMARK);
    }

    /**
     * Initializes the InternshipBuilder with the data of {@code internshipToCopy}.
     */
    public InternshipBuilder(Internship internshipToCopy) {
        name = internshipToCopy.getName();
        position = internshipToCopy.getPosition();
        status = internshipToCopy.getStatus();
        phone = internshipToCopy.getPhone();
        email = internshipToCopy.getEmail();
        website = internshipToCopy.getWebsite();
        tasks = new ArrayList<>(internshipToCopy.getTasks());
        tags = new HashSet<>(internshipToCopy.getTags());
        remark = internshipToCopy.getRemark();
    }

    /**
     * Sets the {@code Name} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withName(String name) {
        this.name = new Name(name);
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
     * Sets the {@code Status} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withStatus(String status) {
        this.status = new Status(status);
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
     * Sets the {@code Website} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withWebsite(String website) {
        this.website = new Website(website);
        return this;
    }

    /**
     * Parses the {@code tasks} into a {@code List<Task>} and set it to the {@code Internship} that we are building.
     */
    public InternshipBuilder withTasks(String ... tasks) {
        this.tasks = SampleDataUtil.getTaskList(tasks);
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
     * Sets the {@code Remark} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    public Internship build() {
        return new Internship(name, position, status, phone, email, website, tasks, tags, remark);
    }

}
