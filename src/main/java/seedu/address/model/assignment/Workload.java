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
    },
    MEDIUM {
        @Override
        public String toString() {
            return "MEDIUM";
        }
    },
    HIGH {
        @Override
        public String toString() {
            return "HIGH";
        }
    };


    public static final String MESSAGE_CONSTRAINTS = "Workload must be either low, medium or high!";

}
