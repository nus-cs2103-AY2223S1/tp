package seedu.condonery.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.condonery.model.client.Client;
import seedu.condonery.model.client.ClientDirectory;
import seedu.condonery.model.client.ReadOnlyClientDirectory;
import seedu.condonery.model.fields.Address;
import seedu.condonery.model.fields.Name;
import seedu.condonery.model.property.Price;
import seedu.condonery.model.property.Property;
import seedu.condonery.model.property.PropertyDirectory;
import seedu.condonery.model.property.ReadOnlyPropertyDirectory;
import seedu.condonery.model.tag.PropertyStatusEnum;
import seedu.condonery.model.tag.PropertyTypeEnum;
import seedu.condonery.model.tag.Tag;

/**
 * Contains utility methods for populating {@code PropertyDirectory} with sample data.
 */
public class SampleDataUtil {
    public static Property[] getSampleProperties() {
        return new Property[] {
            new Property(new Name("PINNACLE@DUXTON"), new Address("Cantonment Rd, #1G, S085301"),
                    new Price("1000000"), getTagSet("High-End", "Available"),
                    getClientSet(
                        new Client(new Name("Bobby"),
                                new Address("BobbyAddress"),
                                getTagSet("Friend"),
                                new HashSet<>()),
                        new Client(new Name("Samuel"),
                                new Address("SamuelAddress"),
                                getTagSet("Friend"),
                                new HashSet<>())),
                    PropertyTypeEnum.valueOf("CONDO"), PropertyStatusEnum.AVAILABLE),
            new Property(new Name("INTERLACE"), new Address("180 Depot Road, S109684"),
                    new Price("1000000"),
                    getTagSet("High-End", "Sold"),
                    getClientSet(
                            new Client(new Name("Casey"),
                                    new Address("CaseyAddress"),
                                    getTagSet("Rich"),
                                    new HashSet<>())), PropertyTypeEnum.valueOf("CONDO"), PropertyStatusEnum.AVAILABLE),
            new Property(new Name("D'LEEDON"), new Address("7 Leedon Heights, D'leedon, S267953"),
                    new Price("1000000"),
                    getTagSet("High-End", "Reserved"),
                    getClientSet(
                            new Client(new Name("Jac"),
                                    new Address("JacAddress"),
                                    getTagSet("Urgent"),
                                    new HashSet<>()),
                            new Client(new Name("Jack"),
                                    new Address("JackAddress"),
                                    getTagSet("Test"),
                                    new HashSet<>()),
                            new Client(new Name("Hamster"),
                                    new Address("Hamster"),
                                    getTagSet("Friend", "Rich"),
                                    new HashSet<>())), PropertyTypeEnum.valueOf("CONDO"), PropertyStatusEnum.AVAILABLE),
        };
    }

    public static Client[] getSampleClients() {
        return new Client[] {
            new Client(new Name("Dennis Tan"), new Address("Wall St, #1G, S085301"),
                    getTagSet("High-End", "Available"), new HashSet<>()),
            new Client(new Name("Jeremy Tan"), new Address("11 Pulau Tekong Besar, Pulau, Tekong Camp, 508450"),
                    getTagSet("High-End", "Sold"), new HashSet<>()),
            new Client(new Name("Walter Wong"), new Address("7 Leedon Heights, D'leedon, S267953"),
                    getTagSet("High-End", "Reserved"), new HashSet<>()),
            new Client(new Name("Jac"), new Address("JacAddress"), getTagSet("Urgent"), new HashSet<>()),
            new Client(new Name("Jack"), new Address("JackAddress"), getTagSet("Friend"), new HashSet<>()),
            new Client(new Name("Hamster"), new Address("Hamster"), getTagSet("Friend", "Rich"), new HashSet<>()),
            new Client(new Name("Casey"), new Address("CaseyAddress"), getTagSet("Rich"), new HashSet<>()),
            new Client(new Name("Bobby"), new Address("BobbyAddress"), getTagSet("Rich"),
                    getPropertySet(
                            new Property(new Name("PINNACLE@DUXTON"), new Address("Cantonment Rd, #1G, S085301"),
                                    new Price("1000000"), getTagSet("High-End", "Available"),
                                    new HashSet<>(), PropertyTypeEnum.valueOf("CONDO"), PropertyStatusEnum.AVAILABLE))),
            new Client(new Name("Samuel"), new Address("SamuelAddress"), getTagSet("Friend"), new HashSet<>())
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

    /**
     * Returns a Property set containing the list of properties given.
     */
    public static Set<Property> getPropertySet(Property... properties) {
        return Arrays.stream(properties)
            .collect(Collectors.toSet());
    }

}
