package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RELIGION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SURVEY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthdate;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Race;
import seedu.address.model.person.Religion;
import seedu.address.model.person.Survey;
import seedu.address.model.tag.Tag;

/**
 * Clones an existing person in the address book.
 */
public class CloneCommand extends Command {

    public static final String COMMAND_WORD = "clone";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Clones the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (the index of the person needs to be exist) "
            + "[Optional parameters in square brackets] "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_GENDER + "GENDER] "
            + "[" + PREFIX_BIRTHDATE + "BIRTHDATE] "
            + "[" + PREFIX_RACE + "RACE] "
            + "[" + PREFIX_RELIGION + "RELIGION] "
            + "[" + PREFIX_SURVEY + "SURVEY] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "John "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_CLONE_PERSON_SUCCESS = "Cloned Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one of the unique optional fields (Name, Phone or Email)"
            + " has to be provided.";
    public static final String MESSAGE_DUPLICATE_CLONED_PERSON = "This person already exists in the address book, "
            + "try again with different details, at least one of the unique optional fields (Name, Phone or Email) "
            + "has to be provided.";

    private final Index index;
    private final ClonePersonDescriptor clonePersonDescriptor;


    /**
     * @param index of the person in the filtered person list to cloned
     */
    public CloneCommand(Index index, ClonePersonDescriptor clonePersonDescriptor) {
        requireNonNull(index);
        this.index = index;
        this.clonePersonDescriptor = new ClonePersonDescriptor(clonePersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToClone = lastShownList.get(index.getZeroBased());
        Person clonedPerson = createClonedPerson(personToClone, clonePersonDescriptor);

        if (personToClone.isSamePerson(clonedPerson) && model.hasPerson(clonedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_CLONED_PERSON);
        }

        model.addPerson(clonedPerson);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_CLONE_PERSON_SUCCESS, clonedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToClone}.
     */
    private static Person createClonedPerson(Person personToClone, ClonePersonDescriptor clonePersonDescriptor) {
        assert personToClone != null;

        Name clonedName = clonePersonDescriptor.getName().orElse(personToClone.getName());
        Phone clonedPhone = clonePersonDescriptor.getPhone().orElse(personToClone.getPhone());
        Email clonedEmail = clonePersonDescriptor.getEmail().orElse(personToClone.getEmail());
        Address clonedAddress = clonePersonDescriptor.getAddress().orElse(personToClone.getAddress());
        Gender clonedGender = clonePersonDescriptor.getGender().orElse(personToClone.getGender());
        Birthdate clonedBirthdate = clonePersonDescriptor.getBirthdate().orElse(personToClone.getBirthdate());
        Race clonedRace = clonePersonDescriptor.getRace().orElse(personToClone.getRace());
        Religion clonedReligion = clonePersonDescriptor.getReligion().orElse(personToClone.getReligion());
        Set<Survey> clonedSurveys = clonePersonDescriptor.getSurveys().orElse(personToClone.getSurveys());
        Set<Tag> clonedTags = clonePersonDescriptor.getTags().orElse(personToClone.getTags());

        return new Person(clonedName, clonedPhone, clonedEmail, clonedAddress,
                clonedGender, clonedBirthdate, clonedRace, clonedReligion, clonedSurveys, clonedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CloneCommand)) {
            return false;
        }

        // state check
        CloneCommand e = (CloneCommand) other;
        return index.equals(e.index)
                && clonePersonDescriptor.equals(e.clonePersonDescriptor);
    }

    /**
     * Stores the details to clone the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class ClonePersonDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Gender gender;
        private Birthdate birthdate;
        private Race race;
        private Religion religion;
        private Set<Survey> surveys;
        private Set<Tag> tags;

        public ClonePersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public ClonePersonDescriptor(CloneCommand.ClonePersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setGender(toCopy.gender);
            setBirthdate(toCopy.birthdate);
            setRace(toCopy.race);
            setReligion(toCopy.religion);
            setSurveys(toCopy.surveys);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, gender, birthdate,
                    race, religion, surveys, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setGender(Gender gender) {
            this.gender = gender;
        }

        public Optional<Gender> getGender() {
            return Optional.ofNullable(gender);
        }

        public void setBirthdate(Birthdate birthdate) {
            this.birthdate = birthdate;
        }

        public Optional<Birthdate> getBirthdate() {
            return Optional.ofNullable(birthdate);
        }

        public void setRace(Race race) {
            this.race = race;
        }

        public Optional<Race> getRace() {
            return Optional.ofNullable(race);
        }

        public void setReligion(Religion religion) {
            this.religion = religion;
        }

        public Optional<Religion> getReligion() {
            return Optional.ofNullable(religion);
        }

        public void setSurveys(Set<Survey> surveys) {
            this.surveys = (surveys != null) ? new HashSet<>(surveys) : null;
        }

        public Optional<Set<Survey>> getSurveys() {
            return (surveys != null) ? Optional.of(Collections.unmodifiableSet(surveys)) : Optional.empty();
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof CloneCommand.ClonePersonDescriptor)) {
                return false;
            }

            // state check
            CloneCommand.ClonePersonDescriptor e = (CloneCommand.ClonePersonDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getGender().equals(e.getGender())
                    && getBirthdate().equals(e.getBirthdate())
                    && getRace().equals(e.getRace())
                    && getReligion().equals(e.getReligion())
                    && getSurveys().equals(e.getSurveys())
                    && getTags().equals(e.getTags());
        }
    }
}
