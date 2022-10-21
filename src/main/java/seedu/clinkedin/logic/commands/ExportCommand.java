package seedu.clinkedin.logic.commands;

import static java.util.Objects.requireNonNull;
//import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_FILETYPE;
//import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_PATH;

import seedu.clinkedin.model.Model;

/**
 * Exports the addressbook.
 */
public class ExportCommand extends Command {
    public static final String COMMAND_WORD = "export";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exports the address book.\n"
            + "Parameters: "
            + "PREFIX_FILETYPE" + "XML/CSV "
            + "PREFIX_PATH" + "PATH\n"
            + "Example: " + COMMAND_WORD + " "
            + "PREFIX_FILETYPE" + "XML "
            + "PREFIX_PATH" + "~/Desktop";
    public static final String MESSAGE_SUCCESS = "File exported successfully!";

    private String pathName;
    private boolean isCsvFormat;

    /**
     * Creates an ExportCommand to export the AddressBook
     */
    public ExportCommand(String fileName, boolean isCsvFormat) {
        this.pathName = fileName;
        this.isCsvFormat = isCsvFormat;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return null;
    }

}
