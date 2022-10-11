package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Occupation;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Adds an address for an existing person in the address book.
 */
public class AddAddressCommand extends Command {

    public static final String COMMAND_WORD = "address";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds the address of the person identified "
        + "by the index number used in the displayed person list. "
        + "Existing values will be overwritten by the input values.\n"
        + "Parameters: INDEX (must be a positive integer) "
        + "ADDRESS\n"
        + "Example: " + COMMAND_WORD + " 1 "
        + "Black and White Rd.";

    public static final String MESSAGE_ADD_ADDRESS_SUCCESS = "Added address for Person: %1$s";
    public static final String MESSAGE_NOT_ADDRESSED = "New address must be specified.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final Index index;
    private final AddAddressDescriptor addAddressDescriptor;

    /**
     * @param index                of the person in the filtered person list to address
     * @param addAddressDescriptor details to address the person with
     */
    public AddAddressCommand(Index index, AddAddressDescriptor addAddressDescriptor) {
        requireNonNull(index);
        requireNonNull(addAddressDescriptor);

        this.index = index;
        this.addAddressDescriptor = new AddAddressDescriptor(addAddressDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToAddAddress = lastShownList.get(index.getZeroBased());
        Person addressedPerson = createAddressedPerson(personToAddAddress, addAddressDescriptor);

        if (!personToAddAddress.isSamePerson(addressedPerson) && model.hasPerson(addressedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }
        ReadOnlyAddressBook pastAddressBook = (ReadOnlyAddressBook) model.getAddressBook().clone();
        model.setPerson(personToAddAddress, addressedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        UndoCommand.saveBeforeMod(this, pastAddressBook, model.getAddressBook());
        return new CommandResult(String.format(MESSAGE_ADD_ADDRESS_SUCCESS, addressedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToAddAddress}
     * addressed with {@code addAddressDescriptor}.
     */
    private static Person createAddressedPerson(Person personToAddAddress, AddAddressDescriptor addAddressDescriptor) {
        assert personToAddAddress != null;

        Occupation updatedOccupation = personToAddAddress.getOccupation();
        Name updatedName = personToAddAddress.getName();
        Phone updatedPhone = personToAddAddress.getPhone();
        Email updatedEmail = personToAddAddress.getEmail();
        Address updatedAddress = addAddressDescriptor.getAddress().orElse(personToAddAddress.getAddress());
        Set<Tag> updatedTags = personToAddAddress.getTags();

        return new Person(updatedOccupation, updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddAddressCommand)) {
            return false;
        }

        // state check
        AddAddressCommand e = (AddAddressCommand) other;
        return index.equals(e.index)
            && addAddressDescriptor.equals(e.addAddressDescriptor);
    }

    /**
     * Stores the details to address the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class AddAddressDescriptor {
        private Address address;

        public AddAddressDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public AddAddressDescriptor(AddAddressDescriptor toCopy) {
            setAddress(toCopy.address);
        }

        /**
         * Returns true if address is added
         */
        public boolean isAddressAdded() {
            return CollectionUtil.isAnyNonNull(address);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof AddAddressDescriptor)) {
                return false;
            }

            // state check
            AddAddressDescriptor e = (AddAddressDescriptor) other;

            return getAddress().equals(e.getAddress());
        }
    }
}
