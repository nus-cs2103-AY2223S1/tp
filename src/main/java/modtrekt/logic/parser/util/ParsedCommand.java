package modtrekt.logic.parser.util;

import static modtrekt.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Optional;
import java.util.function.Function;

import modtrekt.logic.parser.ArgumentMultimap;
import modtrekt.logic.parser.Prefix;
import modtrekt.logic.parser.exceptions.ParseException;

/**
 * ParsedCommand is a wrapper for a command and its multi-map of prefixes to arguments.
 */
public class ParsedCommand {
    private final ArgumentMultimap argMultimap;

    public ParsedCommand(ArgumentMultimap argsMultimap) {
        this.argMultimap = argsMultimap;
    }

    // === === ===
    // Command argument handling

    /**
     * Maps an argument from the given command's argument string to a new value if it is present.
     *
     * @param prefix the prefix of the argument to parse
     * @param mapper the function to map the argument with
     * @param <T>    the type of the mapped argument
     * @return an Optional of the mapped argument
     */
    public <T> Optional<T> mapOptionalArgument(Prefix prefix, Function<String, T> mapper) {
        requireAllNonNull(prefix, mapper);
        return argMultimap.getValue(prefix)
                .map(mapper);
    }

    /**
     * Maps an argument from the given command's argument string to a new value.
     *
     * @param prefix the prefix of the argument to parse
     * @param mapper the function to map the argument with
     * @param <T>    the type of the mapped argument
     * @return the mapped argument
     * @throws ParseException if the prefix is not present in the argument string
     */
    public <T> T mapMandatoryArgument(Prefix prefix, Function<String, T> mapper) throws ParseException {
        requireAllNonNull(prefix, mapper);
        // We know that the prefix is present, so we can safely get the wrapped value.
        return mapOptionalArgument(prefix, mapper).orElseThrow(IllegalStateException::new);
    }

    /**
     * Returns the internal argument multimap as an escape hatch.
     *
     * @return the internal argument multimap
     */
    public ArgumentMultimap getArgMultimap() {
        return argMultimap;
    }
}
