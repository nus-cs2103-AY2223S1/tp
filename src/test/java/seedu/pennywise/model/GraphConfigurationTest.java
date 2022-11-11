package seedu.pennywise.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.pennywise.logic.commands.CommandTestUtil.VALID_GRAPH_TYPE_CATEGORY;
import static seedu.pennywise.logic.commands.CommandTestUtil.VALID_GRAPH_TYPE_MONTH;
import static seedu.pennywise.logic.commands.CommandTestUtil.VALID_TYPE_EXPENDITURE;
import static seedu.pennywise.logic.commands.CommandTestUtil.VALID_TYPE_INCOME;

import org.junit.jupiter.api.Test;

import seedu.pennywise.logic.commands.ClearCommand;
import seedu.pennywise.model.entry.EntryType;
import seedu.pennywise.model.entry.GraphType;

public class GraphConfigurationTest {
    @Test
    public void equals() {
        final GraphConfiguration graphConfiguration = new GraphConfiguration(
                new EntryType(VALID_TYPE_INCOME),
                new GraphType(VALID_GRAPH_TYPE_CATEGORY),
                false
        );

        // same values -> returns true
        GraphConfiguration graphConfigurationWithSameValues = new GraphConfiguration(
                new EntryType(VALID_TYPE_INCOME),
                new GraphType(VALID_GRAPH_TYPE_CATEGORY),
                false
        );
        assertEquals(graphConfiguration, graphConfigurationWithSameValues);

        // same object -> returns true
        assertEquals(graphConfiguration, graphConfiguration);

        // null -> returns false
        assertNotEquals(null, graphConfiguration);

        // different types -> returns false
        assertNotEquals(graphConfiguration, new ClearCommand());

        // Heuristic: No more than one invalid input in a test case

        // different entry type -> returns false
        assertNotEquals(graphConfiguration, new GraphConfiguration(
                new EntryType(VALID_TYPE_EXPENDITURE),
                new GraphType(VALID_GRAPH_TYPE_CATEGORY),
                false
        ));

        // different graph type -> returns false
        assertNotEquals(graphConfiguration, new GraphConfiguration(
                new EntryType(VALID_TYPE_INCOME),
                new GraphType(VALID_GRAPH_TYPE_MONTH),
                false
        ));

        // different update graph flag -> returns false
        assertNotEquals(graphConfiguration, new GraphConfiguration(
                new EntryType(VALID_TYPE_INCOME),
                new GraphType(VALID_GRAPH_TYPE_CATEGORY),
                true
        ));
    }
}
