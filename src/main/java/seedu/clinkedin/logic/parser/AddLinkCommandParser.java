package seedu.clinkedin.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.clinkedin.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_LINK;

import java.util.Set;

import seedu.clinkedin.commons.core.index.Index;
import seedu.clinkedin.commons.exceptions.IllegalValueException;
import seedu.clinkedin.logic.commands.AddLinkCommand;
import seedu.clinkedin.logic.parser.exceptions.ParseException;
import seedu.clinkedin.model.link.Link;

/**
 * Parses input arguments and creates a new AddLinkCommand object
 */
public class AddLinkCommandParser implements Parser<AddLinkCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * AddLinkCommand
     */
    public AddLinkCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_LINK);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddLinkCommand.MESSAGE_USAGE), ive);
        }

        Set<Link> links = ParserUtil.parseLinks(argMultimap.getAllValues(PREFIX_LINK));

        return new AddLinkCommand(index, links);
    }
}
