package seedu.address.model.person;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InterviewDate {

    public LocalDate interviewDate;

    public InterviewDate(String date) {
        interviewDate = LocalDate.parse(date);
    }

    @Override
    public String toString() {
        return interviewDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}
