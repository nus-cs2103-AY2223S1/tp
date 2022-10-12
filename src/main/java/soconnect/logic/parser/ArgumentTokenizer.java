package soconnect.logic.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Tokenizes arguments string of the form: {@code preamble <prefix>value <prefix>value ...}<br>
 *     e.g. {@code some preamble text t/ 11.00 t/12.00 k/ m/ July}  where prefixes are {@code t/ k/ m/}.<br>
 * 1. An argument's value can be an empty string e.g. the value of {@code k/} in the above example.<br>
 * 2. Leading and trailing whitespaces of an argument value will be discarded.<br>
 * 3. An argument may be repeated and all its values will be accumulated e.g. the value of {@code t/}
 *    in the above example.<br>
 */
public class ArgumentTokenizer {

    /**
     * Tokenizes an arguments string and returns an {@code ArgumentMultimap} object that maps prefixes to their
     * respective argument values. Only the given prefixes will be recognized in the arguments string.
     *
     * @param argsString Arguments string of the form: {@code preamble <prefix>value <prefix>value ...}
     * @param prefixes   Prefixes to tokenize the arguments string with.
     * @return           ArgumentMultimap object that maps prefixes to their arguments.
     */
    public static ArgumentMultimap tokenize(String argsString, Prefix... prefixes) {
        List<PrefixPosition> positions = findAllPrefixPositions(argsString, prefixes);
        return extractArguments(argsString, positions);
    }

    /**
     * Tokenizes an arguments string and returns an {@code List<PrefixArgument>} object that stores a list of
     * PrefixArguments. Each PrefixArgument contains a prefix and its argument value. Only the given prefixes will be
     * recognized in the arguments string. The PrefixArguments are in the same order as that their prefix appeared in
     * argsString.
     *
     * @param argsString Arguments string of the form: {@code preamble <prefix>value <prefix>value ...}
     * @param prefixes   Prefixes to tokenize the arguments string with.
     * @return           List of prefixes and their respective argument values.
     */
    public static List<PrefixArgument> tokenizeToList(String argsString, Prefix... prefixes) {
        List<PrefixPosition> positions = findAllPrefixPositions(argsString, prefixes);
        return extractArgumentsToList(argsString, positions);
    }

    /**
     * Finds all zero-based prefix positions in the given arguments string.
     *
     * @param argsString Arguments string of the form: {@code preamble <prefix>value <prefix>value ...}
     * @param prefixes   Prefixes to find in the arguments string.
     * @return           List of zero-based prefix positions in the given arguments string.
     */
    private static List<PrefixPosition> findAllPrefixPositions(String argsString, Prefix... prefixes) {
        return Arrays.stream(prefixes)
                .flatMap(prefix -> findPrefixPositions(argsString, prefix).stream())
                .collect(Collectors.toList());
    }

    /**
     * {@see findAllPrefixPositions}
     */
    private static List<PrefixPosition> findPrefixPositions(String argsString, Prefix prefix) {
        List<PrefixPosition> positions = new ArrayList<>();

        int prefixPosition = findPrefixPosition(argsString, prefix.getPrefix(), 0);
        while (prefixPosition != -1) {
            PrefixPosition extendedPrefix = new PrefixPosition(prefix, prefixPosition);
            positions.add(extendedPrefix);
            prefixPosition = findPrefixPosition(argsString, prefix.getPrefix(), prefixPosition);
        }

        return positions;
    }

    /**
     * Returns the index of the first occurrence of {@code prefix} in
     * {@code argsString} starting from index {@code fromIndex}. An occurrence
     * is valid if there is a whitespace before {@code prefix}. Returns -1 if no
     * such occurrence can be found.
     *
     * E.g. if {@code argsString} = "e/hip/900", {@code prefix} = "p/" and
     * {@code fromIndex} = 0, this method returns -1 as there are no valid
     * occurrences of "p/" with whitespace before it. However, if
     * {@code argsString} = "e/hi p/900", {@code prefix} = "p/" and
     * {@code fromIndex} = 0, this method returns 5.
     */
    private static int findPrefixPosition(String argsString, String prefix, int fromIndex) {
        int prefixIndex = argsString.indexOf(" " + prefix, fromIndex);
        return prefixIndex == -1 ? -1
                : prefixIndex + 1; // +1 as offset for whitespace
    }

    /**
     * Extracts prefixes and their argument values, and returns an {@code ArgumentMultimap} object that maps the
     * extracted prefixes to their respective arguments. Prefixes are extracted based on their zero-based positions in
     * {@code argsString}.
     *
     * @param argsString      Arguments string of the form: {@code preamble <prefix>value <prefix>value ...}
     * @param prefixPositions Zero-based positions of all prefixes in {@code argsString}.
     * @return                ArgumentMultimap object that maps prefixes to their arguments.
     */
    private static ArgumentMultimap extractArguments(String argsString, List<PrefixPosition> prefixPositions) {
        sortPrefixPositions(argsString.length(), prefixPositions);

        // Store prefixes and their argument values (if any)
        ArgumentMultimap argMultimap = new ArgumentMultimap();
        for (int i = 0; i < prefixPositions.size() - 1; i++) {
            // Extract and store prefixes and their arguments
            Prefix argPrefix = prefixPositions.get(i).getPrefix();
            String argValue = extractArgumentValue(argsString, prefixPositions.get(i), prefixPositions.get(i + 1));
            argMultimap.put(argPrefix, argValue);
        }

        return argMultimap;
    }

    /**
     * Extracts prefixes and their argument values, converts them to {@code PrefixArgument}, and returns a {@code List}
     * of {@code PrefixArgument}. Prefixes are extracted based on their zero-based positions in {@code argsString}.
     *
     * @param argsString      Arguments string of the form: {@code preamble <prefix>value <prefix>value ...}
     * @param prefixPositions Zero-based positions of all prefixes in {@code argsString}
     * @return                List of PrefixArgument, in the order that the prefixes appeared in {@code argsString}
     */
    private static List<PrefixArgument> extractArgumentsToList(String argsString,
                                                               List<PrefixPosition> prefixPositions) {
        sortPrefixPositions(argsString.length(), prefixPositions);

        // Map prefixes to their argument values (if any)
        List<PrefixArgument> argList = new ArrayList<>();
        for (int i = 0; i < prefixPositions.size() - 1; i++) {
            // Extract and store prefixes and their arguments
            Prefix argPrefix = prefixPositions.get(i).getPrefix();
            String argValue = extractArgumentValue(argsString, prefixPositions.get(i), prefixPositions.get(i + 1));
            argList.add(new PrefixArgument(argPrefix, argValue));
        }

        return argList;
    }

    /**
     * Sorts a list of PrefixPositions by their positions.
     * Inserts dummy PrefixPositions at the start and end of the list to denote the boundaries of the argument string.
     *
     * @param len             The length of the argument string.
     * @param prefixPositions The list of PrefixPositions to sort.
     */
    private static void sortPrefixPositions(int len, List<PrefixPosition> prefixPositions) {
        // Sort by start position
        prefixPositions.sort(Comparator.comparingInt(PrefixPosition::getStartPosition));

        // Insert a PrefixPosition to represent the preamble
        PrefixPosition preambleMarker = new PrefixPosition(new Prefix(""), 0);
        prefixPositions.add(0, preambleMarker);

        // Add a dummy PrefixPosition to represent the end of the string
        PrefixPosition endPositionMarker = new PrefixPosition(new Prefix(""), len);
        prefixPositions.add(endPositionMarker);
    }

    /**
     * Returns the trimmed value of the argument in the arguments string specified by {@code currentPrefixPosition}.
     * The end position of the value is determined by {@code nextPrefixPosition}.
     */
    private static String extractArgumentValue(String argsString,
                                        PrefixPosition currentPrefixPosition,
                                        PrefixPosition nextPrefixPosition) {
        Prefix prefix = currentPrefixPosition.getPrefix();

        int valueStartPos = currentPrefixPosition.getStartPosition() + prefix.getPrefix().length();
        String value = argsString.substring(valueStartPos, nextPrefixPosition.getStartPosition());

        return value.trim();
    }

    /**
     * Represents a prefix's position in an arguments string.
     */
    private static class PrefixPosition {
        private final int startPosition;
        private final Prefix prefix;

        PrefixPosition(Prefix prefix, int startPosition) {
            this.prefix = prefix;
            this.startPosition = startPosition;
        }

        int getStartPosition() {
            return startPosition;
        }

        Prefix getPrefix() {
            return prefix;
        }
    }

    /**
     * Represents a prefix and its argument value.
     */
    public static class PrefixArgument {
        private final Prefix prefix;
        private final String argument;

        PrefixArgument(Prefix prefix, String argument) {
            this.prefix = prefix;
            this.argument = argument;
        }

        public String getArgument() {
            return argument;
        }

        public Prefix getPrefix() {
            return prefix;
        }
    }

}
