package seedu.masslinkers.testutil;

import java.util.HashSet;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.masslinkers.model.interest.Interest;
import seedu.masslinkers.model.student.Email;
import seedu.masslinkers.model.student.GitHub;
import seedu.masslinkers.model.student.Mod;
import seedu.masslinkers.model.student.Name;
import seedu.masslinkers.model.student.Phone;
import seedu.masslinkers.model.student.Student;
import seedu.masslinkers.model.student.Telegram;
import seedu.masslinkers.model.util.SampleDataUtil;
//@@author
/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_TELEGRAM = "amybee";
    public static final String DEFAULT_GITHUB = "amybee";

    private Name name;
    private Phone phone;
    private Email email;
    private Telegram handle;
    private GitHub username;
    private Set<Interest> interests;
    private ObservableList<Mod> mods;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        handle = new Telegram(DEFAULT_TELEGRAM);
        username = new GitHub(DEFAULT_GITHUB);
        interests = new HashSet<>();
        mods = FXCollections.observableArrayList();
    }

    /**
     * Initializes the StudentBuilder with the data of {@code studentToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        phone = studentToCopy.getPhone();
        email = studentToCopy.getEmail();
        handle = studentToCopy.getTelegram();
        username = studentToCopy.getGitHub();
        interests = new HashSet<>(studentToCopy.getInterests());
        mods = FXCollections.observableArrayList(studentToCopy.getMods());
    }

    /**
     * Sets the {@code Name} of the {@code Student} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code interests} into a {@code Set<Interest>} and set it to the {@code Student} that we are building.
     */
    public StudentBuilder withInterests(String ... interests) {
        this.interests = SampleDataUtil.getInterestsSet(interests);
        return this;
    }

    /**
     * Parses the {@code mods} into a {@code Set<Mod>} and set it to the {@code Student} that we are building.
     */
    public StudentBuilder withMods(String ... mods) {
        this.mods = SampleDataUtil.getModSet(mods);
        return this;
    }

    /**
     * Sets the {@code Telegram} of the {@code Student} that we are building.
     */
    public StudentBuilder withTelegram(String handle) {
        this.handle = new Telegram(handle);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Student} that we are building.
     */
    public StudentBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Student} that we are building.
     */
    public StudentBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code GitHub} of the {@code Student} that we are building.
     */
    public StudentBuilder withGitHub(String gitHub) {
        this.username = new GitHub(gitHub);
        return this;
    }

    public Student build() {
        return new Student(name, phone, email, handle, username, interests, mods);
    }
}
