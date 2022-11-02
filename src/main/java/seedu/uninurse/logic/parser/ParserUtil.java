package seedu.uninurse.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIXES_OPTION_ALL;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIXES_PATIENT_ALL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.commons.util.StringUtil;
import seedu.uninurse.logic.commands.EditMedicationCommand.EditMedicationDescriptor;
import seedu.uninurse.logic.commands.EditTaskCommand.EditTaskDescriptor;
import seedu.uninurse.logic.parser.exceptions.ParseException;
import seedu.uninurse.model.condition.Condition;
import seedu.uninurse.model.condition.ConditionList;
import seedu.uninurse.model.medication.Medication;
import seedu.uninurse.model.medication.MedicationList;
import seedu.uninurse.model.person.Address;
import seedu.uninurse.model.person.Email;
import seedu.uninurse.model.person.Name;
import seedu.uninurse.model.person.Phone;
import seedu.uninurse.model.remark.Remark;
import seedu.uninurse.model.remark.RemarkList;
import seedu.uninurse.model.tag.Tag;
import seedu.uninurse.model.tag.TagList;
import seedu.uninurse.model.task.DateTime;
import seedu.uninurse.model.task.RecurringTask;
import seedu.uninurse.model.task.Task;
import seedu.uninurse.model.task.TaskList;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    public static final String MESSAGE_INVALID_OPTIONS = "Command options is invalid";

    public static final String MESSAGE_INVALID_PARAMETERS = "Command parameters is invalid";

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
     * Parses two one based index and returns it. Leading and trailing whitespaces will be trimmed.
     *
     * @param oneBasedIndex to be parsed.
     * @return a list of 2 oneBasedIndex.
     * @throws ParseException if indices are invalid.
     */
    public static List<Index> parseTwoIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        String[] indexes = trimmedIndex.split(" ");

        if (indexes.length != 2
                || !StringUtil.isNonZeroUnsignedInteger(indexes[0])
                || !StringUtil.isNonZeroUnsignedInteger(indexes[1])) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }

        List<Index> indices = new LinkedList<>();
        indices.add(Index.fromOneBased(Integer.parseInt(indexes[0])));
        indices.add(Index.fromOneBased(Integer.parseInt(indexes[1])));

        return indices;
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
     * Parses a {@code String condition} into a {@code Condition}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code condition} is invalid.
     */
    public static Condition parseCondition(String condition) throws ParseException {
        requireNonNull(condition);
        String trimmedCondition = condition.trim();
        if (!Condition.isValidCondition(trimmedCondition)) {
            throw new ParseException(Condition.MESSAGE_CONSTRAINTS);
        }
        return new Condition(trimmedCondition);
    }

    /**
     * Parses {@code Collection<String> conditions} into a {@code ConditionList}.
     */
    public static ConditionList parseConditions(Collection<String> conditions) throws ParseException {
        requireNonNull(conditions);
        final List<Condition> conditionList = new ArrayList<>();
        for (String condition : conditions) {
            conditionList.add(parseCondition(condition));
        }
        return new ConditionList(conditionList);
    }

    /**
     * Parses a {@code String medication} into a {@code Medication}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code String medication} is invalid.
     */
    public static Medication parseMedication(String medication) throws ParseException {
        requireNonNull(medication);
        String[] medicationTypeAndDosage = medication.split("\\|");

        if (medicationTypeAndDosage.length != 2) {
            throw new ParseException(Medication.MESSAGE_CONSTRAINTS);
        }

        String trimmedMedicationType = medicationTypeAndDosage[0].trim();
        String trimmedMedicationDosage = medicationTypeAndDosage[1].trim();

        if (!Medication.isValidMedication(trimmedMedicationType, trimmedMedicationDosage)) {
            throw new ParseException(Medication.MESSAGE_CONSTRAINTS);
        }

        return new Medication(trimmedMedicationType, trimmedMedicationDosage);
    }

    /**
     * Parses {@code Collection<String> medications} into a {@code MedicationList}.
     */
    public static MedicationList parseMedications(Collection<String> medications) throws ParseException {
        requireNonNull(medications);
        final List<Medication> medicationList = new ArrayList<>();
        for (String medication : medications) {
            medicationList.add(parseMedication(medication));
        }
        return new MedicationList(medicationList);
    }

    /**
     * Parses a {@code String medication} into a {@code EditMedicationDescriptor}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code String medication} is invalid.
     */
    public static EditMedicationDescriptor parseEditMedicationDescriptor(String medication) throws ParseException {
        requireNonNull(medication);
        String[] medicationTypeAndDosage = medication.split("\\|");

        if (medicationTypeAndDosage.length > 2) {
            throw new ParseException(Medication.MESSAGE_CONSTRAINTS);
        }

        String trimmedMedicationType = medicationTypeAndDosage[0].trim();
        String trimmedMedicationDosage = "";

        if (medicationTypeAndDosage.length == 2) {
            trimmedMedicationDosage = medicationTypeAndDosage[1].trim();
        }

        Optional<String> optionalMedicationType = Optional.empty();
        Optional<String> optionalMedicationDosage = Optional.empty();

        if (trimmedMedicationType.length() > 0) {
            optionalMedicationType = Optional.of(trimmedMedicationType);
        }

        if (trimmedMedicationDosage.length() > 0) {
            optionalMedicationDosage = Optional.of(trimmedMedicationDosage);
        }

        if (!Medication.isValidMedication(optionalMedicationType, optionalMedicationDosage)) {
            throw new ParseException(Medication.MESSAGE_CONSTRAINTS);
        }

        return new EditMedicationDescriptor(optionalMedicationType, optionalMedicationDosage);
    }

    /**
     * Parses a {@code String taskDescription} into a {@code Task}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code taskDescription} is invalid.
     */
    public static Task parseTask(String taskDescription) throws ParseException {
        requireNonNull(taskDescription);
        String[] descriptionAndTime = taskDescription.trim().split("\\|");

        if (descriptionAndTime.length == 1) {
            return parseTaskWithoutDateTime(descriptionAndTime[0]);
        }

        if (descriptionAndTime.length == 3) {
            return parseRecurringTask(descriptionAndTime[0], descriptionAndTime[1], descriptionAndTime[2]);
        }

        if (descriptionAndTime.length > 3) {
            // TODO proper parseexception
            throw new ParseException("Invalid format");
        }

        return parseTaskWithDateTime(descriptionAndTime[0], descriptionAndTime[1]);
    }

    private static Task parseTaskWithoutDateTime(String taskDescription) throws ParseException {
        String trimmedTaskDescription = taskDescription.trim();

        if (!Task.isValidTaskDescription(trimmedTaskDescription)) {
            throw new ParseException(Task.MESSAGE_CONSTRAINTS);
        }

        return new Task(trimmedTaskDescription);
    }

    private static Task parseTaskWithDateTime(String taskDescription, String dateTime) throws ParseException {
        String trimmedTaskDescription = taskDescription.trim();
        String trimmedDateTime = dateTime.trim();

        if (!Task.isValidTaskDescription(trimmedTaskDescription)) {
            throw new ParseException(Task.MESSAGE_CONSTRAINTS);
        }

        if (!DateTime.isValidDateTime(trimmedDateTime) && !DateTime.isValidDate(trimmedDateTime)) {
            throw new ParseException(DateTime.MESSAGE_CONSTRAINTS);
        }

        DateTime dateTime1 = trimmedDateTime.contains(" ")
                ? new DateTime(trimmedDateTime) : DateTime.ofDate(trimmedDateTime);

        return new Task(trimmedTaskDescription, dateTime1);
    }

    private static Task parseRecurringTask(String taskDescription, String dateTime, String recurAndFreq)
            throws ParseException {
        String trimmedTaskDescription = taskDescription.trim();
        String trimmedDateTime = dateTime.trim();
        String trimmedRecurAndFreq = recurAndFreq.trim();

        if (!Task.isValidTaskDescription(trimmedTaskDescription)) {
            throw new ParseException(Task.MESSAGE_CONSTRAINTS);
        }

        if (!DateTime.isValidDateTime(trimmedDateTime) && !DateTime.isValidDate(trimmedDateTime)) {
            throw new ParseException(DateTime.MESSAGE_CONSTRAINTS);
        }

        if (!RecurringTask.isValidRecurAndFreq(trimmedRecurAndFreq)) {
            throw new ParseException(RecurringTask.MESSAGE_CONSTRAINTS);
        }

        DateTime dateTime1 = trimmedDateTime.contains(" ")
                ? new DateTime(trimmedDateTime) : DateTime.ofDate(trimmedDateTime);

        return RecurringTask.parseRecurringTask(trimmedTaskDescription, dateTime1,
                trimmedRecurAndFreq);
    }

    /**
     * Parses {@code Collection<String> tasks} into a {@code TaskList}.
     */
    public static TaskList parseTasks(Collection<String> taskDescriptions) throws ParseException {
        requireNonNull(taskDescriptions);
        final ArrayList<Task> tasks = new ArrayList<>();
        for (String taskDescription : taskDescriptions) {
            tasks.add(parseTask(taskDescription));
        }
        return new TaskList(tasks);
    }

    /**
     * Parses a {@code String task} into a {@code parseEditTaskDescriptor}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code String task} is invalid.
     */
    public static EditTaskDescriptor parseEditTaskDescriptor(String task) throws ParseException {
        requireNonNull(task);
        String[] taskArguments = task.split("\\|");

        if (taskArguments.length > 3) {
            throw new ParseException(Task.MESSAGE_CONSTRAINTS);
        }

        String trimmedTaskDescription = taskArguments[0].trim();
        String trimmedTaskDateAndTime = "";
        String trimmedTaskRecurrenceAndFrequency = "";

        if (taskArguments.length >= 2) {
            trimmedTaskDateAndTime = taskArguments[1].trim();
        }

        if (taskArguments.length == 3) {
            trimmedTaskRecurrenceAndFrequency = taskArguments[2].trim();
        }

        Optional<String> optionalTaskDescription = Optional.empty();
        Optional<DateTime> optionalTaskDateAndTime = Optional.empty();
        Optional<String> optionalTaskRecurrenceAndFrequency = Optional.empty();

        if (trimmedTaskDescription.length() > 0) {
            if (!Task.isValidTaskDescription(trimmedTaskDescription)) {
                throw new ParseException(Task.MESSAGE_CONSTRAINTS);
            }
            optionalTaskDescription = Optional.of(trimmedTaskDescription);
        }

        if (trimmedTaskDateAndTime.length() > 0) {
            if (!DateTime.isValidDateTime(trimmedTaskDateAndTime) && !DateTime.isValidDate(trimmedTaskDateAndTime)) {
                throw new ParseException(DateTime.MESSAGE_CONSTRAINTS);
            }
            optionalTaskDateAndTime = Optional.of(trimmedTaskDateAndTime.contains(" ")
                    ? new DateTime(trimmedTaskDateAndTime) : DateTime.ofDate(trimmedTaskDateAndTime));
        }

        if (trimmedTaskRecurrenceAndFrequency.length() > 0) {
            if (!RecurringTask.isValidRecurAndFreq(trimmedTaskRecurrenceAndFrequency)) {
                throw new ParseException(RecurringTask.MESSAGE_CONSTRAINTS);
            }
            optionalTaskRecurrenceAndFrequency = Optional.of(trimmedTaskRecurrenceAndFrequency);
        }

        return new EditTaskDescriptor(optionalTaskDescription, optionalTaskDateAndTime,
                optionalTaskRecurrenceAndFrequency);
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
     * Parses {@code Collection<String> tags} into a {@code TagList}.
     */
    public static TagList parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final List<Tag> tagList = new ArrayList<>();
        for (String tagName : tags) {
            tagList.add(parseTag(tagName));
        }
        return new TagList(tagList);
    }

    /**
     * Parses a {@code String remark} into a {@code Remark}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code remark} is invalid.
     */
    public static Remark parseRemark(String remark) throws ParseException {
        requireNonNull(remark);
        String trimmedRemark = remark.trim();
        if (!Remark.isValidRemark(trimmedRemark)) {
            throw new ParseException(Remark.MESSAGE_CONSTRAINTS);
        }
        return new Remark(trimmedRemark);
    }

    /**
     * Parses {@code Collection<String> remarks} into a {@code RemarkList}.
     */
    public static RemarkList parseRemarks(Collection<String> remarks) throws ParseException {
        requireNonNull(remarks);
        final List<Remark> remarkList = new ArrayList<>();
        for (String remark : remarks) {
            remarkList.add(parseRemark(remark));
        }
        return new RemarkList(remarkList);
    }

    private static boolean isOptionLimit(String s) {
        // s is guaranteed to contain no whitespace
        // (" " + s).contains(" " + x) checks if x is a prefix of s
        return Arrays.stream(PREFIXES_PATIENT_ALL).anyMatch(x -> (" " + s).contains(" " + x));
    }

    /**
     * Parses the value of {@code String option} from {@code String arguments}.
     */
    private static Optional<String> parseOption(String arguments, String option) {
        requireNonNull(arguments);
        requireNonNull(option);
        String[] options = arguments.trim().split("\\s+");
        Optional<String> ret = Optional.empty();
        for (int i = 0; i < options.length; i++) {
            if (isOptionLimit(options[i])) {
                break;
            }
            if (i > 0 && options[i - 1].equals(option)) {
                ret = Optional.of(options[i]);
            }
        }
        return ret;
    }

    /**
     * Parses the values of {@code Prefix... options} from {@code String arguments}
     * and return them in a {@code ArgumentMultimap}.
     */
    public static ArgumentMultimap parseOptions(String arguments, Prefix... options) {
        requireNonNull(arguments);
        requireNonNull(options);
        ArgumentMultimap optionValues = new ArgumentMultimap();
        for (int i = 0; i < options.length; i++) {
            Optional<String> value = parseOption(arguments, options[i].toString());
            if (value.isPresent()) {
                optionValues.put(options[i], value.get());
            }
        }
        return optionValues;
    }

    /**
     * Erases the options and value of {@code String option} from {@code String arguments}
     * and return the resulting {@code String}.
     */
    private static String eraseOption(String arguments, String option) {
        requireNonNull(arguments);
        requireNonNull(option);
        String[] options = arguments.trim().split("\\s+");
        int ret = -1;
        for (int i = 0; i < options.length; i++) {
            if (isOptionLimit(options[i])) {
                break;
            }
            if (i > 0 && options[i - 1].equals(option)) {
                ret = i;
            }
        }
        if (ret != -1) {
            options[ret - 1] = "";
            options[ret] = "";
        }
        return Arrays.stream(options).reduce((x, y) -> x + " " + y).get().trim();
    }

    /**
     * Erases the options and values of {@code Prefix... options} from {@code String arguments}
     * and return the resulting {@code String}.
     */
    public static String eraseOptions(String arguments, Prefix... options) {
        requireNonNull(arguments);
        requireNonNull(options);
        for (int i = 0; i < options.length; i++) {
            arguments = eraseOption(arguments, options[i].toString());
        }
        return arguments;
    }

    /**
     * Checks if in the given argumentMultimap, *only* the given validOptions occur.
     */
    public static boolean optionsOnlyContains(ArgumentMultimap argumentMultimap, Prefix... validOptions) {
        requireNonNull(argumentMultimap);
        requireNonNull(validOptions);
        // (option in validOptions and isPresent()) || (option not in validOptions and !isPresent())
        return Arrays.stream(PREFIXES_OPTION_ALL).allMatch(option ->
                Arrays.stream(validOptions).anyMatch(x -> x.equals(option))
                        ^ !argumentMultimap.getValue(option).isPresent());
    }

    /**
     * Checks if in the given argumentMultimap, *only* the given validParameters occur.
     */
    public static boolean parametersOnlyContains(ArgumentMultimap argumentMultimap, Prefix... validParameters) {
        requireNonNull(argumentMultimap);
        requireNonNull(validParameters);
        // (option in validOptions and isPresent()) || (option not in validOptions and !isPresent())
        return Arrays.stream(PREFIXES_PATIENT_ALL).allMatch(option ->
                Arrays.stream(validParameters).anyMatch(x -> x.equals(option))
                        ^ !argumentMultimap.getValue(option).isPresent());
    }
}
