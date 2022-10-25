package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRAPH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import java.time.YearMonth;
import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.GraphConfiguration;
import seedu.address.model.Model;
import seedu.address.model.entry.Entry;
import seedu.address.model.entry.EntryInYearMonthPredicate;
import seedu.address.model.entry.EntryType;
import seedu.address.model.entry.GraphType;

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

    private Predicate<Entry> predicateSelector(ViewEntriesDescriptor viewEntriesDescriptor) {
        GraphType graphType = viewEntriesDescriptor.getGraphType();

        // If the specified graph type is "Month", we select the predicate to check if the entry
        // is in the corresponding month.
        if (graphType.getGraphType().equals(GraphType.Type.MONTH)) {
            Optional<YearMonth> yearMonth = viewEntriesDescriptor.getYearMonth();
            assert yearMonth.isPresent();

            return new EntryInYearMonthPredicate(yearMonth.get());
        }

        // By default, show all entries in the entry list.
        return Model.PREDICATE_SHOW_ALL_ENTRIES;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        EntryType entryType = viewEntriesDescriptor.getEntryType();
        GraphType graphType = viewEntriesDescriptor.getGraphType();
        GraphConfiguration graphConfiguration = new GraphConfiguration(entryType, graphType, true);


        Predicate<Entry> predicate = predicateSelector(viewEntriesDescriptor);

        switch (entryType.getEntryType()) {
        case EXPENDITURE:
            model.updateFilteredExpenditureList(predicate);
            break;
        case INCOME:
            model.updateFilteredIncomeList(predicate);
            break;
        default:
            break;
        }

        return new CommandResult(
                String.format(MESSAGE_SUCCESS, entryType, graphType), false, false, graphConfiguration);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof ViewCommand)) {
            return false;
        }
        ViewCommand otherViewCommand = (ViewCommand) other;
        return viewEntriesDescriptor.equals(otherViewCommand.viewEntriesDescriptor);
    }

    /**
     * Stores the details to view entries. Each non-empty field value will replace the
     * default value of the view configuration.
     */
    public static class ViewEntriesDescriptor {
        private EntryType entryType;
        private YearMonth yearMonth;
        private GraphType graphType;

        public ViewEntriesDescriptor() {
        }

        /**
         * Copy constructor.
         */
        public ViewEntriesDescriptor(ViewEntriesDescriptor toCopy) {
            setEntryType(toCopy.entryType);
            setGraphType(toCopy.graphType);
            setYearMonth(toCopy.yearMonth);
        }

        public void setEntryType(EntryType entryType) {
            // Defensive approach to guard against null entry types
            assert entryType != null;
            this.entryType = entryType;
        }

        public EntryType getEntryType() {
            return entryType;
        }

        public void setYearMonth(YearMonth yearMonth) {
            this.yearMonth = yearMonth;
        }

        public Optional<YearMonth> getYearMonth() {
            return Optional.ofNullable(yearMonth);
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
            if (getGraphType().equals(graphTypeMonth) && getYearMonth().isEmpty()) {
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
            // instanceof handles nulls
            if (!(other instanceof ViewEntriesDescriptor)) {
                return false;
            }

            // state check
            ViewEntriesDescriptor otherEntriesDescriptor = (ViewEntriesDescriptor) other;

            return getEntryType().equals(otherEntriesDescriptor.getEntryType())
                    && getGraphType().equals(otherEntriesDescriptor.getGraphType())
                    && getYearMonth().equals(otherEntriesDescriptor.getYearMonth());
        }
    }
}
