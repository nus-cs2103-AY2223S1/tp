package seedu.address.model.person;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InternshipDate {

    public LocalDate internshipDate;

    public InternshipDate(String date) {
        internshipDate = LocalDate.parse(date);
    }

    @Override
    public String toString() {
        return internshipDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}
