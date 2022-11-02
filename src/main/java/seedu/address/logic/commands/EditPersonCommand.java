package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.FLAG_ADDRESS_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_ADDRESS_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_EMAIL_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_EMAIL_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_PERSON_ADDRESS_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_PERSON_EMAIL_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_PERSON_INDEX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_PERSON_NAME_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_PERSON_PHONE_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_PERSON_TAGS_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.FLAG_PHONE_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_PHONE_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_TAG_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_TAG_STR_LONG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import picocli.CommandLine;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.TagsConverter;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing person in the TruthTable.
 */
@CommandLine.Command(name = EditPersonCommand.COMMAND_WORD, aliases = {"p"}, mixinStandardHelpOptions = true)
public class EditPersonCommand extends Command {
    public static final String COMMAND_WORD = "person";

    public static final String FULL_COMMAND = EditCommand.COMMAND_WORD + " " + COMMAND_WORD;

    public static final String MESSAGE_USAGE = FULL_COMMAND + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: PERSON_INDEX (must be a valid positive integer) "
            + "[" + FLAG_NAME_STR + " NAME] "
            + "[" + FLAG_PHONE_STR + " PHONE] "
            + "[" + FLAG_EMAIL_STR + " EMAIL] "
            + "[" + FLAG_ADDRESS_STR + " ADDRESS] "
            + "[" + FLAG_TAG_STR + " TAG]...\n"
            + "Example: " + FULL_COMMAND + " 1 "
            + FLAG_PHONE_STR + " 91234567 "
            + FLAG_EMAIL_STR + " johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the TruthTable.";

    @CommandLine.Parameters(arity = "1", description = FLAG_PERSON_INDEX_DESCRIPTION)
    private Index index;

    @CommandLine.ArgGroup(exclusive = false, multiplicity = "1")
    private Arguments arguments;

    @CommandLine.Option(names = {FLAG_HELP_STR, FLAG_HELP_STR_LONG}, usageHelp = true,
            description = FLAG_HELP_DESCRIPTION)
    private boolean help;

    @CommandLine.Spec
    private CommandLine.Model.CommandSpec commandSpec;

    private final EditPersonDescriptor editPersonDescriptor;

    public EditPersonCommand() {
        this.editPersonDescriptor = new EditPersonDescriptor();
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (commandSpec.commandLine().isUsageHelpRequested()) {
            return new CommandResult(commandSpec.commandLine().getUsageMessage());
        }
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        if (arguments.name != null) {
            editPersonDescriptor.name = arguments.name;
        }
        if (arguments.address != null) {
            editPersonDescriptor.address = arguments.address;
        }
        if (arguments.email != null) {
            editPersonDescriptor.email = arguments.email;
        }
        if (arguments.tags != null) {
            editPersonDescriptor.tags = arguments.tags;
        }

        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditPersonCommand)) {
            return false;
        }

        // state check
        EditPersonCommand e = (EditPersonCommand) other;
        return index.equals(e.index)
                && arguments.equals(e.arguments);
    }

    private static class Arguments {
        @CommandLine.Option(names = {FLAG_NAME_STR, FLAG_NAME_STR_LONG}, description = FLAG_PERSON_NAME_DESCRIPTION)
        private Name name;

        @CommandLine.Option(names = {FLAG_PHONE_STR, FLAG_PHONE_STR_LONG}, description = FLAG_PERSON_PHONE_DESCRIPTION)
        private Phone phone;

        @CommandLine.Option(names = {FLAG_EMAIL_STR, FLAG_EMAIL_STR_LONG}, description = FLAG_PERSON_EMAIL_DESCRIPTION)
        private Email email;

        @CommandLine.Option(names = {FLAG_ADDRESS_STR, FLAG_ADDRESS_STR_LONG},
                description = FLAG_PERSON_ADDRESS_DESCRIPTION)
        private Address address;

        @CommandLine.Option(names = {FLAG_TAG_STR, FLAG_TAG_STR_LONG}, description = FLAG_PERSON_TAGS_DESCRIPTION,
                parameterConsumer = TagsConverter.class, arity = "*")
        private Set<Tag> tags;

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            } else if (other instanceof Arguments) {
                Arguments target = (Arguments) other;
                return this.name == null? false: this.name.equals(target.name)
                        && this.phone == null? false: this.phone.equals(target.phone)
                        && this.email == null? false: this.email.equals(target.email)
                        && this.address == null? false: this.address.equals(target.address)
                        && this.tags == null? false: this.tags.equals(target.tags);
            } else {
                return false;
            }
        }
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Tag> tags;

        public EditPersonDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, tags);
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            // state check
            EditPersonDescriptor e = (EditPersonDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getTags().equals(e.getTags());
        }
    }
}
