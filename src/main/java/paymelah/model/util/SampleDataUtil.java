package paymelah.model.util;

import static paymelah.model.debt.Debt.makeDebt;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import paymelah.model.AddressBook;
import paymelah.model.ReadOnlyAddressBook;
import paymelah.model.debt.Debt;
import paymelah.model.debt.DebtList;
import paymelah.model.person.Address;
import paymelah.model.person.Name;
import paymelah.model.person.Person;
import paymelah.model.person.Phone;
import paymelah.model.person.Telegram;
import paymelah.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static final DebtList EMPTY_DEBT_LIST = new DebtList();
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Telegram("alexyeoh_handle"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends"),
                EMPTY_DEBT_LIST.addDebt(makeDebt("McDonald's", "5", "2022-10-11", "00:00"))
                        .addDebt(makeDebt("Taxi", "20", "2022-10-11", "00:00"))),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Telegram("berniceyu_handle"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends"), EMPTY_DEBT_LIST),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Telegram("charlotte_handle"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), getTagSet("neighbours"),
                EMPTY_DEBT_LIST.addDebt(makeDebt("Grab", "15.80", "2022-09-12", "10:24"))),
            new Person(new Name("David Li"), new Phone("91031282"), new Telegram("lidavid_handle"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family"), EMPTY_DEBT_LIST),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Telegram("irfan_handle"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates"),
                EMPTY_DEBT_LIST.addDebt(makeDebt("Bubble Tea", "4.5", "2022-09-11", "20:02"))),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Telegram("royb_handle"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"),
                EMPTY_DEBT_LIST.addDebt(makeDebt("Ramen", "9.90", "2022-10-15", "17:22")))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
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

    /**
     * Returns a debt list containing the list of debts given.
     */
    public static DebtList getDebtList(Debt... debts) {
        return DebtList.fromList(Arrays.stream(debts).collect(Collectors.toList()));
    }

}
