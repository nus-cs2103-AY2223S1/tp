package seedu.address.model.person.predicates;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

/**
 * A predicate the encapsulates the combination of several other Person predicates.
 */
public class CombinedPersonPredicate implements Predicate<Person> {
    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final List<String> tagList;

    // There is no equals() method for predicates. Ensure this predicate and variables to generate it are always final!
    private final Predicate<Person> combinedPredicate;

    /**
     * Creates a {@code CombinedPersonPredicate} object that tests if a given Person contains a given
     * name, phone, email, address and has a word-for-word match for tags.
     *
     * @param name The string to test on a {@code Person}'s name.
     * @param phone The string to test on a {@code Person}'s phone.
     * @param email The string to test on a {@code Person}'s email.
     * @param address The string to test on a {@code Person}'s address.
     * @param tagList The string to test on a {@code Person}'s list of tags.
     */
    public CombinedPersonPredicate(String name, String phone, String email, String address, List<String> tagList) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tagList = tagList;

        combinedPredicate = combineALlPredicates();
    }

    private Predicate<Person> combineALlPredicates() {
        List<Predicate<Person>> personPredicates = new ArrayList<>();

        if (!name.isEmpty()) {
            addNamePredicate(personPredicates);
        }
        if (!phone.isEmpty()) {
            addPhonePredicate(personPredicates);
        }
        if (!email.isEmpty()) {
            addEmailPredicate(personPredicates);
        }
        if (!address.isEmpty()) {
            addAddressPredicate(personPredicates);
        }
        if (!tagList.isEmpty()) {
            addTagListPredicate(personPredicates);
        }

        return personPredicates.stream().reduce(PREDICATE_SHOW_ALL_PERSONS, Predicate::and);
    }

    private void addNamePredicate(List<Predicate<Person>> personPredicates) {
        Predicate<Person> nameContainsSequencePredicate =
                person -> StringUtil.containsIgnoreCase(person.getName().fullName, name);

        personPredicates.add(nameContainsSequencePredicate);
    }

    private void addPhonePredicate(List<Predicate<Person>> personPredicates) {
        Predicate<Person> phoneContainsSequencePredicate =
                person -> StringUtil.containsIgnoreCase(person.getPhone().value, phone);

        personPredicates.add(phoneContainsSequencePredicate);
    }

    private void addEmailPredicate(List<Predicate<Person>> personPredicates) {
        Predicate<Person> emailContainsSequencePredicate =
                person -> StringUtil.containsIgnoreCase(person.getEmail().value, email);

        personPredicates.add(emailContainsSequencePredicate);
    }

    private void addAddressPredicate(List<Predicate<Person>> personPredicates) {
        Predicate<Person> addressContainsSequencePredicate =
                person -> StringUtil.containsIgnoreCase(person.getAddress().value, address);

        personPredicates.add(addressContainsSequencePredicate);
    }

    private void addTagListPredicate(List<Predicate<Person>> personPredicates) {
        Predicate<Person> personContainsTagsPredicate =
                person -> {
                    List<String> tagsToSearchInUpperCase =
                            tagList.stream().map(String::toUpperCase).collect(Collectors.toList());
                    List<String> personTagsInUpperCase =
                            person.getTags().stream().map(
                                    tag -> tag.getTagName().toUpperCase()).collect(Collectors.toList());

                    return personTagsInUpperCase.containsAll(tagsToSearchInUpperCase);
                };

        personPredicates.add(personContainsTagsPredicate);
    }

    @Override
    public boolean test(Person person) {
        return combinedPredicate.test(person);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof CombinedPersonPredicate)) {
            return false;
        }

        CombinedPersonPredicate otherPredicate = (CombinedPersonPredicate) other;
        return name.equals(otherPredicate.name) && phone.equals(otherPredicate.phone)
                && email.equals(otherPredicate.email) && address.equals(otherPredicate.address)
                && tagList.equals(otherPredicate.tagList);
    }
}
