package seedu.address.commons.util;

import static java.lang.Integer.parseInt;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Helper functions for handling strings.
 */
public class StringUtil {

    /**
     * Returns true if the {@code sentence} contains the {@code word}.
     *   Ignores case, but a full word match is required.
     *   <br>examples:<pre>
     *       containsWordIgnoreCase("ABc def", "abc") == true
     *       containsWordIgnoreCase("ABc def", "DEF") == true
     *       containsWordIgnoreCase("ABc def", "AB") == false //not a full word match
     *       </pre>
     * @param sentence cannot be null
     * @param word cannot be null, cannot be empty, must be a single word
     */
    public static boolean containsWordIgnoreCase(String sentence, String word) {
        requireNonNull(sentence);
        requireNonNull(word);

        String preppedWord = word.trim();
        checkArgument(!preppedWord.isEmpty(), "Word parameter cannot be empty");
        checkArgument(preppedWord.split("\\s+").length == 1, "Word parameter should be a single word");

        String preppedSentence = sentence;
        String[] wordsInPreppedSentence = preppedSentence.split("\\s+");

        return Arrays.stream(wordsInPreppedSentence)
                .anyMatch(preppedWord::equalsIgnoreCase);
    }

    /**
     * Returns a detailed message of the t, including the stack trace.
     */
    public static String getDetails(Throwable t) {
        requireNonNull(t);
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return t.getMessage() + "\n" + sw.toString();
    }

    /**
     * Returns true if {@code s} represents a non-zero unsigned integer
     * e.g. 1, 2, 3, ..., {@code Integer.MAX_VALUE} <br>
     * Will return false for any other non-null string input
     * e.g. empty string, "-1", "0", "+1", and " 2 " (untrimmed), "3 0" (contains whitespace), "1 a" (contains letters)
     * @throws NullPointerException if {@code s} is null.
     */
    public static boolean isNonZeroUnsignedInteger(String s) {
        requireNonNull(s);

        try {
            int value = parseInt(s);
            return value > 0 && !s.startsWith("+"); // "+1" is successfully parsed by Integer#parseInt(String)
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Returns a string removing consecutive duplicate whitespaces.
     */
    public static String removeDuplicateWhitespace(String str) {
        requireNonNull(str);

        Pattern pattern = Pattern.compile("\\s+");
        Matcher matcher = pattern.matcher(str);
        return matcher.replaceAll(" ");
    }

    /**
     * Returns a string removing consecutive duplicate commas.
     */
    public static String removeDuplicateComma(String str) {
        requireNonNull(str);

        Pattern pattern = Pattern.compile(",+");
        Matcher matcher = pattern.matcher(str);
        return matcher.replaceAll(",");
    }

    /**
     * Returns a string capitalising every first letter of each word.
     */
    public static String capitaliseOnlyFirstLetter(String str) {
        requireNonNull(str);

        str = str.toLowerCase();
        Pattern pattern = Pattern.compile("\\s\\p{Alpha}");
        Matcher matcher = pattern.matcher(str);
        str = matcher.replaceAll(matchResult -> matchResult.group().toUpperCase());
        pattern = Pattern.compile("\\p{Alpha}");
        matcher = pattern.matcher(str);
        return matcher.replaceFirst(matchResult -> matchResult.group().toUpperCase());
    }

    //mainly for Level parsing here
    /**
     * Returns a string after removing whitespace Primary/Secondary and digit.
     */
    public static String removeWhitespaceForLevel(String level) {
        requireNonNull(level);

        Pattern pattern = Pattern.compile("(?i)primary\\s*|secondary\\s*");
        Matcher matcher = pattern.matcher(level);
        return matcher.replaceAll(matchResult -> matchResult.group().contains("primary") ? "primary" : "secondary");
    }

    /**
     * Returns a string converting short form academic levels to full form.
     */
    public static String convertShortFormLevel(String level) {
        requireNonNull(level);

        //allow p, pri, s, sec
        Pattern pattern = Pattern.compile("(?i)(pri|sec|p|s)\\s*\\d");
        Matcher matcher = pattern.matcher(level);
        return matcher.replaceAll(matchResult1 -> {
            Pattern p = Pattern.compile("(?i)(pri|sec|p|s)\\s*");
            Matcher m = p.matcher(matchResult1.group());
            return m.replaceAll(matchResult2 ->
                    matchResult2.group().toLowerCase().contains("p") ? "primary" : "secondary");
        });
    }

    //for Time parsing
    /**
     * Returns a string converting plausible time formats to format accepted by LocalTime parser.
     * @throws ParseException if invalid hours provided for 12-hour format.
     */
    public static String formatTime(String time) throws ParseException {
        requireNonNull(time);

        //add 0 padding to make up 2 digit hour
        if (time.matches("\\p{Digit}{1,2}(am|pm)")) {
            if (time.contains("am")) {
                int hours = parseInt(time.substring(0, time.length() - 2));
                if (hours > 12 || hours == 0) {
                    throw new ParseException("invalid time");
                }
                if (hours == 12) {
                    return "00:00";
                } else {
                    time = hours + ":00";
                    return time.length() < 5
                            ? "0" + time
                            : time;
                }
            } else if (time.contains("pm")) {
                int hours = parseInt(time.substring(0, time.length() - 2));
                if (hours > 12 || hours == 0) {
                    throw new ParseException("invalid time");
                }
                if (hours != 12) {
                    hours += 12;
                }
                return hours + ":00";
            }
        } else if (time.matches("\\p{Digit}{1,2}:{0,1}\\p{Digit}{2}(am|pm){0,1}")) {
            if (time.matches("\\p{Digit}{1,2}:{0,1}\\p{Digit}{2}")) {
                if (time.contains(":")) {

                    if (time.equals("24:00")) { //because a PE-D student argued that not accepting 24:00 is a bug lol
                        time = "00:00";
                    }

                    return time.length() < 5
                            ? "0" + time
                            : time;
                } else {

                    if (time.equals("2400")) { //because a PE-D student argued that not accepting 24:00 is a bug lol
                        time = "0000";
                    }

                    return time.length() < 4
                            ? "0" + time.substring(0, 1) + ":" + time.substring(1)
                            : time.substring(0, 2) + ":" + time.substring(2);
                }
            } else if (time.matches("\\p{Digit}{1,2}:{0,1}\\p{Digit}{2}(am|pm)")) {
                if (time.contains("am")) {
                    if (time.contains(":")) {
                        int hours = parseInt(time.substring(0, time.length() - 5));
                        if (hours > 12 || hours == 0) {
                            throw new ParseException("invalid time");
                        }
                        if (hours == 12) {
                            time = "0" + time.substring(time.length() - 5);
                        }
                        return time.length() < 7
                                ? "0" + time.substring(0, time.length() - 2)
                                : time.substring(0, time.length() - 2);
                    } else {
                        int hours = parseInt(time.substring(0, time.length() - 4));
                        if (hours > 12 || hours == 0) {
                            throw new ParseException("invalid time");
                        }
                        if (hours == 12) {
                            time = "0" + time.substring(time.length() - 4);
                        }
                        return time.length() < 6
                                ? "0" + time.substring(0, 1) + ":" + time.substring(1, time.length() - 2)
                                : time.substring(0, 2) + ":" + time.substring(2, time.length() - 2);
                    }
                } else if (time.contains("pm")) {
                    if (time.contains(":")) {
                        int hours = parseInt(time.substring(0, time.length() - 5));
                        if (hours > 12 || hours == 0) {
                            throw new ParseException("invalid time");
                        }
                        if (hours != 12) {
                            hours += 12;
                            time = hours + time.substring(time.length() - 5);
                        }
                        return time.substring(0, time.length() - 2);

                    } else {
                        int hours = parseInt(time.substring(0, time.length() - 4));
                        if (hours > 12 || hours == 0) {
                            throw new ParseException("invalid time");
                        }
                        if (hours != 12) {
                            hours += 12;
                            time = hours + time.substring(time.length() - 4);
                        }
                        return time.substring(0, 2) + ":" + time.substring(2, time.length() - 2);
                    }
                }
            }
        } else {
            throw new ParseException("invalid format");
        }
        assert false;
        return "";
    }
}
