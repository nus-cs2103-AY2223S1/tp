package seedu.address.testutil;

import static seedu.address.model.category.Category.PATIENT_SYMBOL;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.category.Category;
import seedu.address.model.person.Address;
import seedu.address.model.person.DateSlot;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private final EditPersonDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditPersonDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing
     * {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Person person) {
        descriptor = new EditPersonDescriptor();
        descriptor.setUid(person.getUid());
        descriptor.setCategory(person.getCategory());
        descriptor.setName(person.getName());
        descriptor.setGender(person.getGender());
        descriptor.setPhone(person.getPhone());
        descriptor.setEmail(person.getEmail());
        descriptor.setAddress(person.getAddress());
        descriptor.setTags(person.getTags());
        if (person.getCategory().categoryName.equals(PATIENT_SYMBOL)) {
            descriptor.setDatesSlots(((Patient) person).getDatesSlots());
            descriptor.setDateSlotIndexes(new ArrayList<Index>());
        }
    }

    /**
     * Sets the {@code Category} of the {@code EditPersonDescriptor} that we are
     * building.
     */
    public EditPersonDescriptorBuilder withCategory(String category) {
        descriptor.setCategory(new Category(category));
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are
     * building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Gender} of the {@code EditPersonDescriptor} that we are
     * building.
     */
    public EditPersonDescriptorBuilder withGender(String gender) {
        descriptor.setGender(new Gender(gender));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are
     * building.
     */
    public EditPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are
     * building.
     */
    public EditPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPersonDescriptor} that we are
     * building.
     */
    public EditPersonDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the
     * {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Parses the {@code dateSlots} into a {@code List<DateSlot>} and set it to the
     * {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withDatesSlots(String... datesSlots) {
        List<DateSlot> dateSlotList = Stream.of(datesSlots).map(DateSlot::new).collect(Collectors.toList());
        descriptor.setDatesSlots(dateSlotList);
        return this;
    }

    /**
     * Parses the {@code dateSlotIndexes} into a {@code List<Index>} and set it to
     * the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withDateSlotIndexes(String... dateSlotIndexes) {
        List<Integer> dateSlotIndexesNoList = Stream.of(dateSlotIndexes).map(Integer::parseInt)
                .collect(Collectors.toList());
        List<Index> dateSlotIndexesList = new ArrayList<>();
        for (Integer integer : dateSlotIndexesNoList) {
            dateSlotIndexesList.add(Index.fromOneBased(integer));
        }
        descriptor.setDateSlotIndexes(dateSlotIndexesList);
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }

}
