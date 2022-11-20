package bookface.logic.commands.edit;

import static bookface.logic.parser.CliSyntax.PREFIX_AUTHOR;
import static bookface.logic.parser.CliSyntax.PREFIX_TITLE;
import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import bookface.commons.core.Messages;
import bookface.commons.core.index.Index;
import bookface.commons.util.CollectionUtil;
import bookface.logic.commands.CommandResult;
import bookface.logic.commands.exceptions.CommandException;
import bookface.model.Model;
import bookface.model.book.Author;
import bookface.model.book.Book;
import bookface.model.book.Title;

/**
 * Edits a book from the book list using its displayed index from the book list
 */
public class EditBookCommand extends EditCommand {
    public static final String COMMAND_WORD = "book";
    public static final String MESSAGE_USAGE =
            EditCommand.generateMessage(COMMAND_WORD, "INDEX (must be a positive integer) "
                    + "[" + PREFIX_TITLE + "TITLE] [" + PREFIX_AUTHOR + "AUTHOR]", COMMAND_WORD
                    + " 1 t/The Broken House");

    public static final String MESSAGE_EDIT_BOOK_SUCCESS = "Edited Book: %1$s";

    private final Index index;
    private final EditBookDescriptor editBookDescriptor;

    /**
     * @param index the book index in the filtered book list to edit
     * @param editBookDescriptor details to edit the book with
     */
    public EditBookCommand(Index index, EditBookDescriptor editBookDescriptor) {
        requireNonNull(index);
        requireNonNull(editBookDescriptor);

        this.index = index;
        this.editBookDescriptor = new EditBookDescriptor(editBookDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Book> lastShownList = model.getFilteredBookList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
        }

        Book bookToEdit = lastShownList.get(index.getZeroBased());
        Book editedBook = createEditedBook(bookToEdit, editBookDescriptor);

        if (!bookToEdit.equals(editedBook) && model.hasBook(editedBook)) {
            throw new CommandException(Messages.MESSAGE_DUPLICATE_BOOK);
        }

        model.setBook(bookToEdit, editedBook);
        model.updateFilteredBookList(Model.PREDICATE_SHOW_ALL_BOOKS);
        return new CommandResult(String.format(MESSAGE_EDIT_BOOK_SUCCESS, editedBook));
    }

    /**
     * Creates and returns a {@code Book} with the details of {@code bookToEdit}
     * edited with {@code editBookDescriptor}.
     */
    private static Book createEditedBook(Book bookToEdit, EditBookDescriptor editBookDescriptor) {
        assert bookToEdit != null;

        Author updatedAuthor = editBookDescriptor.getAuthor().orElse(bookToEdit.getAuthor());
        Title updatedTitle = editBookDescriptor.getTitle().orElse(bookToEdit.getTitle());

        return new Book(updatedTitle, updatedAuthor);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditBookCommand)) {
            return false;
        }

        // state check
        EditBookCommand e = (EditBookCommand) other;
        return index.equals(e.index)
                && editBookDescriptor.equals(e.editBookDescriptor);
    }

    /**
     * Stores the details to edit the book with. Each non-empty field value will replace the
     * corresponding field value of the book.
     */
    public static class EditBookDescriptor {
        private Author author;
        private Title title;

        public EditBookDescriptor() {
        }

        /**
         * Copy constructor.
         */
        public EditBookDescriptor(EditBookDescriptor toCopy) {
            setTitle(toCopy.title);
            setAuthor(toCopy.author);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(title, author);
        }

        /**
         * Returns the Author of the {@code Book}
         * Returns {@code Optional#empty()} if {@code book} is null.
         */
        public Optional<Author> getAuthor() {
            return Optional.ofNullable(author);
        }

        /**
         * Sets the author of the {@code book} to this object's {@code book} author.
         */
        public void setAuthor(Author author) {
            this.author = author;
        }

        /**
         * Returns the Title of the {@code Book}
         * Returns {@code Optional#empty()} if {@code book} is null.
         */
        public Optional<Title> getTitle() {
            return Optional.ofNullable(title);
        }

        /**
         * Sets the Title of the {@code Book} to this object's {@code book} title.
         */
        public void setTitle(Title title) {
            this.title = title;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditBookDescriptor)) {
                return false;
            }

            // state check
            EditBookDescriptor e = (EditBookDescriptor) other;

            return getAuthor().equals(e.getAuthor())
                    && getTitle().equals(e.getTitle());
        }
    }
}
