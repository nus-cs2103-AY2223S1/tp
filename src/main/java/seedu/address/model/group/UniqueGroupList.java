package seedu.address.model.group;

import seedu.address.model.item.DisplayItemList;

/**
 * A list of groups that enforces uniqueness between its elements and does not
 * allow nulls.
 * A group is considered unique by comparing using
 * {@code DisplayItem#weakEqual(displayItem))}. As such, adding and updating of
 * persons uses Person#isSamePerson(Person) for equality so as to ensure that
 *
 * Supports a minimal set of list operations.
 *
 * @see Person#isSamePerson(Person)
 */
public class UniqueGroupList extends DisplayItemList<Group> {

}
