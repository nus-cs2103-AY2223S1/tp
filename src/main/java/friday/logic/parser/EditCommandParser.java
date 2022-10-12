package friday.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import friday.commons.core.Messages;
import friday.commons.core.index.Index;
import friday.logic.commands.EditCommand;
import friday.logic.parser.exceptions.ParseException;
import friday.model.tag.Tag;

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
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_TELEGRAMHANDLE,
                        CliSyntax.PREFIX_CONSULTATION, CliSyntax.PREFIX_MASTERYCHECK, CliSyntax.PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE),
                    pe);
        }

        EditCommand.EditStudentDescriptor editStudentDescriptor = new EditCommand.EditStudentDescriptor();
        if (argMultimap.getValue(CliSyntax.PREFIX_NAME).isPresent()) {
            editStudentDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(CliSyntax.PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_TELEGRAMHANDLE).isPresent()) {
            editStudentDescriptor.setTelegramHandle(ParserUtil.parseTelegramHandle(argMultimap.getValue(CliSyntax
                    .PREFIX_TELEGRAMHANDLE).get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_CONSULTATION).isPresent()) {
            editStudentDescriptor.setConsultation(ParserUtil.parseConsultation(
                    argMultimap.getValue(CliSyntax.PREFIX_CONSULTATION).get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_MASTERYCHECK).isPresent()) {
            editStudentDescriptor.setMasteryCheck(ParserUtil.parseMasteryCheck(
                    argMultimap.getValue(CliSyntax.PREFIX_MASTERYCHECK).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(CliSyntax.PREFIX_TAG)).ifPresent(editStudentDescriptor::setTags);

        if (!editStudentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editStudentDescriptor);
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
