package seedu.address.testutil;

import static seedu.address.model.person.Specialisation.EMPTY_SPECIALISATION;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.GithubUsername;
import seedu.address.model.person.Location;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Name;
import seedu.address.model.person.OfficeHour;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Professor;
import seedu.address.model.person.Rating;
import seedu.address.model.person.Specialisation;
import seedu.address.model.person.Student;
import seedu.address.model.person.TeachingAssistant;
import seedu.address.model.person.Year;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditPersonDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Student person) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(person.getName());
        descriptor.setPhone(person.getPhone());
        descriptor.setEmail(person.getEmail());
        descriptor.setGender(person.getGender());
        descriptor.setTags(person.getTags());
        descriptor.setModuleCodes(person.getModuleCodes());
        descriptor.setYear(person.getYear());
        descriptor.setLocation(person.getLocation());
        descriptor.setGithubUsername(person.getUsername());
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Professor person) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(person.getName());
        descriptor.setModuleCode(person.getModuleCode());
        descriptor.setModuleCodes(new HashSet<>(List.of(person.getModuleCode())));
        descriptor.setPhone(person.getPhone());
        descriptor.setEmail(person.getEmail());
        descriptor.setGender(person.getGender());
        descriptor.setTags(person.getTags());
        descriptor.setLocation(person.getLocation());
        descriptor.setGithubUsername(person.getUsername());
        descriptor.setRating(person.getRating());
        descriptor.setSpecialisation(person.getSpecialisation());
        descriptor.setOfficeHour(person.getOfficeHour());
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(TeachingAssistant person) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(person.getName());
        descriptor.setModuleCode(person.getModuleCode());
        descriptor.setModuleCodes(new HashSet<>(List.of(person.getModuleCode())));
        descriptor.setPhone(person.getPhone());
        descriptor.setEmail(person.getEmail());
        descriptor.setGender(person.getGender());
        descriptor.setTags(person.getTags());
        descriptor.setLocation(person.getLocation());
        descriptor.setGithubUsername(person.getUsername());
        descriptor.setRating(person.getRating());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Gender} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withGender(String gender) {
        descriptor.setGender(new Gender(gender));
        return this;
    }

    /**
     * Sets the {@code ModuleCode} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withModuleCode(String moduleCode) {
        descriptor.setModuleCode(new ModuleCode(moduleCode));
        //set module hashset for equals() of EditPersonDescriptor
        Set<ModuleCode> moduleCodes = new HashSet<>();
        moduleCodes.add(new ModuleCode(moduleCode));
        descriptor.setModuleCodes(moduleCodes);
        return this;
    }

    /**
     * Sets the module code set of the descriptor we are building.
     * @param moduleCodes the set of module codes to set to
     * @return the builder object
     */
    public EditPersonDescriptorBuilder withModuleCodeSet(Set<ModuleCode> moduleCodes) {
        descriptor.setModuleCodes(moduleCodes);
        return this;
    }

    /**
     * Creates and sets the module code set of the descriptor we are building.
     * @param modules the string of module codes to set to
     * @return the builder object
     */
    public EditPersonDescriptorBuilder withModuleCodeSet(String... modules) {
        List<ModuleCode> moduleCodeList = Stream.of(modules)
                .map(ModuleCode::new).collect(Collectors.toList());
        descriptor.setModuleCodes(new HashSet<>(moduleCodeList));
        //last module code input is set as module code for prof and ta
        descriptor.setModuleCode(moduleCodeList.get(moduleCodeList.size() - 1));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Sets the {@code GithubUsername} of the {@code EditPersonDescriptor} that we are building.
     * that we are building.
     */
    public EditPersonDescriptorBuilder withUsername(String username) {
        if (username == null) {
            descriptor.setGithubUsername(new GithubUsername("", false));
        } else {
            descriptor.setGithubUsername(new GithubUsername(username, true));
        }
        return this;
    }

    /**
     * Sets the year of the descriptor we are building.
     * @param year the year to set to
     * @return the builder object
     */
    public EditPersonDescriptorBuilder withYear(String year) {
        if (year == null) {
            descriptor.setYear(new Year("", false));
        } else {
            descriptor.setYear(new Year(year));
        }
        return this;
    }

    /**
     * Sets the specialisation of the descriptor we are building.
     * @param specialisation the specialisation to set to
     * @return the builder object
     */
    public EditPersonDescriptorBuilder withSpecialisation(String specialisation) {
        if (specialisation == null || specialisation.equals(EMPTY_SPECIALISATION)) {
            descriptor.setSpecialisation(new Specialisation("", false));
        } else {
            descriptor.setSpecialisation(new Specialisation(specialisation));
        }
        return this;
    }

    /**
     * Sets the office hour of the descriptor we are building.
     * @param officeHour the office hour to set to
     * @return the builder object
     */
    public EditPersonDescriptorBuilder withOfficeHour(String officeHour) {
        if (officeHour == null) {
            descriptor.setOfficeHour(new OfficeHour("", false));
        } else {
            descriptor.setOfficeHour(new OfficeHour(officeHour, true));
        }
        return this;
    }

    /**
     * Sets the rating of the descriptor we are building.
     * @param rating the rating to set to
     * @return the builder object
     */
    public EditPersonDescriptorBuilder withRating(String rating) {
        if (rating == null) {
            descriptor.setRating(new Rating("", false));
        } else {
            descriptor.setRating(new Rating(rating));
        }
        return this;
    }

    /**
     * Sets the location of the descriptor we are building.
     * @param location the location to set to
     * @return the builder object
     */
    public EditPersonDescriptorBuilder withLocation(String location) {
        descriptor.setLocation(new Location(location));
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }

}
