package foodwhere.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import foodwhere.model.AddressBook;
import foodwhere.model.ReadOnlyAddressBook;
import foodwhere.model.detail.Detail;
import foodwhere.model.stall.Address;
import foodwhere.model.stall.Name;
import foodwhere.model.stall.Stall;
import foodwhere.model.stall.Phone;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Stall[] getSampleStalls() {
        return new Stall[] {
            new Stall(new Name("Alex Yeoh"), new Phone("87438807"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                    getDetailSet("friends")),
            new Stall(new Name("Bernice Yu"), new Phone("99272758"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    getDetailSet("colleagues", "friends")),
            new Stall(new Name("Charlotte Oliveiro"), new Phone("93210283"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    getDetailSet("neighbours")),
            new Stall(new Name("David Li"), new Phone("91031282"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    getDetailSet("family")),
            new Stall(new Name("Irfan Ibrahim"), new Phone("92492021"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                    getDetailSet("classmates")),
            new Stall(new Name("Roy Balakrishnan"), new Phone("92624417"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                    getDetailSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Stall sampleStall : getSampleStalls()) {
            sampleAb.addStall(sampleStall);
        }
        return sampleAb;
    }

    /**
     * Returns a detail set containing the list of strings given.
     */
    public static Set<Detail> getDetailSet(String... strings) {
        return Arrays.stream(strings)
                .map(Detail::new)
                .collect(Collectors.toSet());
    }

}
