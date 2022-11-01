package seedu.nutrigoals.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import seedu.nutrigoals.commons.core.index.Index;
import seedu.nutrigoals.commons.util.StringUtil;
import seedu.nutrigoals.logic.parser.exceptions.ParseException;
import seedu.nutrigoals.model.Calorie;
import seedu.nutrigoals.model.Location;
import seedu.nutrigoals.model.meal.DateTime;
import seedu.nutrigoals.model.meal.Name;
import seedu.nutrigoals.model.tag.Tag;
import seedu.nutrigoals.model.user.Age;
import seedu.nutrigoals.model.user.Gender;
import seedu.nutrigoals.model.user.Height;
import seedu.nutrigoals.model.user.Weight;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String DEFAULT_TIME = "T00:00";
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
     * Parses {@code String calorie} into a {@code Calorie}
     * @param calorie Calorie
     * @return Calorie
     * @throws ParseException if the given {@code calorie} is invalid.
     */
    public static Calorie parseCalorie(String calorie) throws ParseException {
        requireNonNull(calorie);
        String trimmedCalorie = calorie.trim();
        if (!Calorie.isValidCalorie(trimmedCalorie)) {
            throw new ParseException(Calorie.MESSAGE_CONSTRAINTS);
        }
        return new Calorie(trimmedCalorie);
    }

    /**
     * Parses {@code String locationName} into a {@code Location}
     * @param locationName name of location
     * @return Location
     * @throws ParseException if the given {@code locationName} is invalid.
     */
    public static Location parseLocation(String locationName) throws ParseException {
        requireNonNull(locationName);
        String trimmedLocationName = locationName.trim().toUpperCase();
        Map<String, Location> out = new HashMap<>();
        // ANY UPDATES HERE SHOULD UPDATE LOCATE'S MESSAGE_USAGE SORRY FOR COUPLING
        // https://goo.gl/maps/8a2h8ciNGcc2DUxs8
        out.put("COM2", new Location("COM2", "1.2942815638814327, 103.77410024788284"));
        // https://goo.gl/maps/TJQd8dfRYovJe8Va7
        out.put("S13", new Location("S13", "1.2968733957422691, 103.7790094606027"));
        // https://goo.gl/maps/MgTnNAnMCVqokPqx9
        out.put("S17", new Location("S17", "1.2976996370988612, 103.78060787462833"));
        // https://goo.gl/maps/2Zxpi6xsmfUNzU2h7
        out.put("CLB", new Location("CLB", "1.296642317024345, 103.77322870790687"));
        // https://goo.gl/maps/TBxRtewZLVCgkEdF6
        out.put("UHC", new Location("UHC", "1.2991034341416665, 103.77639982958577"));
        // https://goo.gl/maps/bURCv14zruYzxxNV9
        out.put("LT1", new Location("LT1", "1.299463315530677, 103.77114712647649"));
        // https://goo.gl/maps/e8vnmt5FzS19NbNk8
        out.put("LT9", new Location("LT9", "1.2952456082872508, 103.77221840699619"));
        // https://goo.gl/maps/EEWy1fTuFcBkqxT97
        out.put("AS6", new Location("AS6", "1.2955038493756006, 103.77324977312634"));
        if (!out.containsKey(trimmedLocationName)) {
            throw new ParseException(Location.MESSAGE_CONSTRAINTS);
        }
        return out.get(trimmedLocationName);
    }

    /**
     * Parses a {@code String gender} into a {@code Gender}
     * @param gender gender of the user
     * @return Gender
     * @throws ParseException if the given {@code gender} is invalid
     */
    public static Gender parseGender(String gender) throws ParseException {
        requireNonNull(gender);
        String trimmedGender = gender.trim().toUpperCase();
        if (!Gender.isValidGender(trimmedGender)) {
            throw new ParseException(Gender.MESSAGE_CONSTRAINTS);
        }
        return new Gender(trimmedGender);
    }

    /**
     * Parses a {@code String height} into a {@code Height}
     * @param height height of the user
     * @return Height
     * @throws ParseException if the given {@code height} is invalid
     */
    public static Height parseHeight(String height) throws ParseException {
        requireNonNull(height);
        String trimmedHeight = height.trim();
        if (!Height.isValidHeight(trimmedHeight)) {
            throw new ParseException(Height.MESSAGE_CONSTRAINTS);
        }
        return new Height(trimmedHeight);
    }

    /**
     * Parses a {@code String weight} into a {@code Weight}
     * @param weight weight of the user
     * @return Weight
     * @throws ParseException if the given {@code weight} is invalid
     */
    public static Weight parseWeight(String weight) throws ParseException {
        requireNonNull(weight);
        String trimmedWeight = weight.trim();
        if (!Weight.isValidWeight(trimmedWeight)) {
            throw new ParseException(Weight.MESSAGE_CONSTRAINTS);
        }
        return new Weight(trimmedWeight);
    }

    /**
     * Parses a {@code String date} into a {@code DateTime}.
     * Leading and trailing whitespaces will be trimmed.
     * @param date The string representing the date to parse.
     * @return A {@code DateTime} representing the given {@code String date}.
     * @throws ParseException if the given {@code date} is not in the format: YYYY-MM-DD.
     */
    public static DateTime parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDateTime = date.trim() + DEFAULT_TIME;
        if (!DateTime.isValidDateTime(trimmedDateTime)) {
            throw new ParseException(DateTime.MESSAGE_CONSTRAINTS);
        }
        return new DateTime(trimmedDateTime);
    }

    /**
     * Parses a {@code String age} into a {@code Age}.
     * Leading and trailing whitespaces will be trimmed.
     * @param age The string representing the age to parse.
     * @return A {@code Age} representing the given {@code String age}.
     * @throws ParseException if the given {@code age} is invalid
     */
    public static Age parseAge(String age) throws ParseException {
        requireNonNull(age);
        String trimmedAge = age.trim();
        if (!Age.isValidAge(trimmedAge)) {
            throw new ParseException(Age.MESSAGE_CONSTRAINTS);
        }
        return new Age(trimmedAge);
    }
}
