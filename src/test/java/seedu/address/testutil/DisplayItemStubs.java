package seedu.address.testutil;

import static seedu.address.model.AccessDisplayFlags.GROUP;
import static seedu.address.model.AccessDisplayFlags.PERSON;
import static seedu.address.model.AccessDisplayFlags.TASK;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import seedu.address.model.attribute.Attribute;
import seedu.address.model.attribute.Name;
import seedu.address.model.attribute.exceptions.AttributeException;
import seedu.address.model.item.DisplayItem;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with stubs DisplayItems for group, person and task
 */
public class DisplayItemStubs {
    public static final DisplayItem BASIC_GROUP_STUB = new DisplayItem() {

        @Override
        public int getTypeFlag() {
            return GROUP;
        }

        @Override
        public boolean stronglyEqual(DisplayItem o) {
            return false;
        }

        @Override
        public boolean weaklyEqual(DisplayItem o) {
            return false;
        }

        @Override
        public void setParent(DisplayItem o) {}

        @Override
        public void rename(String name) {}

        @Override
        public void removeParent(DisplayItem o) {}

        @Override
        public String getFullPath() {
            return "Group_STUB/";
        }

        @Override
        public String getRelativePath(DisplayItem parent) {
            return getFullPath();
        }

        @Override
        public List<Attribute<?>> getAttributes() {
            return List.<Attribute<?>>of();
        }

        @Override
        public List<Attribute<?>> getSavedAttributes() {
            return List.<Attribute<?>>of();
        }

        @Override
        public Optional<Attribute<?>> getAttribute(String type) {
            return Optional.empty();
        }

        @Override
        public Name getName() {
            return new Name("Group_STUB");
        }

        @Override
        public Set<Tag> getTags() {
            return Set.of();
        }

        @Override
        public void addTags(String... description) {}

        @Override
        public void deleteTag(String description) {}

        @Override
        public void setTags(Set<Tag> tags) {}

        @Override
        public Set<? extends DisplayItem> getParents() {
            return null;
        }

        @Override
        public void addAttribute(Attribute<?> attribute) {}

        @Override
        public void addAttribute(String attributeName, String attributeContent) throws AttributeException {}

        @Override
        public void editAttribute(String attributeName, String attributeContent) throws AttributeException {}

        @Override
        public void deleteAttribute(String type) throws AttributeException {}

        @Override
        public boolean isPartOfContext(DisplayItem o) {
            return false;
        }

        @Override
        public UUID getUid() {
            return UUID.nameUUIDFromBytes("GROUPSTUB".getBytes());
        }
    };

    public static final DisplayItem BASIC_PERSON_STUB = new DisplayItem() {

        @Override
        public int getTypeFlag() {
            return PERSON;
        }

        @Override
        public boolean stronglyEqual(DisplayItem o) {
            return false;
        }

        @Override
        public boolean weaklyEqual(DisplayItem o) {
            return false;
        }

        @Override
        public void setParent(DisplayItem o) {}

        @Override
        public void rename(String name) {}

        @Override
        public void removeParent(DisplayItem o) {}

        @Override
        public String getFullPath() {
            return "PERSON_STUB";
        }

        @Override
        public String getRelativePath(DisplayItem parent) {
            return getFullPath();
        }

        @Override
        public List<Attribute<?>> getAttributes() {
            return List.<Attribute<?>>of();
        }

        @Override
        public List<Attribute<?>> getSavedAttributes() {
            return List.<Attribute<?>>of();
        }

        @Override
        public Optional<Attribute<?>> getAttribute(String type) {
            return Optional.empty();
        }

        @Override
        public Name getName() {
            return new Name("PERSON_STUB");
        }

        @Override
        public Set<Tag> getTags() {
            return Set.of();
        }

        @Override
        public void addTags(String... description) {}

        @Override
        public void deleteTag(String description) {}

        @Override
        public void setTags(Set<Tag> tags) {}

        @Override
        public Set<? extends DisplayItem> getParents() {
            return null;
        }

        @Override
        public void addAttribute(Attribute<?> attribute) {}

        @Override
        public void addAttribute(String attributeName, String attributeContent) throws AttributeException {}

        @Override
        public void editAttribute(String attributeName, String attributeContent) throws AttributeException {}

        @Override
        public void deleteAttribute(String type) throws AttributeException {}

        @Override
        public boolean isPartOfContext(DisplayItem o) {
            return false;
        }

        @Override
        public UUID getUid() {
            return UUID.nameUUIDFromBytes("PERSONSTUB".getBytes());
        }
    };

    public static final DisplayItem BASIC_TASK_STUB = new DisplayItem() {

        @Override
        public int getTypeFlag() {
            return TASK;
        }

        @Override
        public boolean stronglyEqual(DisplayItem o) {
            return false;
        }

        @Override
        public boolean weaklyEqual(DisplayItem o) {
            return false;
        }

        @Override
        public void setParent(DisplayItem o) {}

        @Override
        public void rename(String name) {}

        @Override
        public void removeParent(DisplayItem o) {}

        @Override
        public String getFullPath() {
            return "TASK_STUB";
        }

        @Override
        public String getRelativePath(DisplayItem parent) {
            return getFullPath();
        }

        @Override
        public List<Attribute<?>> getAttributes() {
            return List.<Attribute<?>>of();
        }

        @Override
        public List<Attribute<?>> getSavedAttributes() {
            return List.<Attribute<?>>of();
        }

        @Override
        public Optional<Attribute<?>> getAttribute(String type) {
            return Optional.empty();
        }

        @Override
        public Name getName() {
            return new Name("TASK_STUB");
        }

        @Override
        public Set<Tag> getTags() {
            return Set.of();
        }

        @Override
        public void addTags(String... description) {}

        @Override
        public void deleteTag(String description) {}

        @Override
        public void setTags(Set<Tag> tags) {}

        @Override
        public Set<? extends DisplayItem> getParents() {
            return null;
        }

        @Override
        public void addAttribute(Attribute<?> attribute) {}

        @Override
        public void addAttribute(String attributeName, String attributeContent) throws AttributeException {}

        @Override
        public void editAttribute(String attributeName, String attributeContent) throws AttributeException {}

        @Override
        public void deleteAttribute(String type) throws AttributeException {}

        @Override
        public boolean isPartOfContext(DisplayItem o) {
            return false;
        }

        @Override
        public UUID getUid() {
            return UUID.nameUUIDFromBytes("TASKSTUB".getBytes());
        }
    };
}
