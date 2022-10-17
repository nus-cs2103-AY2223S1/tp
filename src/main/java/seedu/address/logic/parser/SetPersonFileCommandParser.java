package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILEPATH;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.SetPersonFileCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.FilePath;

public class SetPersonFileCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code SetPersonFileCommand}
     * and returns a {@code SetPersonFileCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SetPersonFileCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_FILEPATH);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetPersonFileCommand.MESSAGE_USAGE), ive);
        }

        String filePath = argMultimap.getValue(PREFIX_FILEPATH).orElse("");

        return new SetPersonFileCommand(index, new FilePath(filePath));
    }
}
