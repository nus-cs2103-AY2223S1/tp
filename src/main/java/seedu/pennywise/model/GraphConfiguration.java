package seedu.pennywise.model;

import seedu.pennywise.model.entry.EntryType;
import seedu.pennywise.model.entry.GraphType;

/**
 * Encapsulates the configurations that determine the interactions and effects
 * on the displayed graph based on a user's command.
 */
public class GraphConfiguration {
    public static final GraphConfiguration UPDATE_CURR_GRAPH_CONFIG =
            new GraphConfiguration(null, null, true);
    private final EntryType entryType;
    private final GraphType graphType;
    /** Whether the graph should be updated or not. */
    private final boolean shouldUpdateGraph;

    /**
     * Constructs a {@code GraphConfiguration} with the specified {@code entryType}, {@code graphType}
     * and {@code shouldUpdateGraph}.
     *
     * @param entryType The type of the entry to be displayed on the graph.
     * @param graphType The type of the graph.
     * @param shouldUpdateGraph If true, the graph will be updated when the command is executed.
     */
    public GraphConfiguration(EntryType entryType, GraphType graphType, boolean shouldUpdateGraph) {
        this.entryType = entryType;
        this.graphType = graphType;
        this.shouldUpdateGraph = shouldUpdateGraph;
    }

    /**
     * Constructs and returns a default configuration for the graph, where the:
     *
     * <ul>
     *     <li>Entry type is specified as "Expenditure"</li>
     *     <li>Graph type is specified as "Category"</li>
     *     <li>Graph should not be updated</li>
     * </ul>
     *
     * @return Default construction of graph configuration.
     */
    public static GraphConfiguration ofDefault() {
        EntryType expenditureEntryType = new EntryType(EntryType.ENTRY_TYPE_EXPENDITURE);
        GraphType categoryGraphType = new GraphType(GraphType.GRAPH_TYPE_CATEGORY);
        return new GraphConfiguration(expenditureEntryType, categoryGraphType, false);
    }

    public EntryType getEntryType() {
        return entryType;
    }

    public GraphType getGraphType() {
        return graphType;
    }

    public boolean getShouldUpdateGraph() {
        return shouldUpdateGraph;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof GraphConfiguration)) {
            return false;
        }
        GraphConfiguration otherGraphConfiguration = (GraphConfiguration) other;
        return getEntryType().equals(otherGraphConfiguration.getEntryType())
                && getGraphType().equals(otherGraphConfiguration.getGraphType())
                && getShouldUpdateGraph() == otherGraphConfiguration.getShouldUpdateGraph();
    }
}
