package seedu.address.model.assignment;

/**
 * Represents an Assignment's workload.
 * Workload can only be defined as low, medium or high.
 */
public enum Workload {

    LOW {
        @Override
        public String toString() {
            return "LOW";
        }

        @Override
        public int workloadValue() {
            return LOW_WORKLOAD_VALUE;
        }
    },
    MEDIUM {
        @Override
        public String toString() {
            return "MEDIUM";
        }

        @Override
        public int workloadValue() {
            return MEDIUM_WORKLOAD_VALUE;
        }
    },
    HIGH {
        @Override
        public String toString() {
            return "HIGH";
        }

        @Override
        public int workloadValue() {
            return HIGH_WORKLOAD_VALUE;
        }
    };

    public static final String MESSAGE_CONSTRAINTS = "Workload must be either low, medium or high!";

    private static final int LOW_WORKLOAD_VALUE = 1;

    private static final int MEDIUM_WORKLOAD_VALUE = 3;

    private static final int HIGH_WORKLOAD_VALUE = 5;

    public int workloadValue() {
        return 0;
    }


}
