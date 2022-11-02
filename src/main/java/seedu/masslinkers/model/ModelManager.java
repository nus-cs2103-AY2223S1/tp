package seedu.masslinkers.model;

import static java.util.Objects.requireNonNull;
import static seedu.masslinkers.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.masslinkers.commons.core.GuiSettings;
import seedu.masslinkers.commons.core.LogsCenter;
import seedu.masslinkers.model.student.Email;
import seedu.masslinkers.model.student.GitHub;
import seedu.masslinkers.model.student.Phone;
import seedu.masslinkers.model.student.Student;
import seedu.masslinkers.model.student.Telegram;

//@@author
/**
 * Represents the in-memory model of the mass linkers data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final MassLinkers massLinkers;
    private final UserPrefs userPrefs;
    private final FilteredList<Student> filteredStudents;

    /**
     * Initializes a ModelManager with the given massLinkers and userPrefs.
     */
    public ModelManager(ReadOnlyMassLinkers massLinkers, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(massLinkers, userPrefs);

        logger.fine("Initializing with mass linkers: " + massLinkers + " and user prefs " + userPrefs);

        this.massLinkers = new MassLinkers(massLinkers);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredStudents = new FilteredList<>(this.massLinkers.getStudentList());
    }

    public ModelManager() {
        this(new MassLinkers(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getMassLinkersFilePath() {
        return userPrefs.getMassLinkersFilePath();
    }

    @Override
    public void setMassLinkersFilePath(Path massLinkersFilePath) {
        requireNonNull(massLinkersFilePath);
        userPrefs.setMassLinkersFilePath(massLinkersFilePath);
    }

    //=========== MassLinkers ================================================================================

    @Override
    public void setMassLinkers(ReadOnlyMassLinkers massLinkers) {
        this.massLinkers.resetData(massLinkers);
    }

    @Override
    public ReadOnlyMassLinkers getMassLinkers() {
        return massLinkers;
    }

    @Override
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return massLinkers.hasStudent(student);
    }

    @Override
    public boolean hasTelegram(Telegram telegram) {
        requireNonNull(telegram);
        return massLinkers.hasTelegram(telegram);
    }

    @Override
    public boolean hasGitHub(GitHub gitHub) {
        requireNonNull(gitHub);
        return massLinkers.hasGitHub(gitHub);
    }

    @Override
    public boolean hasEmail(Email email) {
        requireNonNull(email);
        return massLinkers.hasEmail(email);
    }

    @Override
    public boolean hasPhone(Phone phone) {
        requireNonNull(phone);
        return massLinkers.hasPhone(phone);
    }

    @Override
    public void deleteStudent(Student target) {
        massLinkers.removeStudent(target);
    }

    @Override
    public void addStudent(Student student) {
        massLinkers.addStudent(student);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
    }

    @Override
    public void setStudent(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);

        massLinkers.setStudent(target, editedStudent);
    }

    //=========== Filtered Student List Accessors =============================================================
    /**
     * Returns an unmodifiable view of the list of {@code Student} backed by the internal list of
     * {@code versionedMassLinkers}
     */
    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return filteredStudents;
    }

    @Override
    public void updateFilteredStudentList(Predicate<Student> predicate) {
        requireNonNull(predicate);
        filteredStudents.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return massLinkers.equals(other.massLinkers)
                && userPrefs.equals(other.userPrefs)
                && filteredStudents.equals(other.filteredStudents);
    }

}
