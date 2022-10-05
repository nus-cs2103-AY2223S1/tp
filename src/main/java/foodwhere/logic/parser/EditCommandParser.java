package foodwhere.logic.parser;

import static foodwhere.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import foodwhere.commons.core.index.Index;
import foodwhere.logic.commands.EditCommand;
import foodwhere.logic.commands.EditCommand.EditStallDescriptor;
import foodwhere.logic.parser.exceptions.ParseException;
import foodwhere.model.detail.Detail;

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
                ArgumentTokenizer.tokenize(args,
                        CliSyntax.PREFIX_NAME,
                        CliSyntax.PREFIX_PHONE,
                        CliSyntax.PREFIX_ADDRESS,
                        CliSyntax.PREFIX_DETAIL);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditStallDescriptor editStallDescriptor = new EditStallDescriptor();
        if (argMultimap.getValue(CliSyntax.PREFIX_NAME).isPresent()) {
            editStallDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(CliSyntax.PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_PHONE).isPresent()) {
            editStallDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(CliSyntax.PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_ADDRESS).isPresent()) {
            editStallDescriptor.setAddress(ParserUtil.parseAddress(
                    argMultimap.getValue(CliSyntax.PREFIX_ADDRESS).get()));
        }
        parseDetailsForEdit(argMultimap.getAllValues(CliSyntax.PREFIX_DETAIL))
                .ifPresent(editStallDescriptor::setDetails);

        if (!editStallDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editStallDescriptor);
    }

    /**
     * Parses {@code Collection<String> details} into a {@code Set<Detail>} if {@code details} is non-empty.
     * If {@code details} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Detail>} containing zero details.
     */
    private Optional<Set<Detail>> parseDetailsForEdit(Collection<String> details) throws ParseException {
        assert details != null;

        if (details.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> detailSet = details.size() == 1 && details.contains("") ? Collections.emptySet() : details;
        return Optional.of(ParserUtil.parseDetails(detailSet));
    }

}
