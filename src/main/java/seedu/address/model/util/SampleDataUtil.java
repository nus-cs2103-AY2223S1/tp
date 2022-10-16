package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.JeeqTracker;
import seedu.address.model.ReadOnlyJeeqTracker;
import seedu.address.model.client.Address;
import seedu.address.model.client.Client;
import seedu.address.model.client.ClientEmail;
import seedu.address.model.client.ClientPhone;
import seedu.address.model.client.Name;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code JeeqTracker} with sample data.
 */
public class SampleDataUtil {

    public static Client[] getSampleClients() {
        return new Client[] {
            new Client(new Name("Alex Yeoh"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                new ClientPhone("8273281"),
                new ClientEmail("alex@gmail.com"),
                getTagSet("friends")),
            new Client(new Name("Bernice Yu"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                new ClientPhone("112323323"),
                new ClientEmail("byue@gmail.com"),
                getTagSet("colleagues", "friends")),
            new Client(new Name("Charlotte Oliveiro"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                new ClientPhone("9818218"),
                new ClientEmail("char@gmail.com"),
                getTagSet("neighbours")),
            new Client(new Name("David Li"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                new ClientPhone("8217128"),
                new ClientEmail("dav@gmail.com"),
                getTagSet("family")),
            new Client(new Name("Irfan Ibrahim"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                new ClientPhone("11121212"),
                new ClientEmail("irib@gmail.com"),
                getTagSet("classmates")),
            new Client(new Name("Roy Balakrishnan"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                new ClientPhone("9182812"),
                new ClientEmail("royx@gmail.com"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyJeeqTracker getSampleJeeqTracker() {
        JeeqTracker sampleAb = new JeeqTracker();
        for (Client sampleClient : getSampleClients()) {
            sampleAb.addClient(sampleClient);
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
