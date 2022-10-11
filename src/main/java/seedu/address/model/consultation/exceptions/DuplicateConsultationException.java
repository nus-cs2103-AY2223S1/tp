package seedu.address.model.consultation.exceptions;

public class DuplicateConsultationException extends RuntimeException{
    public DuplicateConsultationException() {
        super("Operation would result in duplicate consultations");
    }
}
