package gim.logic.parser;

import static gim.commons.core.Messages.MESSAGE_INCORRECT_INDEX_FORMAT;
import static gim.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static gim.commons.core.Messages.MESSAGE_INVALID_EXERCISE_DISPLAYED_INDEX;
import static gim.commons.core.Messages.MESSAGE_INVALID_LEVEL;
import static gim.commons.core.Messages.MESSAGE_MISSING_LEVEL;
import static gim.logic.parser.CliSyntax.PREFIX_LEVEL;
import static gim.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Stream;

import gim.commons.core.index.Index;
import gim.commons.exceptions.IllegalValueException;
import gim.logic.commands.GenerateCommand;
import gim.logic.generators.ValidLevel;
import gim.logic.parser.exceptions.ParseException;
import gim.model.exercise.Name;


/**
 * Parses input arguments and creates a new {@code GenerateCommand} object
 */
public class GenerateCommandParser implements Parser<GenerateCommand> {

    static final String INDEX_VALIDATION_REGEX = "^ *\\d+ *(?:, *\\d+ *)*$";



    /**
     * Parses the given {@code String} of arguments in the context of the {@code RemarkCommand}
     * and returns a {@code RemarkCommand} object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public GenerateCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GenerateCommand.MESSAGE_USAGE));
        }
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_LEVEL);
        // parse names
        if (arePrefixesPresent(argMultimap, PREFIX_NAME)) {
            return getGenerateCommandWithNames(argMultimap);
        }
        // parse indices
        return getGenerateCommandWithIndices(argMultimap);
    }

    // return a GenerateCommand with a list of indices
    private GenerateCommand getGenerateCommandWithIndices(ArgumentMultimap argMultimap) throws ParseException {
        String indicesAsString = argMultimap.getPreamble();
        String level = argMultimap.getValue(PREFIX_LEVEL).orElse("");
        ArrayList<Index> indices = getIndices(indicesAsString);
        ValidLevel validLevel = validateLevel(level);
        return new GenerateCommand(indices, validLevel);
    }

    // return a GenerateCommand with a set of names
    private GenerateCommand getGenerateCommandWithNames(ArgumentMultimap argMultimap) throws ParseException {
        Set<Name> nameSet;
        try {
            nameSet = ParserUtil.parseNames(argMultimap.getAllValues(PREFIX_NAME));
        } catch (ParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, GenerateCommand.MESSAGE_USAGE));
        }
        String level = argMultimap.getValue(PREFIX_LEVEL).orElse("");
        ValidLevel validLevel = validateLevel(level);
        return new GenerateCommand(nameSet, validLevel);
    }

    // validate and return ArrayList of indices if all indices are valid
    private ArrayList<Index> getIndices(String indicesAsString) throws ParseException {
        if (!indicesAsString.matches(INDEX_VALIDATION_REGEX)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    GenerateCommand.MESSAGE_USAGE + "\n\n" + MESSAGE_INCORRECT_INDEX_FORMAT));
        }
        try {
            String[] indicesArr = indicesAsString.split(",");
            return parseIndices(indicesArr);
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    GenerateCommand.MESSAGE_USAGE + "\n\n" + MESSAGE_INVALID_EXERCISE_DISPLAYED_INDEX), ive);
        }
    }

    // parses array of Strings into array of Indexes
    private ArrayList<Index> parseIndices(String[] indicesArr) throws ParseException {
        ArrayList<Index> indices = new ArrayList<>();
        for (String indexString : indicesArr) {
            Index index = ParserUtil.parseIndex(indexString);
            indices.add(index);
        }
        return indices;
    }

    // validate against ValidLevels and return the level if valid
    private ValidLevel validateLevel(String level) throws ParseException {
        if (level.isBlank()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    GenerateCommand.MESSAGE_USAGE + "\n\n" + MESSAGE_MISSING_LEVEL));
        }
        for (ValidLevel validLevel : ValidLevel.values()) {
            if (validLevel.name().equalsIgnoreCase(level)) {
                return validLevel;
            }
        }
        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                GenerateCommand.MESSAGE_USAGE + "\n\n" + MESSAGE_INVALID_LEVEL));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

