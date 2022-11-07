package seedu.address.model.student;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Student in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {
    public static final Integer DEFAULT_RATES_PER_CLASS = 40;

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Phone nokPhone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Money moneyOwed;
    private final Money moneyPaid;
    private final Money ratesPerClass;
    private final AdditionalNotes additionalNotes;
    private Class aClass;
    private final Set<Tag> tags = new HashSet<>();
    private Mark mark;

    /**
     * Constructs a {@code Student} class when first initialized with add command.
     */
    public Student(Name name, Phone phone, Phone nokPhone, Email email, Address address, Set<Tag> tags) {
        requireAllNonNull(name, phone, nokPhone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.nokPhone = nokPhone;
        this.email = email;
        this.address = address;
        this.aClass = new Class();
        this.moneyOwed = new Money();
        this.moneyPaid = new Money();
        this.ratesPerClass = new Money(DEFAULT_RATES_PER_CLASS);
        this.additionalNotes = new AdditionalNotes("");
        this.tags.addAll(tags);
        this.mark = new Mark();
    }

    /**
     * Overloaded constructor.
     * Every field must be present and not null.
     */
    public Student(Name name, Phone phone, Phone nokPhone, Email email, Address address, Class aClass,
                   Money moneyOwed, Money moneyPaid, Money ratesPerClass, AdditionalNotes additionalNotes,
                   Set<Tag> tags, Mark mark) {
        requireAllNonNull(name, phone, email, address, additionalNotes, aClass);
        this.name = name;
        this.phone = phone;
        this.nokPhone = nokPhone;
        this.email = email;
        this.address = address;
        this.aClass = aClass;
        this.moneyOwed = moneyOwed;
        this.moneyPaid = moneyPaid;
        this.ratesPerClass = ratesPerClass;
        this.additionalNotes = additionalNotes;
        this.tags.addAll(tags);
        this.mark = mark;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Phone getNokPhone() {
        return nokPhone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Class getAClass() {
        if (aClass == null) {
            return new Class();
        }
        return aClass;
    }

    public void setClass(Class aClass) {
        this.aClass = aClass;
    }

    public Money getMoneyOwed() {
        return moneyOwed;
    }

    public Money getMoneyPaid() {
        return moneyPaid;
    }

    public Money getRatesPerClass() {
        return ratesPerClass;
    }

    public AdditionalNotes getAdditionalNotes() {
        return additionalNotes;
    }

    public Mark getMarkStatus() {
        return mark;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both students have the same phone number.
     * This defines a weaker notion of equality between two students.
     */
    public boolean isSameStudent(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null
                && otherStudent.getPhone().equals(getPhone());
    }

    /**
     * Determines whether the student is owing money.
     *
     * @return true if the student is owing money
     */
    public boolean isOwingMoney() {
        return moneyOwed.isGreaterThanZero();
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
                && otherStudent.getPhone().equals(getPhone())
                && otherStudent.getNokPhone().equals(getNokPhone())
                && otherStudent.getEmail().equals(getEmail())
                && otherStudent.getAddress().equals(getAddress())
                && otherStudent.getAClass().toString().equals(getAClass().toString())
                && otherStudent.getMoneyOwed().equals(getMoneyOwed())
                && otherStudent.getMoneyPaid().equals(getMoneyPaid())
                && otherStudent.getRatesPerClass().equals(getRatesPerClass())
                && otherStudent.getAdditionalNotes().equals(getAdditionalNotes())
                && otherStudent.getAddress().equals(getAddress())
                && otherStudent.getTags().equals(getTags())
                && otherStudent.getMarkStatus().equals(getMarkStatus());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, nokPhone, email, address, aClass, moneyOwed, moneyPaid, ratesPerClass,
                additionalNotes, tags, mark);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Next of Kin Phone: ")
                .append(getNokPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; Class Date: ")
                .append(getAClass().toString())
                .append("; Money Owed: ")
                .append(getMoneyOwed())
                .append("; Money Paid: ")
                .append(getMoneyPaid())
                .append("; Money Per Class: ")
                .append(getRatesPerClass())
                .append("; Additional notes: ")
                .append(getAdditionalNotes())
                .append("; Mark: ")
                .append(getMarkStatus());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

    /**
     * Checks if class is empty.
     *
     * @return true if class is empty.
     */
    public boolean hasEmptyClass() {
        return getAClass().isEmpty();
    }

    /**
     * Returns compared result between {@code this} and the given {@code student} by Class Start Time.
     * Returns positive integer if {@code this} should be placed after, 0 if same, and negative if before.
     */
    public int compareToByClassStartTimeAsc(Student student) {
        if (student.aClass.startTime == null && this.aClass.startTime == null) {
            return 0;
        } else if (student.aClass.startTime == null) {
            return -1;
        } else if (this.aClass.startTime == null) {
            return 1;
        }
        return this.aClass.startTime.compareTo(student.aClass.startTime);
    }

    /**
     * Returns compared result between {@code this} and the given {@code student} by Name in ascending order.
     * Returns positive integer if {@code this} should be placed after, 0 if same, and negative if before.
     */
    public int compareToByNameAsc(Student student) {
        return this.name.compareTo(student.name);
    }

    /**
     * Returns compared result between {@code this} and the given {@code student} by Name in descending order.
     * Returns positive integer if {@code this} should be placed after, 0 if same, and negative if before.
     */
    public int compareToByNameDesc(Student student) {
        // return opposite result as this::compareToByNameAsc
        return -1 * this.compareToByNameAsc(student);
    }

    /**
     * Returns compared result between {@code this} and the given {@code student} by Class Time in ascending order.
     * Returns positive integer if {@code this} should be placed after, 0 if same, and negative if before.
     * When one of the two has empty {@code Class}, this student should be placed at the end.
     * When both have empty {@code Class}, they are compared by their {@code name} in ascending order.
     */
    public int compareToByClassAsc(Student student) {
        if (this.aClass.isEmpty() && student.aClass.isEmpty()) {
            return this.compareToByNameAsc(student);
        } else if (this.aClass.isEmpty()) {
            return 1;
        } else if (student.aClass.isEmpty()) {
            return -1;
        } else {
            return this.aClass.compareToByClassTime(student.aClass);
        }
    }

    /**
     * Returns compared result between {@code this} and the given {@code student} by Class Time in descending order.
     * Returns positive integer if {@code this} should be placed after, 0 if same, and negative if before.
     * When one of the two has empty {@code Class}, this student should be placed at the end.
     * When both have empty {@code Class}, they are compared by their {@code name} in ascending order.
     */
    public int compareToByClassDesc(Student student) {
        if (this.aClass.isEmpty() && student.aClass.isEmpty()) {
            return this.compareToByNameAsc(student);
        } else if (this.aClass.isEmpty()) {
            return 1;
        } else if (student.aClass.isEmpty()) {
            return -1;
        } else {
            return -1 * this.aClass.compareToByClassTime(student.aClass);
        }
    }

    /**
     * Returns compared result between {@code this} and the given {@code student} by Money Owed in ascending order.
     * Returns positive integer if {@code this} should be placed after, and negative if before.
     * When they have same amount of {@code moneyOwed}, they are compared by their {@code name} in ascending order.
     */
    public int compareToByMoneyOwedAsc(Student student) {
        int result = this.moneyOwed.compareTo(student.moneyOwed);
        if (result == 0) {
            return this.compareToByNameAsc(student);
        } else {
            return result;
        }
    }

    /**
     * Returns compared result between {@code this} and the given {@code student} by Money Owed in descending order.
     * Returns positive integer if {@code this} should be placed after, and negative if before.
     * When they have same amount of {@code moneyOwed}, they are compared by their {@code name} in ascending order.
     */
    public int compareToByMoneyOwedDesc(Student student) {
        int result = -1 * this.moneyOwed.compareTo(student.moneyOwed);
        if (result == 0) {
            return this.compareToByNameAsc(student);
        } else {
            return result;
        }
    }

    /**
     * Checks if the student has a phone number that is the same as the next-of-kin's.
     *
     * @return true if phone number equals next-of-kin's phone.
     */
    public boolean hasSharedPhone() {
        return phone.equals(nokPhone);
    }

    /**
     * Validates whether a student has 2 classes on the same date.
     *
     * @param student the student to check against.
     * @return true if the student is not marked and have the same date.
     */
    public boolean hasSameDateAs(Student student) {
        if (!mark.isMarked()) {
            return false;
        }
        return aClass.isSameDateAs(student.aClass.date);
    }

    /**
     * Resets the mark status to being not present.
     */
    public void resetMarkStatus() {
        this.mark = new Mark(Boolean.FALSE);
    }
}
