package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class PersonContainsKeywordsPredicateTest {

    private final String nameKeyword1 = "Alice";
    private final String nameKeyword2 = "Benson";
    private final String phoneKeyword1 = "999";
    private final String phoneKeyword2 = "62353535";
    private final String emailKeyword1 = "john@gmail.com";
    private final String emailKeyword2 = "colonel-sanders-123@kfc.co.uk";
    private final String addressKeyword1 = "17A Lower Kent Ridge Road, #01-01, S(119081)";
    private final String addressKeyword2 = "17A Lower Kent Ridge Road, c/o reception@sally, #01-01, S(119081)";

    private final List<Name> nameKeywords = Arrays.asList(new Name(nameKeyword1), new Name(nameKeyword2));
    private final List<Phone> phoneKeywords = Arrays.asList(new Phone(phoneKeyword1), new Phone(phoneKeyword2));
    private final List<Email> emailKeywords = Arrays.asList(new Email(emailKeyword1), new Email(emailKeyword2));
    private final List<Address> addressKeywords = Arrays.asList(new Address(addressKeyword1),
            new Address(addressKeyword2));

    private final List<Name> emptyNameKeywords = new ArrayList<>();
    private final List<Phone> emptyPhoneKeywords = new ArrayList<>();
    private final List<Email> emptyEmailKeywords = new ArrayList<>();
    private final List<Address> emptyAddressKeywords = new ArrayList<>();

    @Test
    public void equals() {

        PersonContainsKeywordsPredicate firstPredicate =
                new PersonContainsKeywordsPredicate(nameKeywords, phoneKeywords,
                        emailKeywords, addressKeywords);
        PersonContainsKeywordsPredicate secondPredicate =
                new PersonContainsKeywordsPredicate(nameKeywords, emptyPhoneKeywords,
                        emptyEmailKeywords, emptyAddressKeywords);
        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonContainsKeywordsPredicate firstPredicateCopy = new PersonContainsKeywordsPredicate(
                nameKeywords, phoneKeywords, emailKeywords, addressKeywords);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }
}
