package seedu.clinkedin.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_PATH;

import seedu.clinkedin.logic.parser.ParserUtil.FileType;
import seedu.clinkedin.model.Model;

/**
 * Exports the addressbook.
 */
public class ExportCommand extends Command {
    public static final String COMMAND_WORD = "export";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exports the address book.\n"
            + "Parameters: "
            + PREFIX_PATH + "PATH\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PATH + "~/Desktop/data.csv";
    public static final String MESSAGE_SUCCESS = "File exported successfully!";

    private String filePath;
    private FileType fileType;

    /**
     * Creates an ExportCommand to export the AddressBook
     */
    public ExportCommand(String filePath, FileType fileType) {
        this.filePath = filePath;
        this.fileType = fileType;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return null;
    }

}
