package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_LINK_ALIAS;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import seedu.address.logic.commands.DeleteLinkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleCode;

/**
 * Parses input arguments and creates a new {@code DeleteLinkCommand} object.
 */
public class DeleteLinkCommandParser implements Parser<DeleteLinkCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code DeleteLinkCommand}
     * and returns an {@code DeleteLinkCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteLinkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MODULE_CODE,
                PREFIX_MODULE_LINK_ALIAS);

        boolean isPreambleEmpty = argMultimap.getPreamble().isEmpty();
        if (!isPreambleEmpty) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteLinkCommand.MESSAGE_USAGE));
        }

        String moduleCodeStringToEdit = argMultimap.getValue(PREFIX_MODULE_CODE)
                .orElseThrow(() -> new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteLinkCommand.MESSAGE_USAGE)));

        //Throws ParseException if module code or link alias/es is invalid
        ModuleCode moduleCodeToEdit = ParserUtil.parseModuleCode(moduleCodeStringToEdit);
        Optional<List<String>> linkAliases =
                parseLinkAliasesToDelete(argMultimap.getAllValues(PREFIX_MODULE_LINK_ALIAS));

        return new DeleteLinkCommand(moduleCodeToEdit,
                linkAliases.orElseThrow(() -> new ParseException(DeleteLinkCommand.MESSAGE_NOT_EDITED)));
    }

    /**
     * Parses {@code Collection<String> linkAliases} into a {@code Set<String>} if {@code linkAliases} is non-empty.
     */
    private Optional<List<String>> parseLinkAliasesToDelete(Collection<String> linkAliases) throws ParseException {
        assert linkAliases != null;
        boolean isLinkAliasesEmpty = linkAliases.isEmpty();
        if (isLinkAliasesEmpty) {
            return Optional.empty();
        }
        return Optional.of(ParserUtil.parseLinkAliases(linkAliases));
    }
}
