package nus.climods.logic.parser.parameters;

import java.util.Optional;
import java.util.function.Function;

/**
 * Represents a positional parameter yielding a value of type T.
 *
 * @param <T> The expected type from the parameter
 */
public class OptionalPositionalParameter<T> extends PositionalParameter {
    /**
     * Creates an OptionalPositionalParameter given argumentsString
     *
     * @param index                 Expected index for the parameter (>= 0)
     * @param argumentsString       A string of all arguments, without command name
     * @param conversionFunction    A function to convert the argument string into an Optional of type T
     * @param parseExceptionMessage ParseException message to show upon unsuccessful parse
     */
    public OptionalPositionalParameter(int index, String argumentsString, Function<String,
            Optional<T>> conversionFunction, String parseExceptionMessage) {
        super(index, argumentsString, conversionFunction, parseExceptionMessage);
    }

    /**
     * Get Optional argument that is either empty or has the relevant arg value
     */
    public Optional<T> getOptionalArgValue() {
        if (index >= arguments.size()) {
            return Optional.empty();
        }

        optionalArg = (Optional) conversionFunction.apply(arguments.get(index));
        return optionalArg;
    }
}

