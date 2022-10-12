package tuthub.testutil;

import java.util.HashSet;
import java.util.Set;

import tuthub.model.tag.Tag;
import tuthub.model.tutor.Comment;
import tuthub.model.tutor.Email;
import tuthub.model.tutor.Module;
import tuthub.model.tutor.Name;
import tuthub.model.tutor.Phone;
import tuthub.model.tutor.StudentId;
import tuthub.model.tutor.Tutor;
import tuthub.model.tutor.Year;
import tuthub.model.util.SampleDataUtil;

/**
 * A utility class to help with building Tutor objects.
 */
public class TutorBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_MODULE = "cs2103t";
    public static final String DEFAULT_YEAR = "3";
    public static final String DEFAULT_STUDENTID = "A1234567X";
    public static final String DEFAULT_COMMENT = "";

    private Name name;
    private Phone phone;
    private Email email;
    private Module module;
    private Year year;
    private StudentId studentId;
    private Comment comment;
    private Set<Tag> tags;

    /**
     * Creates a {@code TutorBuilder} with the default details.
     */
    public TutorBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        module = new Module(DEFAULT_MODULE);
        year = new Year(DEFAULT_YEAR);
        studentId = new StudentId(DEFAULT_STUDENTID);
        comment = new Comment(DEFAULT_COMMENT);
        tags = new HashSet<>();
    }

    /**
     * Initializes the TutorBuilder with the data of {@code tutorToCopy}.
     */
    public TutorBuilder(Tutor tutorToCopy) {
        name = tutorToCopy.getName();
        phone = tutorToCopy.getPhone();
        email = tutorToCopy.getEmail();
        module = tutorToCopy.getModule();
        year = tutorToCopy.getYear();
        studentId = tutorToCopy.getStudentId();
        comment = tutorToCopy.getComment();
        tags = new HashSet<>(tutorToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Tutor} that we are building.
     */
    public TutorBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Tutor} that we are building.
     */
    public TutorBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Tutor} that we are building.
     */
    public TutorBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Tutor} that we are building.
     */
    public TutorBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }
    /**
     * Sets the {@code Comment} of the {@code Tutor} that we are building.
     */
    public TutorBuilder withComment(String comment) {
        this.comment = new Comment(comment);
        return this;
    }

    /**
     * Sets the {@code Module} of the {@code Tutor} that we are building.
     */
    public TutorBuilder withModule(String module) {
        this.module = new Module(module);
        return this;
    }

    /**
     * Sets the {@code Year} of the {@code Tutor} that we are building.
     */
    public TutorBuilder withYear(String year) {
        this.year = new Year(year);
        return this;
    }

    /**
     * Sets the {@code StudentId} of the {@code Tutor} that we are building.
     */
    public TutorBuilder withStudentId(String studentId) {
        this.studentId = new StudentId(studentId);
        return this;
    }

    public Tutor build() {
        return new Tutor(name, phone, email, module, year, studentId, comment, tags);
    }

}
