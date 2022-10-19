package gim.logic.parser;

import static gim.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static gim.logic.parser.CliSyntax.PREFIX_LEVEL;


import java.util.ArrayList;

import gim.commons.core.index.Index;
import gim.commons.exceptions.IllegalValueException;
import gim.logic.commands.GenerateCommand;
import gim.logic.parser.exceptions.ParseException;



/**
 * Parses input arguments and creates a new {@code GenerateCommand} object
 */
public class GenerateCommandParser implements Parser<GenerateCommand> {

    static final String INDEX_VALIDATION_REGEX = "[0-9]+(,[0-9]+)*";

    enum ValidLevel {
        EASY, MEDIUM, HARD
    }

    /**
     * Parses the given {@code String} of arguments in the context of the {@code RemarkCommand}
     * and returns a {@code RemarkCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public GenerateCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GenerateCommand.MESSAGE_USAGE));
        }
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_LEVEL);
        String indicesAsString = argMultimap.getPreamble();
        String level = argMultimap.getValue(PREFIX_LEVEL).orElse("");
        ArrayList<Index> indices = validateIndices(indicesAsString);
        String validLevel = validateLevel(level).toString();
        return new GenerateCommand(indices, validLevel);
    }

    // validate and return ArrayList of indices if all indices are valid
    private ArrayList<Index> validateIndices(String indicesAsString) throws ParseException {
        if (!indicesAsString.matches(INDEX_VALIDATION_REGEX)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    GenerateCommand.MESSAGE_USAGE) + "\nIncorrect format for index(es)");
        }
        try {
            ArrayList<Index> indices = new ArrayList<>();
            String[] indicesArr = indicesAsString.split(",");
            for (String indexString : indicesArr) {
                Index index = ParserUtil.parseIndex(indexString);
                indices.add(index);
            }
            return indices;
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    GenerateCommand.MESSAGE_USAGE + "\nInvalid index"), ive);
        }
    }

    // validate against ValidLevels and return difficulty level
    private ValidLevel validateLevel(String level) throws ParseException {
        if (level.isBlank()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    GenerateCommand.MESSAGE_USAGE) + "\nNo difficulty level selected!");
        }
        for (ValidLevel validLevel : ValidLevel.values()) {
            if (validLevel.name().equalsIgnoreCase(level)) {
                return validLevel;
            }
        }
        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                GenerateCommand.MESSAGE_USAGE) + "\nDifficulty level not supported!");
    }

}

