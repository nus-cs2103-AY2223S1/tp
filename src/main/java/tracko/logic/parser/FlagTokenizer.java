package tracko.logic.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Tokenizes arguments string of the form: {@code preamble <flag> <flag> ...}<br>
 *     e.g. {@code some preamble text -p -d}  where flags are {@code -p -d}.<br>
 * 1. Any input following the flag will be ignored.<br>
 * 2. If a flag exists, its corresponding argument value will be tokenized to the boolean {@code true}.<br>
 */
public class FlagTokenizer extends ArgumentToken {
    /**
     * Tokenizes an arguments string and returns an {@code ArgumentMultimap} object that maps flags to their
     * respective argument values. Only the given flags will be recognized in the arguments string.
     *
     * @param argsString Arguments string of the form: {@code preamble <flag> <flag> ...}
     * @param flags   Flags to tokenize the boolean status of arguments
     * @return           ArgumentMultimap object that maps flags to their arguments
     */
    public static ArgumentMultimap tokenize(String argsString, Flag... flags) {
        List<FlagPosition> positions = findAllFlagPositions(argsString, flags);
        return extractArguments(argsString, positions);
    }

    /**
     * Finds all zero-based flag positions in the given arguments string.
     *
     * @param argsString Arguments string of the form: {@code preamble <flag> <flag> ...}
     * @param flags   Flags to find in the arguments string
     * @return           List of zero-based flag positions in the given arguments string
     */
    private static List<FlagPosition> findAllFlagPositions(String argsString, Flag... flags) {
        return Arrays.stream(flags)
                .flatMap(flag -> findFlagPositions(argsString, flag).stream())
                .collect(Collectors.toList());
    }

    /**
     * {@see findAllFlagPositions}
     */
    private static List<FlagPosition> findFlagPositions(String argsString, Flag flag) {
        List<FlagPosition> positions = new ArrayList<>();

        int flagPosition = findFlagPosition(argsString, flag.getFlag(), 0);
        while (flagPosition != -1) {
            FlagPosition extendedFlag = new FlagPosition(flag, flagPosition);
            positions.add(extendedFlag);
            flagPosition = findFlagPosition(argsString, flag.getFlag(), flagPosition);
        }

        return positions;
    }

    /**
     * Returns the index of the first occurrence of {@code flag} in
     * {@code argsString} starting from index {@code fromIndex}. An occurrence
     * is valid if there is a whitespace before {@code flag} and nothing after. Returns -1 if no
     * such occurrence can be found.
     *
     * E.g if {@code argsString} = "e/hi-p900", {@code flag} = "-p" and
     * {@code fromIndex} = 0, this method returns -1 as there are no valid
     * occurrences of "-p" with whitespace before it and nothing after it. However, if
     * {@code argsString} = "e/hi -p", {@code flag} = "-p" and
     * {@code fromIndex} = 0, this method returns 5.
     */
    private static int findFlagPosition(String argsString, String flag, int fromIndex) {
        String leftPadArgsString = argsString + " ";
        int flagIndex = leftPadArgsString.indexOf(" " + flag + " ", fromIndex);
        return flagIndex == -1 ? -1
                : flagIndex + 1; // +1 as offset for whitespace
    }

    /**
     * Extracts flags, and returns an {@code ArgumentMultimap} object that maps the
     * extracted flags to "true" as arguments. Flags are extracted based on their zero-based positions in
     * {@code argsString}.
     *
     * @param argsString      Arguments string of the form: {@code preamble <flag> <flag> ...}
     * @param flagPositions Zero-based positions of all flags in {@code argsString}
     * @return                ArgumentMultimap object that maps flags to "true" as arguments
     */
    private static ArgumentMultimap extractArguments(String argsString, List<FlagPosition> flagPositions) {

        // Sort by start position
        flagPositions.sort((flag1, flag2) -> flag1.getStartPosition() - flag2.getStartPosition());

        // Insert a FlagPosition to represent the preamble
        FlagPosition preambleMarker = new FlagPosition(new Flag(""), 0);
        flagPositions.add(0, preambleMarker);

        // Add a dummy FlagPosition to represent the end of the string
        FlagPosition endPositionMarker = new FlagPosition(new Flag(""), argsString.length());
        flagPositions.add(endPositionMarker);

        // Map flags to argument values "true" (if any)
        ArgumentMultimap argMultimap = new ArgumentMultimap();
        for (int i = 0; i < flagPositions.size() - 1; i++) {
            // Extract and store flags and their arguments "true"
            Flag argFlag = flagPositions.get(i).getFlag();
            String argValue = extractArgumentValue(argsString, flagPositions.get(i), flagPositions.get(i + 1));
            argMultimap.put(argFlag, argValue);
        }

        return argMultimap;
    }

    /**
     * Returns "true" as argument value as specified by {@code currentFlagPosition}.
     * The end position of the value is determined by {@code nextFlagPosition}.
     */
    private static String extractArgumentValue(String argsString,
                                               FlagPosition currentFlagPosition,
                                               FlagPosition nextFlagPosition) {
        Flag flag = currentFlagPosition.getFlag();

        int valueStartPos = currentFlagPosition.getStartPosition() + flag.getFlag().length();
        //value = preamble string when valueStartPos == 0
        String value = argsString.substring(valueStartPos, nextFlagPosition.getStartPosition());

        //value = true when valueStartPos > 0 since the presence of a flag indicates that the input argument is true
        if (valueStartPos > 0) {
            value = "true";
        }

        return value.trim();
    }

    /**
     * Represents a flag's position in an arguments string.
     */
    private static class FlagPosition {
        private int startPosition;
        private final Flag flag;

        FlagPosition(Flag flag, int startPosition) {
            this.flag = flag;
            this.startPosition = startPosition;
        }

        int getStartPosition() {
            return startPosition;
        }

        Flag getFlag() {
            return flag;
        }
    }
}
