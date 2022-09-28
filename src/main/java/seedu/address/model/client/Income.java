package seedu.address.model.client;

public class Income {

    public static String MESSAGE_FORMAT_CONSTRAINTS = "Error: Please enter a double value for income";
    private double income;

    public String value;

    public Income(String income) {
        value = income;
        this.income = Double.valueOf(income);
    }

    public double getIncome() {
        return income;
    }

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


}
