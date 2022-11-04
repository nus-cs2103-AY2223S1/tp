package seedu.condonery.model.property.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import seedu.condonery.model.Model;
import seedu.condonery.model.client.Client;
import seedu.condonery.model.property.Property;

/**
 * Parses a {@code Property} holding a list of interested client names.
 * The interested clients might only hold partial names (given by the user) along with placeholder
 * addresses and tags.
 */
public class ParsePropertyInterestedClients {

    private final ArrayList<String> missingClients = new ArrayList<>();
    private final ArrayList<String> duplicateClients = new ArrayList<>();
    private final Set<Client> filteredInterestedClients = new HashSet<>();
    private final Property originalProperty;

    /**
     * Creates a ParsePropertyInterestedClients to parse the specified {@code Property} using the
     * input {@code Model}.
     */
    public ParsePropertyInterestedClients(Property property, Model model) {
        originalProperty = property;

        Set<String> interestedClientNames = originalProperty.getInterestedClientNames();

        for (String clientName : interestedClientNames) {
            if (model.hasClientName(clientName)) {
                if (model.hasUniqueClientName(clientName)) {
                    filteredInterestedClients.add(model.getUniqueClientByName(clientName));
                } else {
                    duplicateClients.add(clientName);
                }
            } else {
                missingClients.add(clientName);
            }
        }
    }

    public ArrayList<String> getMissingClients() {
        return missingClients;
    }

    public ArrayList<String> getDuplicateClients() {
        return duplicateClients;
    }

    public Property getNewProperty() {
        Property property = new Property(
            originalProperty.getName(),
            originalProperty.getAddress(),
            originalProperty.getPrice(),
            originalProperty.getTags(),
            filteredInterestedClients,
            originalProperty.getPropertyTypeEnum(),
            originalProperty.getPropertyStatusEnum()
        );
        property.setImageDirectoryPath(originalProperty.getImageDirectoryPath());
        return property;
    }
}
