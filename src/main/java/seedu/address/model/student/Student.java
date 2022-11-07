package seedu.address.model.student;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Student in the SETA application.
 */
public class Student {

    // Identity fields
    private final StuName name;
    private final Telegram telegram;
    private final StuEmail email;

    // Data fields
    private final Response response;
    private final Attendance attendance;
    private final HelpTag helpTag;

    /**
     * Name, Telegram and Email field must not be null.
     */

    public Student(StuName name, Telegram telegram, StuEmail email, Response response, Attendance attendance,
                   HelpTag helpTag) {

        requireAllNonNull(name, telegram, email);
        this.name = name;
        this.telegram = telegram;
        this.email = email;
        this.response = response;
        this.attendance = attendance;
        this.helpTag = helpTag;
    }

    public StuName getName() {
        return name;
    }

    public Telegram getTelegram() {
        return telegram;
    }

    public StuEmail getEmail() {
        return email;
    }

    public Attendance getAttendance() {
        return attendance;
    }

    public Response getResponse() {
        return response;
    }

    public HelpTag getHelpTag() {
        return helpTag;
    }

    public boolean needsHelp() {
        return helpTag.getBool();
    }

    /**
     * Returns a StuName object with its fullname attribute converted to all lowercase and with spaces removed.
     */
    public StuName nameToLowercaseRemoveSpaces(String name) {
        name = name.replaceAll("\\s", "");
        name = name.toLowerCase();
        return new StuName(name);
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
                && nameToLowercaseRemoveSpaces(otherStudent.getName().fullName)
                .equals(nameToLowercaseRemoveSpaces(getName().fullName));
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
                .append(getAttendance())
                .append("; Help Tag: ")
                .append(getHelpTag());

        return builder.toString();
    }

}
