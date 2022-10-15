package seedu.condonery.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.condonery.model.ClientDirectory;
import seedu.condonery.model.PropertyDirectory;
import seedu.condonery.model.ReadOnlyClientDirectory;
import seedu.condonery.model.ReadOnlyPropertyDirectory;
import seedu.condonery.model.client.Client;
import seedu.condonery.model.fields.Address;
import seedu.condonery.model.fields.Name;
import seedu.condonery.model.property.Property;
import seedu.condonery.model.tag.Tag;

/**
 * Contains utility methods for populating {@code PropertyDirectory} with sample data.
 */
public class SampleDataUtil {
    public static Property[] getSampleProperties() {
        return new Property[] {
            new Property(new Name("PINNACLE@DUXTON"), new Address("Cantonment Rd, #1G, S085301"),
                    getTagSet("High-End", "Available"),
                    getClientSet(
                            new Client(new Name("Bobby"), new Address("BobbyAddress"), getTagSet("")),
                            new Client(new Name("Samuel"), new Address("SamuelAddress"), getTagSet("Friend")))),
            new Property(new Name("INTERLACE"), new Address("180 Depot Road, S109684"),
                    getTagSet("High-End", "Sold"),
                    getClientSet(new Client(new Name("Casey"), new Address("CaseyAddress"), getTagSet("Rich")))),
            new Property(new Name("D'LEEDON"), new Address("7 Leedon Heights, D'leedon, S267953"),
                    getTagSet("High-End", "Reserved"),
                    getClientSet(
                            new Client(new Name("Jac"), new Address("JacAddress"), getTagSet("Urgent")),
                            new Client(new Name("Jack"), new Address("JackAddress"), getTagSet("")),
                            new Client(new Name("Hamster"), new Address("Hamster"), getTagSet("Friend", "Rich"))))
        };
    }

    public static Client[] getSampleClients() {
        return new Client[] {
            new Client(new Name("Jac"), new Address("JacAddress"), getTagSet("Urgent")),
            new Client(new Name("Jack"), new Address("JackAddress"), getTagSet("")),
            new Client(new Name("Hamster"), new Address("Hamster"), getTagSet("Friend", "Rich")),
            new Client(new Name("Casey"), new Address("CaseyAddress"), getTagSet("Rich")),
            new Client(new Name("Bobby"), new Address("BobbyAddress"), getTagSet("")),
            new Client(new Name("Samuel"), new Address("SamuelAddress"), getTagSet("Friend"))
        };
    }

    public static ReadOnlyPropertyDirectory getSamplePropertyDirectory() {
        PropertyDirectory sampleAb = new PropertyDirectory();
        for (Property sampleProperty : getSampleProperties()) {
            sampleAb.addProperty(sampleProperty);
        }
        return sampleAb;
    }

    public static ReadOnlyClientDirectory getSampleClientDirectory() {
        ClientDirectory sampleCd = new ClientDirectory();
        for (Client sampleClient : getSampleClients()) {
            sampleCd.addClient(sampleClient);
        }
        return sampleCd;
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
     * Returns a Client set containing the list of clients given.
     */
    public static Set<Client> getClientSet(Client... clients) {
        return Arrays.stream(clients)
            .collect(Collectors.toSet());
    }

}
