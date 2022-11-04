package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.MeetingDate;
import seedu.address.model.meeting.MeetingLocation;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Income;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.portfolio.Note;
import seedu.address.model.portfolio.Plan;
import seedu.address.model.portfolio.Risk;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_REPEATED_INDEX = "Index cannot be repeated";
    public static final String MESSAGE_INVALID_RANGE_INDEX =
        "Range index should be in the form of startIndex - endIndex, where startIndex < endIndex";

    public static final String MESSAGE_INVALID_PATH = "Path is invalid.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
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
     * Parses a List of {@code oneBasedIndex} into a List of {@code Index} and returns it.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if any specified index is invalid (not non-zero unsigned integer).
     */
    public static List<Index> parseMultipleIndex(String multipleIndex) throws ParseException {
        List<Index> indexList = new ArrayList<>();
        List<String> indexStringList = Arrays.asList(multipleIndex.split(","));

        for (int i = 0; i < indexStringList.size(); i++) {
            String trimmedIndex = indexStringList.get(i).trim();
            if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
                throw new ParseException(MESSAGE_INVALID_INDEX);
            }
            indexList.add(Index.fromOneBased(Integer.parseInt(trimmedIndex)));
        }
        Collections.sort(indexList);
        if (hasRepeatedIndex(indexList)) {
            throw new ParseException(MESSAGE_INVALID_REPEATED_INDEX);
        }

        return indexList;
    }

    /**
     * Checks if list contains repeated indexes in a descending list.
     *
     * @param index of client to be deleted.
     * @return true if there is not duplicated index, false otherwise.
     */
    public static boolean hasRepeatedIndex(List<Index> index) {
        for (int i = 1; i < index.size(); i++) {
            if (index.get(i - 1).equals(index.get(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Parses a List of {@code oneBasedIndex} into a List of {@code Index} and returns it.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if any specified index is invalid (not non-zero unsigned integer).
     */
    public static List<Index> parseRangeIndex(String multipleIndex) throws ParseException {
        List<Index> indexList = new ArrayList<>();
        List<String> indexStringList = Arrays.asList(multipleIndex.split("-"));

        int startIndex = Integer.parseInt(indexStringList.get(0).trim());
        int endIndex = Integer.parseInt(indexStringList.get(1).trim());

        if (indexStringList.size() > 2 || startIndex > endIndex) {
            throw new ParseException(MESSAGE_INVALID_RANGE_INDEX);
        }

        for (int i = 0; i < indexStringList.size(); i++) {
            String trimmedIndex = indexStringList.get(i).trim();
            if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
                throw new ParseException(MESSAGE_INVALID_INDEX);
            }
        }

        for (int i = startIndex; i <= endIndex; i++) {
            indexList.add(Index.fromOneBased(i));
        }
        Collections.sort(indexList);
        return indexList;
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
     * Parses a {@code String income} into a {@code Income}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code income} is invalid.
     */
    public static Income parseIncome(String income) throws ParseException {
        requireNonNull(income);
        String trimmedIncome = income.trim();
        if (!Income.isValidIncome(trimmedIncome)) {
            throw new ParseException(Income.MESSAGE_CONSTRAINTS);
        }
        return new Income(trimmedIncome);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
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
     * Parses a {@code String meetingDate} into an {@code MeetingDate}.
     * Leading and trailing whitespaces will be trimmed.
     * MeetingDate can be null.
     *
     * @throws ParseException if the given {@code meetingDate} is invalid.
     */
    public static MeetingDate parseMeetingDate(String meetingDate) throws ParseException {
        if (meetingDate != null) {
            String trimmedMeetingDate = meetingDate.trim();
            if (!MeetingDate.isValidMeetingDate(meetingDate)) {
                throw new ParseException(MeetingDate.MESSAGE_CONSTRAINTS);
            }
            return new MeetingDate(trimmedMeetingDate);
        } else {
            return new MeetingDate(null);
        }
    }

    /**
     * Parses a {@code String meetingLocation} into an {@code MeetingLocation}.
     * Leading and trailing whitespaces will be trimmed.
     * MeetingLocation can be null.
     *
     * @throws ParseException if the given {@code meetingLocation} is invalid.
     */
    public static MeetingLocation parseMeetingLocation(String meetingLocation) throws ParseException {
        if (meetingLocation != null) {
            String trimmedMeetingLocation = meetingLocation.trim();
            if (!MeetingLocation.isValidMeetingLocation(trimmedMeetingLocation)) {
                throw new ParseException(MeetingLocation.MESSAGE_CONSTRAINTS);
            }
            return new MeetingLocation(trimmedMeetingLocation);
        } else {
            return new MeetingLocation(null);
        }
    }

    /**
     * Parses a {@code String risk} into an {@code Risk}.
     * Leading and trailing whitespaces will be trimmed.
     * Risk can be null.
     *
     * @throws ParseException if the given {@code risk} is invalid.
     */
    public static Risk parseRisk(String risk) throws ParseException {
        if (risk != null) {
            String trimmedRisk = risk.trim();
            if (!Risk.isValidRisk(risk)) {
                throw new ParseException(Risk.MESSAGE_CONSTRAINTS);
            }
            return new Risk(trimmedRisk);
        } else {
            return new Risk(null);
        }
    }

    /**
     * Parses a {@code String plan} into an {@code Plan}.
     * Plan can be null.
     *
     * @throws ParseException if the given {@code plan} is invalid.
     */
    public static Plan parsePlan(String plan) throws ParseException {
        requireNonNull(plan);
        if (!Plan.isValidPlan(plan)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Plan(plan);
    }

    /**
     * Parses {@code Collection<String> Plans} into a {@code Set<Plan>}.
     */
    public static Set<Plan> parsePlans(Collection<String> plans) throws ParseException {
        requireNonNull(plans);
        final Set<Plan> planSet = new HashSet<>();
        for (String planName : plans) {
            planSet.add(parsePlan(planName));
        }
        return planSet;
    }

    /**
     * Parses a {@code String note} into an {@code Note}.
     * Leading and trailing whitespaces will be trimmed.
     * Note can be null.
     *
     * @throws ParseException if the given {@code note} is invalid.
     */
    public static Note parseNote(String note) throws ParseException {
        requireNonNull(note);
        String trimmedNote = note.trim();
        if (!Note.isValidNote(trimmedNote)) {
            throw new ParseException(Note.MESSAGE_CONSTRAINTS);
        }
        return new Note(trimmedNote);
    }

    /**
     * Parses {@code Collection<String> Plans} into a {@code Set<Plan>}.
     */
    public static Set<Note> parseNotes(Collection<String> notes) throws ParseException {
        requireNonNull(notes);
        final Set<Note> noteSet = new HashSet<>();
        for (String noteName : notes) {
            noteSet.add(parseNote(noteName));
        }
        return noteSet;
    }


    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses {@code filePath} into an {@code Path} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified path is invalid (not JSON or CSV, or not readable)
     */
    public static Path parseImportPath(String filePath) throws ParseException {
        String trimmedPath = filePath.trim();
        File file = new File(trimmedPath);
        if (!(file.getName().toLowerCase().endsWith(".json") || file.getName().toLowerCase().endsWith(".csv"))
            || !Files.isReadable(file.toPath())) {
            throw new ParseException(MESSAGE_INVALID_PATH);
        }
        return file.toPath();
    }

    /**
     * Parses {@code filePath} into an {@code Path} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified path is invalid (not CSV)
     */
    public static Path parseExportPath(String filePath) throws ParseException {
        String trimmedPath = filePath.trim();
        File file = new File(trimmedPath);
        if (!file.getName().toLowerCase().endsWith(".csv")) {
            throw new ParseException(MESSAGE_INVALID_PATH);
        }
        return file.toPath();
    }

    /**
     * Parses a {@code String sortParam} into a {@code sortParam}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code sortParam} is invalid.
     */
    public static String parseSort(String sortParam) throws ParseException {
        requireNonNull(sortParam);
        String trimmedSortParam = sortParam.trim();
        return trimmedSortParam;
    }
}
