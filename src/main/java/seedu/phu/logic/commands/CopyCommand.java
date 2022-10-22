package seedu.phu.logic.commands;

import static java.util.Objects.requireNonNull;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.List;

import seedu.phu.commons.core.Messages;
import seedu.phu.commons.core.index.Indexes;
import seedu.phu.commons.exceptions.IllegalIndexException;
import seedu.phu.logic.commands.exceptions.CommandException;
import seedu.phu.model.Model;
import seedu.phu.model.internship.Internship;
import seedu.phu.model.internship.UniqueInternshipList;

/**
 * Copies all details of specified internship into system clipboard.
 */
public class CopyCommand extends Command {
    public static final String COMMAND_WORD = "copy";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Copies all details specified internship"
            + " at the given index into system clipboard.\n"
            + "Parameters: copy index\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Copied details into clipboard";
    private final Indexes targetIndex;

    /**
     * Constructor for ViewCommand
     */
    public CopyCommand(Indexes targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory commandHistory) throws CommandException {
        requireNonNull(model);
        List<Internship> lastShownList = model.getFilteredInternshipList();
        UniqueInternshipList targetInternships;

        try {
            targetInternships = targetIndex.getAllInternshipsFromIndexes(lastShownList);
        } catch (IllegalIndexException error) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
        }

        List<Internship> targetInternshipList = new ArrayList<>();
        for (Internship internship : targetInternships) {
            targetInternshipList.add(internship);
        }
        assert (targetInternshipList.size() == 1);

        Internship internship = targetInternshipList.get(0);
        String toCopy = internship.toString();
        StringSelection stringSelection = new StringSelection(toCopy);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);

        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CopyCommand // instanceof handles nulls
                && targetIndex.equals(((CopyCommand) other).targetIndex)); // state check
    }
}
