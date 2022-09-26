package seedu.address.model.client;

public class Income {

    private double income;

    public Income(double income) {
        this.income = income;
    }

    public double getIncome() {
        return income;
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
