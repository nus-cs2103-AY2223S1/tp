package seedu.clinkedin.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.clinkedin.commons.util.CollectionUtil.requireAllNonNull;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import seedu.clinkedin.commons.core.index.Index;
import seedu.clinkedin.commons.util.StringUtil;
import seedu.clinkedin.logic.parser.exceptions.InvalidExtensionException;
import seedu.clinkedin.logic.parser.exceptions.InvalidPersonException;
import seedu.clinkedin.logic.parser.exceptions.ParseException;
import seedu.clinkedin.model.link.Link;
import seedu.clinkedin.model.person.Address;
import seedu.clinkedin.model.person.Email;
import seedu.clinkedin.model.person.Name;
import seedu.clinkedin.model.person.Note;
import seedu.clinkedin.model.person.Person;
import seedu.clinkedin.model.person.Phone;
import seedu.clinkedin.model.person.Rating;
import seedu.clinkedin.model.person.Status;
import seedu.clinkedin.model.person.UniqueTagTypeMap;
import seedu.clinkedin.model.tag.Tag;
import seedu.clinkedin.model.tag.TagType;
import seedu.clinkedin.model.tag.UniqueTagList;
import seedu.clinkedin.model.tag.exceptions.DuplicateTagException;
import seedu.clinkedin.model.tag.exceptions.TagTypeNotFoundException;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Types of file extensions supported for export and/or import.
     */
    public enum FileType {
        CSV,
        JSON
    }

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
     * @throws ParseException if the given {@code clinkedin} is invalid.
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
    public static UniqueTagList parseTagList(List<String> tags) throws ParseException {
        requireNonNull(tags);
        UniqueTagList tagList = new UniqueTagList();
        for (String t : tags) {
            if (!Tag.isValidTagName(t.trim())) {
                throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
            }
            tagList.add(new Tag(t.trim()));
        }
        return tagList;
    }

    /**
     * Parses a {@code String status} into a {@code Status}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code status} is invalid.
     */
    public static Status parseStatus(String status) throws ParseException {
        requireNonNull(status);
        String trimmedStatus = status.trim();
        if (!Status.isValidStatus(trimmedStatus)) {
            throw new ParseException(Status.MESSAGE_CONSTRAINTS);
        }
        return new Status(trimmedStatus);
    }

    /**
     * Parses a {@code String note} into a {@code Note}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code note} is invalid.
     */
    public static Note parseNote(String note) throws ParseException {
        requireNonNull(note);
        return new Note(note.trim());
    }

    /**
     * Parses a {@code String rating} into a {@code Rating}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code rating} is invalid.
     */
    public static Rating parseRating(String rating) throws ParseException {
        requireNonNull(rating);
        String trimmedRating = rating.trim();
        if (!Rating.isValidRatingStr(trimmedRating)) {
            throw new ParseException(Rating.MESSAGE_CONSTRAINTS);
        }
        return new Rating(trimmedRating);
    }

    /**
     * Parses a {@code String link} into a {@code Link}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code link} is invalid.
     */
    public static Link parseLink(String link) throws ParseException {
        requireNonNull(link);
        String trimmedLink = link.trim();
        try {
            URL url = new URL(trimmedLink);
            return new Link(url);
        } catch (MalformedURLException m) {
            throw new ParseException(m.getMessage());
        }
    }

    /**
     * Parses {@code Collection<String> links} into a {@code Set<Link>}.
     */
    public static Set<Link> parseLinks(Collection<String> links) throws ParseException {
        requireNonNull(links);
        final Set<Link> linkSet = new HashSet<>();
        for (String tagName : links) {
            linkSet.add(parseLink(tagName));
        }
        return linkSet;
    }

    /**
     * Parses {@code Collection<String> links} into a {@code Set<Link>} if {@code links} is non-empty.
     * If {@code links} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Link>} containing zero link.
     */
    public static Optional<Set<Link>> parseLinksForEdit(Collection<String> links) throws ParseException {
        assert links != null;

        if (links.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> linkSet = links.size() == 1 && links.contains("") ? Collections.emptySet() : links;
        return Optional.of(ParserUtil.parseLinks(linkSet));
    }

    /**
     * Parses {@code Collection<String> names} into a {@code Set<Name>}.
     */
    public static Set<Name> parseNames(Collection<String> names) throws ParseException {
        requireNonNull(names);
        final Set<Name> nameSet = new HashSet<>();
        for (String nameName : names) {
            nameSet.add(parseName(nameName));
        }
        return nameSet;
    }

    /**
     * Parses {@code Collection<String> phones} into a {@code Set<Phone>}.
     */
    public static Set<Phone> parsePhones(Collection<String> phones) throws ParseException {
        requireNonNull(phones);
        final Set<Phone> phoneSet = new HashSet<>();
        for (String phoneName : phones) {
            phoneSet.add(parsePhone(phoneName));
        }
        return phoneSet;
    }

    /**
     * Parses {@code Collection<String> emails} into a {@code Set<Email>}.
     */
    public static Set<Email> parseEmails(Collection<String> emails) throws ParseException {
        requireNonNull(emails);
        final Set<Email> emailSet = new HashSet<>();
        for (String emailName : emails) {
            emailSet.add(parseEmail(emailName));
        }
        return emailSet;
    }

    /**
     * Parses {@code Collection<String> addresss} into a {@code Set<Address>}.
     */
    public static Set<Address> parseAddresses(Collection<String> addresses) throws ParseException {
        requireNonNull(addresses);
        final Set<Address> addressSet = new HashSet<>();
        for (String addressName : addresses) {
            addressSet.add(parseAddress(addressName));
        }
        return addressSet;
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static UniqueTagTypeMap parseTags(Map<Prefix, List<String>> tags) throws ParseException {
        requireNonNull(tags);
        final UniqueTagTypeMap tagMap = new UniqueTagTypeMap();
        Map<TagType, UniqueTagList> tagTypeMap = new HashMap<>();
        for (Prefix tagName : tags.keySet()) {
            if (tags.get(tagName).size() != 0) {
                tagTypeMap.put(UniqueTagTypeMap.getTagType(tagName), parseTagList(tags.get(tagName)));
            }
        }
        tagMap.setTagTypeMap(tagTypeMap);
        return tagMap;
    }

    /**
     * Parses a {@code String prefix} into a {@code Prefix}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code prefix} is invalid.
     */
    public static Prefix parsePrefix(String prefix) throws ParseException {
        requireNonNull(prefix);
        String trimmedPrefix = prefix.trim();
        if (!Prefix.isValidPrefixName(trimmedPrefix + "/")) {
            throw new ParseException(Prefix.MESSAGE_CONSTRAINTS);
        }
        Prefix p;
        try {
            p = new Prefix(trimmedPrefix + "/");
        } catch (IllegalArgumentException iae) {
            throw new ParseException(iae.getMessage());
        }
        return p;
    }

    /**
     * Parses a {@code String tagType} into a {@code TagType}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tagType} is invalid.
     */
    public static TagType parseTagType(String tagType, String prefix) throws ParseException {
        requireAllNonNull(tagType, prefix);
        String trimmedTagType = tagType.trim();
        if (!TagType.isValidTagType(trimmedTagType)) {
            throw new ParseException(TagType.MESSAGE_CONSTRAINTS);
        }
        Prefix pref = parsePrefix(prefix);
        return new TagType(trimmedTagType, pref);
    }

    /**
     * Parses a {@code String tagType} into a {@code TagType}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tagType} is invalid.
     */
    public static TagType parseTagType(String tagType, Prefix prefix) throws ParseException {
        requireNonNull(tagType);
        String trimmedTagType = tagType.trim();
        if (!TagType.isValidTagType(trimmedTagType)) {
            throw new ParseException(TagType.MESSAGE_CONSTRAINTS);
        }
        return new TagType(trimmedTagType, prefix);
    }

    /**
     * Splits a hyphen.
     * @param oldNew Hyphenated String.
     * @return Array of Strings consisting of strings preceding and following the hyphen.
     * @throws ParseException If argument format incorrect.
     */
    public static String[] parseHyphen(String oldNew) throws ParseException {
        String[] oldNewPair = oldNew.split("-", 2);
        if (oldNewPair.length != 2) {
            throw new ParseException("Old and new tag types and tag prefixes must be separated by a hyphen!");
        }
        oldNewPair[0] = oldNewPair[0].trim();
        oldNewPair[1] = oldNewPair[1].trim();
        return oldNewPair;
    }

    /**
     * Parses {@code Collection<String> statuses} into a {@code Set<Status>}.
     */
    public static Set<Status> parseStatuses(Collection<String> statuses) throws ParseException {
        requireNonNull(statuses);
        final Set<Status> statusSet = new HashSet<>();
        for (String statusName : statuses) {
            statusSet.add(parseStatus(statusName));
        }
        return statusSet;
    }

    /**
     * Parses {@code Collection<String> notes} into a {@code Set<Note>}.
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
     * Gets {@code FileType} from {@code String filePath}
     */
    public static FileType getFileType(String filePath) throws InvalidExtensionException {
        requireNonNull(filePath);
        String trimmedFilePath = filePath.trim();

        int periodIndex = trimmedFilePath.lastIndexOf(".");
        if (periodIndex == -1) {
            throw new InvalidExtensionException();
        }
        String extension = trimmedFilePath.substring(periodIndex + 1).trim().toUpperCase();
        FileType fileType;
        try {
            fileType = FileType.valueOf(extension);
        } catch (IllegalArgumentException e) {
            throw new InvalidExtensionException();
        }

        return fileType;
    }

    /**
     * Parses a person.
     */
    public static Person parsePerson(ArrayList<String[]> person) throws InvalidPersonException {
        boolean atleastOne = person.stream().allMatch(detail -> detail.length >= 1);
        if (!atleastOne) {
            throw new InvalidPersonException();
        }
        Name name = null;
        Phone phone = null;
        Email email = null;
        Address address = null;
        Status status = null;
        Note note = null;
        UniqueTagTypeMap tagTypeMap = new UniqueTagTypeMap();
        Rating rating = null;
        Set<Link> links = new HashSet<>();

        for (String[] detail: person) {
            String check = detail[0];
            String tagType = null;
            if (check.startsWith("Tag:")) {
                tagType = check.substring(4);
                check = "Tag:";
            } else if (!check.startsWith("Links") && detail.length != 2) {
                throw new InvalidPersonException("More arguments found than possible!");
            }
            try {
                switch (check) {
                case "Name":
                    name = new Name(detail[1]);
                    break;
                case "Phone":
                    phone = new Phone(detail[1]);
                    break;
                case "Email":
                    email = new Email(detail[1]);
                    break;
                case "Address":
                    address = new Address(detail[1]);
                    break;
                case "Status":
                    status = new Status(detail[1]);
                    break;
                case "Note":
                    note = new Note(detail[1]);
                    break;
                case "Tag:":
                    String[] prefixTagNamePair = tagType.split("-", 2);
                    if (prefixTagNamePair.length != 2) {
                        throw new InvalidPersonException();
                    }
                    Prefix p = new Prefix(prefixTagNamePair[0]);
                    TagType tagTypeName = new TagType(prefixTagNamePair[1], p);
                    ParserUtil.addTags(tagTypeMap, tagTypeName, detail);
                    break;
                case "Rating":
                    rating = new Rating(detail[1]);
                    break;
                case "Links":
                    for (int i = 1; i < detail.length; i++) {
                        try {
                            URL url = new URL(detail[i]);
                            links.add(new Link(url));
                        } catch (MalformedURLException m) {
                            throw new InvalidPersonException();
                        }
                    }
                    break;
                default:
                    throw new InvalidPersonException("Invalid attribute found!");
                }
            } catch (IllegalArgumentException | DuplicateTagException | TagTypeNotFoundException e) {
                throw new InvalidPersonException();
            }
        }
        boolean foundAll = checkAllNonNull(name, phone, email, address, tagTypeMap, status, note, rating, links);
        if (!foundAll) {
            throw new InvalidPersonException("All attributes not present!");
        }
        return new Person(name, phone, email, address, tagTypeMap, status, note, rating, links);
    }

    private static void addTags(UniqueTagTypeMap tagTypeMap, TagType tagType, String[] tags) {
        for (int i = 1; i < tags.length; i++) {
            tagTypeMap.mergeTag(tagType, new Tag(tags[i]));
        }
    }
    private static boolean checkAllNonNull(Name name, Phone phone, Email email,
                                           Address address, UniqueTagTypeMap tagTypeMap, Status status,
                                           Note note, Rating rating, Set<Link> links) {
        if (name == null || phone == null || email == null || address == null || tagTypeMap == null || status == null
                || note == null || rating == null || links == null) {
            return false;
        }
        return true;
    }

    /**
     * Parses {@code Collection<String> ratings} into a {@code Set<Rating>}.
     */
    public static Set<Rating> parseRatings(List<String> ratings) throws ParseException {
        requireNonNull(ratings);
        final Set<Rating> ratingSet = new HashSet<>();
        for (String ratingName : ratings) {
            ratingSet.add(parseRating(ratingName));
        }
        return ratingSet;
    }
}
