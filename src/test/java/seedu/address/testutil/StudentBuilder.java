package seedu.address.testutil;

import seedu.address.model.student.Attendance;
import seedu.address.model.student.Response;
import seedu.address.model.student.StuEmail;
import seedu.address.model.student.StuName;
import seedu.address.model.student.Student;
import seedu.address.model.student.Telegram;

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_TELEGRAM = "@Amybee";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_RESPONSE = "1";
    public static final String DEFAULT_ATTENDANCE = "1";


    private StuName name;
    private StuEmail email;
    private Telegram telegram;
    private Response response;
    private Attendance attendance;


    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new StuName(DEFAULT_NAME);
        email = new StuEmail(DEFAULT_EMAIL);
        telegram = new Telegram(DEFAULT_TELEGRAM);
        response = new Response(DEFAULT_RESPONSE);
        attendance = new Attendance(DEFAULT_ATTENDANCE);
    }

    /**
     * Initializes the StudentBuilder with the data of {@code studentToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        telegram = studentToCopy.getTelegram();
        email = studentToCopy.getEmail();
        response = studentToCopy.getResponse();
        attendance = studentToCopy.getAttendance();
    }

    /**
     * Sets the {@code Name} of the {@code Student} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.name = new StuName(name);
        return this;
    }

    /**
     * Sets the {@code Telegram} of the {@code Student} that we are building.
     */
    public StudentBuilder withTelegram(String telegram) {
        this.telegram = new Telegram(telegram);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Student} that we are building.
     */
    public StudentBuilder withEmail(String email) {
        this.email = new StuEmail(email);
        return this;
    }

    /**
     * Sets the {@code Response} of the {@code Student} that we are building.
     */
    public StudentBuilder withResponse(String response) {
        this.response = new Response(response);
        return this;
    }

    /**
     * Sets the {@code Attendance} of the {@code Student} that we are building.
     */
    public StudentBuilder withAttendance(String attendance) {
        this.attendance = new Attendance(attendance);
        return this;
    }

    public Student build() {
        return new Student(name, telegram, email, response, attendance);
    }

}

