package seedu.intrack.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.intrack.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.intrack.logic.parser.CliSyntax.PREFIX_REMARK;

import java.util.List;

import seedu.intrack.commons.core.Messages;
import seedu.intrack.logic.commands.exceptions.CommandException;
import seedu.intrack.model.Model;
import seedu.intrack.model.internship.Internship;
import seedu.intrack.model.internship.Remark;

/**
 * Edits the remark of the selected internship application.
 */
public class RemarkCommand extends Command {

    public static final String COMMAND_WORD = "remark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the remark of the selected internship application. "
            + "Existing remark will be overwritten by the input.\n"
            + "Parameters: "
            + PREFIX_REMARK + "[REMARK]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_REMARK + "Revise Graphs";

    public static final String MESSAGE_ADD_REMARK_SUCCESS = "Added remark to internship application: \n%1$s";
    public static final String MESSAGE_DELETE_REMARK_SUCCESS = "Removed remark from internship application: \n%1$s";

    private final Remark remark;

    /**
     * @param remark of the internship to be updated to
     */
    public RemarkCommand(Remark remark) {
        requireAllNonNull(remark);
        this.remark = remark;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Internship> lastShownList = model.getSelectedInternship();
        if (lastShownList.size() != 1) {
            throw new CommandException(Messages.MESSAGE_NO_INTERNSHIP_SELECTED);
        }
        Internship internshipToEdit = lastShownList.get(0);

        Internship editedInternship = new Internship(internshipToEdit.getName(), internshipToEdit.getPosition(),
                internshipToEdit.getStatus(), internshipToEdit.getEmail(), internshipToEdit.getWebsite(),
                internshipToEdit.getTasks(), internshipToEdit.getSalary(), internshipToEdit.getTags(), remark);

        model.setInternship(internshipToEdit, editedInternship);

        return new CommandResult(generateSuccessMessage(editedInternship));
    }

    /**
     * Generates a command execution success message based on whether the remark is added to or removed from
     * {@code internshipToEdit}.
     */
    private String generateSuccessMessage(Internship internshipToEdit) {
        String message = remark.value.isEmpty() ? MESSAGE_DELETE_REMARK_SUCCESS : MESSAGE_ADD_REMARK_SUCCESS;
        return String.format(message, internshipToEdit);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemarkCommand // instanceof handles nulls
                && remark.equals(((RemarkCommand) other).remark)); // state check
    }

}
