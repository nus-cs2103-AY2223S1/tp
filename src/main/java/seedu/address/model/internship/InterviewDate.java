package seedu.address.model.person;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Internship's interview date in the address book.
 */
public class InterviewDate {

    private LocalDate interviewDate;

    public InterviewDate(String date) {
        interviewDate = LocalDate.parse(date);
    }

    public LocalDate getInterviewDate() {
        return interviewDate;
    }

    @Override
    public String toString() {
        return interviewDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}
