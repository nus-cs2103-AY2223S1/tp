package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_URL_STR;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_LINKS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.team.Link;
import seedu.address.model.team.Url;

/**
 * Edits the details of an existing link in the address book.
 */
public class EditLinkCommand extends Command {
    public static final String COMMAND_WORD = "edit_link";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits a current link identified by the index number used in the displayed link list. \n"
            + "Existing values will be overwritten by the input values. \n"
            + "Parameters: INDEX (must be a positive integer) "
            + "-" + FLAG_NAME_STR + " NAME "
            + "-" + FLAG_URL_STR + " PHONE \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "-" + FLAG_NAME_STR + " \"Google\" "
            + "-" + FLAG_URL_STR + " https://google.com ";
    public static final String MESSAGE_EDIT_LINK_SUCCESS = "Edited link: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_LINK = "This link already exists in the address book.";

    private final Index index;
    private final EditLinkDescriptor editLinkDescriptor;

    /**
     * @param index                of the link in the filtered link list to edit
     * @param editLinkDescriptor details to edit the link with
     */
    public EditLinkCommand(Index index, EditLinkDescriptor editLinkDescriptor) {
        requireNonNull(index);
        requireNonNull(editLinkDescriptor);
        this.index = index;
        this.editLinkDescriptor = editLinkDescriptor;
    }

    /**
     * Creates and returns a {@code Link} with the details of {@code linkToEdit}
     * edited with {@code editLinkDescriptor}.
     */
    private static Link createEditedLink(Link linkToEdit, EditLinkCommand.EditLinkDescriptor editLinkDescriptor) {
        assert editLinkDescriptor != null;

        Name updatedName = editLinkDescriptor.getName().orElse(linkToEdit.getDisplayedName());
        Url updatedUrl = editLinkDescriptor.getUrl().orElse(linkToEdit.getUrl());

        return new Link(updatedName, updatedUrl);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Link> lastShownList = model.getFilteredLinkList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_LINK_DISPLAYED_INDEX);
        }

        Link linkToEdit = lastShownList.get(index.getZeroBased());
        Link editedLink = createEditedLink(linkToEdit, editLinkDescriptor);

        if (!linkToEdit.isSameLink(editedLink) && model.hasLink(editedLink)) {
            throw new CommandException(MESSAGE_DUPLICATE_LINK);
        }

        model.setLink(linkToEdit, editedLink);
        model.updateFilteredLinkList(PREDICATE_SHOW_ALL_LINKS);
        return new CommandResult(String.format(MESSAGE_EDIT_LINK_SUCCESS, editedLink));

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditLinkCommand)) {
            return false;
        }

        // state check
        EditLinkCommand e = (EditLinkCommand) other;
        return index.equals(e.index)
                && editLinkDescriptor.equals(e.editLinkDescriptor);
    }

    /**
     * Stores the details to edit the link with. Each non-empty field value will replace the
     * corresponding field value of the link.
     */
    public static class EditLinkDescriptor {
        private Name name;

        private Url url;

        public EditLinkDescriptor() {};

        /**
         * Copy constructor.
         */
        public EditLinkDescriptor(EditLinkDescriptor toCopy) {
            setName(toCopy.name);
            setUrl(toCopy.url);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, url);
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Url> getUrl() {
            return Optional.ofNullable(url);
        }

        public void setUrl(Url url) {
            this.url = url;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditLinkCommand.EditLinkDescriptor)) {
                return false;
            }

            // state check
            EditLinkCommand.EditLinkDescriptor e = (EditLinkCommand.EditLinkDescriptor) other;

            return getName().equals(e.getName())
                    && getUrl().equals(e.getUrl());
        }


    }
}
