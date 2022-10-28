package seedu.address.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.note.Content;
import seedu.address.model.note.Note;
import seedu.address.model.note.Title;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Loan;
import seedu.address.model.person.LoanHistory;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Reason;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(
                    new Name("Lynette Chong"),
                    new Phone("91883124"),
                    new Email("lynette@example.com"),
                    new Address("Blk 2 Holland Avenue, #23-05"),
                    new Birthday("09/07/1995"),
                    getTagSet("president"),
                    new Loan(0), List.of(
                        new LoanHistory(new Loan(100), new Reason("Deposited $100")),
                        new LoanHistory(new Loan(-100), new Reason("Invoice #066-011445-305"))
                    )
            ),

            new Person(
                    new Name("Gerald Yap"),
                    new Phone("84558922"),
                    new Email("gerald_yap@example.com"),
                    new Address("Blk 432 Bukit Panjang Ring Rd, #12-40"),
                    new Birthday("10/11/2000"),
                    getTagSet("colleagues", "operations"),
                    new Loan(-426), List.of(
                            new LoanHistory(new Loan(8), new Reason("Buy shirt")),
                            new LoanHistory(new Loan(-100), new Reason("Invoice #122-10493-293")),
                            new LoanHistory(new Loan(-200), new Reason("Invoice #133-589313-211")),
                            new LoanHistory(new Loan(-184), new Reason("Invoice #158-970377-008")),
                            new LoanHistory(new Loan(50), new Reason("Pay back excess charge"))
                    )
            ),

            new Person(
                    new Name("Alex Yeoh"),
                    new Phone("87438807"),
                    new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"),
                    new Birthday("20/01/2003"),
                    getTagSet("friends", "operations"),
                    new Loan(0), new ArrayList<LoanHistory>()),

            new Person(
                    new Name("Bernice Yu"),
                    new Phone("99272758"),
                    new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    new Birthday("18/06/1999"),
                    getTagSet("colleagues", "friends"),
                    new Loan(48), List.of(
                        new LoanHistory(new Loan(8), new Reason("Buy shirt")),
                        new LoanHistory(new Loan(40), new Reason("Loaned money to buy 2x white paint"))
                    )
            ),

            new Person(
                    new Name("Charlotte Oliveiro"),
                    new Phone("93210283"),
                    new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    new Birthday("03/05/1976"),
                    getTagSet("operations"),
                    new Loan(0), List.of(
                        new LoanHistory(new Loan(8), new Reason("Buy shirt")),
                        new LoanHistory(new Loan(42), new Reason("Loaned money to buy foam boards")),
                        new LoanHistory(new Loan(-80), new Reason("Invoice #210-399032-029")),
                        new LoanHistory(new Loan(30), new Reason("Paid back loaned amount"))
                    )
            ),

            new Person(
                    new Name("David Li"),
                    new Phone("91031282"),
                    new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    new Birthday("11/04/1985"),
                    getTagSet("family"),
                    new Loan(-42), List.of(
                        new LoanHistory(new Loan(8), new Reason("Buy shirt")),
                        new LoanHistory(new Loan(-50), new Reason("To pay back after fixing table"))
                    )
            ),

            new Person(
                    new Name("Irfan Ibrahim"),
                    new Phone("92492021"),
                    new Email("irfan@example.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35"),
                    new Birthday("25/09/2010"),
                    getTagSet("classmates"),
                    new Loan(100), List.of(
                        new LoanHistory(new Loan(100), new Reason("Borrowed for mass dinner event funds"))
                    )
            ),

            new Person(
                    new Name("Roy Balakrishnan"),
                    new Phone("92624417"),
                    new Email("royb@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"),
                    new Birthday("09/07/2005"),
                    getTagSet("colleagues"),
                    new Loan(0), new ArrayList<LoanHistory>())
        };
    }

    public static Tag[] getSampleTags() {
        return new Tag[] {
            new Tag("friends"),
            new Tag("colleagues"),
            new Tag("operations"),
            new Tag("family"),
            new Tag("classmates")
        };
    }

    public static Note[] getSampleNotes() {
        return new Note[] {
            new Note(
                    new Title("Indent buffet for meeting"),
                    new Content("Buffet to be indented at 3pm next Tuesday"),
                    getTagSet("colleagues")
            ),
            new Note(
                    new Title("Collect funds from operations team"),
                    new Content("Collect $20 from everyone involved in operations"),
                    getTagSet("operations")
            ),
            new Note(
                    new Title("Check meeting availability"),
                    new Content("Prepare attendance roll for those coming for next Tuesday's meeting"),
                    getTagSet("president")
            )
            };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        for (Tag sampleTag : getSampleTags()) {
            sampleAb.addTag(sampleTag);
        }
        for (Note sampleNote : getSampleNotes()) {
            sampleAb.addNote(sampleNote);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
