package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.UniqueTagTypeMap;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.getPrefixes());

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editPersonDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        // parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);
        Map<Prefix, List<String>> prefToStrings = new HashMap<>();
        CliSyntax.getPrefixTags().stream().forEach(pref -> prefToStrings.put(pref, argMultimap.getAllValues(pref)));

        Map<Prefix, List<String>> oldTagMap = new HashMap<>();
        Map<Prefix, List<String>> newTagMap = new HashMap<>();

        for (Prefix p : prefToStrings.keySet()) {
            for (String tagPair: prefToStrings.get(p)) {
                String[] oldNewPair = this.split(tagPair);
                if (oldTagMap.containsKey(p)) {
                    oldTagMap.get(p).add(oldNewPair[0]);
                    newTagMap.get(p).add(oldNewPair[1]);
                }
                else {
                    oldTagMap.put(p, new ArrayList<>(Arrays.asList(oldNewPair[0])));
                    newTagMap.put(p, new ArrayList<>(Arrays.asList(oldNewPair[1])));
                }
            }
        }
        UniqueTagTypeMap oldMap = ParserUtil.parseTags(oldTagMap);
        UniqueTagTypeMap newMap = ParserUtil.parseTags(newTagMap);
        if (oldMap.getCount() != 0) {
            editPersonDescriptor.setOldTagTypeMap(oldMap);
            editPersonDescriptor.setNewTagTypeMap(newMap);
        }

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editPersonDescriptor);
    }

    private String[] split(String oldNew) throws ParseException {
        String[] oldNewPair = oldNew.split("\\s+-\\s+", 2);
        if (oldNewPair.length != 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }
        return oldNewPair;
    }

    //    /**
    //     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
    //     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
    //     * {@code Set<Tag>} containing zero tags.
    //     */
    //    private Optional<UniqueTagTypeMap> parseTagsForEdit(Collection<String> tags) throws ParseException {
    //        assert tags != null;
    //
    //        if (tags.isEmpty()) {
    //            return Optional.empty();
    //        }
    //        // Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
    //        Map<Prefix, List<String>> tagSet = new HashMap<>();
    //        return Optional.of(ParserUtil.parseTags(tagSet));
    //    }

}
