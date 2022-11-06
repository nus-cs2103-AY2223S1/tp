package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.commons.core.order.Order;
import seedu.address.model.Model;

/**
 * Sort all persons in the address book by
 * name in lexicographical order
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sort the list of contact by "
            + "specified field either in increasing or decreasing order.\n"
            + "Example: " + COMMAND_WORD + " A-Z "
            + PREFIX_NAME;

    public static final String MESSAGE_SUCCESS = "Contacts sorted successfully";
    public static final String MESSAGE_NOT_SELECTED = "At least one field must be selected.\n"
            + "Pick from either:\n"
            + "- n/\n"
            + "- m/";
    public static final String MULTIPLE_SELECTED = "Please only pick either n/ or m/. Do not pick both.";
    private final Order order;
    private final SortPersonListDescriptor sortPersonListDescriptor;

    /**
     * @param order the list will be sorted by
     * @param sortPersonListDescriptor the fields that the list will be sorted by
     */
    public SortCommand(Order order, SortPersonListDescriptor sortPersonListDescriptor) {
        requireNonNull(order);
        requireNonNull(sortPersonListDescriptor);
        this.order = order;
        this.sortPersonListDescriptor = sortPersonListDescriptor;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sort(order,
                sortPersonListDescriptor.hasName,
                sortPersonListDescriptor.hasModuleCode);
        return new CommandResult(MESSAGE_SUCCESS);
    }
    @Override
    public boolean equals(Object other) {
        return other == this
                || other instanceof SortCommand;
    }

    /**
     * Stores the details to sort the person list with.
     */
    public static class SortPersonListDescriptor {
        private boolean hasName = false;
        private boolean hasModuleCode = false;

        public SortPersonListDescriptor() {
        }

        /**
         * Returns true if at least one field is added.
         */
        public boolean isAnyFieldSelected() {
            return this.hasName || this.hasModuleCode;
        }
        public void setName(boolean hasName) {
            this.hasName = hasName;
        }
        public void setModuleCode(boolean hasModuleCode) {
            this.hasModuleCode = hasModuleCode;
        }
    }
}
