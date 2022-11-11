package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.person.tutor.Institution;
import seedu.address.model.person.tutor.Qualification;
import seedu.address.model.person.tutor.Tutor;
import seedu.address.model.tuitionclass.TuitionClass;

/**
 * A utility class to help with building Tutor objects.
 */
public class TutorBuilder extends PersonBuilder {

    public static final String DEFAULT_QUALIFICATION = "Bachelor of Computing";
    public static final String DEFAULT_INSTITUTION = "National University of Singapore";

    private Qualification qualification;
    private Institution institution;
    private List<TuitionClass> tuitionClasses = new ArrayList<>();

    /**
     * Creates a {@code TutorBuilder} with the default details.
     */
    public TutorBuilder() {
        super();
        qualification = new Qualification(DEFAULT_QUALIFICATION);
        institution = new Institution(DEFAULT_INSTITUTION);
    }

    /**
     * Initializes the TutorBuilder with the data of {@code tutorToCopy}.
     */
    public TutorBuilder(Tutor tutorToCopy) {
        super(tutorToCopy);
        qualification = tutorToCopy.getQualification();
        institution = tutorToCopy.getInstitution();
        tuitionClasses = tutorToCopy.getTuitionClasses();
    }

    /**
     * Sets the {@code Name} of the {@code Tutor} that we are building.
     */
    @Override
    public TutorBuilder withName(String name) {
        return (TutorBuilder) super.withName(name);
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Tutor} that we are building.
     */
    @Override
    public TutorBuilder withTags(String ... tags) {
        return (TutorBuilder) super.withTags(tags);
    }

    /**
     * Sets the {@code Address} of the {@code Tutor} that we are building.
     */
    @Override
    public TutorBuilder withAddress(String address) {
        return (TutorBuilder) super.withAddress(address);

    }

    /**
     * Sets the {@code Phone} of the {@code Tutor} that we are building.
     */
    @Override
    public TutorBuilder withPhone(String phone) {
        return (TutorBuilder) super.withPhone(phone);
    }

    /**
     * Sets the {@code Email} of the {@code Tutor} that we are building.
     */
    @Override
    public TutorBuilder withEmail(String email) {
        return (TutorBuilder) super.withEmail(email);
    }

    /**
     * Sets the {@code Qualification} of the {@code Tutor} that we are building.
     */
    public TutorBuilder withQualification(String qualification) {
        this.qualification = new Qualification(qualification);
        return this;
    }

    /**
     * Sets the {@code Institution} of the {@code Tutor} that we are building.
     */
    public TutorBuilder withInstitution(String institution) {
        this.institution = new Institution(institution);
        return this;
    }

    /**
     * Sets the {@code TuitionClass} of the {@code Tutor} that we are building.
     */
    public TutorBuilder withTuitionClasses(TuitionClass... tuitionClass) {
        this.tuitionClasses = new ArrayList<>(Arrays.asList(tuitionClass));
        return this;
    }

    /**
     * Sets the {@code TuitionClass} of the {@code Tutor} that we are building.
     */
    public TutorBuilder withTuitionClasses(List<TuitionClass> tuitionClasses) {
        this.tuitionClasses = tuitionClasses;
        return this;
    }

    /**
     * Builds {@code Tutor} object.
     *
     * @return A Tutor object.
     */
    public Tutor build() {
        return new Tutor(this.getName(), this.getPhone(), this.getEmail(), this.getAddress(),
                this.getTags(), qualification, institution, tuitionClasses);
    }
}
