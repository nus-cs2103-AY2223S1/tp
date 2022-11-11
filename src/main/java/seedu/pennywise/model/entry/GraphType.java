package seedu.pennywise.model.entry;

import static java.util.Objects.requireNonNull;
import static seedu.pennywise.commons.util.AppUtil.checkArgument;

/**
 * Represents a Graph's type in the PennyWise application.
 * Guarantees: immutable; is valid as declared in {@link #isValidGraphType(String)}}
 */
public class GraphType {
    public static final String GRAPH_TYPE_CATEGORY = "c";
    public static final String GRAPH_TYPE_MONTH = "m";
    public static final String GRAPH_TYPE_CATEGORY_LABEL = "category";
    public static final String GRAPH_TYPE_MONTH_LABEL = "month";

    public static final String MESSAGE_CONSTRAINTS = "Graph type should only be either '"
            + GRAPH_TYPE_CATEGORY
            + "' for pie chart or '"
            + GRAPH_TYPE_MONTH
            + "' for bar graph";
    public static final String VALIDATION_REGEX = "^\\s*([cm])\\s*$";

    /**
     * Types that can be used.
     */
    public enum Type {
        CATEGORY() {
            @Override
            public String toString() {
                return GRAPH_TYPE_CATEGORY_LABEL;
            }
        },
        MONTH() {
            @Override
            public String toString() {
                return GRAPH_TYPE_MONTH_LABEL;
            }
        };

        /**
         * Creates a {@code Type} with the input of {@code graphType}.
         *
         * @return Created graph type enumeration based on the given string.
         */
        public static Type of(String graphType) {
            boolean isCategoryGraph = graphType.equals(GRAPH_TYPE_CATEGORY);
            boolean isMonthGraph = graphType.equals(GRAPH_TYPE_MONTH);

            assert isCategoryGraph || isMonthGraph;

            if (isCategoryGraph) {
                return Type.CATEGORY;
            }
            return Type.MONTH;
        }
    };

    private final Type graphType;

    /**
     * Constructs a {@code GraphType}.
     *
     * @param graphType A valid graph type.
     */
    public GraphType(String graphType) {
        requireNonNull(graphType);
        checkArgument(isValidGraphType(graphType), MESSAGE_CONSTRAINTS);
        this.graphType = GraphType.Type.of(graphType);
    }

    /**
     * Returns true if a given string is a valid graph type.
     */
    public static boolean isValidGraphType(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public GraphType.Type getGraphType() {
        return graphType;
    }

    @Override
    public String toString() {
        return graphType.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GraphType // instanceof handles nulls
                && graphType.equals(((GraphType) other).graphType)); // state check
    }

    @Override
    public int hashCode() {
        return graphType.hashCode();
    }
}
