package seedu.address.model.student;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Student in the SETA application.
 */
public class Student {

    // Identity fields
    private final Name name;
    private final Telegram telegram;
    private final Email email;

    // Data fields
    private final Response response;
    private final Attendance attendance;
//    private final HelpTag helpTag;

    /**
     * Every field must be present and not null.
     */
    public Student(Name name, Telegram telegram, Email email, Response response, Attendance attendance) {
        requireAllNonNull(name, telegram, email);
        this.name = name;
        this.telegram = telegram;
        this.email = email;
        this.response = response;
        this.attendance = attendance;
    }

    public Name getName() {
        return name;
    }

    public Telegram getTelegram() {
        return telegram;
    }

    public Email getEmail() {
        return email;
    }

    public Attendance getAttendance() {
        return attendance;
    }

    public Response getResponse() {
        return response;
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
                && otherStudent.getTelegram().equals(getTelegram())
                && otherStudent.getEmail().equals(getEmail())
                && otherStudent.getAttendance().equals(getAttendance())
                && otherStudent.getResponse().equals(getResponse());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, telegram, email, response, attendance);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Telegram: ")
                .append(getTelegram())
                .append("; Email: ")
                .append(getEmail())
                .append("; Response: ")
                .append(getResponse())
                .append("; Attendance: ")
                .append(getAttendance());

        return builder.toString();
    }

}
