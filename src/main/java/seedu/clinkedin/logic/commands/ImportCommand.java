package seedu.clinkedin.logic.commands;

import static java.util.Objects.requireNonNull;
//import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_FILETYPE;
//import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_PATH;

import seedu.clinkedin.model.Model;

/**
 * Imports an addressbook.
 */
public class ImportCommand extends Command {
    public static final String COMMAND_WORD = "import";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Imports the address book.\n"
            + "Parameters: "
            + "PREFIX_FILETYPE" + "XML/CSV "
            + "PREFIX_PATH" + "PATH\n"
            + "Example: " + COMMAND_WORD + " "
            + "PREFIX_FILETYPE" + "XML "
            + "PREFIX_PATH" + "~/Desktop/data.xml";
    public static final String MESSAGE_SUCCESS = "File imported successfully!";

    private String pathName;
    private boolean isCsvFormat;

    /**
     * Creates an ImportCommand to import an AddressBook
     */
    public ImportCommand(String fileName, boolean isCsvFormat) {
        this.pathName = fileName;
        this.isCsvFormat = isCsvFormat;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return null;
    }

}
