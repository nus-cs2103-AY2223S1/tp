package seedu.application.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.application.commons.core.index.Index;
import seedu.application.commons.util.StringUtil;
import seedu.application.logic.parser.exceptions.ParseException;
import seedu.application.model.application.Company;
import seedu.application.model.application.Contact;
import seedu.application.model.application.Date;
import seedu.application.model.application.Email;
import seedu.application.model.application.Position;
import seedu.application.model.application.interview.InterviewDate;
import seedu.application.model.application.interview.InterviewTime;
import seedu.application.model.application.interview.Location;
import seedu.application.model.application.interview.Round;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String company} into a {@code Company}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code company} is invalid.
     */
    public static Company parseCompany(String company) throws ParseException {
        requireNonNull(company);
        String trimmedCompany = company.trim();
        if (!Company.isValidCompany(trimmedCompany)) {
            throw new ParseException(Company.MESSAGE_CONSTRAINTS);
        }
        return new Company(trimmedCompany);
    }

    /**
     * Parses a {@code String contact} into a {@code Contact}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code contact} is invalid.
     */
    public static Contact parseContact(String contact) throws ParseException {
        requireNonNull(contact);
        String trimmedContact = contact.trim();
        if (!Contact.isValidContact(trimmedContact)) {
            throw new ParseException(Contact.MESSAGE_CONSTRAINTS);
        }
        return new Contact(trimmedContact);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String position} into a {@code Position}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code position} is invalid.
     */
    public static Position parsePosition(String position) throws ParseException {
        requireNonNull(position);
        String trimmedPosition = position.trim();
        if (!Position.isValidPosition(trimmedPosition)) {
            throw new ParseException(Position.MESSAGE_CONSTRAINTS);
        }
        return new Position(trimmedPosition);
    }

    /**
     * Parses a {@code String date} into a {@code Date}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static Date parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        if (!Date.isValidDate(trimmedDate)) {
            throw new ParseException(Date.MESSAGE_CONSTRAINTS);
        }
        return new Date(trimmedDate);
    }

    /**
     * Parses a {@code String round} into a {@code Round}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code round} is invalid.
     */
    public static Round parseRound(String round) throws ParseException {
        requireNonNull(round);
        String trimmedRound = round.trim();
        if (!Round.isValidRound(trimmedRound)) {
            throw new ParseException(Round.MESSAGE_CONSTRAINTS);
        }
        return new Round(trimmedRound);
    }

    /**
     * Parses a {@code String interviewDate} into a {@code InterviewDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code interviewDate} is invalid.
     */
    public static InterviewDate parseInterviewDate(String interviewDate) throws ParseException {
        requireNonNull(interviewDate);
        String trimmedInterviewDate = interviewDate.trim();
        if (!InterviewDate.isValidDate(trimmedInterviewDate)) {
            throw new ParseException(InterviewDate.MESSAGE_CONSTRAINTS);
        }
        return new InterviewDate(trimmedInterviewDate);
    }

    /**
     * Parses a {@code String interviewTime} into a {@code InterviewTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code interviewTime} is invalid.
     */
    public static InterviewTime parseInterviewTime(String interviewTime) throws ParseException {
        requireNonNull(interviewTime);
        String trimmedInterviewTime = interviewTime.trim();
        if (!InterviewTime.isValidTime(trimmedInterviewTime)) {
            throw new ParseException(InterviewTime.MESSAGE_CONSTRAINTS);
        }
        return new InterviewTime(trimmedInterviewTime);
    }

    /**
     * Parses a {@code String location} into a {@code Location}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code location} is invalid.
     */
    public static Location parseLocation(String location) throws ParseException {
        requireNonNull(location);
        String trimmedLocation = location.trim();
        if (!Location.isValidLocation(trimmedLocation)) {
            throw new ParseException(Location.MESSAGE_CONSTRAINTS);
        }
        return new Location(trimmedLocation);
    }
}
