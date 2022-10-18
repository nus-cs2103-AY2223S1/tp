package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILEPATH;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.SetPersonFileCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.FilePath;

/**
 * Parses input arguments and creates a new {@code SetPersonFileCommand} object
 */
public class SetPersonFileCommandParser implements Parser<SetPersonFileCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code SetPersonFileCommand}
     * and returns a {@code SetPersonFileCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SetPersonFileCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_FILEPATH);


        Index index;
        FilePath filePath;
        if (!isOnlyFilePrefixPresent(argMultimap, PREFIX_FILEPATH)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetPersonFileCommand.MESSAGE_USAGE));
        }

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetPersonFileCommand.MESSAGE_USAGE), ive);
        }

        filePath = ParserUtil.parseFilePath(argMultimap.getValue(PREFIX_FILEPATH).orElse(""));
        return new SetPersonFileCommand(index, filePath);
    }

    /**
     * Returns true if only {@code PREFIX_FILEPATH} {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean isOnlyFilePrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).filter(prefix -> argumentMultimap.getValue(prefix).isPresent()).count() == 1;
    }
}
