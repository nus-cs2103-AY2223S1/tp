package seedu.clinkedin.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.clinkedin.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_LINK;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import seedu.clinkedin.commons.core.index.Index;
import seedu.clinkedin.logic.commands.EditCommand;
import seedu.clinkedin.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.clinkedin.logic.parser.exceptions.ParseException;
import seedu.clinkedin.model.link.Link;
import seedu.clinkedin.model.person.UniqueTagTypeMap;

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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, CliSyntax.getPrefixes());
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
        Map<Prefix, List<String>> prefToStrings = new HashMap<>();
        CliSyntax.getPrefixTags().stream().forEach(pref -> prefToStrings.put(pref, argMultimap.getAllValues(pref)));

        Map<Prefix, List<String>> oldTagMap = new HashMap<>();
        Map<Prefix, List<String>> newTagMap = new HashMap<>();

        for (Prefix p : prefToStrings.keySet()) {
            for (String tagPair: prefToStrings.get(p)) {
                String[] oldNewPair;
                try {
                    oldNewPair = ParserUtil.parseHyphen(tagPair);
                } catch (ParseException pe) {
                    throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
                }
                if (oldTagMap.containsKey(p)) {
                    oldTagMap.get(p).add(oldNewPair[0]);
                    newTagMap.get(p).add(oldNewPair[1]);
                } else {
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
        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            editPersonDescriptor.setStatus(ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get()));
        }
        if (argMultimap.getValue(PREFIX_NOTE).isPresent()) {
            editPersonDescriptor.setNote(ParserUtil.parseNote(argMultimap.getValue(PREFIX_NOTE).get()));
        }
        if (argMultimap.getValue(PREFIX_RATING).isPresent()) {
            editPersonDescriptor.setRating(ParserUtil.parseRating(argMultimap.getValue(PREFIX_RATING).get()));
        }
        parseLinksForEdit(argMultimap.getAllValues(PREFIX_LINK)).ifPresent(editPersonDescriptor::setLinks);
        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editPersonDescriptor);
    }

    /**
     * Parses {@code Collection<String> links} into a {@code Set<Link>} if {@code links} is non-empty.
     * If {@code links} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Link>} containing zero link.
     */
    private Optional<Set<Link>> parseLinksForEdit(Collection<String> links) throws ParseException {
        assert links != null;

        if (links.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> linkSet = links.size() == 1 && links.contains("") ? Collections.emptySet() : links;
        return Optional.of(ParserUtil.parseLinks(linkSet));
    }
}
