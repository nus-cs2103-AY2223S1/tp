package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.DescriptionCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Description;

/**
 * Parses input arguments and creates a new {@code DescriptionCommand} object
 */
public class DescriptionCommandParser implements Parser<DescriptionCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code DescriptionCommand}
     * and returns a {@code DescriptionCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DescriptionCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DescriptionCommand.MESSAGE_USAGE), ive);
        }

        String remark = argMultimap.getValue(PREFIX_DESCRIPTION).orElse("");

        return new DescriptionCommand(index, new Description(remark));
    }
}
