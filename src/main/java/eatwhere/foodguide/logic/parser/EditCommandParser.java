package eatwhere.foodguide.logic.parser;

import static eatwhere.foodguide.logic.parser.CliSyntax.PREFIX_CUISINE;
import static eatwhere.foodguide.logic.parser.CliSyntax.PREFIX_LOCATION;
import static eatwhere.foodguide.logic.parser.CliSyntax.PREFIX_NAME;
import static eatwhere.foodguide.logic.parser.CliSyntax.PREFIX_PRICE;
import static eatwhere.foodguide.logic.parser.CliSyntax.PREFIX_TAG;
import static eatwhere.foodguide.logic.parser.ParserUtil.isDisplayHelp;
import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import eatwhere.foodguide.commons.core.Messages;
import eatwhere.foodguide.commons.core.index.Index;
import eatwhere.foodguide.logic.commands.EditCommand;
import eatwhere.foodguide.logic.parser.exceptions.DisplayCommandHelpException;
import eatwhere.foodguide.logic.parser.exceptions.ParseException;
import eatwhere.foodguide.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     * @throws DisplayCommandHelpException if the user input is for displaying command help
     */
    public EditCommand parse(String args) throws ParseException, DisplayCommandHelpException {
        requireNonNull(args);

        if (isDisplayHelp(args)) {
            throw new DisplayCommandHelpException(EditCommand.MESSAGE_USAGE);
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PRICE,
                        PREFIX_CUISINE, PREFIX_LOCATION, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE),
                    pe);
        }

        EditCommand.EditEateryDescriptor editEateryDescriptor = new EditCommand.EditEateryDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editEateryDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PRICE).isPresent()) {
            editEateryDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PRICE).get()));
        }
        if (argMultimap.getValue(PREFIX_CUISINE).isPresent()) {
            editEateryDescriptor.setCuisine(ParserUtil.parseCuisine(argMultimap.getValue(PREFIX_CUISINE).get()));
        }
        if (argMultimap.getValue(PREFIX_LOCATION).isPresent()) {
            editEateryDescriptor.setAddress(ParserUtil.parseLocation(argMultimap.getValue(PREFIX_LOCATION).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editEateryDescriptor::setTags);

        if (!editEateryDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editEateryDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}
