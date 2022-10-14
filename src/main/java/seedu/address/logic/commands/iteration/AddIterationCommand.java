package seedu.address.logic.commands.iteration;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITERATION_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITERATION_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITERATION_FEEDBACK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITERATION_IMAGEPATH;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.commission.Commission;
import seedu.address.model.iteration.ImagePath;
import seedu.address.model.iteration.Iteration;
import seedu.address.storage.Storage;
import seedu.address.ui.GuiTab;

/**
 * Adds an iteration to an existing commission in ArtBuddy.
 */
public class AddIterationCommand extends Command {

    public static final String COMMAND_WORD = "additer";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds an iteration to ArtBuddy.\n"
            + "Parameters: "
            + PREFIX_ITERATION_DATE + "DATE "
            + PREFIX_ITERATION_DESCRIPTION + "DESCRIPTION "
            + PREFIX_ITERATION_IMAGEPATH + "IMAGE PATH "
            + PREFIX_ITERATION_FEEDBACK + "FEEDBACK\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ITERATION_DATE + "2022-10-10 "
            + PREFIX_ITERATION_DESCRIPTION + "Changed the colour scheme. "
            + PREFIX_ITERATION_FEEDBACK + "Updated colour scheme is much better.";

    public static final String MESSAGE_ADD_ITERATION_SUCCESS = "New iteration with attributes\n%1$s\n"
            + "added to commission \"%2$s\"";
    public static final String MESSAGE_DUPLICATE_ITERATION =
            "This iteration already exists in this commission \"%1$s\"";
    private final Iteration toAdd;

    /**
     * Constructs an {@code AddIterationCommand}, which adds the Iteration {@code toAdd} to
     * the current active {@code Commission}.
     */
    public AddIterationCommand(Iteration toAdd) {
        requireNonNull(toAdd);
        this.toAdd = toAdd;
    }

    @Override
    public CommandResult execute(Model model, Storage...storage) throws CommandException {
        requireNonNull(model);

        if (!model.hasSelectedCommission()) {
            throw new CommandException(Messages.MESSAGE_NO_ACTIVE_COMMISSION);
        }

        Commission activeCommission = model.getSelectedCommission().getValue();

        if (activeCommission.hasIteration(toAdd)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_ITERATION,
                    activeCommission.getTitle().toString()));
        }

        String src = toAdd.getImagePath().path;
        String dst = storage[0].saveIterationImage(src);

        Iteration toAdd2 = new Iteration(
                toAdd.getDate(),
                toAdd.getDescription(),
                new ImagePath(dst),
                toAdd.getFeedback()
        );

        activeCommission.addIteration(toAdd2);
        model.selectTab(GuiTab.COMMISSION);
        return new CommandResult(String.format(MESSAGE_ADD_ITERATION_SUCCESS, toAdd2,
                activeCommission.getTitle().toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddIterationCommand // instanceof handles nulls
                && toAdd.equals(((AddIterationCommand) other).toAdd)); // state check
    }
}
