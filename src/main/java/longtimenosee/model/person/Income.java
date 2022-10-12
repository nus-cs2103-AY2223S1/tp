package longtimenosee.model.person;

/**
 * Immutable representation of a Perosn's income
 */
public class Income {

    public static final String MESSAGE_FORMAT_CONSTRAINTS = "Error: Please enter a double value for income";
    public final String value;

    private double income;


    /**
     * Constructs an {@code Income}.
     *
     * @param income A valid email address.
     */
    public Income(String income) {
        value = income;
        this.income = Double.valueOf(income);
    }

    public double getIncome() {
        return income;
    }

    /**
     * Checks if income format is valid
     * @param income
     * @return
     */
    public static boolean isValidFormat(String income) {
        try {
            double testIncome = Double.valueOf(income);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Income)) {
            return false;
        }
        Income otherIncome = (Income) other;
        return this.getIncome() == (otherIncome.getIncome());
    }

    @Override
    public String toString() {
        return value;
    }


}
