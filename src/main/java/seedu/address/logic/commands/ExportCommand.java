package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.Export;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ExportCommand extends Command {
    public static final String COMMAND_WORD = "export";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Export the current state of the "
            + "address book as an encrypted json file\n"
            + "example: " + COMMAND_WORD ;
    private Path exportFilePath = Paths.get("export", "export.json");

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Export.exportAsJSON(model.getAddressBook(), exportFilePath);
        return new CommandResult()
    }


}
