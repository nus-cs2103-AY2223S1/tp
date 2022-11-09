package seedu.address.model.person;

import java.util.Collection;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Represents a predicate to check if a Minecraft friend should be suggested
 * based on some supplied attribute constraints.
 */
public class PersonSuggestionPredicate implements Predicate<Person> {

    private final Set<DayTimeInWeek> dayTimeInWeek;
    private final Set<Keyword> keywords;

    /**
     * Creates a predicate based off the constraints below.
     * @param dayTimeInWeek The Set collection that contains {@code DayTimeInWeek}.
     * @param keywords The Set collection that contains the {@code Keyword} that the friend needs to contain.
     */
    public PersonSuggestionPredicate(Set<DayTimeInWeek> dayTimeInWeek, Set<Keyword> keywords) {
        this.dayTimeInWeek = dayTimeInWeek;
        this.keywords = keywords;
    }

    /**
     * Tests if the {@code person} should be suggested based on the attribute constraints given.
     * @param person A {@code Person}.
     * @return A boolean value.
     */
    @Override
    public boolean test(Person person) {

        Collection<String> predStrings = person.toPredicateCheckingString();

        // All keywords must be matched
        boolean isKeywordMatching = keywords.stream()
                .allMatch(keyword -> predStrings.stream()
                        .anyMatch(ps -> StringUtil.containsIgnoreCase(ps, keyword.toString())));

        // Only one time interval need to be matched
        boolean isDayTimeInWeekMatching = true;
        if (!dayTimeInWeek.isEmpty()) {
            if (!dayTimeInWeek.stream()
                    .anyMatch(dayTimeInWeek -> person.getTimesAvailable().stream()
                            .anyMatch(iTimesAvailable -> iTimesAvailable.isAvailable(dayTimeInWeek)))) {
                isDayTimeInWeekMatching = false;
            }
        }

        // Both the keyword and time interval conditions must be satisfied
        return isDayTimeInWeekMatching && isKeywordMatching;
    }

    @Override
    public boolean equals(Object other) {

        if (other == this) {
            return true;
        } else if (!(other instanceof PersonSuggestionPredicate)) {
            return false;
        }

        PersonSuggestionPredicate pred = (PersonSuggestionPredicate) other;
        return pred.keywords.equals(this.keywords) && pred.dayTimeInWeek.equals(this.dayTimeInWeek);

    }
}
