package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CreateCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.remark.Remark;
import seedu.address.model.remark.RemarkAddress;
import seedu.address.model.remark.RemarkName;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new {@code RemarkCommand} object
 */
public class CreateCommandParser implements Parser<CreateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code RemarkCommand}
     * and returns a {@code RemarkCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CreateCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME,
                PREFIX_ADDRESS, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateCommand.MESSAGE_USAGE), ive);
        }

        if (argMultimap.getValue(PREFIX_NAME).isEmpty() || argMultimap.getValue(PREFIX_ADDRESS).isEmpty()) {
            throw new ParseException(CreateCommand.MESSAGE_COMPANY_INVALID);
        }

        RemarkName name = ParserUtil.parseRemarkName(argMultimap.getValue(PREFIX_NAME).get());
        RemarkAddress address = ParserUtil.parseRemarkAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Remark company = new Remark(name, address, tagList);

        return new CreateCommand(index, company);
    }

}
