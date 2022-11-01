package seedu.address.logic.commands.addcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_CERTIFICATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_COLOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_COLOR_PATTERN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_DATE_OF_BIRTH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_HEIGHT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_SPECIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_VACCINATION_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_WEIGHT;

import java.util.Collections;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Supplier;
import seedu.address.model.pet.Pet;

/**
 * Adds a pet to the address book.
 */
public class AddPetCommand extends Command {
    public static final String COMMAND_WORD = "add-p";

    public static final String COMMON_PARAMETERS =
            PREFIX_PET_NAME + "NAME "
                    + PREFIX_PET_DATE_OF_BIRTH + "DATE_OF_BIRTH "
                    + PREFIX_PET_COLOR + "COLOR "
                    + PREFIX_PET_COLOR_PATTERN + "COLOR_PATTERN "
                    + PREFIX_PET_HEIGHT + "HEIGHT "
                    + PREFIX_PET_SPECIES + "SPECIES "
                    + PREFIX_PET_VACCINATION_STATUS + "VACCINATION_STATUS "
                    + PREFIX_PET_WEIGHT + "WEIGHT "
                    + PREFIX_PET_PRICE + "PRICE"
                    + "[" + PREFIX_PET_CERTIFICATE + "CERTIFICATE]...\n"

                    + "\nExample: " + COMMAND_WORD + " ";

    public static final String COMMON_SAMPLE_PARAMETERS = PREFIX_PET_NAME + "Wu Lezheng "
            + PREFIX_PET_DATE_OF_BIRTH + "2001-11-20 "
            + PREFIX_PET_COLOR + "red "
            + PREFIX_PET_COLOR_PATTERN + "stripped "
            + PREFIX_PET_HEIGHT + "39.5 "
            + PREFIX_PET_SPECIES + "chihuahua "
            + PREFIX_PET_VACCINATION_STATUS + "true "
            + PREFIX_PET_WEIGHT + "15.3 "
            + PREFIX_PET_PRICE + "20"
            + PREFIX_PET_CERTIFICATE + "Good-Dog Cert. "
            + PREFIX_PET_CERTIFICATE + "Royal Blood Cert. ";

    public static final String MESSAGE_USAGE_EXISTING_SUPPLIER = COMMAND_WORD
            + ": Adds a new pet to an existing supplier. "
            + "Parameters: "
            + PREFIX_INDEX + "INDEX "
            + COMMON_PARAMETERS
            + PREFIX_INDEX + "1 "
            + COMMON_SAMPLE_PARAMETERS;

    public static final String MESSAGE_USAGE_NEW_SUPPLIER = COMMAND_WORD
            + ": Adds a pet when adding a supplier. "
            + "Parameters: "
            + COMMON_PARAMETERS
            + COMMON_SAMPLE_PARAMETERS;


    public static final String MESSAGE_DUPLICATE_PET = "This pet already exists in the buyer list";

    public static final String MESSAGE_SUCCESS = "Added Pet: %1$s";

    public static final String MESSAGE_FAILURE = "Unable to execute AddPetCommand.";

    private final Index index;
    private final Pet toAdd;

    /**
     * Constructs a new AddPetCommand object.
     *
     * @param pet The pet to be added.
     * @param index The index of the associated supplier.
     */
    public AddPetCommand(Pet pet, Index index) {
        this.toAdd = pet;
        this.index = index;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ObservableList<Supplier> lastShownList = model.getFilteredSupplierList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Supplier associatedSupplier = lastShownList.get(index.getZeroBased());

        associatedSupplier.addPets(Collections.singletonList(toAdd.getId()));
        toAdd.setSupplier(associatedSupplier);
        model.addPet(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof AddPetCommand)) {
            return false;
        }
        AddPetCommand otherCommand = (AddPetCommand) other;
        return otherCommand.index.equals(this.index) && otherCommand.toAdd.isSamePet(this.toAdd);
    }
}
