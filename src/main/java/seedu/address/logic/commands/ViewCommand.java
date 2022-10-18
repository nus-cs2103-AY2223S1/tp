package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRAPH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
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
    public static final String MESSAGE_INVALID_ENTRY_TYPE = "Entry type is invalid.";

    private static final String ENTRY_EXPENDITURE = "expenditures";
    private static final String ENTRY_INCOME = "income";
    private static final String GRAPH_CATEGORY = "category";
    private static final String GRAPH_MONTH = "month";

    private final EntryType entryType;

    private final GraphType graphType;

    /**
     * Creates a ViewCommand to view the specified {@code entryType}
     */
    public ViewCommand(EntryType entryType, GraphType graphType) {
        requireNonNull(entryType);
        this.entryType = entryType;
        this.graphType = graphType;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        switch (entryType.getEntryType()) {
        case EXPENDITURE:
            switch (graphType.getGraphType()) {
            case CATEGORY:
                System.out.println("[ViewCommand] Show all expenditure by categories");
                model.updateExpensePieChart();
                return new CommandResult(String.format(MESSAGE_SUCCESS, ENTRY_EXPENDITURE, GRAPH_CATEGORY), false,
                                         false, true);
            case MONTH:
                break;
            default:
                break;
            }
            break;

        case INCOME:
            switch (graphType.getGraphType()) {
            case CATEGORY:
                System.out.println("[ViewCommand] Show all expenditure by categories");
                model.updateIncomePieChart();
                return new CommandResult(String.format(MESSAGE_SUCCESS, ENTRY_INCOME, GRAPH_CATEGORY), false, false,
                                         true);
            case MONTH:
                break;
            default:
                break;
            }
            break;
        default:
            //should never reach here
            break;
        }

        throw new CommandException(MESSAGE_INVALID_ENTRY_TYPE);
    }
}
