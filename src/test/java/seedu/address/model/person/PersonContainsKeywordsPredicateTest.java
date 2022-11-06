package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class PersonContainsKeywordsPredicateTest {

    private final String nameKeyword1 = "Alice";
    private final String nameKeyword2 = "Benson";
    private final String nameKeyword3 = "Carol";
    private final String phoneKeyword1 = "999";
    private final String phoneKeyword2 = "62353535";
    private final String phoneKeyword3 = "93654125";
    private final String emailKeyword1 = "john@gmail.com";
    private final String emailKeyword2 = "colonel-sanders-123@kfc.co.uk";
    private final String emailKeyword3 = "carol@gmail.com";
    private final String addressKeyword1 = "17A";
    private final String addressKeyword2 = "S(119081)";
    private final String addressKeyword3 = "USA";
    private final String remarkKeywords1 = "online";
    private final String remarkKeywords2 = "f2f";
    private final String remarkKeywords3 = "zoom";
    private final String tagKeyword1 = "cs2101";
    private final String tagKeyword2 = "cs2103t";
    private final String tagKeyword3 = "ma3269";
    private final String tagKeyword4 = "ma1521";

    private final List<Name> nameKeywords = Arrays.asList(new Name(nameKeyword1), new Name(nameKeyword2));
    private final List<Phone> phoneKeywords = Arrays.asList(new Phone(phoneKeyword1), new Phone(phoneKeyword2));
    private final List<Email> emailKeywords = Arrays.asList(new Email(emailKeyword1), new Email(emailKeyword2));
    private final List<Address> addressKeywords = Arrays.asList(new Address(addressKeyword1),
            new Address(addressKeyword2));
    private final List<Remark> remarkKeywords = Arrays.asList(new Remark(remarkKeywords1),
            new Remark(remarkKeywords2));
    private final Set<Tag> tagKeywords =
            new HashSet<>(Arrays.asList(new Tag(tagKeyword1), new Tag(tagKeyword2)));

    private final List<Name> nameKeywordsAllUpperCase =
            Arrays.asList(new Name(nameKeyword1.toUpperCase()), new Name(nameKeyword2.toUpperCase()));
    private final List<Email> emailKeywordsAllUpperCase =
            Arrays.asList(new Email(emailKeyword1.toUpperCase()), new Email(emailKeyword2.toUpperCase()));
    private final List<Address> addressKeywordsAllUpperCase =
            Arrays.asList(new Address(addressKeyword1.toUpperCase()), new Address(addressKeyword2.toUpperCase()));
    private final List<Remark> remarkKeywordsAllUpperCase =
            Arrays.asList(new Remark(remarkKeywords1.toUpperCase()), new Remark(remarkKeywords2.toUpperCase()));
    private final Set<Tag> tagKeywordsAllUpperCase =
            new HashSet<>(Arrays.asList(new Tag(tagKeyword1.toUpperCase()), new Tag(tagKeyword2.toUpperCase())));

    private final List<Name> nameKeywordsUnmatched = List.of(new Name(nameKeyword3));
    private final List<Phone> phoneKeywordsUnmatched = List.of(new Phone(phoneKeyword3));
    private final List<Email> emailKeywordsUnmatched = List.of(new Email(emailKeyword3));
    private final List<Address> addressKeywordsUnmatched = List.of(new Address(addressKeyword3));
    private final List<Remark> remarkKeywordsUnmatched = List.of(new Remark(remarkKeywords3));
    private final Set<Tag> tagKeywordsUnmatched =
            new HashSet<>(Arrays.asList(new Tag(tagKeyword3), new Tag(tagKeyword4)));

    private final List<Name> emptyNameKeywords = new ArrayList<>();
    private final List<Phone> emptyPhoneKeywords = new ArrayList<>();
    private final List<Email> emptyEmailKeywords = new ArrayList<>();
    private final List<Address> emptyAddressKeywords = new ArrayList<>();
    private final List<Remark> emptyRemarkKeywords = new ArrayList<>();
    private final Set<Tag> emptyTags = new HashSet<>();

    private final Person samplePerson = new PersonBuilder()
            .withName(nameKeyword1 + " " + nameKeyword2)
            .withPhone(phoneKeyword1)
            .withEmail(emailKeyword1)
            .withAddress(addressKeyword1 + " " + addressKeyword2)
            .withTags(tagKeyword1, tagKeyword2)
            .withRemark(remarkKeywords1)
            .build();

    private final Person samplePersonAllFieldsUpperCase = new PersonBuilder()
            .withName(nameKeyword1.toUpperCase() + " " + nameKeyword2.toUpperCase())
            .withPhone(phoneKeyword1)
            .withEmail(emailKeyword1.toUpperCase())
            .withAddress(addressKeyword1.toUpperCase() + " " + addressKeyword2.toUpperCase())
            .withTags(tagKeyword1.toUpperCase(), tagKeyword2.toUpperCase())
            .withRemark(remarkKeywords1.toUpperCase())
            .build();

    @Test
    public void equals() {
        PersonContainsKeywordsPredicate firstPredicate =
                new PersonContainsKeywordsPredicate(nameKeywords, phoneKeywords,
                        emailKeywords, addressKeywords, remarkKeywords);
        PersonContainsKeywordsPredicate secondPredicate =
                new PersonContainsKeywordsPredicate(nameKeywords, emptyPhoneKeywords,
                        emptyEmailKeywords, emptyAddressKeywords, emptyRemarkKeywords);
        PersonContainsKeywordsPredicate thirdPredicate =
                new PersonContainsKeywordsPredicate(tagKeywords);
        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonContainsKeywordsPredicate firstPredicateCopy = new PersonContainsKeywordsPredicate(
                nameKeywords, phoneKeywords, emailKeywords, addressKeywords, remarkKeywords);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // same values -> returns true (different constructor)
        PersonContainsKeywordsPredicate thirdPredicateCopy = new PersonContainsKeywordsPredicate(tagKeywords);
        assertTrue(thirdPredicate.equals(thirdPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different predicate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_fieldsContainsKeywords_returnsTrue() {
        // Zero keywords, defined to be true
        PersonContainsKeywordsPredicate predicate =
                new PersonContainsKeywordsPredicate(emptyNameKeywords, emptyPhoneKeywords,
                        emptyEmailKeywords, emptyAddressKeywords, emptyRemarkKeywords);
        assertTrue(predicate.test(samplePerson));

        //Zero tags, defined to be true
        predicate = new PersonContainsKeywordsPredicate(emptyTags);
        assertTrue(predicate.test(samplePerson));

        // Keywords match name, phone, email, address, remark
        predicate = new PersonContainsKeywordsPredicate(nameKeywords, phoneKeywords,
                emailKeywords, addressKeywords, remarkKeywords);
        assertTrue(predicate.test(samplePerson));

        // Keywords match name, phone, email, address, remark (Person fields all upper-case)
        predicate = new PersonContainsKeywordsPredicate(nameKeywords, phoneKeywords,
                emailKeywords, addressKeywords, remarkKeywords);
        assertTrue(predicate.test(samplePersonAllFieldsUpperCase));

        // Keywords match name, phone, email, address, remark (keywords fields all upper-case)
        predicate = new PersonContainsKeywordsPredicate(nameKeywordsAllUpperCase, phoneKeywords,
                emailKeywordsAllUpperCase, addressKeywordsAllUpperCase, remarkKeywordsAllUpperCase);
        assertTrue(predicate.test(samplePerson));

        // Keywords match tags
        predicate = new PersonContainsKeywordsPredicate(tagKeywords);
        assertTrue(predicate.test(samplePerson));
    }

    @Test
    public void test_fieldsDoNotContainKeywords_returnsFalse() {
        // Keywords match phone, email, address, and remark, but does not match name
        PersonContainsKeywordsPredicate predicate =
                new PersonContainsKeywordsPredicate(nameKeywordsUnmatched, phoneKeywords,
                        emailKeywords, addressKeywords, remarkKeywords);
        assertFalse(predicate.test(samplePerson));

        // Keywords match name, email, address, and remark, but does not match phone
        predicate = new PersonContainsKeywordsPredicate(nameKeywords, phoneKeywordsUnmatched,
                emailKeywords, addressKeywords, remarkKeywords);
        assertFalse(predicate.test(samplePerson));

        // Keywords match name, phone, address, and remark, but does not match email
        predicate = new PersonContainsKeywordsPredicate(nameKeywords, phoneKeywords,
                emailKeywordsUnmatched, addressKeywords, remarkKeywords);
        assertFalse(predicate.test(samplePerson));

        // Keywords match name, phone, email, and remark, but does not match address
        predicate = new PersonContainsKeywordsPredicate(nameKeywords, phoneKeywords,
                emailKeywords, addressKeywordsUnmatched, remarkKeywords);
        assertFalse(predicate.test(samplePerson));

        // Keywords match name, phone, email, and address, but does not match remark
        predicate = new PersonContainsKeywordsPredicate(nameKeywords, phoneKeywords,
                emailKeywords, addressKeywords, remarkKeywordsUnmatched);
        assertFalse(predicate.test(samplePerson));

        //Keywords do not match tags
        predicate = new PersonContainsKeywordsPredicate(tagKeywordsUnmatched);
        assertFalse(predicate.test(samplePerson));

        //Keywords match tags but different case -> return false (Person's tags all upper-case)
        predicate = new PersonContainsKeywordsPredicate(tagKeywords);
        assertFalse(predicate.test(samplePersonAllFieldsUpperCase));

        //Keywords match tags but different case -> return false (Tag keywords all upper-case)
        predicate = new PersonContainsKeywordsPredicate(tagKeywordsAllUpperCase);
        assertFalse(predicate.test(samplePerson));
    }
}
