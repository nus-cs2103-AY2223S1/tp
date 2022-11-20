package friday.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Map;
import java.util.Set;

import friday.model.alias.Alias;
import friday.model.alias.AliasMap;
import friday.model.alias.ReservedKeyword;
import friday.model.student.Student;
import friday.model.student.UniqueStudentList;
import javafx.collections.ObservableList;

/**
 * Wraps all data at the highest level
 * Duplicates are not allowed (by .isSameStudent comparison)
 */
public class Friday implements ReadOnlyFriday {


    private final UniqueStudentList students;
    private final AliasMap aliases;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        students = new UniqueStudentList();
        aliases = new AliasMap();
    }

    public Friday() {}

    /**
     * Creates FRIDAY using the Students in the {@code toBeCopied}
     */
    public Friday(ReadOnlyFriday toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the student list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setStudents(List<Student> students) {
        this.students.setStudents(students);
    }

    /**
     * Replaces the contents of the alias map with alias, keyword pair.
     */
    public void setAliases(Set<Map.Entry<Alias, ReservedKeyword>> aliases) {
        this.aliases.setAliases(aliases);
    }

    /**
     * Resets the existing data of {@code FRIDAY} with {@code newData}.
     */
    public void resetData(ReadOnlyFriday newData) {
        requireNonNull(newData);

        setStudents(newData.getStudentList());
        setAliases(newData.getAliasMap());
    }

    //// student-level operations

    /**
     * Returns true if a student with the same identity as {@code student} exists in FRIDAY.
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return students.contains(student);
    }

    /**
     * Adds a student to FRIDAY.
     * The student must not already exist in FRIDAY.
     */
    public void addStudent(Student p) {
        students.add(p);
    }

    /**
     * Replaces the given student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in FRIDAY.
     * The student identity of {@code editedStudent} must not be the same as another existing student in FRIDAY.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireNonNull(editedStudent);

        students.setStudent(target, editedStudent);
    }

    /**
     * Removes {@code key} from this {@code FRIDAY}.
     * {@code key} must exist in FRIDAY.
     */
    public void removeStudent(Student key) {
        students.remove(key);
    }

    //// alias-level operations

    /**
     * Returns true if an alias with the same identity as {@code alias} exists in FRIDAY.
     */
    public boolean hasAlias(Alias alias) {
        requireNonNull(alias);
        return aliases.contains(alias);
    }

    /**
     * Adds an alias that maps to keyword to FRIDAY.
     * The alias must not already exist in FRIDAY.
     * The keyword must be a valid reserved keyword.
     */
    public void addAlias(Alias toAdd, ReservedKeyword keyword) {
        aliases.add(toAdd, keyword);
    }

    /**
     * Removes {@code key} from this {@code FRIDAY}.
     * {@code key} must exist in FRIDAY.
     */
    public void removeAlias(Alias key) {
        aliases.remove(key);
    }

    /**
     * Returns the keyword map by a {@code key}.
     * {@code key} must exist in FRIDAY.
     */
    public String getKeyword(String key) {
        return aliases.getKeyword(key);
    }

    /**
     * Returns the String representation of all aliases in FRIDAY.
     */
    public String displayAliases() {
        return aliases.displayAliases();
    }

    //// util methods

    @Override
    public String toString() {
        return students.asUnmodifiableObservableList().size() + " students";
        // TODO: refine later
    }

    @Override
    public ObservableList<Student> getStudentList() {
        return students.asUnmodifiableObservableList();
    }

    @Override
    public Set<Map.Entry<Alias, ReservedKeyword>> getAliasMap() {
        return aliases.entrySet();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Friday // instanceof handles nulls
                && students.equals(((Friday) other).students)
                && aliases.equals(((Friday) other).aliases));
    }

    @Override
    public int hashCode() {
        return students.hashCode();
    }
}
