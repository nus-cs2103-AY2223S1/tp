package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddLinkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.link.Link;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_LINK;

/**
 * Parses input arguments and creates a new {@code AddLinkCommand} object
 */
public class AddLinkCommandParser implements Parser<AddLinkCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code AddLinkCommand}
     * and returns an {@code AddLinkCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddLinkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MODULE_LINK);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLinkCommand.MESSAGE_USAGE), pe);
        }

        Optional<Set<Link>> links = parseLinksToAdd(argMultimap.getAllValues(PREFIX_MODULE_LINK));

        return new AddLinkCommand(index,
                links.orElseThrow(() -> new ParseException(AddLinkCommand.MESSAGE_NOT_EDITED)));
    }

    /**
     * Parses {@code Collection<String> links} into a {@code Set<Link>} if {@code links} is non-empty.
     * If {@code links} contain only one element which is an empty string, {@code links} is treated as empty.
     */
    private Optional<Set<Link>> parseLinksToAdd(Collection<String> links) throws ParseException {
        assert links != null;
        if (links.isEmpty() || (links.size() == 1 && links.contains(""))) {
            return Optional.empty();
        }
        return Optional.of(ParserUtil.parseLinks(links));
    }
}
