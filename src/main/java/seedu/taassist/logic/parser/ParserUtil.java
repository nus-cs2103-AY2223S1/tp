package seedu.taassist.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.commons.util.CollectionUtil.requireAllNonNull;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.taassist.commons.core.index.Index;
import seedu.taassist.commons.util.StringUtil;
import seedu.taassist.logic.parser.exceptions.ParseException;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.session.Date;
import seedu.taassist.model.session.Session;
import seedu.taassist.model.student.Address;
import seedu.taassist.model.student.Email;
import seedu.taassist.model.student.Name;
import seedu.taassist.model.student.Phone;
import seedu.taassist.model.student.SessionData;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index should be a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @param oneBasedIndex String representation of a one-based index.
     * @return Matching Index object (one-based).
     * @throws ParseException If the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses {@code oneBasedIndices} into an {@code List<Index>} and returns it. Leading and trailing whitespaces will
     * be trimmed.
     *
     * @param oneBasedIndices String representations of one-based indices delimited by whitespace.
     * @return Matching Index objects (one-based).
     * @throws ParseException If any of the specified indices is invalid (not non-zero unsigned integer).
     */
    public static List<Index> parseIndices(String oneBasedIndices) throws ParseException {
        List<Index> indices = new ArrayList<>();
        String[] stringIndices = oneBasedIndices.trim().split("\\s+");
        for (String stringIndex : stringIndices) {
            if (!StringUtil.isNonZeroUnsignedInteger(stringIndex)) {
                throw new ParseException(MESSAGE_INVALID_INDEX);
            }
            indices.add(Index.fromOneBased(Integer.parseInt(stringIndex)));
        }
        return indices;
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param name String representation of a Name object.
     * @return Matching Name object.
     * @throws ParseException If the given {@code name} is invalid.
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
     * @param phone String representation of a Phone object.
     * @return Matching Phone object.
     * @throws ParseException If the given {@code phone} is invalid.
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
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param address String representation of an Address object.
     * @return Matching Address object.
     * @throws ParseException If the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param email String representation of an Email object.
     * @return Matching Email object.
     * @throws ParseException If the given {@code email} is invalid.
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
     * Parses a {@code String moduleClass} into a {@code ModuleClass}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param moduleClass String representation of a ModuleClass object.
     * @return Matching ModuleClass object.
     * @throws ParseException If the given {@code moduleClass} is invalid.
     */
    public static ModuleClass parseModuleClass(String moduleClass) throws ParseException {
        requireNonNull(moduleClass);
        String trimmedModuleClass = moduleClass.trim();
        if (!ModuleClass.isValidModuleClassName(trimmedModuleClass)) {
            throw new ParseException(ModuleClass.MESSAGE_CONSTRAINTS);
        }
        return new ModuleClass(trimmedModuleClass);
    }

    /**
     * Parses a Collection of String representations of ModuleClass objects into a set of matching
     * {@code moduleClasses}.
     *
     * @param moduleClasses String representations of ModuleClass objects.
     * @return Matching ModuleClass objects.
     * @throws ParseException If any of the given {@code moduleClass} in {@code moduleClasses} is invalid.
     */
    public static Set<ModuleClass> parseModuleClasses(Collection<String> moduleClasses) throws ParseException {
        requireNonNull(moduleClasses);
        final Set<ModuleClass> moduleClassSet = new HashSet<>();
        for (String moduleClassName : moduleClasses) {
            moduleClassSet.add(parseModuleClass(moduleClassName));
        }
        return moduleClassSet;
    }

    /**
     * Parses a {@code String date} into a {@code LocalDate}.
     * {@code date} is parsed using {@code DateTimeFormatter.ISO_LOCAL_DATE}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param date String representation of a Date object.
     * @return Matching Date object.
     * @throws ParseException If the given {@code date} is invalid.
     */
    public static Date parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        try {
            return new Date(LocalDate.parse(trimmedDate));
        } catch (DateTimeException de) {
            throw new ParseException(Date.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses the string representation of a grade value into a double.
     *
     * @param numberString String representation of a number.
     * @return Matching number.
     * @throws ParseException If the given {@code numberString} is not a valid number.
     */
    public static Double parseGrade(String numberString) throws ParseException {
        requireNonNull(numberString);
        Double grade;
        try {
            grade = Double.parseDouble(numberString);
        } catch (NumberFormatException nfe) {
            throw new ParseException(SessionData.MESSAGE_CONSTRAINTS);
        }
        if (!SessionData.isValidGrade(grade)) {
            throw new ParseException(SessionData.MESSAGE_CONSTRAINTS);
        }
        return grade;
    }

    /**
     * Parses a {@code String session} into a {@code Session}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param session String representation of a Session object.
     * @return Matching Session object.
     * @throws ParseException If the given {@code session} is invalid.
     */
    public static Session parseSession(String session) throws ParseException {
        requireNonNull(session);
        String trimmedSession = session.trim();
        if (!Session.isValidSessionName(trimmedSession)) {
            throw new ParseException(Session.MESSAGE_CONSTRAINTS);
        }
        return new Session(session);
    }

    /**
     * Parses a {@code String session} along with a {@code Date date} into a {@code Session}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param session String representation of a Session object.
     * @param date Date for created Session object.
     * @return Matching Session object.
     * @throws ParseException If the given {@code session} is invalid.
     */
    public static Session parseSession(String session, Date date) throws ParseException {
        requireAllNonNull(session, date);
        String trimmedSession = session.trim();
        if (!Session.isValidSessionName(trimmedSession)) {
            throw new ParseException(Session.MESSAGE_CONSTRAINTS);
        }
        return new Session(session, date);
    }

    /**
     * Parses a Collection of String representations of Session objects into a set of matching
     * {@code sessions} with the default date.
     *
     * @param sessions String representations of Session objects.
     * @return Matching Session objects.
     * @throws ParseException If any of the given {@code session} in {@code sessions} is invalid.
     */
    public static Set<Session> parseSessions(Collection<String> sessions) throws ParseException {
        requireNonNull(sessions);
        final Set<Session> sessionSet = new HashSet<>();
        for (String sessionName : sessions) {
            sessionSet.add(parseSession(sessionName));
        }
        return sessionSet;
    }

    /**
     * Parses a Collection of String representations of Session objects into a set of matching
     * {@code sessions} with the given {@code date}.
     *
     * @param sessions String representations of Session objects.
     * @param date Date of each of the sessions.
     * @return Matching Session objects.
     * @throws ParseException If any of the given {@code session} in {@code sessions} is invalid.
     */
    public static Set<Session> parseSessions(Collection<String> sessions, Date date) throws ParseException {
        requireNonNull(sessions);
        final Set<Session> sessionSet = new HashSet<>();
        for (String sessionName : sessions) {
            sessionSet.add(parseSession(sessionName, date));
        }
        return sessionSet;
    }
}
