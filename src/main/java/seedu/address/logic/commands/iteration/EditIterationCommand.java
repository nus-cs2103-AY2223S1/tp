package seedu.address.logic.commands.iteration;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.LogicManager.FILE_OPS_OPEN_ERROR_MESSAGE;
import static seedu.address.logic.commands.iteration.AddIterationCommand.saveImage;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITERATION_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITERATION_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITERATION_FEEDBACK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITERATION_IMAGEPATH;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.commission.Commission;
import seedu.address.model.iteration.Date;
import seedu.address.model.iteration.Feedback;
import seedu.address.model.iteration.Iteration;
import seedu.address.model.iteration.IterationDescription;
import seedu.address.storage.Storage;
import seedu.address.ui.GuiTab;

/**
 * Edits an existing iteration in ArtBuddy.
 */
public class EditIterationCommand extends Command {

    public static final String COMMAND_WORD = "edititer";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the details of the iteration identified "
            + "by the index number used in the displayed iteration list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_ITERATION_DATE + "DATE " + "]"
            + "[" + PREFIX_ITERATION_DESCRIPTION + "DESCRIPTION " + "]"
            + "[" + PREFIX_ITERATION_IMAGEPATH + "IMAGE PATH " + "]"
            + "[" + PREFIX_ITERATION_FEEDBACK + "FEEDBACK" + "]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_ITERATION_DATE + "2022-10-10 "
            + PREFIX_ITERATION_DESCRIPTION + "Changed the colour scheme. "
            + PREFIX_ITERATION_IMAGEPATH + "/Users/john/Downloads/Draft 1.png "
            + PREFIX_ITERATION_FEEDBACK + "Updated colour scheme is much better.";

    public static final String MESSAGE_EDIT_ITERATION_SUCCESS = "Edited Iteration: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_ITERATION = "This iteration already exists in the art buddy.";

    private final Index index;
    private final EditIterationDescriptor editIterationDescriptor;

    /**
     * @param index                    of the iteration in the filtered iteration list to edit
     * @param editIterationDescriptor details to edit the iteration with
     */
    public EditIterationCommand(Index index, EditIterationDescriptor editIterationDescriptor) {
        requireAllNonNull(index, editIterationDescriptor);
        this.index = index;
        this.editIterationDescriptor = new EditIterationDescriptor(editIterationDescriptor);
    }

    /**
     * Creates and returns a {@code Iteration} with the details of {@code iterationToEdit}
     * edited with {@code editIterationDescriptor}.
     */
    private static Iteration createEditedIteration(Storage storage, Iteration iterationToEdit,
                                                   EditIterationDescriptor editIterationDescriptor)
            throws CommandException {
        requireAllNonNull(iterationToEdit, editIterationDescriptor);

        Date updatedDate = editIterationDescriptor.getDate().orElse(iterationToEdit.getDate());
        IterationDescription updatedDescription =
                editIterationDescriptor.getDescription().orElse(iterationToEdit.getDescription());
        Feedback updatedFeedback =
                editIterationDescriptor.getFeedback().orElse(iterationToEdit.getFeedback());
        Path updatedImagePath =
                editIterationDescriptor.getImagePath().orElse(iterationToEdit.getImagePath());

        Iteration editedIteration = new Iteration(updatedDate, updatedDescription, updatedImagePath, updatedFeedback);

        if (editIterationDescriptor.getImagePath().isPresent()) {
            // new image upload, image save required
            try {
                saveImage(storage, editedIteration);
            } catch (IOException e) {
                throw new CommandException(FILE_OPS_OPEN_ERROR_MESSAGE + e.getLocalizedMessage(), e);
            }
        }

        return editedIteration;
    }

    @Override
    public CommandResult execute(Model model, Storage storage) throws CommandException {
        requireNonNull(model);

        if (!model.hasSelectedCommission()) {
            throw new CommandException(Messages.MESSAGE_NO_ACTIVE_COMMISSION);
        }

        Commission selectedCommission = model.getSelectedCommission().getValue();
        List<Iteration> lastShownList = selectedCommission.getIterationList();

        if (index.getOneBased() > lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ITERATION_DISPLAYED_INDEX);
        }

        Iteration iterationToEdit = lastShownList.get(index.getZeroBased());
        Iteration editedIteration = createEditedIteration(storage, iterationToEdit, editIterationDescriptor);

        if (!iterationToEdit.isSameIteration(editedIteration)
                && selectedCommission.hasIteration(editedIteration)) {
            throw new CommandException(MESSAGE_DUPLICATE_ITERATION);
        }

        selectedCommission.setIteration(iterationToEdit, editedIteration);
        model.selectTab(GuiTab.COMMISSION);
        return new CommandResult(String.format(MESSAGE_EDIT_ITERATION_SUCCESS, editedIteration));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditIterationCommand)) {
            return false;
        }

        // state check
        EditIterationCommand toCheck = (EditIterationCommand) other;
        return index.equals(toCheck.index)
                && editIterationDescriptor.equals(toCheck.editIterationDescriptor);
    }

    /**
     * Stores the details to edit the iteration with. Each non-empty field value will replace the
     * corresponding field value of the iteration.
     */
    public static class EditIterationDescriptor {
        private Date date;
        private IterationDescription description;
        private Feedback feedback;
        private Path imagePath;

        public EditIterationDescriptor() {
        }

        /**
         * Copy constructor.
         */
        public EditIterationDescriptor(EditIterationDescriptor toCopy) {
            setDate(toCopy.date);
            setDescription(toCopy.description);
            setFeedback(toCopy.feedback);
            setImagePath(toCopy.imagePath);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(date, description, feedback, imagePath);
        }

        public Optional<Date> getDate() {
            return Optional.ofNullable(date);
        }

        public Optional<IterationDescription> getDescription() {
            return Optional.ofNullable(description);
        }

        public Optional<Feedback> getFeedback() {
            return Optional.ofNullable(feedback);
        }

        public Optional<Path> getImagePath() {
            return Optional.ofNullable(imagePath);
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public void setDescription(IterationDescription description) {
            this.description = description;
        }

        public void setFeedback(Feedback feedback) {
            this.feedback = feedback;
        }

        public void setImagePath(Path imagePath) {
            this.imagePath = imagePath;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditIterationDescriptor)) {
                return false;
            }

            // state check
            EditIterationDescriptor toCheck = (EditIterationDescriptor) other;

            return getDate().equals(toCheck.getDate())
                    && getDescription().equals(toCheck.getDescription())
                    && getFeedback().equals(toCheck.getFeedback())
                    && getImagePath().equals(toCheck.getImagePath());
        }
    }
}
