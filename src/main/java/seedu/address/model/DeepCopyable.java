package seedu.address.model;

/**
 * Classes implementing {@code DeepCopyable} allows the object to recursively copy
 * fields of its superclass and its own fields.
 * Deep copying in this form is a form of prototyping and does not guarantee parent
 * classes are deep copyable, unlike cloning, although they are suggested to be as well.
 */
public interface DeepCopyable {
    Object deepCopy();
}
