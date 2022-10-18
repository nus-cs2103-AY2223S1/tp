package seedu.address.logic.parser.student;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.*;
import seedu.address.model.tag.Tag;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class StudentParserUtil {
    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        Objects.requireNonNull(name);
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
        Objects.requireNonNull(phone);
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
        Objects.requireNonNull(address);
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
        Objects.requireNonNull(email);
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
        Objects.requireNonNull(tag);
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
        Objects.requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<Tag>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String id} into a {@code ID}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code id} is invalid.
     */
    public static ID parseId(String id) throws ParseException {
        Objects.requireNonNull(id);
        String trimmedId = id.trim();
        if (!ID.isValidId(trimmedId)) {
            throw new ParseException(ID.MESSAGE_CONSTRAINTS);
        }
        return new ID(trimmedId);
    }

    /**
     * Parses a {@code String telegram} into a {@code Telegram}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code telegram} is invalid.
     */
    public static Telegram parseTelegram(String telegram) throws ParseException {
        Objects.requireNonNull(telegram);
        String trimmedTelegram = telegram.trim();
        if (!Telegram.isValidTelegram(trimmedTelegram)) {
            throw new ParseException(Telegram.MESSAGE_CONSTRAINTS);
        }
        return new Telegram(trimmedTelegram);
    }

    /**
     * Parses a {@code String grade} into a {@code Grade}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code grade} is invalid.
     */
    public static Grade parseGrade(String grade) {
        Objects.requireNonNull(grade);
        String trimmedGrade = grade.trim();
        return new Grade(trimmedGrade);
    }

    /**
     * Parses a {@code String attendance} into a {@code Attendance}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code attendance} is invalid.
     */
    public static Attendance parseAttendance(String attendance) {
        Objects.requireNonNull(attendance);
        String trimmedAttendance = attendance.trim();
        return new Attendance(trimmedAttendance);
    }

    /**
     * Parses a {@code String participation} into a {@code Participation}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code participation} is invalid.
     */
    public static Participation parseParticipation(String participation) {
        Objects.requireNonNull(participation);
        String trimmedParticipation = participation.trim();
        return new Participation(trimmedParticipation);
    }
}