package longtimenosee.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import longtimenosee.commons.core.index.Index;
import longtimenosee.commons.util.StringUtil;
import longtimenosee.logic.parser.exceptions.ParseException;
import longtimenosee.model.client.Birthday;
import longtimenosee.model.client.Income;
import longtimenosee.model.client.RiskAppetite;
import longtimenosee.model.person.Address;
import longtimenosee.model.person.Email;
import longtimenosee.model.person.Name;
import longtimenosee.model.person.Phone;
import longtimenosee.model.policy.Commission;
import longtimenosee.model.policy.Company;
import longtimenosee.model.policy.Coverage;
import longtimenosee.model.policy.Title;
import longtimenosee.model.tag.Tag;


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
     * Parses {@code String birthday} into a {@code Birthday}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Birthday parseBirthday(String birthday) throws ParseException {
        requireNonNull(birthday);
        String trimmedBirthday = birthday.trim();
        if (!Birthday.isValidFormat(trimmedBirthday)) {
            throw new ParseException(Birthday.MESSAGE_FORMAT_CONSTRAINTS);
        }
        if (!Birthday.isValidBirthday(trimmedBirthday)) {
            throw new ParseException(Birthday.MESSAGE_DATE_CONSTRAINTS);
        }
        return new Birthday(birthday);
    }

    /**
     * Parses {@code String income} into a {@code income}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Income parseIncome(String income) throws ParseException {
        requireNonNull(income);
        String trimmedIncome = income.trim();
        if (!Income.isValidFormat(trimmedIncome)) {
            throw new ParseException(Income.MESSAGE_FORMAT_CONSTRAINTS);
        }
        return new Income(income);
    }

    /**
     * Parses {@code String RA} into a {@code RiskAppetite}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static RiskAppetite parseRA(String ra) throws ParseException {
        requireNonNull(ra);
        String trimmedRiskAppetiteTag = ra.trim();
        if (!RiskAppetite.isValidFormat(trimmedRiskAppetiteTag)) {
            throw new ParseException(RiskAppetite.MESSAGE_FORMAT_CONSTRAINTS);
        }
        return new RiskAppetite(ra);
    }

    /**
     * Parses {@code String Title} into a {@code Title}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Title parseTitle(String title) throws ParseException {
        requireNonNull(title);
        String trimmedTitleTag = title.trim();
        if (!Title.isValidTitle(trimmedTitleTag)) {
            throw new ParseException(Title.MESSAGE_FORMAT_CONSTRAINTS);
        }
        return new Title(trimmedTitleTag);
    }

    /**
     * Parses {@code String company} into a {@code Company}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Company parseCompany(String company) throws ParseException {
        requireNonNull(company);
        String trimmedCompanyTag = company.trim();
        if (!Title.isValidTitle(trimmedCompanyTag)) {
            throw new ParseException(Company.MESSAGE_FORMAT_CONSTRAINTS);
        }
        return new Company(trimmedCompanyTag);
    }

    /**
     * Parses {@code String commissions} into a {@code Commission}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Commission parseCommission(String commission) throws ParseException {
        requireNonNull(commission);
        String trimmedCommission = commission.trim();
        if (!Commission.isValidCommission(trimmedCommission)) {
            throw new ParseException(Commission.MESSAGE_CONSTRAINTS);
        }
        return new Commission(trimmedCommission);
    }

    /**
     * Parses a {@code String Coverage} into a {@code Coverage}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code coverage} is invalid.
     */
    public static Coverage parseCoverage(String coverage) throws ParseException {
        requireNonNull(coverage);
        String trimmedCoverage = coverage.trim();
        if (!Coverage.isValidCoverageName(trimmedCoverage)) {
            throw new ParseException(Coverage.MESSAGE_CONSTRAINTS);
        }
        return new Coverage(trimmedCoverage);
    }

    /**
     * Parses {@code Collection<String> coverages} into a {@code Set<Coverage>}.
     */
    public static Set<Coverage> parseCoverages(Collection<String> coverages) throws ParseException {
        requireNonNull(coverages);
        final Set<Coverage> coverageSet = new HashSet<>();
        for (String coverage : coverages) {
            coverageSet.add(parseCoverage(coverage));
        }
        return coverageSet;
    }

}
