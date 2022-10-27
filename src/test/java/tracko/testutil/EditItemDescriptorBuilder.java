package tracko.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import tracko.logic.commands.item.EditItemCommand.EditItemDescriptor;
import tracko.model.item.Description;
import tracko.model.item.InventoryItem;
import tracko.model.item.ItemName;
import tracko.model.item.Price;
import tracko.model.item.Quantity;
import tracko.model.tag.Tag;

/**
 * A utility class to help with building EditItemDescriptor objects.
 */
public class EditItemDescriptorBuilder {

    private EditItemDescriptor descriptor;

    public EditItemDescriptorBuilder() {
        descriptor = new EditItemDescriptor();
    }

    public EditItemDescriptorBuilder(EditItemDescriptor descriptor) {
        this.descriptor = new EditItemDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditOrderDescriptor} with fields containing {@code Order}'s details
     */
    public EditItemDescriptorBuilder(InventoryItem item) {
        descriptor = new EditItemDescriptor();
        descriptor.setItemName(item.getItemName());
        descriptor.setQuantity(item.getTotalQuantity());
        descriptor.setDescription(item.getDescription());
        descriptor.setTags(item.getTags());
        descriptor.setSellPrice(item.getSellPrice());
        descriptor.setCostPrice(item.getCostPrice());
    }

    /**
     * Sets the {@code ItemName} of the {@code EditItemDescriptor} that we are building.
     */
    public EditItemDescriptorBuilder withItemName(String itemName) {
        descriptor.setItemName(new ItemName(itemName));
        return this;
    }

    /**
     * Sets the {@code Quantity} of the {@code EditItemDescriptor} that we are building.
     */
    public EditItemDescriptorBuilder withQuantity(Integer quantity) {
        descriptor.setQuantity(new Quantity(quantity));
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code EditItemDescriptor} that we are building.
     */
    public EditItemDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    /**
     * Sets the {@code Tags} of the {@code EditItemDescriptor} that we are building.
     */
    public EditItemDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Sets the {@code SellPrice} of the {@code EditItemDescriptor} that we are building.
     */
    public EditItemDescriptorBuilder withSellPrice(double price) {
        descriptor.setSellPrice(new Price(price));
        return this;
    }

    /**
     * Sets the {@code CostPrice} of the {@code EditItemDescriptor} that we are building.
     */
    public EditItemDescriptorBuilder withCostPrice(double price) {
        descriptor.setCostPrice(new Price(price));
        return this;
    }

    public EditItemDescriptor build() {
        return descriptor;
    }
}

