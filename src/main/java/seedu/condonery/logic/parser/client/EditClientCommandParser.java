package seedu.condonery.logic.parser.client;

import static seedu.condonery.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.condonery.commons.core.index.Index;
import seedu.condonery.logic.commands.client.EditClientCommand;
import seedu.condonery.logic.parser.ArgumentMultimap;
import seedu.condonery.logic.parser.ArgumentTokenizer;
import seedu.condonery.logic.parser.Parser;
import seedu.condonery.logic.parser.ParserUtil;
import seedu.condonery.logic.parser.exceptions.ParseException;
import seedu.condonery.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditClientCommand object
 */
public class EditClientCommandParser implements Parser<EditClientCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditClientCommand
     * and returns a Command object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public EditClientCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_TAG);
        EditClientCommand.EditClientDescriptor editPropertyDescriptor =
                new EditClientCommand.EditClientDescriptor();
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditClientCommand.MESSAGE_USAGE), pe);
        }

        if (!argMultimap.getValue(PREFIX_NAME).isPresent() && !argMultimap.getValue(PREFIX_ADDRESS).isPresent()
                && argMultimap.getAllValues(PREFIX_TAG).size() == 0) {
            throw new ParseException(EditClientCommand.MESSAGE_NOT_EDITED);
        }

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPropertyDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editPropertyDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPropertyDescriptor::setTags);

        return new EditClientCommand(index, editPropertyDescriptor);
    }

    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}
