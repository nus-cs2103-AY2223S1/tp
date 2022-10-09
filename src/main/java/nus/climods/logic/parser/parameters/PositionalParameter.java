package nus.climods.logic.parser.parameters;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * Represents a positional parameter yielding a value of type T.
 * @param <T> The expected type from the parameter
 */
public class PositionalParameter<T> {
    // Index of the parameter in delimiter-separated list of arguments
    protected final int index;
    protected final List<String> arguments;

    // Return non-empty Optional if valid T, else empty Optional
    protected Function<String, Optional<T>> conversionFunction;
    protected Optional<T> argValue;

    /**
     * Creates a PositionalParameter.
     * @param index Expected index for the parameter (>= 0)
     * @param arguments A list of argument strings representing all arguments, without command name
     * @param conversionFunction A function to convert the argument string into an Optional of type T
     */
    public PositionalParameter(int index, List<String> arguments, Function<String, Optional<T>> conversionFunction) {
        assert index >= 0;
        Objects.requireNonNull(arguments);
        Objects.requireNonNull(conversionFunction);

        this.arguments = arguments;
        this.index = index;
        this.conversionFunction = conversionFunction;
    }

    /**
     * Attempts to extract the needed argument.
     * @return Optional representing the value required. Optional is empty if invalid index, or invalid argument value
     *      according to provided conversion function
     */
    protected Optional<T> parse() {
        if (index >= arguments.size()) {
            argValue = Optional.empty();
            return argValue;
        }
        argValue = conversionFunction.apply(arguments.get(index));
        return argValue;
    }

    /**
     * Returns an Optional of the argument value which is empty if argument was invalid
     * @return Optional of the argument value which is empty if argument was invalid
     */
    public Optional<T> getArgValue() {
        // Null check to know if parse was already called
        if (argValue == null) {
            return parse();
        }
        return argValue;
    }
}
