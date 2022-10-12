package seedu.address.logic.commands;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.person.AddressContainsSequencePredicate;
import seedu.address.model.person.EmailContainsSequencePredicate;
import seedu.address.model.person.NameContainsSequencePredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonContainsTagsPredicate;
import seedu.address.model.person.PhoneContainsSequencePredicate;

public class CombinedPersonPredicate implements Predicate<Person> {
    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final List<String> tagList;

    // There is no equals() method for predicates. Ensure this predicate and variables to generate it are always final!
    private final Predicate<Person> combinedPredicate;

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
            personPredicates.add(new NameContainsSequencePredicate(name));
        }
        if (!phone.isEmpty()) {
            personPredicates.add(new PhoneContainsSequencePredicate(phone));
        }
        if (!email.isEmpty()) {
            personPredicates.add(new EmailContainsSequencePredicate(email));
        }
        if (!address.isEmpty()) {
            personPredicates.add(new AddressContainsSequencePredicate(address));
        }
        if (!tagList.isEmpty()) {
            personPredicates.add(new PersonContainsTagsPredicate(tagList));
        }

        return personPredicates.stream().reduce(PREDICATE_SHOW_ALL_PERSONS, Predicate::and);
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
