package tuthub.testutil;

import java.util.HashSet;
import java.util.Set;

import tuthub.model.tag.Tag;
import tuthub.model.tutor.Comment;
import tuthub.model.tutor.CommentList;
import tuthub.model.tutor.Email;
import tuthub.model.tutor.Module;
import tuthub.model.tutor.Name;
import tuthub.model.tutor.Phone;
import tuthub.model.tutor.Rating;
import tuthub.model.tutor.StudentId;
import tuthub.model.tutor.TeachingNomination;
import tuthub.model.tutor.Tutor;
import tuthub.model.tutor.Year;
import tuthub.model.util.SampleDataUtil;

/**
 * A utility class to help with building Tutor objects.
 */
public class TutorBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "E1234567";
    public static final Set<Module> DEFAULT_MODULE = SampleDataUtil.getModuleSet("cs2103");
    public static final String DEFAULT_YEAR = "3";
    public static final String DEFAULT_STUDENTID = "A1234567X";
    public static final String DEFAULT_TEACHINGNOMINATION = "0";
    public static final String DEFAULT_RATING = "5.0";

    private Name name;
    private Phone phone;
    private Email email;
    private Set<Module> modules;
    private Year year;
    private StudentId studentId;
    private TeachingNomination teachingNomination;
    private Rating rating;
    private CommentList comments;
    private Set<Tag> tags;

    /**
     * Creates a {@code TutorBuilder} with the default details.
     */
    public TutorBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        modules = new HashSet<>(DEFAULT_MODULE);
        year = new Year(DEFAULT_YEAR);
        studentId = new StudentId(DEFAULT_STUDENTID);
        teachingNomination = new TeachingNomination(DEFAULT_TEACHINGNOMINATION);
        rating = new Rating(DEFAULT_RATING);
        comments = new CommentList();
        tags = new HashSet<>();
    }

    /**
     * Initializes the TutorBuilder with the data of {@code tutorToCopy}.
     */
    public TutorBuilder(Tutor tutorToCopy) {
        name = tutorToCopy.getName();
        phone = tutorToCopy.getPhone();
        email = tutorToCopy.getEmail();
        modules = new HashSet<>(tutorToCopy.getModules());
        year = tutorToCopy.getYear();
        studentId = tutorToCopy.getStudentId();
        teachingNomination = tutorToCopy.getTeachingNomination();
        rating = tutorToCopy.getRating();
        comments = new CommentList(tutorToCopy.getComments().getList());
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
        this.comments = comments.addComment(new Comment(comment));
        return this;
    }

    /**
     * Sets the {@code Module} of the {@code Tutor} that we are building.
     */
    public TutorBuilder withModule(String... module) {
        this.modules = SampleDataUtil.getModuleSet(module);
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

    /**
     * Sets the {@code TeachingNomination} of the {@code Tutor} that we are building.
     */
    public TutorBuilder withTeachingNomination(String teachingNomination) {
        this.teachingNomination = new TeachingNomination(teachingNomination);
        return this;
    }

    /**
     * Sets the {@code Rating} of the {@code Tutor} that we are building.
     */
    public TutorBuilder withRating(String rating) {
        this.rating = new Rating(rating);
        return this;
    }

    public Tutor build() {
        return new Tutor(name, phone, email, modules, year, studentId, comments, teachingNomination, rating, tags);
    }

}
