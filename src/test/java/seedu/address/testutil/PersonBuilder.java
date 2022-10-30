package seedu.address.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.scene.Node;
import seedu.address.model.attribute.Address;
import seedu.address.model.attribute.Attribute;
import seedu.address.model.attribute.Email;
import seedu.address.model.attribute.Field;
import seedu.address.model.attribute.Name;
import seedu.address.model.attribute.Phone;
import seedu.address.model.person.Fields;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private List<Attribute<?>> attrs;
    private Set<Tag> tags;
    private Fields fields;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        attrs = new ArrayList<>();
        tags = new HashSet<>();
        fields = new Fields();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        attrs = new ArrayList<>(personToCopy.getAttributes());
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are
     * building.
     */
    public PersonBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Adds a custom attribute.
     * 
     * @param name
     * @param data
     * @param <U>
     * @return
     */
    public <U> PersonBuilder addCustomAttr(String name, U data) {
        this.attrs.add(new Attribute<U>() {
            @Override
            public String getAttributeType() {
                return name;
            }

            @Override
            public U getAttributeContent() {
                return data;
            }

            @Override
            public boolean isVisibleInMenu() {
                return true;
            }

            @Override
            public boolean isDisplayable() {
                return true;
            }

            @Override
            public boolean isAllFlagMatch(int flag) {
                return false;
            }

            @Override
            public boolean isAnyFlagMatch(int flag) {
                return false;
            }

            /**
             * Returns true of any of the bits of the style flag settings is true
             *
             * @param flag
             */
            @Override
            public boolean isAnyStyleMatch(int flag) {
                return false;
            }

            /**
             * Returns true of all of the bits of the style flag settings is true
             *
             * @param flag
             */
            @Override
            public boolean isAllStyleMatch(int flag) {
                return false;
            }

            @Override
            public Node getJavaFxRepresentation() {
                return null;
            }

            @Override
            public <T> boolean isSameType(Attribute<T> o) {
                return false;
            }

            @Override
            public Map<String, Object> toSaveableData() {
                return null;
            }

            @Override
            public boolean isNameMatch(String name) {
                return false;
            }
        });
        return this;
    }

    /**
     * Sets the {@code Fields} of the {@code Person} that we are building.
     */
    public PersonBuilder withFields(String... fieldNames) {
        fields = new Fields();
        for (String fieldName : fieldNames) {
            Field field = new Field(fieldName);
            fields.addField(field);
        }
        return this;
    }

    /**
     * Returns a person with specified attributes in builder.
     * 
     * @return
     */
    public Person build() {
        Person p = new Person(name.fullName);
        p.setTags(tags);
        attrs.forEach(attr -> p.addAttribute(attr));
        return p;
    }

    /**
     * Adds address attribute to person
     * 
     * @param string address
     * @return
     */
    public PersonBuilder withAddress(String string) {
        attrs.add(new Address(string));
        return this;
    }

    /**
     * Adds email attribute to person
     * 
     * @param string email
     * @return
     */
    public PersonBuilder withEmail(String string) {
        attrs.add(new Email(string));
        return this;
    }

    /**
     * Adds phone attribute to person
     * 
     * @param string phone number
     * @return
     */
    public PersonBuilder withPhone(String string) {
        attrs.add(new Phone(string));
        return this;
    }

}
