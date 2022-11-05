package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import com.mdimension.jchronic.Chronic;
import com.mdimension.jchronic.Options;
import com.mdimension.jchronic.tags.Pointer;
import com.mdimension.jchronic.utils.Span;

import seedu.address.logic.parser.exceptions.ParseException;

//@@author parnikkapore-reused
// Taken from Parnikkapore's ip. Note for graders: The test code is new

/**
 * A simple JChronic-based date + time parser.
 */
public class NaturalDateParser {
    /**
     * Parses the given string, assumed to be in the local time zone, into a LocalDateTime.
     *
     * @param input The string to be parsed.
     * @return The parsed string.
     * @throws DateTimeNotFoundException if neither a date nor a time can be resolved.
     */
    public static LocalDateTime parse(String input) throws DateTimeNotFoundException, ParseFailureException {
        requireNonNull(input);

        Options options = new Options();
        options.setContext(Pointer.PointerType.FUTURE);
        options.setGuess(false);

        Span parsedDate;
        try {
            parsedDate = Chronic.parse(input, options);
        } catch (RuntimeException e) {
            throw new ParseFailureException(input, e);
        }

        if (parsedDate == null) {
            throw new DateTimeNotFoundException(input);
        }

        // Chronic's right-hand bound seems to be the first second after the range, so subtract 1 to get the
        // last second in the range
        Instant result = parsedDate.getEndCalendar().toInstant().minusSeconds(1);
        return LocalDateTime.ofInstant(result, ZoneId.systemDefault());
    }

    /**
     * Neither a date nor a time can be found in the given string.
     */
    public static class DateTimeNotFoundException extends ParseException {
        private final String parsedString;

        private DateTimeNotFoundException(String parsedString) {
            super("Cannot find a date in " + parsedString);
            this.parsedString = parsedString;
        }

        public String getParsedString() {
            return parsedString;
        }
    }

    /**
     * Represents the situation that parsing, for some reason, failed.
     */
    public static class ParseFailureException extends ParseException {
        private final String parsedString;

        private ParseFailureException(String parsedString, Throwable cause) {
            super("Failed to parse " + parsedString, cause);
            this.parsedString = parsedString;
        }

        public String getParsedString() {
            return parsedString;
        }
    }

    /**
     * For testing purposes only.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        try {
            LocalDateTime test = parse("2 Jan 2006 15:04:05");
            System.out.println(test);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
