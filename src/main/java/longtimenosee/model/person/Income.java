package longtimenosee.model.person;

import java.util.Comparator;

/**
 * Immutable representation of a Person's income
 */
public class Income {

    public static final Comparator<Person> INCOME_COMPARATOR = new Comparator<Person>() {
        @Override
        public int compare(Person p1, Person p2) {
            return Double.compare(p1.getIncome().getIncome(), p2.getIncome().getIncome());
        }
    };
    public static final String MESSAGE_FORMAT_CONSTRAINTS = "Please enter a decimal number for your income";
    public static final String SORT_INCOME = "income";
    public static final String VALUE_FORMAT_CONSTRAINTS = "Please ensure your income is a positive value :D";

    public final String value;
    public final IncomeBracket incomeBracket;

    private double income;
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
     * Rejects negative income values
     * Asserts that income passed in can be cast into a valid double value
     * @param income value
     */
    public static boolean isPositiveIncome(String income) {
        assert(isValidFormat(income));
        double incomeValue = Double.valueOf(income);
        return incomeValue >= 0;
    }

    /**
     * Classifies a yearly income value into it's appropriate income bracket
     * This information is based off IRAS values for FY 2023/24
     * @ shorturl.at/oPQRV
     */
    public enum IncomeBracket {
        First("first"), Second("second"), Third("third"),
        Fourth("fourth"), Fifth("fifth"), Sixth("sixth"),
        Seventh("seventh"), Eight("eighth"), Ninth("ninth"),
        Tenth("tenth");

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
