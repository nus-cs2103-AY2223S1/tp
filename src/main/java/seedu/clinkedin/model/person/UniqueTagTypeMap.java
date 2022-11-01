package seedu.clinkedin.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.clinkedin.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import seedu.clinkedin.logic.parser.CliSyntax;
import seedu.clinkedin.logic.parser.Prefix;
import seedu.clinkedin.logic.parser.exceptions.DuplicatePrefixException;
import seedu.clinkedin.logic.parser.exceptions.PrefixNotFoundException;
import seedu.clinkedin.model.tag.exceptions.DuplicateTagTypeException;
import seedu.clinkedin.model.tag.exceptions.TagTypeNotFoundException;
import seedu.clinkedin.model.tag.Tag;
import seedu.clinkedin.model.tag.TagType;
import seedu.clinkedin.model.tag.UniqueTagList;
import seedu.clinkedin.model.tag.exceptions.DuplicateTagException;
import seedu.clinkedin.model.tag.exceptions.TagNotFoundException;

/**
 * A list of tagtypes that enforces uniqueness between its elements and does not
 * allow nulls.
 * A tagtype is considered unique by comparing using
 * {@code TagType#equals(Object)}. As such, adding and updating of
 * tag types uses TagType#equals(Object) for equality so as to ensure that
 * the tag type being added or updated is
 * unique in terms of identity in the UniqueTagTypeList. The removal of
 * a tag type also uses TagType#equals(Object) so
 * as to ensure that the tag type with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see TagType#equals(Object)
 */
public class UniqueTagTypeMap implements Iterable<TagType> {
    private static final Map<Prefix, TagType> initialTagTypeMap = Map.of(
            CliSyntax.PREFIX_SKILLTAG, new TagType("Skills", CliSyntax.PREFIX_SKILLTAG),
            CliSyntax.PREFIX_DEGREETAG, new TagType("Degree", CliSyntax.PREFIX_DEGREETAG),
            CliSyntax.PREFIX_JOBTYPETAG, new TagType("Job Type", CliSyntax.PREFIX_JOBTYPETAG)
    );
    private static Map<Prefix, TagType> prefixMap = new HashMap<>(initialTagTypeMap);
    private final ObservableMap<TagType, UniqueTagList> internalMap = FXCollections.observableMap(new HashMap<>());
    private final ObservableMap<TagType, UniqueTagList> internalUnmodifiableMap = FXCollections
            .unmodifiableObservableMap(internalMap);

    /**
     * Adds a new tag type to the existing TAG_TYPES.
     */
    public static void createTagType(Prefix prefix, TagType tagType) throws DuplicatePrefixException,
            DuplicateTagTypeException {
        if (prefixMap.keySet().contains(prefix)) {
            throw new DuplicatePrefixException();
        } else if (prefixMap.values().stream().anyMatch(val -> val.getTagTypeName().equals(tagType.getTagTypeName()))) {
            throw new DuplicateTagTypeException();
        } else {
            prefixMap.put(prefix, tagType);
            CliSyntax.addTagPrefix(prefix);
        }
    }

    /**
     * Removes tagType from list of tag types.
     */
    public static void removeExistingTagType(TagType tagType) throws TagTypeNotFoundException {
        if (prefixMap.values().contains(tagType)) {
            Prefix prefix = prefixMap.keySet().stream().filter(key -> prefixMap.get(key).equals(tagType))
                    .collect(Collectors.toList()).get(0);
            prefixMap.remove(prefix);
            CliSyntax.removeTagPrefix(prefix);
        } else {
            throw new TagTypeNotFoundException();
        }
    }

    public static void setExistingTagType(Prefix toRemovePrefix, Prefix prefix, TagType tagType)
            throws PrefixNotFoundException, TagTypeNotFoundException, DuplicatePrefixException {
        prefixMap.remove(toRemovePrefix);
        CliSyntax.removeTagPrefix(toRemovePrefix);
        UniqueTagTypeMap.createTagType(prefix, tagType);
    }

    /**
     * Returns true if the list contains an equivalent tag type as the given argument.
     */
    public boolean contains(TagType toCheck) {
        requireNonNull(toCheck);
        return internalMap.containsKey(toCheck);
    }

    /**
     * Merges another UniqueTagTypeMap.
     */
    public void mergeTagTypeMap(UniqueTagTypeMap tagTypeMap) throws DuplicateTagException {
        requireNonNull(tagTypeMap);
        boolean isValid = tagTypeMap.toStream().filter(this::contains)
                .allMatch(tagType -> !this.getTagList(tagType).containsAny(tagTypeMap.getTagList(tagType)));
        if (!isValid) {
            throw new DuplicateTagException();
        }
        for (TagType t: tagTypeMap) {
            if (this.contains(t)) {
                this.getTagList(t).merge(tagTypeMap.getTagList(t));
            } else {
                internalMap.put(t, tagTypeMap.getTagList(t));
            }
        }
    }
    /**
     * Subtracts a UniqueTagTypeMap.
     */
    public void removeTags(UniqueTagTypeMap tagTypeMap) throws TagTypeNotFoundException, TagNotFoundException {
        requireNonNull(tagTypeMap);
        boolean allValidTagTypes = tagTypeMap.toStream().allMatch(this::contains);
        if (!allValidTagTypes) {
            throw new TagTypeNotFoundException();
        }
        boolean allValidTags = tagTypeMap.toStream().allMatch(tagType -> this.getTagList(tagType)
                .containsAll(tagTypeMap.getTagList(tagType)));
        if (!allValidTags) {
            throw new TagNotFoundException();
        }
        for (TagType t: tagTypeMap) {
            if (!this.contains(t)) {
                throw new TagTypeNotFoundException();
            }
            this.getTagList(t).removeAll(tagTypeMap.getTagList(t));
            if (this.getTagList(t).getCount() == 0) {
                this.removeTagType(t);
            }
        }
    }

    /**
     * Adds a tag for the given tagType.
     */
    public void mergeTag(TagType tagType, Tag tagName) throws DuplicateTagException {
        requireAllNonNull(tagType, tagName);
        boolean isExisting = this.contains(tagType);
        if (isExisting) {
            this.getTagList(tagType).add(tagName);
        } else {
            UniqueTagList tagList = new UniqueTagList();
            tagList.add(tagName);
            this.internalMap.put(tagType, tagList);
        }
    }
    /**
     * Replaces the tag type {@code target} in the list with {@code editedTagType}.
     * {@code target} must exist in the list.
     * The tag type name of {@code editedTagType} must not be the same as another
     * existing tag type in the list.
     */
    public void setTagType(TagType target, TagType editedTagType) throws TagTypeNotFoundException,
            DuplicateTagTypeException {
        requireAllNonNull(target, editedTagType);
        if (!this.contains(target)) {
            throw new TagTypeNotFoundException();
        }

        if (!target.equals(editedTagType) && this.contains(editedTagType)) {
            throw new DuplicateTagTypeException();
        }
        UniqueTagList tagList = internalMap.get(target);
        internalMap.put(editedTagType, tagList);
        this.removeTagType(target);
    }
    /**
     * Removes the equivalent tag type from the list.
     * The tag type must exist in the list.
     */
    public void removeTagType(TagType toRemove) throws TagTypeNotFoundException {
        requireNonNull(toRemove);
        if (!this.contains(toRemove)) {
            throw new TagTypeNotFoundException();
        }
        internalMap.remove(toRemove);
    }

    public UniqueTagList getTagList(TagType toGet) throws TagTypeNotFoundException {
        requireNonNull(toGet);
        if (!this.contains(toGet)) {
            throw new TagTypeNotFoundException();
        }
        return internalMap.get(toGet);
    }

    public void setTagTypeMap(UniqueTagTypeMap replacement) {
        requireNonNull(replacement);
        internalMap.clear();
        internalMap.putAll(replacement.internalMap);
    }

    /**
     * Replaces the contents of this list with {@code tagTypes}.
     * {@code tagTypes} must not contain duplicate tags.
     */
    public void setTagTypeMap(Map<TagType, UniqueTagList> tagTypeMap) {
        requireAllNonNull(tagTypeMap);
        internalMap.putAll(tagTypeMap);
    }

    /**
     * Get the count of unique tag types in the list. Used for displaying information
     * on total count of tag types.
     * @return the count of unique tag types in the list.
     */
    public int getCount() {
        return internalMap.size();
    }

    public int getTagCount() {
        int count = 0;
        for (TagType t: this) {
            assert this.getTagList(t) != null;
            count += this.getTagList(t).getCount();
        }
        return count;
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableMap<TagType, UniqueTagList> asUnmodifiableObservableMap() {
        return internalUnmodifiableMap;
    }

    @Override
    public Iterator<TagType> iterator() {
        return internalMap.keySet().iterator();
    }

    public static Map<Prefix, TagType> getPrefixMap() {
        return prefixMap;
    }

    public static void setPrefixMap(Map<Prefix, TagType> map) {
        prefixMap = map;
        CliSyntax.setTagPrefix(map.keySet().stream().collect(Collectors.toList()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTagTypeMap // instanceof handles nulls
                && internalMap.equals(((UniqueTagTypeMap) other).internalMap));
    }

    @Override
    public int hashCode() {
        return internalMap.hashCode();
    }
    public Stream<TagType> toStream() {
        return StreamSupport.stream(this.spliterator(), false);
    }
    public static TagType getTagType(Prefix pref) {
        return prefixMap.get(pref);
    }
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (TagType t: internalMap.keySet()) {
            res.append(String.format("%s: %s\n", t.toString(), internalMap.get(t)));
        }
        return res.toString();
    }

    public static Prefix getPrefixFromTagType(String tagType) throws TagTypeNotFoundException {
        for (Prefix pref: prefixMap.keySet()) {
            if (prefixMap.get(pref).getTagTypeName().equals(tagType)) {
                return pref;
            }
        }
        throw new TagTypeNotFoundException(tagType);
    }

    public static TagType getTagTypeFromPrefix(Prefix prefix) throws TagTypeNotFoundException {
        for (TagType tagType: prefixMap.values()) {
            if (tagType.getPrefix().equals(prefix)) {
                return tagType;
            }
        }
        throw new TagTypeNotFoundException();
    }

    public boolean isEmpty() {
        return internalMap.isEmpty();
    }

    public static boolean isExist(String otherTagType) {
        return prefixMap.values().stream().anyMatch(tagType -> tagType.getTagTypeName().equals(otherTagType));
    }
}
