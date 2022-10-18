package seedu.address.model.assignment;

/**
 * Represents an Assignment's workload.
 * Workload can only be defined as low, medium or high.
 */
public enum Workload {
    HIGH {
        @Override
        public String toString() {
            return "HIGH";
        }
    },
    MEDIUM {
        @Override
        public String toString() {
            return "MEDIUM";
        }
    },
    LOW {
        @Override
        public String toString() {
            return "LOW";
        }
    },
}
