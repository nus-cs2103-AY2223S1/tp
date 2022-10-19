package seedu.address.logic.parser.consultation;

import java.util.Objects;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.consultation.ConsultationDescription;
import seedu.address.model.consultation.ConsultationModule;
import seedu.address.model.consultation.ConsultationName;
import seedu.address.model.consultation.ConsultationTimeslot;
import seedu.address.model.consultation.ConsultationVenue;


/**
 * Contains utility methods used for parsing Consultation and related subclasses.
 */
public class ConsultationParserUtil {
    /**
     * Parses a {@code String name} into a {@code TutorialName}.
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
     * Parses a {@code String moduleName} into a {@code ConsultationModule}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code moduleName} is invalid.
     */
    public static ConsultationModule parseConsultationModule(String moduleName) throws ParseException {
        Objects.requireNonNull(moduleName);
        String trimmedName = moduleName.trim();
        if (!ConsultationModule.isValidModule(trimmedName)) {
            throw new ParseException(ConsultationModule.MESSAGE_CONSTRAINTS);
        }
        return new ConsultationModule(trimmedName);
    }

    /**
     * Parses a {@code String venue} into a {@code ConsultationVenue}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code venue} is invalid.
     */
    public static ConsultationVenue parseConsultationVenue(String venue) throws ParseException {
        Objects.requireNonNull(venue);
        String trimmedName = venue.trim();
        if (!ConsultationVenue.isValidVenue(trimmedName)) {
            throw new ParseException(ConsultationVenue.MESSAGE_CONSTRAINTS);
        }
        return new ConsultationVenue(trimmedName);
    }

    /**
     * Parses a {@code String timeslot} into a {@code ConsultationTimeslot}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code timeslot} is invalid.
     */
    public static ConsultationTimeslot parseConsultationTimeslot(String timeslot) throws ParseException {
        Objects.requireNonNull(timeslot);
        String trimmedName = timeslot.trim();
        if (!ConsultationTimeslot.isValidTimeslot(trimmedName)) {
            throw new ParseException(ConsultationTimeslot.MESSAGE_CONSTRAINTS);
        }
        if (!ConsultationTimeslot.isValidDuration(trimmedName)) {
            throw new ParseException(ConsultationTimeslot.MESSAGE_INVALID_DURATION);
        }
        return new ConsultationTimeslot(trimmedName);
    }

    /**
     * Parses a {@code String name} into a {@code TutorialName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static ConsultationDescription parseConsultationDescription(String name) throws ParseException {
        Objects.requireNonNull(name);
        String trimmedName = name.trim();
        if (!ConsultationDescription.isValidDescription(trimmedName)) {
            throw new ParseException(ConsultationName.MESSAGE_CONSTRAINTS);
        }
        return new ConsultationDescription(trimmedName);
    }
}
