package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.task.Task.COMPLETION_STATUS_MESSAGE_CONSTRAINTS;
import static seedu.address.model.task.Task.INPUT_TASK_COMPLETED;
import static seedu.address.model.task.Task.INPUT_TASK_NOT_COMPLETED;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Description;

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
     * Parses a string of {@code String names} into a list of {@code String names}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param names The names to be parsed.
     * @return A list of names.
     * @throws ParseException if any {@code name} is invalid.
     */
    public static List<Name> parseNames(String names) throws ParseException {
        requireNonNull(names);
        String trimmedNames = names.trim();
        String[] nameArr = trimmedNames.split(" ");
        ArrayList<Name> nameList = new ArrayList<>();
        for (String name : nameArr) {
            if (!Name.isValidName(name)) {
                throw new ParseException(Name.MESSAGE_CONSTRAINTS);
            }
            nameList.add(new Name(name));
        }
        return nameList;
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
     * Parses a string of {@code String phones} into a list of {@code String phones}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param phones The phones to be parsed.
     * @return A list of phones.
     * @throws ParseException if any {@code phone} is invalid.
     */
    public static List<Phone> parsePhones(String phones) throws ParseException {
        requireNonNull(phones);
        String trimmedNames = phones.trim();
        String[] phoneArr = trimmedNames.split(" ");
        ArrayList<Phone> phoneList = new ArrayList<>();
        for (String phone : phoneArr) {
            if (!Phone.isValidPhone(phone)) {
                throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
            }
            phoneList.add(new Phone(phone));
        }
        return phoneList;
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
     * Parses a string of {@code String addresses} into a list of {@code String addresses}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param addresses The addresses to be parsed.
     * @return A list of addresses.
     * @throws ParseException if any {@code address} is invalid.
     */
    public static List<Address> parseAddresses(String addresses) throws ParseException {
        requireNonNull(addresses);
        String trimmedNames = addresses.trim();
        String[] addressArr = trimmedNames.split(" ");
        ArrayList<Address> addressList = new ArrayList<>();
        for (String address : addressArr) {
            if (!Address.isValidAddress(address)) {
                throw new ParseException(Address.MESSAGE_CONSTRAINTS);
            }
            addressList.add(new Address(address));
        }
        return addressList;
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
     * Parses a string of {@code String emails} into a list of {@code String emails}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param emails The emails to be parsed.
     * @return A list of emails.
     * @throws ParseException if any {@code email} is invalid.
     */
    public static List<Email> parseEmails(String emails) throws ParseException {
        requireNonNull(emails);
        String trimmedNames = emails.trim();
        String[] emailArr = trimmedNames.split(" ");
        ArrayList<Email> emailList = new ArrayList<>();
        for (String email : emailArr) {
            if (!Email.isValidEmail(email)) {
                throw new ParseException(Email.MESSAGE_CONSTRAINTS);
            }
            emailList.add(new Email(email));
        }
        return emailList;
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
     * Parses a string of {@code String remarks} into a list of {@code String remarks}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param remarks The remarks to be parsed.
     * @return A list of remarks.
     * @throws ParseException if any {@code remark} is invalid.
     */
    public static List<Remark> parseRemarks(String remarks) throws ParseException {
        requireNonNull(remarks);
        String trimmedRemarks = remarks.trim();
        String[] remarkArr = trimmedRemarks.split(" ");
        ArrayList<Remark> remarkList = new ArrayList<>();
        for (String remark : remarkArr) {
            if (!Remark.isValidRemark(remark)) {
                throw new ParseException(Email.MESSAGE_CONSTRAINTS);
            }
            remarkList.add(new Remark(remark));
        }
        return remarkList;
    }

    /**
     * Parses a {@code String description} into a {@code Description}.
     * @param description The String to be parsed.
     * @return A Description instance
     * @throws ParseException if the given description is invalid.
     */
    public static Description parseDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (!Description.isValidDescription(trimmedDescription)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(trimmedDescription);
    }

    /**
     * Parses a string of {@code String descriptions} into a list of {@code String descriptions}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param descriptions The descriptions to be parsed.
     * @return A list of descriptions.
     * @throws ParseException if any {@code description} is invalid.
     */
    public static List<Description> parseDescriptions(String descriptions) throws ParseException {
        requireNonNull(descriptions);
        String trimmedDescriptions = descriptions.trim();
        String[] descriptionArr = trimmedDescriptions.split(" ");
        ArrayList<Description> descriptionList = new ArrayList<>();
        for (String description : descriptionArr) {
            if (!Description.isValidDescription(description)) {
                throw new ParseException(Description.MESSAGE_CONSTRAINTS);
            }
            descriptionList.add(new Description(description));
        }
        return descriptionList;
    }

    /**
     * Parses a {@code String deadline} into a {@code Deadline}.
     *
     * @param deadline The String to be parsed.
     * @return A Deadline instance
     * @throws ParseException if the given deadline is invalid.
     */
    public static Deadline parseDeadline(String deadline) throws ParseException {
        requireNonNull(deadline);
        String trimmedDeadline = deadline.trim();

        Deadline parsedDeadline;

        try {
            parsedDeadline = new Deadline(trimmedDeadline);
        } catch (DateTimeParseException e) {
            throw new ParseException(Deadline.INVALID_DATE);
        }

        requireNonNull(parsedDeadline);

        return parsedDeadline;
    }

    /**
     * Parses a string of {@code String deadlines} into a list of {@code String deadlines}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param deadlines The descriptions to be parsed.
     * @return A list of deadlines.
     * @throws ParseException if any {@code description} is invalid.
     */
    public static List<Deadline> parseDeadlines(String deadlines) throws ParseException {
        requireNonNull(deadlines);
        String trimmedDeadlines = deadlines.trim();
        String[] deadlineArr = trimmedDeadlines.split(" ");
        ArrayList<Deadline> descriptionList = new ArrayList<>();

        for (String deadline : deadlineArr) {
            Deadline parsedDeadline;

            try {
                parsedDeadline = new Deadline(deadline);
            } catch (DateTimeParseException e) {
                throw new ParseException(Deadline.INVALID_DATE);
            }
            descriptionList.add(parsedDeadline);
        }
        return descriptionList;
    }

    /**
     * Parses a string of {@code String completionStatuses} into a list of {@code Boolean completionStatuses}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param completionStatuses The completion statuses to be parsed.
     * @return A list of completion statuses.
     * @throws ParseException if any {@code completionStatus} is invalid.
     */
    public static List<Boolean> parseCompletionStatuses(String completionStatuses) throws ParseException {
        requireNonNull(completionStatuses);
        String trimmedCompletionStatuses = completionStatuses.trim();
        String[] completionStatusArr = trimmedCompletionStatuses.split(" ");
        ArrayList<Boolean> completionStatusList = new ArrayList<>();
        for (String completionStatus : completionStatusArr) {
            if (StringUtil.containsWordIgnoreCase(completionStatus, INPUT_TASK_COMPLETED)) {
                completionStatusList.add(true);
                continue;
            }
            if (StringUtil.containsWordIgnoreCase(completionStatus, INPUT_TASK_NOT_COMPLETED)) {
                completionStatusList.add(false);
                continue;
            }
            throw new ParseException(COMPLETION_STATUS_MESSAGE_CONSTRAINTS);
        }
        return completionStatusList;
    }
}
