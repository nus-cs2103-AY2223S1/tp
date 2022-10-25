package paymelah.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import paymelah.commons.core.index.Index;
import paymelah.commons.util.CollectionUtil;
import paymelah.commons.util.StringUtil;
import paymelah.logic.parser.exceptions.ParseException;
import paymelah.model.debt.Description;
import paymelah.model.debt.Money;
import paymelah.model.person.Address;
import paymelah.model.person.DebtContainsKeywordsPredicate;
import paymelah.model.person.DebtGreaterEqualAmountPredicate;
import paymelah.model.person.Email;
import paymelah.model.person.Name;
import paymelah.model.person.NameContainsKeywordsPredicate;
import paymelah.model.person.Phone;
import paymelah.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser
 * classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading
     * and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero
     *                        unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses {@code Collection<String> oneBasedIndexes} into a {@code Set<Index>} and returns it. Leading and
     * trailing whitespaces will be trimmed.
     *
     * @param oneBasedIndexes Collection of String representing valid indexes (non-zero unsigned integer).
     * @return {@code Set<Index>} of indexes parsed from given String.
     * @throws ParseException if an index is invalid (not non-zero unsigned integer).
     */
    public static Set<Index> parseIndexes(Collection<String> oneBasedIndexes) throws ParseException {
        requireNonNull(oneBasedIndexes);
        final Set<Index> indexSet = new HashSet<>();
        for (String index : oneBasedIndexes) {
            indexSet.add(parseIndex(index));
        }
        return indexSet;
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
     * Parses {@code Collection<String> descriptions} into a {@code Set<Description>}.
     * @param descriptions the Collection of descriptions to parse
     * @return a Set of Descriptions
     * @throws ParseException if a description cannot be parsed
     */
    public static Set<Description> parseDescriptions(Collection<String> descriptions) throws ParseException {
        requireNonNull(descriptions);
        final Set<Description> descriptionSet = new HashSet<>();
        for (String description : descriptions) {
            descriptionSet.add(parseDescription(description));
        }
        return descriptionSet;
    }

    /**
     * Parses {@code Collection<String> monies} into a {@code Set<Money>}.
     * @param monies the Collection of monies to parse
     * @return a Set of Moneys
     * @throws ParseException if a money cannot be parsed
     */
    public static Set<Money> parseMonies(Collection<String> monies) throws ParseException {
        requireNonNull(monies);
        final Set<Money> moneySet = new HashSet<>();
        for (String money : monies) {
            moneySet.add(parseMoney(money));
        }
        return moneySet;
    }

    /**
     * Parses a {@code String description} into a {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param description The description to turn into a {@code Description} object.
     * @return The corresponding {@code Description}.
     * @throws ParseException if the given {@code description} is invalid.
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
     * Parses a {@code String money} into a {@code Money}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param money The money to turn into a {@code Money} object.
     * @return The corresponding {@code Money}.
     * @throws ParseException if the given {@code money} is invalid.
     */
    public static Money parseMoney(String money) throws ParseException {
        requireNonNull(money);
        String trimmedMoney = money.trim();
        if (!Money.isValidMoney(trimmedMoney)) {
            throw new ParseException(Money.MESSAGE_CONSTRAINTS);
        }
        return new Money(trimmedMoney);
    }

    /**
     * Parses {@code String s} into a {@code NameContainsKeywordsPredicate}.
     */
    public static NameContainsKeywordsPredicate prepareNameContainsKeywordsPredicate(String s) throws ParseException {
        requireNonNull(s);
        String trimmed = s.trim();
        if (trimmed.isEmpty()) {
            throw new ParseException(NameContainsKeywordsPredicate.MESSAGE_CONSTRAINTS);
        }
        return new NameContainsKeywordsPredicate(Arrays.asList(trimmed.split("\\s+")));
    }

    /**
     * Parses {@code String s} into a {@code DebtContainsKeywordsPredicate}.
     */
    public static DebtContainsKeywordsPredicate prepareDebtContainsKeywordsPredicate(String s) throws ParseException {
        requireNonNull(s);
        String trimmed = s.trim();
        if (trimmed.isEmpty()) {
            throw new ParseException(DebtContainsKeywordsPredicate.MESSAGE_CONSTRAINTS);
        }
        return new DebtContainsKeywordsPredicate(Arrays.asList(trimmed.split("\\s+")));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses {@code String s} into a {@code DebtGreaterEqualAmountPredicate}.
     */
    public static DebtGreaterEqualAmountPredicate prepareDebtGreaterEqualAmountPredicate(String s)
            throws ParseException {
        Money m = parseMoney(s);
        return new DebtGreaterEqualAmountPredicate(m);
    }

    /**
     * Stores a descriptor of a {@code Person}; all fields are optional.
     */
    public static class PersonDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Tag> tags;
        private Set<Description> descriptions;
        private Set<Money> monies;

        public PersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public PersonDescriptor(PersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
            setDescriptions(toCopy.descriptions);
            setMonies(toCopy.monies);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldSet() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, tags, descriptions, monies);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        /**
         * Sets {@code descriptions} to this object's {@code descriptions}.
         * A defensive copy of {@code descriptions} is used internally.
         */
        public void setDescriptions(Set<Description> descriptions) {
            this.descriptions = (descriptions != null) ? new HashSet<>(descriptions) : null;
        }

        /**
         * Returns an unmodifiable description set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code descriptions} is null.
         */
        public Optional<Set<Description>> getDescriptions() {
            return (descriptions != null) ? Optional.of(Collections.unmodifiableSet(descriptions)) : Optional.empty();
        }

        /**
         * Sets {@code monies} to this object's {@code monies}.
         * A defensive copy of {@code monies} is used internally.
         */
        public void setMonies(Set<Money> monies) {
            this.monies = (monies != null) ? new HashSet<>(monies) : null;
        }

        /**
         * Returns an unmodifiable money set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code monies} is null.
         */
        public Optional<Set<Money>> getMonies() {
            return (monies != null) ? Optional.of(Collections.unmodifiableSet(monies)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof PersonDescriptor)) {
                return false;
            }

            // state check
            PersonDescriptor pd = (PersonDescriptor) other;

            return getName().equals(pd.getName())
                    && getPhone().equals(pd.getPhone())
                    && getEmail().equals(pd.getEmail())
                    && getAddress().equals(pd.getAddress())
                    && getTags().equals(pd.getTags())
                    && getDescriptions().equals(pd.getDescriptions())
                    && getMonies().equals(pd.getMonies());
        }
    }
}
