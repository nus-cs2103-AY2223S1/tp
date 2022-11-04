package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_LESSON_TIME;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.LessonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.CurrentModule;
import seedu.address.model.module.Lab;
import seedu.address.model.module.Lecture;
import seedu.address.model.module.Lesson;
import seedu.address.model.module.Module;
import seedu.address.model.module.PlannedModule;
import seedu.address.model.module.PreviousModule;
import seedu.address.model.module.Recitation;
import seedu.address.model.module.Tutorial;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Github;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

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
     * Parses a {@code String github} into a {@code Github}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code github} is invalid.
     */
    public static Github parseGithub(String github) throws ParseException {
        requireNonNull(github);
        String trimmedGithub = github.trim();
        if (!Github.isValidUsername(trimmedGithub)) {
            throw new ParseException(Github.MESSAGE_CONSTRAINTS);
        }
        return new Github(trimmedGithub);
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
     * Parses a {@code String mod} into a {@code Module}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code mod} is invalid.
     */
    public static Module parseModule(String mod) throws ParseException {
        requireNonNull(mod);
        String trimmedMod = mod.trim();
        if (!Module.isValidModuleName(trimmedMod)) {
            throw new ParseException(Module.MESSAGE_CONSTRAINTS);
        }
        return new Module(trimmedMod);
    }

    /**
     * Parses {@code Collection<String> mods} into a {@code Set<CurrentModule>}.
     */
    public static Set<Module> parseModules(Collection<String> mods) throws ParseException {
        requireNonNull(mods);
        final Set<Module> modSet = new HashSet<>();

        for (String modCode : mods) {
            modSet.add(parseModule(modCode));
        }
        return modSet;
    }

    /**
     * Parses a {@code String mod} into a {@code CurrentModule}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code mod} is invalid.
     */
    public static CurrentModule parseCurrentModule(String mod) throws ParseException {
        requireNonNull(mod);
        String trimmedMod = mod.trim();
        if (!Module.isValidModuleName(trimmedMod)) {
            throw new ParseException(Module.MESSAGE_CONSTRAINTS);
        }
        return new CurrentModule(trimmedMod);
    }

    /**
     * Parses {@code Collection<String> mods} into a {@code Set<CurrentModule>}.
     */
    public static Set<CurrentModule> parseCurrentModules(Collection<String> mods) throws ParseException {
        requireNonNull(mods);
        final Set<CurrentModule> modSet = new HashSet<>();

        for (String modCode : mods) {
            modSet.add(parseCurrentModule(modCode));
        }
        return modSet;
    }

    /**
     * Parses a {@code String mod} into a {@code PreviousModule}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code mod} is invalid.
     */
    public static PreviousModule parsePreviousModule(String mod) throws ParseException {
        requireNonNull(mod);
        String trimmedMod = mod.trim();
        if (!Module.isValidModuleName(trimmedMod)) {
            throw new ParseException(Module.MESSAGE_CONSTRAINTS);
        }
        return new PreviousModule(trimmedMod);
    }

    /**
     * Parses {@code Collection<String> mods} into a {@code Set<PreviousModule>}.
     */
    public static Set<PreviousModule> parsePreviousModules(Collection<String> mods) throws ParseException {
        requireNonNull(mods);
        final Set<PreviousModule> modSet = new HashSet<>();
        for (String modCode : mods) {
            modSet.add(parsePreviousModule(modCode));
        }
        return modSet;
    }

    /**
     * Parses a {@code String mod} into a {@code PlannedModule}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code mod} is invalid.
     */
    public static PlannedModule parsePlannedModule(String mod) throws ParseException {
        requireNonNull(mod);
        String trimmedMod = mod.trim();
        if (!Module.isValidModuleName(trimmedMod)) {
            throw new ParseException(Module.MESSAGE_CONSTRAINTS);
        }
        return new PlannedModule(trimmedMod);
    }

    /**
     * Parses {@code Collection<String> mods} into a {@code Set<PlannedModule>}.
     */
    public static Set<PlannedModule> parsePlannedModules(Collection<String> mods) throws ParseException {
        requireNonNull(mods);
        final Set<PlannedModule> modSet = new HashSet<>();
        for (String modCode : mods) {
            modSet.add(parsePlannedModule(modCode));
        }
        return modSet;
    }

    /**
     * Parses a {@code String time} into a {@code LocalTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code time} is invalid.
     */
    public static LocalTime parseTime(String time) throws ParseException {
        requireNonNull(time);
        String timeTrim = time.trim();
        try {
            return LocalTime.parse(timeTrim);
        } catch (DateTimeParseException e) {
            throw new ParseException(LessonCommand.MESSAGE_INVALID_TIME);
        }
    }

    /**
     * Parses a {@code String day} into a {@code int}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code day} is invalid.
     */
    public static int parseDay(String day) throws ParseException {
        requireNonNull(day);
        String dayTrim = day.trim();
        try {
            int dayNumber = Integer.parseInt(dayTrim);

            if (dayNumber < 1 || dayNumber > 7) {
                throw new ParseException(LessonCommand.MESSAGE_INVALID_DAY);
            }

            return dayNumber;

        } catch (NumberFormatException e) {
            throw new ParseException(LessonCommand.MESSAGE_INVALID_DAY);
        }
    }

    /**
     * Parses a {@code String day} into a {@code int}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code day} is invalid.
     */
    public static Lesson parseLesson(String type, String module, String day,
                                     String startTime, String endTime) throws ParseException {
        requireNonNull(type);
        requireNonNull(module);
        String typeTrim = type.trim();
        int dayNumber = parseDay(day);
        LocalTime start = parseTime(startTime);
        LocalTime end = parseTime(endTime);

        String mod = module.trim().toUpperCase();
        if (!Lesson.isValidLesson(mod)) {
            throw new ParseException(Lesson.MESSAGE_CONSTRAINTS);
        }

        int compareTime = start.compareTo(end);
        if (compareTime >= 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_LESSON_TIME, startTime, endTime));
        }

        switch(typeTrim) {
        case "tut":
            return new Tutorial(module, dayNumber, start, end);
        case "lec":
            return new Lecture(module, dayNumber, start, end);
        case "rec":
            return new Recitation(module, dayNumber, start, end);
        case "lab":
            return new Lab(module, dayNumber, start, end);
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, LessonCommand.MESSAGE_USAGE));
        }
    }
}
