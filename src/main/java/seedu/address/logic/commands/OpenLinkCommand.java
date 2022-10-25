package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_NO_MODULE_IN_FILTERED_LIST;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_LINK_ALIAS;
import static seedu.address.model.module.link.Link.LINK_HEADER_PROTOCOL_HTTP;
import static seedu.address.model.module.link.Link.LINK_HEADER_PROTOCOL_HTTPS;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.exceptions.ModuleNotFoundException;
import seedu.address.model.module.link.Link;

/**
 * Open link/s from an existing module in Plannit.
 */
public class OpenLinkCommand extends Command {
    public static final String COMMAND_WORD = "open-link";
    public static final String MESSAGE_USAGE = "[" + COMMAND_WORD + "]: Open link/s from a module "
            + "using its module code and user-defined alias.\n"
            + "A 'm/' flag should be appended to the front the module code;\n"
            + "A 'la/' flag should be appended to the front of each link alias.\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_MODULE_CODE + "GEA1000 "
            + PREFIX_MODULE_LINK_ALIAS + "coursemo " + PREFIX_MODULE_LINK_ALIAS + "kattis";

    public static final String MESSAGE_OPEN_LINK_SUCCESS = "Successfully opened the following link/s "
            + "from module code [%1$s]!\n%2$s";
    public static final String MESSAGE_NOT_EDITED = "At least one link must be opened.";
    public static final String MESSAGE_MISSING_LINK_ALIAS = "The link alias [%1$s] does not currently exist"
            + " in the module with module code [%2$s].";
    private static final String MESSAGE_LINK_LAUNCH_FAILURE = "Your desktop prevents"
            + " link URLs to be opened from Plannit. "
            + "Please enable system security permissions for Plannit to use this feature.";
    private static final String OS_NAME_LOWERCASE_WINDOWS = "windows";
    private static final String WINDOWS_OPEN_LINK_COMMAND_KEY = "rundll32 url.dll,FileProtocolHandler ";
    private static final String OS_NAME_LOWERCASE_MAC = "mac";
    private static final String MAC_OPEN_LINK_COMMAND_KEY = "open ";
    private static final String OS_NAME_LOWERCASE_LINUX = "linux";
    private static final String LINUX_OPEN_LINK_COMMAND_KEY = "xdg-open";

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
            throw new CommandException(String.format(MESSAGE_NO_MODULE_IN_FILTERED_LIST,
                    moduleCode.getModuleCodeAsUpperCaseString()));
        }
        assert moduleToOpenLinks != null;

        String linksOpened = openLinksFromModule(moduleToOpenLinks, linkAliases);
        return new CommandResult(String.format(MESSAGE_OPEN_LINK_SUCCESS,
                moduleCode.getModuleCodeAsUpperCaseString(), linksOpened));
    }

    /**
     * Iterates through the links from {@code moduleToOpenLinks} and opens it in the user's default browser
     * if its link alias is found in {@code linksToOpen}.
     * Partial opening of links is supported, whereby links will be sequentially opened till the end of
     * the collection {@code linksToOpen} or till a non-matching link alias between the collection and
     * the links from the specified module is encountered.
     */
    private static String openLinksFromModule(Module moduleToOpenLinks, List<String> linkAliasesToOpen)
            throws CommandException {
        assert moduleToOpenLinks != null;
        Set<Link> moduleLinksCopy = moduleToOpenLinks.copyLinks();
        StringBuilder linksOpened = new StringBuilder();
        for (String linkAlias : linkAliasesToOpen) {
            Link linkToOpen = moduleLinksCopy.stream()
                    .filter(link -> link.hasLinkAlias(linkAlias))
                    .findFirst()
                    .orElseThrow(() -> new CommandException(String.format(MESSAGE_MISSING_LINK_ALIAS,
                            linkAlias, moduleToOpenLinks.getModuleCode().getModuleCodeAsUpperCaseString())));
            try {
                launchLinkFromPlannit(linkToOpen.linkUrl);
                linksOpened.append(String.format("[%s]\n", linkToOpen.linkUrl));
            } catch (IOException | SecurityException ex) {
                throw new CommandException(MESSAGE_LINK_LAUNCH_FAILURE);
            }
        }
        return linksOpened.toString();
    }

    //@@author shwene-reused
    //Reused from https://stackoverflow.com/questions/5226212/how-to-open-the-default-webbrowser-using-java
    //with slight modification
    private static void launchLinkFromPlannit(String linkUrl) throws IOException, SecurityException {
        boolean isLinkUrlPaddedWithHttps = linkUrl.startsWith(LINK_HEADER_PROTOCOL_HTTPS);
        boolean isLinkUrlPaddedWithHttp = linkUrl.startsWith(LINK_HEADER_PROTOCOL_HTTP);
        if (!(isLinkUrlPaddedWithHttps || isLinkUrlPaddedWithHttp)) {
            linkUrl = LINK_HEADER_PROTOCOL_HTTP + linkUrl;
        }
        final String finalLinkUrl = linkUrl;

        String os = System.getProperty("os.name").toLowerCase();
        Runtime rt = Runtime.getRuntime();
        if (os.contains(OS_NAME_LOWERCASE_WINDOWS)) {
            rt.exec(WINDOWS_OPEN_LINK_COMMAND_KEY + finalLinkUrl);
        } else if (os.contains(OS_NAME_LOWERCASE_MAC)) {
            rt.exec(MAC_OPEN_LINK_COMMAND_KEY + finalLinkUrl);
        } else if (os.contains(OS_NAME_LOWERCASE_LINUX)) {
            String[] cmd = {LINUX_OPEN_LINK_COMMAND_KEY, finalLinkUrl};
            rt.exec(cmd);
        }
    }
    //@@author

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
