package seedu.address.model.person.tutor;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

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

/**
 * Represents a Tutor in the address book.
 */
public class Tutor extends Person {

    private final Qualification qualification;
    private final Institution institution;
    private final List<TuitionClass> tuitionClasses = new ArrayList<>();

    /**
     * Every field must be present and not null.
     */
    public Tutor(Name name, Phone phone, Email email, Address address, Set<Tag> tags,
                 Qualification qualification, Institution institution, List<TuitionClass> tuitionClasses) {
        super(name, phone, email, address, tags);
        requireAllNonNull(qualification, institution);
        this.qualification = qualification;
        this.institution = institution;
        this.tuitionClasses.addAll(tuitionClasses);
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
                && otherTutor.getInstitution().equals(getInstitution());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(this.getName(), this.getPhone(), this.getEmail(), this.getAddress(), this.getTags(),
                qualification, institution);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        builder.append(super.toString())
                .append("; Highest Qualification: ")
                .append(getQualification())
                .append("; Institution: ")
                .append(getInstitution());

        return builder.toString();
    }
}
