package seedu.address.model.person;

import java.util.Objects;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class Student {

    private final Name name;
    private final Telegram telegram;
    private final Email email;
    private final Response response;
    private final Attendance attendance;
    private final HelpTag helptag;

    public Student(Name name, Telegram telegram, Email email, Response response, Attendance attendance, HelpTag helptag) {
        requireAllNonNull(name, telegram, email);
        this.name = name;
        this.telegram = telegram;
        this.email = email;
        this.response = response;
        this.attendance = attendance;
        this.helptag = helptag;
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

    public Response getResponse() {
        return response;
    }

    public Attendance getAttendance() {
        return attendance;
    }

    public HelpTag getHelptag() {
        return helptag;
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
                && otherStudent.getResponse().equals(getResponse())
                && otherStudent.getAttendance().equals(getAttendance())
                && otherStudent.getHelptag().equals(getHelptag());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, telegram, email, response, attendance, helptag);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Telegram: ")
                .append(getTelegram())
                .append("; Email: ")
                .append(getEmail());

        return builder.toString();
    }

}
