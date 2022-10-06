package seedu.address.model.person;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Internship's working date in the address book.
 */
public class InternshipDate {

    private LocalDate internshipDate;

    public InternshipDate(String date) {
        internshipDate = LocalDate.parse(date);
    }

    public LocalDate getInternshipDate() {
        return internshipDate;
    }

    @Override
    public String toString() {
        return internshipDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}
