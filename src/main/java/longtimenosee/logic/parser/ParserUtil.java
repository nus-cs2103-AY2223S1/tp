package longtimenosee.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import longtimenosee.commons.core.index.Index;
import longtimenosee.commons.util.StringUtil;
import longtimenosee.logic.parser.exceptions.ParseException;
import longtimenosee.model.event.Date;
import longtimenosee.model.event.Description;
import longtimenosee.model.event.Duration;
import longtimenosee.model.person.Address;
import longtimenosee.model.person.Birthday;
import longtimenosee.model.person.Email;
import longtimenosee.model.person.Income;
import longtimenosee.model.person.Name;
import longtimenosee.model.person.Phone;
import longtimenosee.model.person.RiskAppetite;
import longtimenosee.model.policy.Commission;
import longtimenosee.model.policy.Company;
import longtimenosee.model.policy.Coverage;
import longtimenosee.model.policy.PolicyDate;
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
     * @throws ParseException if the given {@code name} is invalid or too long.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        if (!Name.isValidLength(trimmedName)) {
            throw new ParseException(Name.LENGTH_CONSTRAINTS);
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
        if (!Birthday.isValidDate(trimmedBirthday)) {
            throw new ParseException(Birthday.RANGE_FORMAT_CONSTRAINTS);
        }
        if (!Birthday.isReasonableBirthday(trimmedBirthday)) {
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
        if (!Income.isPositiveIncome(trimmedIncome)) {
            throw new ParseException(Income.VALUE_FORMAT_CONSTRAINTS);
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
     * Checks if the title is a valid title.
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
        if (!Company.isValidCompany(trimmedCompanyTag)) {
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
    // Parse functions related to Event //
    /**
     * Parses a {@code String startTime, endTime} into a {@code Duration}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code startTime, endTime} is invalid.
     */
    public static Duration parseDuration(String startTime, String endTime) throws ParseException {
        requireNonNull(startTime);
        requireNonNull(endTime);
        String strippedStartTime = startTime.strip();
        String strippedEndTime = endTime.strip();
        if (!Duration.isValidTime(strippedStartTime) || !Duration
                .isValidTime((strippedEndTime))) {
            throw new ParseException(Duration.FORMAT_CONSTRAINTS);
        }
        String finalInput = strippedStartTime + "__" + strippedEndTime;
        if (!Duration.isValidStartAndEnd(finalInput)) {
            throw new ParseException(Duration.MESSAGE_CONSTRAINTS);
        }
        return new Duration(finalInput);
    }
    /**
     * Parses a {@code String date} into a {@code Date}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static Date parseDate(String date) throws ParseException {
        requireNonNull(date);
        String strippedDate = date.strip();
        if (!Date.isValidFormat(strippedDate)) {
            throw new ParseException(Date.MESSAGE_FORMAT_CONSTRAINTS);
        }

        if (!Date.isValidRange(strippedDate)) {
            throw new ParseException(Date.RANGE_FORMAT_CONSTRAINTS);
        }

        if (!Date.isValidYear(strippedDate)) {
            throw new ParseException(Date.YEAR_RANGE_CONSTRAINTS);
        }
        return new Date(strippedDate);
    }
    /**
     * Parses {@code String policyDate} into a {@code PolicyDate}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static PolicyDate parsePolicyDate(String policyDate) throws ParseException {
        requireNonNull(policyDate);
        String trimmedPolicyDate = policyDate.trim();
        if (!PolicyDate.isValidFormat(trimmedPolicyDate)) {
            throw new ParseException(PolicyDate.MESSAGE_FORMAT_CONSTRAINTS);
        }
        return new PolicyDate(policyDate);
    }


    /**
     * Parses a {@code String description} into a {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */

    public static Description parseDescription(String description) throws ParseException {
        requireNonNull(description);
        String strippedDescription = description.strip();
        if (!Description.isValidDescription(strippedDescription)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        if (!Description.isValidLength(strippedDescription)) {
            throw new ParseException(Description.LENGTH_CONSTRAINTS);
        }
        return new Description(strippedDescription);
    }
}
