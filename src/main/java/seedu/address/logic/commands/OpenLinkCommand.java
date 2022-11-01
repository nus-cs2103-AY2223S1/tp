package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_LINK_ALIAS;
import static seedu.address.model.module.link.Link.LINK_HEADER_PROTOCOL_HTTP;
import static seedu.address.model.module.link.Link.LINK_HEADER_PROTOCOL_HTTPS;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.exceptions.ModuleNotFoundException;
import seedu.address.model.module.link.Link;

/**
 * Open link(s) from an existing module in Plannit.
 */
public class OpenLinkCommand extends Command {
    public static final String COMMAND_WORD = "open-link";
    public static final String MESSAGE_USAGE = "[" + COMMAND_WORD + "]: Open link(s) from a module "
            + "using its module code and user-defined alias.\n"
            + "A 'm/' flag should be appended to the front the module code;\n"
            + "A 'la/' flag should be appended to the front of each link alias.\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_MODULE_CODE + "GEA1000 "
            + PREFIX_MODULE_LINK_ALIAS + "coursemo " + PREFIX_MODULE_LINK_ALIAS + "kattis";

    public static final String MESSAGE_OPEN_LINK_SUCCESS = "Successfully opened link(s) from module code [%1$s]!";
    public static final String MESSAGE_NOT_EDITED = "At least one link must be opened.";
    public static final String MESSAGE_MISSING_LINK_ALIAS = "The link alias [%1$s] does not currently exist"
            + " in the module with module code [%2$s].";
    private static final String MESSAGE_LINK_LAUNCH_FAILURE = "Your desktop prevents"
            + " link URLs to be opened from Plannit. "
            + "Please enable system security permissions for Plannit to use this feature.";

    private final ModuleCode moduleCode;
    private final List<String> linkAliases;

    /**
     * Creates a OpenLinkCommand for the opening of links from a module
     * @param moduleCode module code of the module in which links will be opened
     * @param linkAliases contain aliases of the links to open from the specified module
     */
    public OpenLinkCommand(ModuleCode moduleCode, List<String> linkAliases) {
        requireAllNonNull(moduleCode, linkAliases);
        this.moduleCode = moduleCode;
        this.linkAliases = linkAliases;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Module moduleToOpenLinks = null;
        try {
            moduleToOpenLinks =
                    model.getModuleUsingModuleCode(moduleCode, true);
        } catch (ModuleNotFoundException e) {
            throw new CommandException(String.format(Messages.MESSAGE_NO_MODULE_IN_FILTERED_LIST,
                    moduleCode.getModuleCodeAsUpperCaseString()));
        }
        assert moduleToOpenLinks != null;

        openLinksFromModule(moduleToOpenLinks, linkAliases);
        return new CommandResult(String.format(MESSAGE_OPEN_LINK_SUCCESS,
                moduleCode.getModuleCodeAsUpperCaseString()));
    }

    /**
     * Iterates through the link aliases from {@code linkAliasesToOpen} and opens the corresponding link url
     * in the user's default browser for each link alias that is found in {@code moduleToOpenLinks}.
     * Partial opening of links is supported, whereby links will be sequentially opened from Plannit till the end of
     * {@code linkAliasesToOpen} or till a non-matching link alias is detected from {@code linkAliasesToOpen}.
     */
    private static void openLinksFromModule(Module moduleToOpenLinks, List<String> linkAliasesToOpen)
            throws CommandException {
        assert moduleToOpenLinks != null;
        Set<Link> moduleLinksCopy = moduleToOpenLinks.copyLinks();
        for (String linkAlias : linkAliasesToOpen) {
            Link linkToOpen = moduleLinksCopy.stream()
                    .filter(link -> link.hasLinkAlias(linkAlias))
                    .findFirst()
                    .orElseThrow(() -> new CommandException(String.format(MESSAGE_MISSING_LINK_ALIAS,
                            linkAlias, moduleToOpenLinks.getModuleCode().getModuleCodeAsUpperCaseString())));
            try {
                linkToOpen.open();
            } catch (IOException | SecurityException ex) {
                throw new CommandException(MESSAGE_LINK_LAUNCH_FAILURE);
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof OpenLinkCommand)) {
            return false;
        }

        // state check
        OpenLinkCommand c = (OpenLinkCommand) other;
        return moduleCode.equals(c.moduleCode) && linkAliases.equals(c.linkAliases);
    }
}
