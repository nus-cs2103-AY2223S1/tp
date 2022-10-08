package seedu.address.model.tuitionclass.exceptions;

public class InvalidDayException extends RuntimeException {
    public InvalidDayException() {
        super("Please give the day spelt out in full.\n" +
                "e.g. Monday");
    }
}
