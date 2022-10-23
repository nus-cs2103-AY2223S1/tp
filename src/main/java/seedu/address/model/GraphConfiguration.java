package seedu.address.model;

import seedu.address.logic.commands.ViewCommand;
import seedu.address.model.entry.Entry;
import seedu.address.model.entry.EntryType;
import seedu.address.model.entry.GraphType;

/**
 * Encapsulates the configurations that determine the interactions and effects
 * on the displayed graph based on a user's command.
 */
public class GraphConfiguration {
    private final EntryType entryType;
    private final GraphType graphType;
    /** Whether the graph should be updated or not. */
    private final boolean shouldUpdateGraph;

    public GraphConfiguration(EntryType entryType, GraphType graphType, boolean shouldUpdateGraph) {
        this.entryType = entryType;
        this.graphType = graphType;
        this.shouldUpdateGraph = shouldUpdateGraph;
    }

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
        if (other == null) {
            return false;
        }
        if (!(other instanceof GraphConfiguration)) {
            return false;
        }
        GraphConfiguration otherGraphConfiguration = (GraphConfiguration) other;
        return getEntryType().equals(otherGraphConfiguration.getEntryType())
                && getGraphType().equals(otherGraphConfiguration.getGraphType())
                && getShouldUpdateGraph() == otherGraphConfiguration.getShouldUpdateGraph();
    }
}
