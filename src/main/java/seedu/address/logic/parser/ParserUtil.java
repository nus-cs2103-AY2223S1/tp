package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.person.Cap.CAP_SEPARATOR;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AddressBookFile;
import seedu.address.model.job.Id;
import seedu.address.model.job.Title;
import seedu.address.model.person.Address;
import seedu.address.model.person.Cap;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.GraduationDate;
import seedu.address.model.person.Major;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.University;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_FILE_PATH = "File path is invalid.";
    public static final String CAP_PARSING_REGEX = "^[0-9]\\.?\\d{0,2}\\/[0-9]\\.?\\d{0,2}$";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it.
     * Leading and trailing whitespaces will be trimmed.
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
     * Parses {@code filePath} into a {@code File} and returns it.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the specified file path format is invalid.
     */
    public static Path parseFilePath(String filePath) throws ParseException {
        String trimmedPath = filePath.trim();
        if (!FileUtil.isValidPath(trimmedPath) || trimmedPath.equals("")) {
            throw new ParseException(MESSAGE_INVALID_FILE_PATH);
        }
        return Paths.get(trimmedPath);
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     * Multiple whitespaces will be replaced with a single space.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        String cleanedName = trimmedName.replaceAll(" +", " ");

        if (!Name.isWithinLengthLimit(cleanedName)) {
            throw new ParseException(Name.MESSAGE_LENGTH_LIMIT_EXCEEDED);
        }

        if (!Name.isValidName(cleanedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }

        return new Name(cleanedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     * Multiple whitespaces will be replaced with a single space.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        String cleanedPhone = trimmedPhone.replaceAll(" +", " ");

        if (!Phone.isWithinLengthLimit(cleanedPhone)) {
            throw new ParseException(Phone.MESSAGE_LENGTH_LIMIT_EXCEEDED);
        }

        if (!Phone.isValidPhone(cleanedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }

        return new Phone(cleanedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     * Multiple whitespaces will be replaced with a single space.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        String cleanedAddress = trimmedAddress.replaceAll(" +", " ");

        if (!Address.isWithinLengthLimit(cleanedAddress)) {
            throw new ParseException(Address.MESSAGE_LENGTH_LIMIT_EXCEEDED);
        }

        if (!Address.isValidAddress(cleanedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }

        return new Address(cleanedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     * Multiple whitespaces will be replaced with a single space.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        String cleanedEmail = trimmedEmail.replaceAll(" +", " ");

        if (!Email.isWithinLengthLimit(cleanedEmail)) {
            throw new ParseException(Email.MESSAGE_LENGTH_LIMIT_EXCEEDED);
        }

        if (!Email.isValidEmail(cleanedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }

        return new Email(cleanedEmail);
    }

    /**
     * Parses a {@code String gender} into an {@code Gender}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code gender} is invalid.
     */
    public static Gender parseGender(String gender) throws ParseException {
        requireNonNull(gender);
        String trimmedGender = gender.trim();
        if (!Gender.isValidGender(trimmedGender)) {
            throw new ParseException(Gender.MESSAGE_CONSTRAINTS);
        }
        return new Gender(trimmedGender);
    }

    /**
     * Parses a {@code String graduationDate} into a {@code GraduationDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code graduationDate} is invalid.
     */
    public static GraduationDate parseGraduationDate(String graduationDate) throws ParseException {
        requireNonNull(graduationDate);
        String trimmedGraduationDate = graduationDate.trim();
        if (!GraduationDate.isValidGraduationDate(trimmedGraduationDate)) {
            throw new ParseException(GraduationDate.MESSAGE_CONSTRAINTS);
        }
        return new GraduationDate(trimmedGraduationDate);
    }

    /**
     * Parses a {@code String cap} into a {@code Cap}.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException if the given {@code cap} is invalid.
     */
    public static Cap parseCap(String cap) throws ParseException {
        try {
            requireNonNull(cap);
            String trimmedCap = cap.trim();
            String[] capValues = trimmedCap.split(CAP_SEPARATOR);
            if (!capValues[0].matches(Cap.VALIDATION_REGEX) || !capValues[1].matches(Cap.VALIDATION_REGEX)) {
                throw new ParseException(Cap.MESSAGE_NUMERIC_VALUE_REQUIRED);
            }
            double capValue = Double.parseDouble(capValues[0]);
            double maximumCapValue = Double.parseDouble(capValues[1]);
            if (!Cap.isValidCap(capValue, maximumCapValue)) {
                throw new ParseException(Cap.MESSAGE_CONSTRAINTS);
            }
            return new Cap(capValue, maximumCapValue);
        } catch (NumberFormatException e) {
            throw new ParseException(Cap.MESSAGE_CONSTRAINTS);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ParseException(Cap.MESSAGE_MAXIMUM_CAP_REQUIRED);
        }
    }

    /**
     * Parses a {@code String university} into an {@code University}.
     * Leading and trailing whitespaces will be trimmed.
     * Multiple whitespaces will be replaced with a single space.
     *
     * @throws ParseException if the given {@code university} is invalid.
     */
    public static University parseUniversity(String university) throws ParseException {
        requireNonNull(university);
        String trimmedUniversity = university.trim();
        String cleanedUniversity = trimmedUniversity.replaceAll(" +", " ");

        if (!University.isWithinLengthLimit(cleanedUniversity)) {
            throw new ParseException(University.MESSAGE_LENGTH_LIMIT_EXCEEDED);
        }

        if (!University.isValidUniversity(cleanedUniversity)) {
            throw new ParseException(University.MESSAGE_CONSTRAINTS);
        }

        return new University(cleanedUniversity);
    }

    /**
     * Parses a {@code String major} into an {@code Major}.
     * Leading and trailing whitespaces will be trimmed.
     * Multiple whitespaces will be replaced with a single space.
     *
     * @throws ParseException if the given {@code major} is invalid.
     */
    public static Major parseMajor(String major) throws ParseException {
        requireNonNull(major);
        String trimmedMajor = major.trim();
        String cleanedMajor = trimmedMajor.replaceAll(" +", " ");

        if (!Major.isWithinLengthLimit(cleanedMajor)) {
            throw new ParseException(Major.MESSAGE_LENGTH_LIMIT_EXCEEDED);
        }

        if (!Major.isValidMajor(cleanedMajor)) {
            throw new ParseException(Major.MESSAGE_CONSTRAINTS);
        }

        return new Major(cleanedMajor);
    }

    /**
     * Parses a {@code String id} into an {@code Id}.
     * Leading and trailing whitespaces will be trimmed.
     * Multiple whitespaces will be replaced with a single space.
     *
     * @throws ParseException if the given {@code id} is invalid.
     */
    public static Id parseId(String id) throws ParseException {
        requireNonNull(id);
        String trimmedId = id.trim();
        String cleanedId = trimmedId.replaceAll(" +", " ");

        if (!Id.isWithinLengthLimit(cleanedId)) {
            throw new ParseException(Id.MESSAGE_LENGTH_LIMIT_EXCEEDED);
        }

        if (!Id.isValidId(cleanedId)) {
            throw new ParseException(Id.MESSAGE_CONSTRAINTS);
        }

        return new Id(cleanedId);
    }

    /**
     * Parses a {@code String title} into an {@code Title}.
     * Leading and trailing whitespaces will be trimmed.
     * Multiple whitespaces will be replaced with a single space.
     *
     * @throws ParseException if the given {@code title} is invalid.
     */
    public static Title parseTitle(String title) throws ParseException {
        requireNonNull(title);
        String trimmedTitle = title.trim();
        String cleanedTitle = trimmedTitle.replaceAll(" +", " ");

        if (!Title.isWithinLengthLimit(cleanedTitle)) {
            throw new ParseException(Title.MESSAGE_LENGTH_LIMIT_EXCEEDED);
        }

        if (!Title.isValidTitle(cleanedTitle)) {
            throw new ParseException(Title.MESSAGE_CONSTRAINTS);
        }

        return new Title(cleanedTitle);
    }

    /**
     * Parses a {@code String file} into an {@code AddressBookFile}.
     * Leading and trailing whitespaces will be trimmed.
     * Multiple whitespaces will be replaced with a single space.
     *
     * @throws ParseException if the given {@code file} is invalid.
     */
    public static AddressBookFile parseAddressBookFile(String file) throws ParseException {
        requireNonNull(file);
        String trimmedFile = file.trim();
        String cleanedFile = trimmedFile.replaceAll(" +", " ");

        if (!AddressBookFile.isValidAddressBookFile(cleanedFile)) {
            throw new ParseException(AddressBookFile.MESSAGE_CONSTRAINTS);
        }

        return new AddressBookFile(cleanedFile);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     * Multiple whitespaces will be replaced with a single space.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        String cleanedTag = trimmedTag.replaceAll(" +", " ");

        if (!Tag.isWithinLengthLimit(cleanedTag)) {
            throw new ParseException(Tag.MESSAGE_LENGTH_LIMIT_EXCEEDED);
        }

        if (!Tag.isValidTagName(cleanedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }

        return new Tag(cleanedTag);
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
}
