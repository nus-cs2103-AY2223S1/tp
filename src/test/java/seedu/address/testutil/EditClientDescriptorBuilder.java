package seedu.address.testutil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditClientCommand.EditClientDescriptor;
import seedu.address.model.client.Address;
import seedu.address.model.client.Birthday;
import seedu.address.model.client.Client;
import seedu.address.model.client.Email;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.product.Product;

/**
 * A utility class to help with building EditClientDescriptor objects.
 */
public class EditClientDescriptorBuilder {

    private EditClientDescriptor descriptor;

    public EditClientDescriptorBuilder() {
        descriptor = new EditClientDescriptor();
    }

    public EditClientDescriptorBuilder(EditClientDescriptor descriptor) {
        this.descriptor = new EditClientDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditClientDescriptor} with fields containing {@code Client}'s details
     */
    public EditClientDescriptorBuilder(Client client) {
        descriptor = new EditClientDescriptor();
        descriptor.setName(client.getName());
        descriptor.setPhone(client.getPhone());
        descriptor.setEmail(client.getEmail());
        descriptor.setAddress(client.getAddress());
        descriptor.setProducts(client.getProducts());
    }

    /**
     * Sets the {@code Name} of the {@code EditClientDescriptor} that we are building.
     */
    public EditClientDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditClientDescriptor} that we are building.
     */
    public EditClientDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditClientDescriptor} that we are building.
     */
    public EditClientDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(Optional.of(new Email(email)));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditClientDescriptor} that we are building.
     */
    public EditClientDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(Optional.of(new Address(address)));
        return this;
    }

    /**
     * Sets the {@code Birthday} of the {@code EditClientDescriptor} that we are building.
     */
    public EditClientDescriptorBuilder withBirthday(String birthday) {
        descriptor.setBirthday(Optional.of(
                new Birthday(LocalDate.parse(birthday, DateTimeFormatter.ofPattern("ddMMyyyy")))));
        return this;
    }

    /**
     * Parses the {@code Products} into a {@code Set<Product>} and set it to the {@code EditClientDescriptor}
     * that we are building.
     */
    public EditClientDescriptorBuilder withProducts(String... products) {
        Set<Product> productsSet = Stream.of(products).map(Product::new).collect(Collectors.toSet());
        descriptor.setProducts(productsSet);
        return this;
    }


    public EditClientDescriptor build() {
        return descriptor;
    }
}
