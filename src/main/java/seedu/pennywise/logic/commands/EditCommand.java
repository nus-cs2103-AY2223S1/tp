package seedu.pennywise.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.pennywise.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.pennywise.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.pennywise.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.pennywise.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.pennywise.logic.parser.CliSyntax.PREFIX_TYPE;

import java.util.List;
import java.util.Optional;

import seedu.pennywise.commons.core.Messages;
import seedu.pennywise.commons.core.index.Index;
import seedu.pennywise.commons.util.CollectionUtil;
import seedu.pennywise.logic.commands.exceptions.CommandException;
import seedu.pennywise.model.GraphConfiguration;
import seedu.pennywise.model.Model;
import seedu.pennywise.model.entry.Amount;
import seedu.pennywise.model.entry.Date;
import seedu.pennywise.model.entry.Description;
import seedu.pennywise.model.entry.Entry;
import seedu.pennywise.model.entry.EntryType;
import seedu.pennywise.model.entry.Expenditure;
import seedu.pennywise.model.entry.Income;
import seedu.pennywise.model.entry.Tag;

/**
 * Edits the details of an existing entry in the PennyWise application.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the entry identified "
            + "by the index number used in the displayed entry list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TYPE + "TYPE] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_AMOUNT + "AMOUNT] "
            + "[" + PREFIX_DATE + "DATE] "
            + "[" + PREFIX_TAG + "TAG]  \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TYPE + "e "
            + PREFIX_DESCRIPTION + "LunchDeck";

    public static final String MESSAGE_EDIT_ENTRY_SUCCESS = "Edited Entry: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_ENTRY = "This entry already exists in the PennyWise application.";

    private final Index index;
    private final EditEntryDescriptor editEntryDescriptor;
    private final EntryType entryType;

    /**
     * @param index               of the entry in the filtered entry list to edit
     * @param editEntryDescriptor Details to edit the entry with
     */
    public EditCommand(Index index, EditEntryDescriptor editEntryDescriptor) {
        requireNonNull(index);
        requireNonNull(editEntryDescriptor);

        this.index = index;
        this.editEntryDescriptor = new EditEntryDescriptor(editEntryDescriptor);
        this.entryType = editEntryDescriptor.getType().get();
    }

    private Entry getEntryToEdit(Index targetIndex, List<Entry> lastShownList)
            throws CommandException {
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX);
        }
        return lastShownList.get(targetIndex.getZeroBased());
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Entry> lastShownList;
        switch (entryType.getEntryType()) {
        case EXPENDITURE:
            lastShownList = model.getFilteredExpenditureList();
            break;
        case INCOME:
            lastShownList = model.getFilteredIncomeList();
            break;
        default:
            // should never reach here
            return null;
        }


        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX);
        }

        Entry entryToEdit = getEntryToEdit(index, lastShownList);
        Entry editedEntry = createdEditedEntry(entryToEdit, editEntryDescriptor);

        switch (entryType.getEntryType()) {
        case EXPENDITURE:
            if (!entryToEdit.isSameEntry(editedEntry) && model.hasExpenditure(editedEntry)) {
                throw new CommandException(MESSAGE_DUPLICATE_ENTRY);
            }
            model.setExpenditure(entryToEdit, editedEntry);
            break;
        case INCOME:
            if (!entryToEdit.isSameEntry(editedEntry) && model.hasIncome(editedEntry)) {
                throw new CommandException(MESSAGE_DUPLICATE_ENTRY);
            }
            model.setIncome(entryToEdit, editedEntry);
            break;
        default:
            // should never reach here
            break;
        }
        return new CommandResult(String.format(MESSAGE_EDIT_ENTRY_SUCCESS, editedEntry),
                false,
                false,
                GraphConfiguration.UPDATE_CURR_GRAPH_CONFIG);
    }

    /**
     * Creates and returns a {@code Entry} with the details of {@code entryToEdit}
     * edited with {@code editEntryDescriptor}.
     */
    private static Entry createdEditedEntry(Entry entryToEdit, EditEntryDescriptor editEntryDescriptor) {
        assert entryToEdit != null;

        Description updatedDescription = editEntryDescriptor.getDescription().orElse(entryToEdit.getDescription());
        Amount updatedAmount = editEntryDescriptor.getAmount().orElse(entryToEdit.getAmount());
        Date updatedDate = editEntryDescriptor.getDate().orElse(entryToEdit.getDate());
        Tag updatedTag = editEntryDescriptor.getTag().orElse(entryToEdit.getTag());

        switch (editEntryDescriptor.getType().get().getEntryType()) {
        case INCOME:
            return new Income(updatedDescription, updatedDate, updatedAmount, updatedTag);
        case EXPENDITURE:
            return new Expenditure(updatedDescription, updatedDate, updatedAmount, updatedTag);
        default:
            return null;
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editEntryDescriptor.equals(e.editEntryDescriptor);
    }

    /**
     * Stores the details to edit the entry with. Each non-empty field value will replace the
     * corresponding field value of the entry.
     */
    public static class EditEntryDescriptor {
        private EntryType entryType;
        private Description description;
        private Date date;
        private Amount amount;
        private Tag tag;


        public EditEntryDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tag} is used internally.
         */
        public EditEntryDescriptor(EditEntryDescriptor toCopy) {
            setDescription(toCopy.description);
            setType(toCopy.entryType);
            setAmount(toCopy.amount);
            setDate(toCopy.date);
            setTag(toCopy.tag);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(description, amount, date, tag);
        }

        public void setType(EntryType entryType) {
            this.entryType = entryType;
        }

        public Optional<EntryType> getType() {
            return Optional.ofNullable(entryType);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setAmount(Amount amount) {
            this.amount = amount;
        }

        public Optional<Amount> getAmount() {
            return Optional.ofNullable(amount);
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public Optional<Date> getDate() {
            return Optional.ofNullable(date);
        }

        /**
         * Sets {@code tag} to this object's {@code tag}.
         * A defensive copy of {@code tag} is used internally.
         */
        public void setTag(Tag tag) {
            this.tag = tag;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tag} is null.
         */
        public Optional<Tag> getTag() {
            return (tag != null) ? Optional.of(tag) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditEntryDescriptor)) {
                return false;
            }

            // state check
            EditEntryDescriptor e = (EditEntryDescriptor) other;

            return getDescription().equals(e.getDescription())
                    && getType().equals(e.getType())
                    && getAmount().equals(e.getAmount())
                    && getDate().equals(e.getDate())
                    && getTag().equals(e.getTag());
        }
    }

}
