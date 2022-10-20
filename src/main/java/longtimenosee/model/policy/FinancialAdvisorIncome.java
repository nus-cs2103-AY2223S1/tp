package longtimenosee.model.policy;

import static longtimenosee.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.time.LocalDate;
import java.time.Period;
import java.util.Set;

import javafx.collections.ObservableList;
import longtimenosee.model.Model;
import longtimenosee.model.person.Person;

/**
 * Represents the income that a Financial Advisor earns.
 */
public class FinancialAdvisorIncome {

    private float firstYearIncome;
    private float secondYearIncome;
    private float thirdYearIncome;
    private LocalDate year;
    public FinancialAdvisorIncome() {}

    public LocalDate getYear() {
        return year;
    }
    /**
     * Calculates which term in the three years should we take for commission
     */
    public int calculateYear(LocalDate year, AssignedPolicy policy) {
        LocalDate startDate = policy.getStartDate().getDate();
        LocalDate endDate = policy.getEndDate().getDate();

        Period duration = Period.between(LocalDate.parse(startDate.toString()),
                LocalDate.parse(year.toString()));
        if (duration.getYears() > 1) {
            return 2;
        } else if (duration.getYears() > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Calculates the income of financial advisor in 3 years interval from specified date
     */
    public void threeYearIncome(LocalDate date, Model model) {
        firstYearIncome = calculateIncome(date, model);
        secondYearIncome = calculateIncome(date.plusYears(1), model);
        thirdYearIncome = calculateIncome(date.plusYears(2), model);
    }

    public float getFirstYearIncome() {
        return firstYearIncome;
    }

    public float getSecondYearIncome() {
        return secondYearIncome;
    }

    public float getThirdYearIncome() {
        return thirdYearIncome;
    }

    /**
     * Calculates the income of financial advisor in the specified year
     */
    public float calculateIncome(LocalDate year, Model model) {
        float yearlyIncome = 0;
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        ObservableList<Person> personList = model.getFilteredPersonList();
        LocalDate fullYear = LocalDate.of(year.getYear(), 1, 1);
        this.year = fullYear;
        for (Person currentPerson : personList) {
            Set<AssignedPolicy> assignedPolicy = currentPerson.getAssignedPolicies();

            for (AssignedPolicy policy : assignedPolicy) { //iterate through each policy of a person
                float policyPremiumPaid = policy.getPremium().numericValue; //Premium paid is constant
                Period duration = Period.between(LocalDate.parse(fullYear.toString()),
                        LocalDate.parse(policy.getEndDate().getDate().toString()));
                if (policy.getEndDate().getDate().isBefore(fullYear)) {
                    continue;
                } else if ((policy.getEndDate().getDate().isAfter(fullYear)
                        || policy.getEndDate().getDate().equals(fullYear))
                        && (policy.getStartDate().getDate().isBefore(fullYear)
                        || policy.getStartDate().getDate().equals(fullYear))) {
                    // for case that year falls within
                    int months = duration.getMonths(); //why 0 months?
                    if (months == 0) {
                        if (duration.getYears() > 1) {
                            months = 12;
                        }
                    }
                    yearlyIncome +=
                            policy.getPolicy().getCommission().getCommission(calculateYear(year, policy))
                                    * months
                                    * policyPremiumPaid;
                } else {
                    continue;
                }
            }

        }
        return yearlyIncome;
    }

}
