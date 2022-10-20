package friday.model.student;

import static friday.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import friday.model.tag.Tag;

/**
 * Represents a Student in FRIDAY.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    // Identity fields
    private final Name name;
    private final TelegramHandle telegramHandle;

    // Data fields
    private final MasteryCheck masteryCheck;
    private final Consultation consultation;
    private final Remark remark;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Student(Name name, TelegramHandle telegramHandle, Consultation consultation, MasteryCheck masteryCheck,
                   Remark remark, Set<Tag> tags) {
        requireAllNonNull(name, telegramHandle, consultation, masteryCheck, tags);
        this.name = name;
        this.telegramHandle = telegramHandle;
        this.consultation = consultation;
        this.masteryCheck = masteryCheck;
        this.remark = remark;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public TelegramHandle getTelegramHandle() {
        return telegramHandle;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public MasteryCheck getMasteryCheck() {
        return masteryCheck;
    }

    public Remark getRemark() {
        return remark;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both students have the same name.
     * This defines a weaker notion of equality between two students.
     */
    public boolean isSameStudent(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null
                && otherStudent.getName().equals(getName());
    }

    /**
     * Returns true if both students have the same identity and data fields.
     * This defines a stronger notion of equality between two students.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Student)) {
            return false;
        }

        Student otherStudent = (Student) other;
        return otherStudent.getName().equals(getName())
                && otherStudent.getTelegramHandle().equals(getTelegramHandle())
                && otherStudent.getConsultation().equals(getConsultation())
                && otherStudent.getMasteryCheck().equals(getMasteryCheck())
                && otherStudent.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, telegramHandle, consultation, masteryCheck, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName());

        if (!telegramHandle.isEmpty()) {
            builder.append("; Telegram handle: ")
                    .append(getTelegramHandle());
        }
        if (!consultation.isEmpty()) {
            builder.append("; ")
                    .append(getConsultation());
        }
        if (!masteryCheck.isEmpty()) {
            builder.append("; ")
                    .append(getMasteryCheck());
        }
        if (!remark.isEmpty()) {
            builder.append(" Remark: ")
                    .append(getRemark());
        }

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
