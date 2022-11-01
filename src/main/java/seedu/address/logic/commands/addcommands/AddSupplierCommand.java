package seedu.address.logic.commands.addcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Supplier;
import seedu.address.model.pet.Pet;

/**
 * Adds a supplier to the address book.
 */
public class AddSupplierCommand extends AddPersonCommand {

    public static final String COMMAND_WORD = "add-s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_LOCATION + "LOCATION "
            + "\nExample: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_LOCATION + "Vietnam "
            + PREFIX_PET + AddPetCommand.COMMAND_WORD + " (...Pet1 fields) "
            + PREFIX_PET + AddPetCommand.COMMAND_WORD + " (...Pet2 fields) ";

    public static final String MESSAGE_SUCCESS = "New supplier added: %1$s";
    public static final String MESSAGE_DUPLICATE_SUPPLIER = "This supplier already exists in the address book";

    private final Supplier toAdd;
    private final List<Pet> pets = new ArrayList<>();

    /**
     * Creates an AddSupplierCommand to add the specified {@code Supplier}.
     */
    public AddSupplierCommand(Supplier supplier, List<Pet> pets) {
        requireNonNull(supplier);
        toAdd = supplier;
        if (pets != null) {
            this.pets.addAll(pets);
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasSupplier(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_SUPPLIER);
        }

        List<Pet> pets = this.pets;
        int numPetsAdded = pets.size();

        for (Pet pet : pets) {
            if (model.hasPet(pet)) {
                throw new CommandException(AddPetCommand.MESSAGE_DUPLICATE_PET);
            }
        }

        for (Pet pet : pets) {
            model.addPet(pet);
            pet.setSupplier(toAdd);
        }

        toAdd.addPets(pets.stream().map(Pet::getId).collect(Collectors.toList()));
        model.addSupplier(toAdd);

        //TODO To keep a single MESSAGE_SUCCESS
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd)
                + "\n" + numPetsAdded + (numPetsAdded == 1 ? " pet" : " pets") + " added\n");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddSupplierCommand // instanceof handles nulls
                && toAdd.equals(((AddSupplierCommand) other).toAdd));
    }
}
