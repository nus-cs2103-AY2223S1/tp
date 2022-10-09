package seedu.address.model.person;

import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Student in the address book.
 */
public class Student extends Person {

    private String id;
    private String telegramHandle;
    private String studentInfo;
    /**
     * Every field must be present and not null.
     *
     * @param name
     * @param phone
     * @param email
     * @param address
     * @param tags
     */
    public Student(Name name, Phone phone, Email email, Address address, Set<Tag> tags, String id,
                   String telegramHandle, String studentInfo) {
        super(name, phone, email, address, tags);
        this.id = id;
        this.telegramHandle = telegramHandle;
        this.studentInfo = studentInfo;
    }

    public String getId() {
        return id;
    }

    public String getTelegramHandle() {
        return telegramHandle;
    }

    public String getStudentInfo() {
        return studentInfo;
    }

    /**
     * Compares if students are the same.
     *
     * @param otherStudent
     * @return True if students are the same.
     */
    public boolean isSameStudent(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null
                && otherStudent.getId().equals(getId());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; ID: ")
                .append(getId())
                .append("; Telegram: ")
                .append(getTelegramHandle())
                .append("; Student Info:")
                .append(getStudentInfo());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}
