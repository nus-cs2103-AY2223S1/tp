package seedu.address.model;

/**
 * Classes implementing {@code ShallowCopyable} allows the object to copy only
 * first-level fields of its own class.
 * Shallow copying in this form is a form of prototyping and does not guarantee parent
 * classes are shallow copyable, unlike cloning, although they are suggested to be as well.
 * Shallow copy is used in place of clone if there are incompatibilities with the parent class
 * or the fields consist of recursive substructures that could cause concurrency issues in
 * multithreaded environments.
 */
public interface ShallowCopyable {
    Object shallowCopy();
}
