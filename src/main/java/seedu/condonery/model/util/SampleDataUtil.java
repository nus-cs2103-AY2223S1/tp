package seedu.condonery.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.condonery.model.ClientDirectory;
import seedu.condonery.model.PropertyDirectory;
import seedu.condonery.model.ReadOnlyClientDirectory;
import seedu.condonery.model.ReadOnlyPropertyDirectory;
import seedu.condonery.model.client.Client;
import seedu.condonery.model.property.Address;
import seedu.condonery.model.property.Name;
import seedu.condonery.model.property.Property;
import seedu.condonery.model.tag.Tag;

/**
 * Contains utility methods for populating {@code PropertyDirectory} with sample data.
 */
public class SampleDataUtil {
    public static Property[] getSampleProperties() {
        return new Property[] {
            new Property(new Name("PINNACLE@DUXTON"), new Address("Cantonment Rd, #1G, S085301"),
                    getTagSet("High-End", "Available")),
            new Property(new Name("INTERLACE"), new Address("180 Depot Road, S109684"),
                    getTagSet("High-End", "Sold")),
            new Property(new Name("DLEEDON"), new Address("7 Leedon Heights, D'leedon, S267953"),
                    getTagSet("High-End", "Reserved"))
        };
    }

    public static Client[] getSampleClients() {
        // TODO: add sample client data
        return new Client[] {
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

}
