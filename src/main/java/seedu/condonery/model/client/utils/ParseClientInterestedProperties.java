package seedu.condonery.model.client.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import seedu.condonery.model.Model;
import seedu.condonery.model.client.Client;
import seedu.condonery.model.property.Property;

/**
 * Parses a {@code Client} holding a list of interested property names.
 * The interested properties might only hold partial names (given by the user) along with placeholder
 * values for other fields.
 */
public class ParseClientInterestedProperties {

    private final ArrayList<String> missingProperties = new ArrayList<>();
    private final ArrayList<String> duplicateProperties = new ArrayList<>();
    private final Set<Property> filteredInterestedProperties = new HashSet<>();
    private final Client originalClient;

    /**
     * Creates a ParseClientInterestedProperties to parse the specified {@code Client} using the
     * input {@code Model}.
     */
    public ParseClientInterestedProperties(Client client, Model model) {
        originalClient = client;

        Set<String> interestedPropertyNames = originalClient.getInterestedPropertyNames();

        for (String propertyName : interestedPropertyNames) {
            if (model.hasPropertyName(propertyName)) {
                if (model.hasUniquePropertyName(propertyName)) {
                    filteredInterestedProperties.add(model.getUniquePropertyByName(propertyName));
                } else {
                    duplicateProperties.add(propertyName);
                }
            } else {
                missingProperties.add(propertyName);
            }
        }
    }

    public ArrayList<String> getMissingProperties() {
        return missingProperties;
    }

    public ArrayList<String> getDuplicateProperties() {
        return duplicateProperties;
    }

    public Client getNewClient() {
        Client client = new Client(originalClient.getName(), originalClient.getAddress(),
                originalClient.getTags(), filteredInterestedProperties);
        client.setImageDirectoryPath(originalClient.getImageDirectoryPath());
        return client;
    }
}
