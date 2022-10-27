package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_LINK_ALIAS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_LINK_URL;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.AddLinkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.link.Link;

/**
 * Parses input arguments and creates a new {@code AddLinkCommand} object.
 */
public class AddLinkCommandParser implements Parser<AddLinkCommand> {

    public static final String MESSAGE_CONSTRAINTS = "Each link URL must be paired with a link alias";
    public static final String MESSAGE_DUPLICATE_ALIAS = "Link Aliases should be unique";
    public static final String MESSAGE_DUPLICATE_URL = "Link URLs should be unique";

    /**
     * Parses the given {@code String} of arguments in the context of the {@code AddLinkCommand}
     * and returns an {@code AddLinkCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddLinkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MODULE_CODE,
                PREFIX_MODULE_LINK_ALIAS, PREFIX_MODULE_LINK_URL);

        boolean isPreambleEmpty = argMultimap.getPreamble().isEmpty();
        if (!isPreambleEmpty) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLinkCommand.MESSAGE_USAGE));
        }

        String moduleCodeStringToEdit = argMultimap.getValue(PREFIX_MODULE_CODE)
                .orElseThrow(() -> new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLinkCommand.MESSAGE_USAGE)));

        //Throws ParseException if module code or link/s is invalid
        ModuleCode moduleCodeToEdit = ParserUtil.parseModuleCode(moduleCodeStringToEdit);
        Optional<Set<Link>> links = parseLinksToAdd(
                argMultimap.getAllValues(PREFIX_MODULE_LINK_ALIAS),
                argMultimap.getAllValues(PREFIX_MODULE_LINK_URL));

        return new AddLinkCommand(moduleCodeToEdit,
                links.orElseThrow(() -> new ParseException(AddLinkCommand.MESSAGE_NOT_EDITED)));
    }

    /**
     * Parses {@code Collection<String> linkAliases, Collection<String> linkUrls} into
     * a {@code Map<Link>} if {@code linkAliases} and {@code linkUrls} is non-empty.
     */
    private Optional<Set<Link>> parseLinksToAdd(
            Collection<String> linkAliases, Collection<String> linkUrls) throws ParseException {
        assert linkAliases != null && linkUrls != null;
        boolean isLinksAliasesEmpty = linkAliases.isEmpty();
        boolean isLinkUrlsEmpty = linkUrls.isEmpty();
        if (isLinksAliasesEmpty || isLinkUrlsEmpty) {
            return Optional.empty();
        }
        return Optional.of(ParserUtil.parseLinks(linkAliases, linkUrls));
    }
}
