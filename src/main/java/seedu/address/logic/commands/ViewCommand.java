package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRAPH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import java.util.Optional;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.GraphConfiguration;
import seedu.address.model.Model;
import seedu.address.model.entry.EntryType;
import seedu.address.model.entry.GraphType;
import seedu.address.model.entry.Month;

/**
 * View income/expenditure entries to the application.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_SUCCESS = "Show graphically all %s by %s";
    public static final String MESSAGE_USAGE =
        COMMAND_WORD + ": View income/expenditure entries to PennyWise. " + "Parameters: " + PREFIX_TYPE + "TYPE " + "["
        + PREFIX_GRAPH + "GRAPH]\n" + "Example: " + COMMAND_WORD + " " + PREFIX_TYPE + "e " + PREFIX_GRAPH + "c ";

    private final ViewEntriesDescriptor viewEntriesDescriptor;

    /**
     * Creates a ViewCommand to view the specified {@code entryType}.
     */
    public ViewCommand(ViewEntriesDescriptor viewEntriesDescriptor) {
        requireNonNull(viewEntriesDescriptor);
        this.viewEntriesDescriptor = viewEntriesDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        EntryType entryType = viewEntriesDescriptor.getEntryType();
        GraphType graphType = viewEntriesDescriptor.getGraphType();
        GraphConfiguration graphConfiguration = new GraphConfiguration(entryType, graphType, true);

        return new CommandResult(
                String.format(MESSAGE_SUCCESS, entryType, graphType), false, false, graphConfiguration);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof ViewCommand)) {
            return false;
        }
        ViewCommand otherViewCommand = (ViewCommand) other;
        return viewEntriesDescriptor.equals(otherViewCommand.viewEntriesDescriptor);
    }

    /**
     * Stores the details to view entries. Each non-empty field value will replace the
     * default value of the viwe configuration.
     */
    public static class ViewEntriesDescriptor {
        private EntryType entryType;
        private Month month;
        private GraphType graphType;

        public ViewEntriesDescriptor() {
        }

        /**
         * Copy constructor.
         */
        public ViewEntriesDescriptor(ViewEntriesDescriptor toCopy) {
            setEntryType(toCopy.entryType);
            setGraphType(toCopy.graphType);
            setMonth(toCopy.month);
        }

        public void setEntryType(EntryType entryType) {
            // Defensive approach to guard against null entry types
            assert entryType != null;
            this.entryType = entryType;
        }

        public EntryType getEntryType() {
            return entryType;
        }

        public void setMonth(Month month) {
            this.month = month;
        }

        public Optional<Month> getMonth() {
            return Optional.ofNullable(month);
        }

        public void setGraphType(GraphType graphType) {
            this.graphType = graphType;
        }

        public GraphType getGraphType() {
            return graphType;
        }

        /**
         * Returns true if and only if the provided view parameter configurations are valid. The
         * view parameter configurations are valid if:
         *
         * <ul>
         * <li>Entry type must be specified</li>
         * <li>Graph type must be specified</li>
         * <li>If graph type is 'm' (month), then month must be specified. Otherwise, month is ignored.</li>
         * </ul>
         *
         * @return True if and only if the provided view parameter configurations are valid.
         */
        public boolean isValid() {
            if (getEntryType() == null || getGraphType() == null) {
                return false;
            }
            GraphType graphTypeMonth = new GraphType(GraphType.GRAPH_TYPE_MONTH);
            if (getGraphType().equals(graphTypeMonth) && getMonth().isEmpty()) {
                return false;
            }
            return true;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }
            if (other == null) {
                return false;
            }
            // instanceof handles nulls
            if (!(other instanceof ViewEntriesDescriptor)) {
                return false;
            }

            // state check
            ViewEntriesDescriptor otherEntriesDescriptor = (ViewEntriesDescriptor) other;

            return getEntryType().equals(otherEntriesDescriptor.getEntryType())
                    && getGraphType().equals(otherEntriesDescriptor.getGraphType())
                    && getMonth().equals(otherEntriesDescriptor.getMonth());
        }
    }
}
