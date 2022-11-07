package bookface.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import bookface.logic.commands.edit.EditBookCommand;
import bookface.model.book.Author;
import bookface.model.book.Book;
import bookface.model.book.Title;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditBookDescriptorBuilder {

    private final EditBookCommand.EditBookDescriptor descriptor;

    public EditBookDescriptorBuilder() {
        descriptor = new EditBookCommand.EditBookDescriptor();
    }

    public EditBookDescriptorBuilder(EditBookCommand.EditBookDescriptor descriptor) {
        this.descriptor = new EditBookCommand.EditBookDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditBookDescriptor} with fields containing {@code Book}'s details
     */
    public EditBookDescriptorBuilder(Book book) {
        descriptor = new EditBookCommand.EditBookDescriptor();
        descriptor.setTitle(book.getTitle());
        descriptor.setAuthor((book.getAuthor()));
    }

    /**
     * Sets the {@code Title} of the {@code EditBookDescriptor} that we are building.
     */
    public EditBookDescriptorBuilder withTitle(String title) {
        descriptor.setTitle(new Title(title));
        return this;
    }

    /**
     * Sets the {@code Author} of the {@code EditBookDescriptor} that we are building.
     */
    public EditBookDescriptorBuilder withAuthor(String author) {
        descriptor.setAuthor(new Author(author));
        return this;
    }

    public EditBookCommand.EditBookDescriptor build() {
        return descriptor;
    }
}
