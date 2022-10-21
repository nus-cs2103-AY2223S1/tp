package modtrekt.logic.parser.util;

import static java.util.Objects.requireNonNull;
import static modtrekt.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static modtrekt.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import modtrekt.commons.util.StringUtil;
import modtrekt.logic.parser.ArgumentMultimap;
import modtrekt.logic.parser.ArgumentTokenizer;
import modtrekt.logic.parser.Prefix;
import modtrekt.logic.parser.exceptions.ParseException;

/**
 * CommandParser provides an alternative pathway for parsing commands.
 * <br>
 * Unlike ArgumentTokenizer, CommandParser allows validation of required prefixes, as well as
 * specifying optional prefixes and expected usage message with a fluent API.
 * Additionally, CommandParser also exposes its utility methods used to validate the arguments of a command.
 * <br>
 * The usage message is printed whenever a required prefix is missing via the ParseException that is thrown,
 * along with a list of the missing prefixes.
 * <br>
 * Use CommandParserBuilder to create a CommandParser with a fluent API.
 */
public class CommandParser {
    private final String usageMessage;
    private final Prefix[] requiredPrefixes;
    private final Prefix[] optionalPrefixes;

    /**
     * Constructs a CommandParser object.
     *
     * @param optionalPrefixes the array of optional prefixes
     * @param requiredPrefixes the array of required prefixes
     */
    CommandParser(String usageMessage, Prefix[] optionalPrefixes, Prefix[] requiredPrefixes) {
        this.usageMessage = usageMessage;
        this.optionalPrefixes = optionalPrefixes;
        this.requiredPrefixes = requiredPrefixes;
    }

    /**
     * Validates a command's argument string, asserting that the specified prefixes are present.
     *
     * @param args              the argument string to validate
     * @param prefixes          the prefixes to check for
     * @param additionalMessage the additional message after the list of missing prefixes in the exception message
     * @throws ParseException if the argument string has any missing prefixes
     */
    public static void requirePrefixes(String args, String additionalMessage, Prefix... prefixes)
            throws ParseException {
        requireAllNonNull(args, prefixes);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, prefixes);
        List<Prefix> missingPrefixes = Arrays.stream(prefixes)
                .filter(prefix -> argMultimap.getValue(prefix)
                        .isEmpty())
                .collect(Collectors.toList());
        if (missingPrefixes.isEmpty()) {
            return;
        }
        String message = String.format(
                "Missing %s: %s.%s",
                StringUtil.pluralize(missingPrefixes.size(), "prefix", "prefixes"),
                missingPrefixes.stream()
                        .map(Prefix::getPrefix)
                        .collect(Collectors.joining(", ")),
                additionalMessage == null ? "" : "\n" + additionalMessage
        );
        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, message));
    }

    /**
     * Validates a command's argument string, asserting that the specified prefixes are present,
     * without any additional message in the exception thrown.
     *
     * @param args     the argument string to validate
     * @param prefixes the prefixes to check for
     * @throws ParseException if the argument string has any missing prefixes
     */
    public static void requirePrefixes(String args, Prefix... prefixes) throws ParseException {
        requirePrefixes(args, null, prefixes);
    }

    /**
     * Returns a ParsedCommand object from which the arguments of this command get be retrieved, consumed, or mapped.
     * Checks that all required prefixes are present.
     *
     * @param args the argument string to parse
     * @return a ParsedCommand object
     */
    public ParsedCommand parse(String args) throws ParseException {
        requireNonNull(args);
        requirePrefixes(args, usageMessage, requiredPrefixes);
        Prefix[] allPrefixes = new Prefix[optionalPrefixes.length + requiredPrefixes.length];
        System.arraycopy(optionalPrefixes, 0, allPrefixes, 0, optionalPrefixes.length);
        System.arraycopy(requiredPrefixes, 0, allPrefixes, optionalPrefixes.length, requiredPrefixes.length);
        return new ParsedCommand(ArgumentTokenizer.tokenize(args, allPrefixes));
    }
}
