package modtrekt.logic.parser.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import modtrekt.logic.parser.Prefix;

/**
 * Builder for a CommandParser object via a fluent API.
 */
public class CommandParserBuilder {
    private final List<Prefix> requiredPrefixes = new ArrayList<>();
    private final List<Prefix> optionalPrefixes = new ArrayList<>();
    private String usageMessage;

    /**
     * Adds required prefixes to the CommandParser.
     *
     * @param prefixes the prefixes to add
     * @return the CommandParserBuilder object
     */
    public CommandParserBuilder requiredPrefixes(Prefix... prefixes) {
        requiredPrefixes.addAll(Arrays.asList(prefixes));
        return this;
    }

    /**
     * Adds optional prefixes to the CommandParser.
     *
     * @param prefixes the prefixes to add
     * @return the CommandParserBuilder object
     */
    public CommandParserBuilder optionalPrefixes(Prefix... prefixes) {
        optionalPrefixes.addAll(Arrays.asList(prefixes));
        return this;
    }

    /**
     * Sets the usage message of the CommandParser.
     *
     * @param usageMessage the usage message to set
     * @return the CommandParserBuilder object
     */
    public CommandParserBuilder usageMessage(String usageMessage) {
        this.usageMessage = usageMessage;
        return this;
    }

    /**
     * Returns a CommandParser object with all prefixes specified so far.
     *
     * @return the CommandParser object
     */
    public CommandParser build() {
        return new CommandParser(
                usageMessage,
                optionalPrefixes.toArray(new Prefix[0]),
                requiredPrefixes.toArray(new Prefix[0]));
    }
}
