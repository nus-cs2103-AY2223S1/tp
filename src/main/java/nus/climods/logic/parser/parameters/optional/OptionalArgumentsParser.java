package nus.climods.logic.parser.parameters.optional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

import nus.climods.logic.parser.ParserUtil;
import nus.climods.logic.parser.exceptions.ParseException;

/**
 * Parses multiple optional arguments and provides an interface to check presence of the arguments
 */
public class OptionalArgumentsParser {
    private List<OptionalArgument> argList = new ArrayList<>();
    private Options options = new Options();
    private CommandLine commandLine;

    /**
     * Creates an OptionalArgumentsParser
     * @param argumentsString String to parse
     * @param optionalArguments Variadic list of optional arguments to check for
     * @throws ParseException if any issues occurred during parse
     */
    public OptionalArgumentsParser(String argumentsString, OptionalArgument...optionalArguments) throws ParseException {
        argList.addAll(Arrays.asList(optionalArguments));
        for (OptionalArgument opArg: optionalArguments) {
            options.addOption(opArg.getOption());
        }

        CommandLineParser clp = new DefaultParser();
        String[] argsArray = ParserUtil
                .convertArgumentStringToList(argumentsString)
                .toArray(new String[] {});

        try {
            commandLine = clp.parse(options, argsArray);
        } catch (org.apache.commons.cli.ParseException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

    /**
     * Returns true if the given optional argument was found in the parsed String, otherwise false
     * @param option Optional argument to check for presence of
     * @return boolean indicating presence of requested optional argument
     */
    public boolean hasOption(OptionalArgument option) {
        return commandLine.hasOption(option.getOption());
    }


    /**
     * Returns true if the given {@code OptionalArgument} was found in the parsed String, otherwise false.
     * Supplied optionString can be either the short option or long option name.
     * @param optionString Name of optional argument to check for presence of.
     * @return boolean indicating presence of requested optional argument
     */
    public boolean hasOption(String optionString) {
        return commandLine.hasOption(optionString);
    }
}
