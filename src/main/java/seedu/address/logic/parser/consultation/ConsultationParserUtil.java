package seedu.address.logic.parser.consultation;

import java.util.Objects;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.consultation.ConsultationDescription;
import seedu.address.model.consultation.ConsultationName;


/**
 * Contains utility methods used for parsing Consultation and related subclasses.
 */
public class ConsultationParserUtil {
    /**
     * Parses a {@code String name} into a {@code ConsultationName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static ConsultationName parseConsultationName(String name) throws ParseException {
        Objects.requireNonNull(name);
        String trimmedName = name.trim();
        if (!ConsultationName.isValidName(trimmedName)) {
            throw new ParseException(ConsultationName.MESSAGE_CONSTRAINTS);
        }
        return new ConsultationName(trimmedName);
    }

    /**
     * Parses a {@code String name} into a {@code ConsultationDescription}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static ConsultationDescription parseConsultationDescription(String name) {
        Objects.requireNonNull(name);
        String trimmedName = name.trim();
        return new ConsultationDescription(trimmedName);
    }
}
