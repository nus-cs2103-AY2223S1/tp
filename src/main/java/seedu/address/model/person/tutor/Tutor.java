package seedu.address.model.person.tutor;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.tuitionclass.TuitionClass;
import seedu.address.model.tuitionclass.exceptions.DuplicateTuitionClassException;
import seedu.address.model.tuitionclass.exceptions.TuitionClassNotAssignedException;

/**
 * Represents a Tutor in the address book.
 */
public class Tutor extends Person {
    private static int id = 0;
    private final Qualification qualification;
    private final Institution institution;
    private final List<TuitionClass> tuitionClasses = new ArrayList<>();
    private final String uniqueId;


    /**
     * Every field must be present and not null.
     */
    public Tutor(Name name, Phone phone, Email email, Address address, Set<Tag> tags,
                 Qualification qualification, Institution institution) {
        super(name, phone, email, address, tags);
        requireAllNonNull(qualification, institution);
        id++;
        this.qualification = qualification;
        this.institution = institution;

        LocalTime timeAdded = LocalTime.now();
        this.uniqueId = timeAdded + "tutor";
    }

    /**
     * Every field must be present and not null.
     * Should not be used in AddCommand as tuition classes
     * are not added in AddCommand.
     */
    public Tutor(Name name, Phone phone, Email email, Address address, Set<Tag> tags,
                 Qualification qualification, Institution institution, List<TuitionClass> tuitionClasses) {
        super(name, phone, email, address, tags);
        requireAllNonNull(qualification, institution);
        id++;
        this.qualification = qualification;
        this.institution = institution;
        this.tuitionClasses.addAll(tuitionClasses);

        LocalTime timeAdded = LocalTime.now();
        this.uniqueId = timeAdded + "tutor";
    }

    public Qualification getQualification() {
        return qualification;
    }

    public Institution getInstitution() {
        return institution;
    }

    public List<TuitionClass> getTuitionClasses() {
        return tuitionClasses;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void minusId() {
        id--;
    }

    /**
     * Add {@code tuitionClass} to the list of classes that tutor is currently teaching.
     */
    public void assignClassToTutor(TuitionClass tuitionClass) {
        if (tuitionClasses.stream().anyMatch(tuitionClass::isSameTuitionClass)) {
            throw new DuplicateTuitionClassException();
        }
        tuitionClasses.add(tuitionClass);
    }

    /**
     * Remove {@code tuitionClass} from the list of classes that tutor is currently teaching.
     */
    public void unassignClassFromTutor(TuitionClass tuitionClass) {
        if (tuitionClasses.stream().noneMatch(tuitionClass::isSameTuitionClass)) {
            throw new TuitionClassNotAssignedException();
        }
        tuitionClasses.remove(tuitionClass);
    }

    /**
     * Returns true if both tutors have the same identity and data fields.
     * This defines a stronger notion of equality between two tutors.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Tutor)) {
            return false;
        }

        Tutor otherTutor = (Tutor) other;
        return otherTutor.getName().equals(getName())
                && otherTutor.getPhone().equals(getPhone())
                && otherTutor.getEmail().equals(getEmail())
                && otherTutor.getAddress().equals(getAddress())
                && otherTutor.getTags().equals(getTags())
                && otherTutor.getQualification().equals(getQualification())
                && otherTutor.getInstitution().equals(getInstitution())
                && otherTutor.getTuitionClasses().equals(getTuitionClasses());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(this.getName(), this.getPhone(), this.getEmail(), this.getAddress(), this.getTags(),
                qualification, institution, tuitionClasses);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        builder.append(super.toString())
                .append("; Highest Qualification: ")
                .append(getQualification())
                .append("; Institution: ")
                .append(getInstitution());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        return builder.toString();
    }
}
