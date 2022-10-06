package seedu.address.model.person;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Tag;

public class TagContainsKeywordsPredicate implements Predicate<Person> {
    private final String operator;
    private final List<Tag> keywords;

    public TagContainsKeywordsPredicate(List<String> keywords) {
        operator = keywords.get(0);
        int listSize = keywords.size();
        this.keywords = new ArrayList<>();
        for (int i = 1; i < listSize; i++) {
            this.keywords.add(new Tag(keywords.get(i)));
        }
    }

    @Override
    public boolean test(Person person) {
        if (operator.equals("AND")) {
            return keywords.stream()
                    .allMatch(keyword -> person.getTags().contains(keyword));
        } else {
            return keywords.stream()
                    .anyMatch(keyword -> person.getTags().contains(keyword));
        }
    }
}
