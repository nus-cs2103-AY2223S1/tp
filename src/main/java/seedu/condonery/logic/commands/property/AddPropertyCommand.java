package seedu.condonery.logic.commands.property;

import static java.util.Objects.requireNonNull;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.condonery.logic.commands.Command;
import seedu.condonery.logic.commands.CommandResult;
import seedu.condonery.logic.commands.exceptions.CommandException;
import seedu.condonery.model.Model;
import seedu.condonery.model.property.Property;
import seedu.condonery.model.client.Client;

/**
 * Adds a property to Condonery.
 */
public class AddPropertyCommand extends Command {

    public static final String COMMAND_WORD = "add -p";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a property to Condonery. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "PINNACLE@DUXTON "
            + PREFIX_ADDRESS + "Cantonment Rd, #1G, S085301 "
            + PREFIX_TAG + "High-End "
            + PREFIX_TAG + "Available";

    public static final String MESSAGE_SUCCESS = "New property added: %1$s";
    public static final String MESSAGE_DUPLICATE_PROPERTY = "This property already exists in Condonery";

    private final Property toAdd;
    private final ArrayList<String> missingClients = new ArrayList<>();
    private final ArrayList<String> duplicateClients = new ArrayList<>();

    /**
     * Creates an AddCommand to add the specified {@code Property}
     */
    public AddPropertyCommand(Property property) {
        requireNonNull(property);
        toAdd = property;
    }

    private Set<Client> filterInterestedClients(Set<String> interestedClientNames,
            Model model) {
       Set<Client> filteredInterestedClients = new HashSet<>();
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
       return filteredInterestedClients;
    }

    private String getUpdatedSuccessMessage() {
       String newSuccessMessage = MESSAGE_SUCCESS + ". ";

       if (missingClients.isEmpty() && duplicateClients.isEmpty()) {
           newSuccessMessage = newSuccessMessage + " No rejected client names.";
       } else {
           if (!missingClients.isEmpty()) {
               newSuccessMessage = newSuccessMessage + "Missing clients: " + missingClients
                       .stream()
                       .collect(Collectors.joining(" "))
                       + ". ";
           }
           if (!duplicateClients.isEmpty()) {
               newSuccessMessage = newSuccessMessage + "Duplicate clients: " + duplicateClients
                       .stream()
                       .collect(Collectors.joining(" "))
                       + ". ";
           }
       }

       return newSuccessMessage;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasProperty(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PROPERTY);
        }

        Set<Client> filteredInterestedClients = filterInterestedClients(
                toAdd.getInterestedClientNames(), model);

        String newMessageSuccess = getUpdatedSuccessMessage();
        
        Property newPropertyToAdd = new Property(toAdd.getName(), toAdd.getAddress(),
                toAdd.getTags(), filteredInterestedClients);

        model.addProperty(newPropertyToAdd);
        return new CommandResult(String.format(newMessageSuccess, newPropertyToAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddPropertyCommand // instanceof handles nulls
                && toAdd.equals(((AddPropertyCommand) other).toAdd));
    }
}
