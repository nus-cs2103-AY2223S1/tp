package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FEE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_COMMISSIONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.commission.Commission;
import seedu.address.model.commission.CompletionStatus;
import seedu.address.model.commission.Deadline;
import seedu.address.model.commission.Description;
import seedu.address.model.commission.Fee;
import seedu.address.model.commission.Title;
import seedu.address.model.customer.Customer;
import seedu.address.model.iteration.UniqueIterationList;
import seedu.address.model.tag.Tag;
import seedu.address.storage.Storage;
import seedu.address.ui.GuiTab;

/**
 * Edits the details of an existing commission in the address book.
 */
public class EditCommissionCommand extends Command {

    public static final String COMMAND_WORD = "editcom";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the commission identified "
            + "by the index number used in the displayed commission list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TITLE + "TITLE" + "] "
            + "[" + PREFIX_FEE + "FEE" + "] "
            + "[" + PREFIX_DEADLINE + "DEADLINE" + "] "
            + "[" + PREFIX_STATUS + "COMPLETION STATUS" + "] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TITLE + "Tokyo Ghoul Kaneki "
            + PREFIX_FEE + "50 "
            + PREFIX_DEADLINE + "2022-10-10 "
            + PREFIX_STATUS + "False "
            + PREFIX_DESCRIPTION + "Unfamiliar, I will need to do up a reference board first. "
            + PREFIX_TAG + "digital "
            + PREFIX_TAG + "neon ";

    public static final String MESSAGE_EDIT_COMMISSION_SUCCESS = "Edited Commission: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_COMMISSION = "This commission already exists in the art buddy.";

    private final Index index;
    private final EditCommissionDescriptor editCommissionDescriptor;

    /**
     * @param index                    of the commission in the filtered commission list to edit
     * @param editCommissionDescriptor details to edit the commission with
     */
    public EditCommissionCommand(Index index, EditCommissionDescriptor editCommissionDescriptor) {
        requireNonNull(index);
        requireNonNull(editCommissionDescriptor);

        this.index = index;
        this.editCommissionDescriptor = new EditCommissionDescriptor(editCommissionDescriptor);
    }

    /**
     * Creates and returns a {@code Commission} with the details of {@code commissionToEdit}
     * edited with {@code editCommissionDescriptor}.
     */
    private static Commission createEditedCommission(Commission commissionToEdit,
                                                   EditCommissionDescriptor editCommissionDescriptor) {
        requireNonNull(commissionToEdit);

        Title updatedTitle = editCommissionDescriptor.getTitle().orElse(commissionToEdit.getTitle());
        Fee updatedFee = editCommissionDescriptor.getFee().orElse(commissionToEdit.getFee());
        Deadline updatedDeadline = editCommissionDescriptor.getDeadline().orElse(commissionToEdit.getDeadline());
        CompletionStatus updatedCompletionStatus = editCommissionDescriptor.getCompletionStatus()
                .orElse(commissionToEdit.getCompletionStatus());

        Set<Tag> updatedTags = editCommissionDescriptor.getTags().orElse(commissionToEdit.getTags());

        Optional<Description> updatedDescription = Optional.ofNullable(
                editCommissionDescriptor.getDescription().orElse(commissionToEdit.getDescription().orElse(null)));


        UniqueIterationList oldIterations = commissionToEdit.getIterations();

        Commission.CommissionBuilder commissionBuilder = new Commission.CommissionBuilder(updatedTitle,
                updatedFee, updatedDeadline, updatedCompletionStatus, updatedTags);
        updatedDescription.ifPresent(commissionBuilder::setDescription);
        commissionBuilder.setIterations(oldIterations);
        return commissionBuilder.build(commissionToEdit.getCustomer());

    }

    @Override
    public CommandResult execute(Model model, Storage storage) throws CommandException {
        requireNonNull(model);

        List<Commission> lastShownList = model.getFilteredCommissionList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_COMMISSION_DISPLAYED_INDEX);
        }

        Commission commissionToEdit = lastShownList.get(index.getZeroBased());
        Commission editedCommission = createEditedCommission(commissionToEdit, editCommissionDescriptor);
        Customer selectedCustomer = commissionToEdit.getCustomer();
        if (!commissionToEdit.isSameCommission(editedCommission) && selectedCustomer.hasCommission(editedCommission)) {
            throw new CommandException(MESSAGE_DUPLICATE_COMMISSION);
        }

        model.setCommission(selectedCustomer, commissionToEdit, editedCommission);
        model.selectCommission(editedCommission);

        model.updateFilteredCommissionList(PREDICATE_SHOW_ALL_COMMISSIONS);
        model.selectTab(GuiTab.COMMISSION);
        return new CommandResult(String.format(MESSAGE_EDIT_COMMISSION_SUCCESS, editedCommission));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommissionCommand)) {
            return false;
        }

        // state check
        EditCommissionCommand e = (EditCommissionCommand) other;
        return index.equals(e.index)
                && editCommissionDescriptor.equals(e.editCommissionDescriptor);
    }

    /**
     * Stores the details to edit the commission with. Each non-empty field value will replace the
     * corresponding field value of the commission.
     */
    public static class EditCommissionDescriptor {
        private Title title;
        private Fee fee;
        private Deadline deadline;
        private CompletionStatus completionStatus;
        private Description description;
        private Set<Tag> tags;

        public EditCommissionDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditCommissionDescriptor(EditCommissionDescriptor toCopy) {
            setTitle(toCopy.title);
            setFee(toCopy.fee);
            setDeadline(toCopy.deadline);
            setCompletionStatus(toCopy.completionStatus);
            setTags(toCopy.tags);
            setDescription(toCopy.description);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(title, fee, deadline, completionStatus, tags, description);
        }

        public Optional<Title> getTitle() {
            return Optional.ofNullable(title);
        }

        public void setTitle(Title title) {
            this.title = title;
        }

        public Optional<Fee> getFee() {
            return Optional.ofNullable(fee);
        }

        public void setFee(Fee fee) {
            this.fee = fee;
        }

        public Optional<Deadline> getDeadline() {
            return Optional.ofNullable(deadline);
        }

        public void setDeadline(Deadline deadline) {
            this.deadline = deadline;
        }

        public Optional<CompletionStatus> getCompletionStatus() {
            return Optional.ofNullable(completionStatus);
        }

        public void setCompletionStatus(CompletionStatus completionStatus) {
            this.completionStatus = completionStatus;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditCommissionDescriptor)) {
                return false;
            }

            // state check
            EditCommissionDescriptor e = (EditCommissionDescriptor) other;

            return getTitle().equals(e.getTitle())
                    && getFee().equals(e.getFee())
                    && getDeadline().equals(e.getDeadline())
                    && getCompletionStatus().equals(e.getCompletionStatus())
                    && getTags().equals(e.getTags())
                    && getDescription().equals(e.getDescription());
        }

    }
}
