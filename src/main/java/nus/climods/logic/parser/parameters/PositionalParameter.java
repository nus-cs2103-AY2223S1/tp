package nus.climods.logic.parser.parameters;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import nus.climods.logic.parser.exceptions.ParseException;



/**
 * Represents a positional parameter yielding a value of type T.
 * @param <T> The expected type from the parameter
 */
public class PositionalParameter<T> {
    // ParseException message to show when invalid argument provided
    protected String parseExceptionMessage;
    // Index of the parameter in delimiter-separated list of arguments
    protected final int index;
    protected final List<String> arguments;

    // Return non-empty Optional if valid T, else empty Optional
    protected Function<String, Optional<T>> conversionFunction;
    protected Optional<T> optionalArg;

    /**
     * Creates a PositionalParameter.
     * @param index Expected index for the parameter (>= 0)
     * @param arguments A list of argument strings representing all arguments, without command name
     * @param conversionFunction A function to convert the argument string into an Optional of type T
     */
    public PositionalParameter(int index, List<String> arguments, Function<String, Optional<T>> conversionFunction,
                               String parseExceptionMessage) {
        assert index >= 0;
        Objects.requireNonNull(arguments);
        Objects.requireNonNull(conversionFunction);

        this.arguments = arguments;
        this.index = index;
        this.conversionFunction = conversionFunction;
        this.parseExceptionMessage = parseExceptionMessage;
    }

    /**
     * Returns argument value parsed according to index and conversion function
     * @return Return argument value
     * @throws ParseException if the input for this parameter was invalid or doesn't exist
     */
    public T getArgValue() throws ParseException {
        if (index >= arguments.size()) {
            throw new ParseException(parseExceptionMessage);
        }
        optionalArg = conversionFunction.apply(arguments.get(index));

        if (optionalArg.isEmpty()) {
            throw new ParseException(parseExceptionMessage);
        }
        return optionalArg.get();
    }
}
