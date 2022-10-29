package seedu.trackascholar.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.trackascholar.commons.core.index.Index;
import seedu.trackascholar.commons.util.StringUtil;
import seedu.trackascholar.logic.parser.exceptions.ParseException;
import seedu.trackascholar.model.applicant.ApplicationStatus;
import seedu.trackascholar.model.applicant.Email;
import seedu.trackascholar.model.applicant.Name;
import seedu.trackascholar.model.applicant.Phone;
import seedu.trackascholar.model.applicant.Scholarship;
import seedu.trackascholar.model.major.Major;

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
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String scholarship} into an {@code Scholarship}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code scholarship} is invalid.
     */
    public static Scholarship parseScholarship(String scholarship) throws ParseException {
        requireNonNull(scholarship);
        String trimmedScholarship = scholarship.trim();
        if (!Scholarship.isValidScholarship(trimmedScholarship)) {
            throw new ParseException(Scholarship.MESSAGE_CONSTRAINTS);
        }
        return new Scholarship(trimmedScholarship);
    }

    /**
     * Parses a {@code String applicationStatus} into an {@code ApplicationStatus}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code applicationStatus} is invalid.
     */
    public static ApplicationStatus parseApplicationStatus(String applicationStatus) throws ParseException {
        requireNonNull(applicationStatus);
        String trimmedApplicationStatus = applicationStatus.trim();
        if (!ApplicationStatus.isValidApplicationStatus(trimmedApplicationStatus)) {
            throw new ParseException(ApplicationStatus.MESSAGE_CONSTRAINTS);
        }
        return new ApplicationStatus(trimmedApplicationStatus);
    }

    /**
     * Parses a {@code String applicationStatus} into a completed {@code ApplicationStatus}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code applicationStatus} is invalid or pending.
     */
    public static ApplicationStatus parseCompletedApplicationStatus(String applicationStatus) throws ParseException {
        requireNonNull(applicationStatus);
        String trimmedApplicationStatus = applicationStatus.trim();
        if (!ApplicationStatus.isCompletedApplicationStatus(trimmedApplicationStatus)) {
            throw new ParseException(ApplicationStatus.MESSAGE_STATUS_REJECTION);
        }
        return new ApplicationStatus(trimmedApplicationStatus);
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
     * Parses a {@code String major} into a {@code Major}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code major} is invalid.
     */
    public static Major parseMajor(String major) throws ParseException {
        requireNonNull(major);
        String trimmedMajor = major.trim();
        if (!Major.isValidMajor(trimmedMajor)) {
            throw new ParseException(Major.MESSAGE_CONSTRAINTS);
        }
        return new Major(trimmedMajor);
    }

    /**
     * Parses {@code Collection<String> majors} into a {@code Set<Major>}.
     */
    public static Set<Major> parseMajors(Collection<String> majors) throws ParseException {
        requireNonNull(majors);
        final Set<Major> majorSet = new HashSet<>();
        for (String major : majors) {
            majorSet.add(parseMajor(major));
        }
        if (majorSet.size() > Major.MAXIMUM_NUMBER_OF_MAJORS || hasDuplicate(majorSet)) {
            throw new ParseException(Major.MESSAGE_CONSTRAINTS);
        }
        return majorSet;
    }

    /**
     * Returns true if {@code Set<Major> majorSet} has a duplicate major.
     */
    private static boolean hasDuplicate(Set<Major> majorSet) {
        return majorSet.stream()
                .anyMatch(currentMajor -> majorSet.stream()
                        .anyMatch(otherMajor -> otherMajor != currentMajor && otherMajor.isSameMajor(currentMajor)));
    }
}
