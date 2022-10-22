package friday.logic.parser;

import static friday.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static friday.logic.parser.CliSyntax.*;
import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import friday.commons.core.index.Index;
import friday.logic.commands.AliasCommand;
import friday.logic.commands.EditCommand;
import friday.logic.parser.exceptions.ParseException;
import friday.model.tag.Tag;

/**
 * Parses input arguments and creates a new AliasCommand object
 */
public class AliasCommandParser implements Parser<AliasCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AliasCommand
     * and returns an AliasCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AliasCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ALIAS, PREFIX_RESERVED_KEYWORD);

        String alias;
        String reservedKeyword;

        if (!argMultimap.getValue(PREFIX_ALIAS).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AliasCommand.MESSAGE_USAGE);
        }

        if (!argMultimap.getValue(PREFIX_RESERVED_KEYWORD).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AliasCommand.MESSAGE_USAGE);
        }
        if (argMultimap.getValue(PREFIX_TELEGRAMHANDLE).isPresent()) {
            editStudentDescriptor.setTelegramHandle(ParserUtil.parseTelegramHandle(
                    argMultimap.getValue(PREFIX_TELEGRAMHANDLE).get()));
        }
        if (argMultimap.getValue(PREFIX_CONSULTATION).isPresent()) {
            editStudentDescriptor.setConsultation(ParserUtil.parseConsultation(
                    argMultimap.getValue(PREFIX_CONSULTATION).get()));
        }
        if (argMultimap.getValue(PREFIX_MASTERYCHECK).isPresent()) {
            editStudentDescriptor.setMasteryCheck(ParserUtil.parseMasteryCheck(
                    argMultimap.getValue(PREFIX_MASTERYCHECK).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editStudentDescriptor::setTags);

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

