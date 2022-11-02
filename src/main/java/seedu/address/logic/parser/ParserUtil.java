package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleDescription;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.schedule.ClassType;
import seedu.address.model.module.schedule.Schedule;
import seedu.address.model.module.schedule.Venue;
import seedu.address.model.module.schedule.Weekdays;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.TelegramHandle;
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
     * Parses id.
     *
     * @param id
     * @return StudentID.
     * @throws ParseException If null.
     */
    public static StudentId parseID(String id) throws ParseException {
        requireNonNull(id);
        String trimmedID = id.trim();
        if (!StudentId.isValidStudentID(trimmedID)) {
            throw new ParseException(StudentId.MESSAGE_CONSTRAINTS);
        }
        return new StudentId(trimmedID);
    }

    /**
     * Parses telegram Handle.
     *
     * @param handle
     * @return handle.
     * @throws ParseException If null.
     */
    public static TelegramHandle parseTelegramHandle(String handle) throws ParseException {
        requireNonNull(handle);
        String trimmedHandle = handle.trim();
        if (!TelegramHandle.isValidTelegramHandle(trimmedHandle)) {
            throw new ParseException(TelegramHandle.MESSAGE_CONSTRAINTS);
        }
        return new TelegramHandle(trimmedHandle);
    }

    /**
     * Parses student info.
     *
     * @param info
     * @return String
     * @throws ParseException If null.
     */
    public static Set<ModuleCode> parseStudentInfo(Collection<String> info) throws ParseException {
        requireNonNull(info);
        final Set<ModuleCode> moduleSet = new HashSet<>();
        for (String moduleCode: info) {
            moduleSet.add(parseModuleCode(moduleCode));
        }
        return moduleSet;
    }

    /**
     * Parses teaching assistant info.
     *
     * @param info
     * @return String
     * @throws ParseException If null.
     */
    public static Set<ModuleCode> parseTeachingAssistantInfo(Collection<String> info) throws ParseException {
        requireNonNull(info);
        final Set<ModuleCode> moduleSet = new HashSet<>();
        for (String moduleCode: info) {
            moduleSet.add(parseModuleCode(moduleCode));
        }
        return moduleSet;
    }

    /**
     * Parses class groups
     *
     * @param classGroups
     * @return String
     * @throws ParseException If null.
     */
    public static Set<String> parseClassGroups(Collection<String> classGroups) throws ParseException {
        requireNonNull(classGroups);
        final Set<String> classGroupSet = new HashSet<>();
        for (String classGroup: classGroups) {
            classGroupSet.add(classGroup);
        }
        return classGroupSet;
    }

    /**
     * Parses module name.
     *
     * @param moduleName
     * @return ModuleName
     * @throws ParseException If null.
     */
    public static ModuleName parseModuleName(String moduleName) throws ParseException {
        requireNonNull(moduleName);
        String trimmedModuleName = moduleName.trim();
        if (!ModuleName.isValidName(trimmedModuleName)) {
            throw new ParseException(ModuleName.MESSAGE_CONSTRAINTS);
        }
        return new ModuleName(trimmedModuleName);
    }

    /**
     * Parses module code.
     *
     * @param moduleCode
     * @return ModuleCode
     * @throws ParseException If null.
     */
    public static ModuleCode parseModuleCode(String moduleCode) throws ParseException {
        requireNonNull(moduleCode);
        String trimmedModuleCode = moduleCode.trim();
        String moduleCodeInUpperCase = trimmedModuleCode.toUpperCase();
        if (!ModuleCode.isValidCode(moduleCodeInUpperCase)) {
            throw new ParseException(ModuleCode.MESSAGE_CONSTRAINTS);
        }
        return new ModuleCode(moduleCodeInUpperCase);
    }

    /**
     * Parses module description.
     *
     * @param moduleDescription
     * @return ModuleDescription
     * @throws ParseException If null.
     */
    public static ModuleDescription parseModuleDescription(String moduleDescription) throws ParseException {
        requireNonNull(moduleDescription);
        String trimmedModuleDescription = moduleDescription.trim();
        if (!ModuleDescription.isValidDescription(trimmedModuleDescription)) {
            throw new ParseException(ModuleDescription.MESSAGE_CONSTRAINTS);
        }
        return new ModuleDescription(trimmedModuleDescription);
    }

    /**
     * @param moduleCode user's input of module code
     * @throws ParseException if the format is incorrect
     */
    public static String parseModule(String moduleCode) throws ParseException {
        requireNonNull(moduleCode);
        moduleCode = moduleCode.toLowerCase();
        Pattern pattern = Pattern.compile("[a-z]{2,3}[0-9]{4}[a-z]?");
        Matcher matcher = pattern.matcher(moduleCode);
        if (!matcher.find()) {
            throw new ParseException(Module.MESSAGE_MODULE_CODE_CONSTRAINT);
        }
        return matcher.group();
    }

    /**
     * Parses {@code Collection<String> modules} into a {@code Set<String>}.
     */
    public static Set<String> parseModules(Collection<String> modules) throws ParseException {
        requireNonNull(modules);
        final Set<String> modulesSet = new HashSet<>();
        for (String moduleName : modules) {
            modulesSet.add(parseModule(moduleName));
        }
        return modulesSet;
    }

    /**
     * Parses weekday
     * @param weekday user's input of weekday
     * @return Weekdays
     * @throws ParseException if weekday does not exist
     */
    public static Weekdays parseWeekday(String weekday) throws ParseException {
        requireNonNull(weekday);
        switch (weekday.trim().toLowerCase()) {
        case "monday":
            return Weekdays.Monday;
        case "tuesday":
            return Weekdays.Tuesday;
        case "wednesday":
            return Weekdays.Wednesday;
        case "thursday":
            return Weekdays.Thursday;
        case "friday":
            return Weekdays.Friday;
        case "saturday":
            return Weekdays.Saturday;
        case "sunday":
            return Weekdays.Sunday;
        default:
            throw new ParseException(Schedule.MESSAGE_WEEKDAYS_CONSTRAINT);
        }
    }

    /**
     * Parses {@code Collection<String> weekdays} into a {@code Set<String>}.
     */
    public static Set<String> parseWeekdays(Collection<String> weekdays) throws ParseException {
        requireNonNull(weekdays);
        final Set<String> weekdaysSet = new HashSet<>();
        for (String weekdays1 : weekdays) {
            weekdaysSet.add(parseWeekday(weekdays1).name());
        }
        return weekdaysSet;
    }

    /**
     * Parses class start time
     * @param startTime user's input of class start time
     * @return class start time
     * @throws ParseException if time format is invalid
     */
    public static String parseClassStartTime(String startTime) throws ParseException {
        requireNonNull(startTime);
        Pattern pattern = Pattern.compile("[0-9]{2}:[0-9]{2}-[0-9]{2}:[0-9]{2}");
        Matcher matcher = pattern.matcher(startTime.trim());
        if (!matcher.find()) {
            throw new ParseException(Schedule.MESSAGE_CLASS_TIME_CONSTRAINT);
        }

        return matcher.group().split("-")[0];
    }

    /**
     * Parses class end time
     * @param endTime user's input of class end time
     * @return class end time
     * @throws ParseException if time format is invalid
     */
    public static String parseClassEndTime(String endTime) throws ParseException {
        requireNonNull(endTime);
        Pattern pattern = Pattern.compile("[0-9]{2}:[0-9]{2}-[0-9]{2}:[0-9]{2}");
        Matcher matcher = pattern.matcher(endTime.trim());
        if (!matcher.find()) {
            throw new ParseException(Schedule.MESSAGE_CLASS_TIME_CONSTRAINT);
        }
        return matcher.group().split("-")[1];
    }

    /**
     * Parses class type
     * @param classType user's input of class type
     * @return ClassType
     * @throws ParseException if class type doesn't exist
     */
    public static ClassType parseClassType(String classType) throws ParseException {
        switch (classType.trim()) {
        case "lec":
            return ClassType.Lecture;
        case "tut":
            return ClassType.Tutorial;
        case "lab":
            return ClassType.Lab;
        case "rec":
            return ClassType.Reflection;
        default:
            throw new ParseException(Schedule.MESSAGE_CLASS_TYPE_CONSTRAINT);
        }
    }

    /**
     * Parses class group
     */
    public static String parseClassGroup(String classGroup) {
        return classGroup.trim().toUpperCase();
    }

    /**
     * Parses class venue.
     * @param venue venue name
     * @return venue
     */
    public static Venue parseVenue(String venue) {
        requireNonNull(venue);
        return new Venue(venue.trim());
    }

    /**
     * Checks if the time is valid
     */
    public static boolean isValidTimeSlot(String startTime, String endTime) throws ParseException {
        try {
            int startHour = Integer.parseInt(startTime.split(":")[0]);
            int startMin = Integer.parseInt(startTime.split(":")[1]);
            int endHour = Integer.parseInt(endTime.split(":")[0]);
            int endMin = Integer.parseInt(endTime.split(":")[1]);

            if (startHour >= 24 || endHour >= 24 || startHour < 0 || endHour < 0) {
                throw new ParseException(Schedule.MESSAGE_TIMESLOT_CONSTRAINT);
            }
            if (startMin != 0 && startMin != 30) {
                throw new ParseException(Schedule.MESSAGE_TIMESLOT_CONSTRAINT);
            }
            if (endMin != 0 && endMin != 30) {
                throw new ParseException(Schedule.MESSAGE_TIMESLOT_CONSTRAINT);
            }
            if (startHour < 7) {
                throw new ParseException(Schedule.MESSAGE_CLASS_STARTINGTIME_CONSTRAINT);
            }
            if (endHour >= 22 && endMin > 0) {
                throw new ParseException(Schedule.MESSAGE_CLASS_ENDINGTIME_CONSTRAINT);
            }
            if ((startHour > endHour) || ((startHour == endHour) && (startMin >= endMin))) {
                throw new ParseException(Schedule.MESSAGE_CLASS_STARTING_ENDINGT_CONSTRAINT);
            }

            double start = startMin == 0 ? startHour : startHour + 0.5;
            double end = endMin == 0 ? endHour : endHour + 0.5;
            if (end - start < 1 || end - start > 3) {
                throw new ParseException(Schedule.MESSAGE_CLASS_TIME_CONSTRAINT);
            }
            return true;
        } catch (Exception e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, e.getMessage()));
        }

    }
}
