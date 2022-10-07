package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_COMPANIES;

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
import seedu.address.model.company.Address;
import seedu.address.model.company.Company;
import seedu.address.model.company.Name;
import seedu.address.model.poc.Poc;
import seedu.address.model.poc.UniquePocList;
import seedu.address.model.tag.Tag;

/**
 * Changes the remark of an existing person in the address book.
 */
public class CreateCommand extends Command {

    public static final String COMMAND_WORD = "create";


    //Update here
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a Point-Of-Contact and links to Company. "
            + "Parameters: "
            + "Index "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "HP "
            + PREFIX_EMAIL + "EMAIL "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 2 "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "97814456 "
            + PREFIX_EMAIL + "test@test.com "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New POC created: %1$s\nLinked to Company: %2$s";

    private final Index index;
    private final Poc poc;
//    private final AddPocCompanyDescriptor addPocCompanyDescriptor;

    /**
     * @param index of the company in the company list to add the POC to
     * @param poc to be added
     */
    public CreateCommand(Index index, Poc poc) {
        requireNonNull(index);
        requireNonNull(poc);
//        requireNonNull(addPocCompanyDes.criptor);

        this.index = index;
        this.poc = poc;
//        this.addPocCompanyDescriptor = addPocCompanyDescriptor;

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Company> lastShownList = model.getFilteredCompanyList();

        // if index of company not valid
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
        }

        Company companyToEdit = lastShownList.get(index.getZeroBased());
        Company editedCompany = createEditedCompany(companyToEdit, poc);

//        if (!companyToEdit.isSameCompany(editedCompany) && model.hasCompany(editedCompany)) {
//            throw new CommandException(MESSAGE_DUPLICATE_COMPANY);
//        }

        model.setCompany(companyToEdit, editedCompany);
        model.updateFilteredCompanyList(PREDICATE_SHOW_ALL_COMPANIES);

        return new CommandResult(String.format(MESSAGE_SUCCESS, poc.getName(), editedCompany.getName()));
    }

    /**
     * Creates and returns a {@code Company} with the details of {@code companyToEdit}
     * edited with {@code addPocCompanyDescriptor}.
     */
    private static Company createEditedCompany(Company companyToEdit, Poc poc) {
        assert companyToEdit != null;

//        Name updatedName = addPocCompanyDescriptor.getName().orElse(companyToEdit.getName());
//        Address updatedAddress = addPocCompanyDescriptor.getAddress().orElse(companyToEdit.getAddress());
//        Set<Tag> updatedTags = addPocCompanyDescriptor.getTags().orElse(companyToEdit.getTags());
//        UniquePocList pocs = addPocCompanyDescriptor.getPocs().orElse(companyToEdit.getPocs());

        companyToEdit.addPoc(poc);
        return companyToEdit;

    }

    /**
     * Stores the details to add the poc to the company. Each non-empty field value will replace the
     * corresponding field value of the company.
     */
    public static class AddPocCompanyDescriptor {
        private Name name;
        private Address address;
        private Set<Tag> tags;
        private UniquePocList pocs;

        public AddPocCompanyDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public AddPocCompanyDescriptor(AddPocCompanyDescriptor toCopy) {
            setName(toCopy.name);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
            setPocs(toCopy.pocs);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, address, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
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

        public void setPocs(UniquePocList pocs) {
            this.pocs = pocs;
        }

        public Optional<UniquePocList> getPocs() {
            return Optional.ofNullable(pocs);
        }

        //        @Override
//        public boolean equals(Object other) {
//            // short circuit if same object
//            if (other == this) {
//                return true;
//            }
//
//            // instanceof handles nulls
//            if (!(other instanceof EditCommand.EditCompanyDescriptor)) {
//                return false;
//            }
//
//            // state check
//            EditCommand.EditCompanyDescriptor e = (EditCommand.EditCompanyDescriptor) other;
//
//            return getName().equals(e.getName())
//                    && getAddress().equals(e.getAddress())
//                    && getTags().equals(e.getTags());
//        }
    }


}
