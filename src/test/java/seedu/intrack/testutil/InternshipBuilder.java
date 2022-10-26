package seedu.intrack.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.intrack.model.internship.Email;
import seedu.intrack.model.internship.Internship;
import seedu.intrack.model.internship.Name;
import seedu.intrack.model.internship.Position;
import seedu.intrack.model.internship.Remark;
import seedu.intrack.model.internship.Salary;
import seedu.intrack.model.internship.Status;
import seedu.intrack.model.internship.Task;
import seedu.intrack.model.internship.Website;
import seedu.intrack.model.tag.Tag;
import seedu.intrack.model.util.SampleDataUtil;

/**
 * A utility class to help with building Internship objects.
 */
public class InternshipBuilder {

    public static final String DEFAULT_NAME = "Airbnb Inc";
    public static final String DEFAULT_POSITION = "Software Engineer";
    public static final String DEFAULT_STATUS = "Progress";
    public static final String DEFAULT_EMAIL = "careers@airbnb.com";
    public static final String DEFAULT_WEBSITE = "https://careers.airbnb.com/";
    public static final String DEFAULT_SALARY = "100000";
    public static final String DEFAULT_REMARK = "";

    private Name name;
    private Position position;
    private Status status;
    private Email email;
    private Website website;
    private List<Task> tasks;
    private Salary salary;
    private Set<Tag> tags;
    private Remark remark;

    /**
     * Creates a {@code InternshipBuilder} with the default details.
     */
    public InternshipBuilder() {
        name = new Name(DEFAULT_NAME);
        position = new Position(DEFAULT_POSITION);
        status = new Status(DEFAULT_STATUS);
        email = new Email(DEFAULT_EMAIL);
        website = new Website(DEFAULT_WEBSITE);
        tasks = new ArrayList<>();
        salary = new Salary(DEFAULT_SALARY);
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
        email = internshipToCopy.getEmail();
        website = internshipToCopy.getWebsite();
        tasks = new ArrayList<>(internshipToCopy.getTasks());
        salary = internshipToCopy.getSalary();
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
     * Sets {@code List<Task> tasks} to the {@code Internship} that we are building.
     */
    public InternshipBuilder withTaskList(List<Task> tasks) {
        this.tasks = tasks;
        return this;
    }

    /**
     * Sets the {@code Salary} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withSalary(String salary) {
        this.salary = new Salary(salary);
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
        return new Internship(name, position, status, email, website, tasks, salary, tags, remark);
    }

}
