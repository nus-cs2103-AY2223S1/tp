package seedu.clinkedin.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.clinkedin.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_LINK;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_RATING;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import seedu.clinkedin.commons.core.index.Index;
import seedu.clinkedin.logic.commands.AddToCommand;
import seedu.clinkedin.logic.commands.AddToCommand.UpdatePersonDescriptor;
import seedu.clinkedin.logic.parser.exceptions.ParseException;
import seedu.clinkedin.model.person.UniqueTagTypeMap;

/**
 * Parses input arguments and creates a new AddToCommand object
 */
public class AddToCommandParser implements Parser<AddToCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddToCommand
     * and returns an AddToCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddToCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, CliSyntax.getPrefixes());
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddToCommand.MESSAGE_USAGE), pe);
        }

        UpdatePersonDescriptor updatePersonDescriptor = new UpdatePersonDescriptor();

        Map<Prefix, List<String>> prefToStrings = new HashMap<>();
        CliSyntax.getPrefixTags().forEach(pref -> prefToStrings.put(pref, argMultimap.getAllValues(pref)));
        UniqueTagTypeMap tagMap = ParserUtil.parseTags(prefToStrings);

        updatePersonDescriptor.setTagTypeMap(tagMap);

        if (argMultimap.getValue(PREFIX_NOTE).isPresent()) {
            updatePersonDescriptor.setNote(ParserUtil.parseNote(argMultimap.getValue(PREFIX_NOTE).get()));
        }
        if (argMultimap.getValue(PREFIX_RATING).isPresent()) {
            updatePersonDescriptor.setRating(ParserUtil.parseRating(argMultimap.getValue(PREFIX_RATING).get()));
        }

        ParserUtil.parseLinksForEdit(argMultimap.getAllValues(PREFIX_LINK)).ifPresent(updatePersonDescriptor::setLinks);

        if (!updatePersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(AddToCommand.MESSAGE_NO_ATTRIBUTE_TO_ADD);
        }
        return new AddToCommand(index, updatePersonDescriptor);
    }
}
