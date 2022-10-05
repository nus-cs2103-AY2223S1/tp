package foodwhere.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import foodwhere.logic.commands.EditCommand;
import foodwhere.model.detail.Detail;
import foodwhere.model.stall.Address;
import foodwhere.model.stall.Name;
import foodwhere.model.stall.Stall;
import foodwhere.model.stall.Phone;

/**
 * A utility class to help with building EditStallDescriptor objects.
 */
public class EditStallDescriptorBuilder {

    private EditCommand.EditStallDescriptor descriptor;

    public EditStallDescriptorBuilder() {
        descriptor = new EditCommand.EditStallDescriptor();
    }

    public EditStallDescriptorBuilder(EditCommand.EditStallDescriptor descriptor) {
        this.descriptor = new EditCommand.EditStallDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditStallDescriptor} with fields containing {@code stall}'s details
     */
    public EditStallDescriptorBuilder(Stall stall) {
        descriptor = new EditCommand.EditStallDescriptor();
        descriptor.setName(stall.getName());
        descriptor.setPhone(stall.getPhone());
        descriptor.setAddress(stall.getAddress());
        descriptor.setDetails(stall.getDetails());
    }

    /**
     * Sets the {@code Name} of the {@code EditStallDescriptor} that we are building.
     */
    public EditStallDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditStallDescriptor} that we are building.
     */
    public EditStallDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditStallDescriptor} that we are building.
     */
    public EditStallDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code details} into a {@code Set<Detail>} and set it to the {@code EditStallDescriptor}
     * that we are building.
     */
    public EditStallDescriptorBuilder withDetails(String... details) {
        Set<Detail> detailSet = Stream.of(details).map(Detail::new).collect(Collectors.toSet());
        descriptor.setDetails(detailSet);
        return this;
    }

    public EditCommand.EditStallDescriptor build() {
        return descriptor;
    }
}
