package seedu.address.testutil;


import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.OfficeHour;
import seedu.address.model.person.Professor;
import seedu.address.model.person.Rating;
import seedu.address.model.person.Specialisation;

/**
 * A utility class to help with building Person objects.
 */
public class ProfessorBuilder extends PersonBuilder {

    public static final String DEFAULT_MODULE_CODE = "CS1231S";
    public static final String DEFAULT_RATING = "5";
    public static final String DEFAULT_SPECIALISATION = "Computer Graphics";
    public static final String DEFAULT_OFFICE_HOUR = "TUESDAY, 06:00 PM - 09:00 PM";
    private ModuleCode moduleCode;
    private Rating rating;
    private Specialisation field;
    private OfficeHour officeHour;

    private Specialisation specialisation;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public ProfessorBuilder() {
        super();
        this.moduleCode = new ModuleCode(DEFAULT_MODULE_CODE);
        this.rating = new Rating(DEFAULT_RATING, false);
        this.field = new Specialisation(DEFAULT_SPECIALISATION, false);
        this.officeHour = new OfficeHour(DEFAULT_OFFICE_HOUR, false);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public ProfessorBuilder(Professor personToCopy) {
        super(personToCopy);
        moduleCode = personToCopy.getModuleCode();
        this.officeHour = personToCopy.getOfficeHour();
    }


    /**
     * Sets the {@code OfficeHour} of the {@code Professor} that we are building.
     */
    public ProfessorBuilder withOfficeHours(String officeHour) {
        this.officeHour = new OfficeHour(officeHour, false);
        return this;
    }

    @Override
    public Professor build() {
        return new Professor(getName(), moduleCode, getPhone(), getEmail(), getGender(), getTags(), getLocation(),
                getGithubUsername(), rating, field, officeHour);
    }
    /**
     * Sets the {@code ModuleCode} of the {@code Professor} that we are building.
     */
    public ProfessorBuilder withModuleCode(String moduleCode) {
        this.moduleCode = new ModuleCode(moduleCode);
        return this;
    }

    /**
     * Sets the {@code Rating} of the {@code Professor} that we are building.
     */
    public ProfessorBuilder withRating(String rating) {
        this.rating = new Rating(rating);
        return this;
    }

    /**
     * Sets the {@code Specialisation} of the {@code Professor} that we are building.
     */
    public ProfessorBuilder withSpecialisation(String specialisation) {
        this.field = new Specialisation(specialisation);
        return this;
    }

    /**
     * Sets the {@code OfficeHour} of the {@code Professor} that we are building.
     */
    public ProfessorBuilder withOfficeHour(String officeHour) {
        this.officeHour = new OfficeHour(officeHour, true);
        System.out.println(officeHour);
        return this;
    }
}
