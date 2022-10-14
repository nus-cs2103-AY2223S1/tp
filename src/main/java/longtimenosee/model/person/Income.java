package longtimenosee.model.person;

/**
 * Immutable representation of a Person's income
 */
public class Income {

    public static final String MESSAGE_FORMAT_CONSTRAINTS = "Error: Please enter a double value for income";
    public final String value;
    public final IncomeBracket incomeBracket;

    private double income;

    /**
     * Classifies a yearly income value into it's appropriate income bracket
     * This information is based off IRAS values for FY 2023/24
     * @ shorturl.at/oPQRV
     */
    public enum IncomeBracket {
        First("First"), Second("Second"), Third("Third"),
        Fourth("Fourth"), Fifth("Fifth"), Sixth("Sixth"),
        Seventh("Seventh"), Eight("Eighth"), Ninth("Ninth"),
        Tenth("Tenth");

        private String message;

        IncomeBracket(String message) {
            this.message = message;
        }
        @Override
        public String toString() {
            return "The income for this individual falls in the "
                    + message + " bracket";
        }
    }

    /**
     * Constructs an {@code Income}.
     *
     * @param income A valid email address.
     */
    public Income(String income) {
        value = income;
        this.income = Double.valueOf(income);
        this.incomeBracket = parseIncome(this.income);
    }

    /**
     * Parses the given income into the appropriate income bracket.
     * @param income
     * @return
     */
    public static IncomeBracket parseIncome(double income) {
        if (income <= 30000) {
            return IncomeBracket.First;
        } else if (income <= 40000) {
            return IncomeBracket.Second;
        } else if (income <= 80000) {
            return IncomeBracket.Third;
        } else if (income <= 120000) {
            return IncomeBracket.Fourth;
        } else if (income <= 160000) {
            return IncomeBracket.Fifth;
        } else if (income <= 200000) {
            return IncomeBracket.Sixth;
        } else if (income <= 240000) {
            return IncomeBracket.Seventh;
        } else if (income <= 280000) {
            return IncomeBracket.Eight;
        } else if (income <= 320000) {
            return IncomeBracket.Ninth;
        } else {
            return IncomeBracket.Tenth;
        }
    }

    public IncomeBracket getIncomeBracket() {
        return incomeBracket;
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
        return incomeBracket.toString();
    }


}
