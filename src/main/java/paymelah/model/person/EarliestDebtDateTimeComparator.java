package paymelah.model.person;

import java.util.Comparator;

/**
 * Compares a {@code Person} to another using their earliest Debt's {@code DebtDate} and {@code DebtTime}.
 * Note: this comparator imposes orderings that are inconsistent with {@code equals} in {@code Person}.
 */
public class EarliestDebtDateTimeComparator implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        if (o1.getDebts().isEmpty() && o2.getDebts().isEmpty()) {
            return 0;
        } else if (o1.getDebts().isEmpty()) {
            return 1;
        } else if (o2.getDebts().isEmpty()) {
            return -1;
        } else {
            return o1.compareEarliestDebtDateTimeWith(o2);
        }
    }

}
