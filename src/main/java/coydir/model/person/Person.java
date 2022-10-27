package coydir.model.person;

import static coydir.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import coydir.model.tag.Tag;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Represents a Person in the database.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final EmployeeId employeeId;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Position position;
    private final Department department;
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private Queue<Leave> leaves = new PriorityQueue<>(Leave.COMPARATOR);
    private final int totalNumberOfLeaves;
    private int leavesLeft = 0;
    private ArrayList<Rating> performanceHistory = new ArrayList<>();
    private Rating rating;

    // Rating table requirements
    private SimpleStringProperty col1;
    private SimpleStringProperty col2;
    private SimpleStringProperty col3;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, EmployeeId employeeId, Phone phone, Email email, Position position,
            Department department, Address address, Set<Tag> tags, int numberOfLeaves, Rating rating) {
        requireAllNonNull(name, employeeId, phone, email, position, department, address, numberOfLeaves, rating);
        this.name = name;
        this.employeeId = employeeId;
        this.phone = phone;
        this.email = email;
        this.position = position;
        this.department = department;
        this.address = address;
        this.tags.addAll(tags);
        this.totalNumberOfLeaves = numberOfLeaves;
        this.leavesLeft = numberOfLeaves;
        this.rating = rating;
        this.col1 = new SimpleStringProperty(this.employeeId.toString());
        this.col2 = new SimpleStringProperty(this.name.toString());
        this.col3 = new SimpleStringProperty(this.rating.toString());
    }

    public Name getName() {
        return this.name;
    }

    public EmployeeId getEmployeeId() {
        return this.employeeId;
    }

    public Phone getPhone() {
        return this.phone;
    }

    public Email getEmail() {
        return this.email;
    }

    public Position getPosition() {
        return this.position;
    }

    public Department getDepartment() {
        return this.department;
    }

    public Address getAddress() {
        return this.address;
    }

    public String getCol1() {
        return this.col1.get();
    }

    public String getCol2() {
        return this.col2.get();
    }

    public String getCol3() {
        return this.col3.get();
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public void addLeave(Leave toAdd) {
        this.leaves.add(toAdd);
    }

    public void deleteLeave(Leave toDelete) {
        this.leaves.remove(toDelete);
    }

    public Queue<Leave> getLeaves() {
        return this.leaves;
    }

    public ObservableList<Leave> getObservableListLeaves() {
        ObservableList<Leave> list = FXCollections.observableArrayList(leaves);
        list.sort(Leave.COMPARATOR);
        return list;
    }

    public int getTotalNumberOfLeaves() {
        return this.totalNumberOfLeaves;
    }

    public int getLeavesLeft() {
        return this.leavesLeft;
    }

    public void setLeavesLeft(int leavesLeft) {
        this.leavesLeft = leavesLeft;
    }

    /**
     * Add a rating to the person.
     * @param toAdd the rating to be added.
     */
    public void addRating(Rating toAdd) {
        this.performanceHistory.add(toAdd);
        this.col3 = new SimpleStringProperty(toAdd.toString());
    }

    public void setRating(Rating rating) {
        this.rating = rating;
        this.col3 = new SimpleStringProperty(rating.toString());
    }

    public Rating getRating() {
        return this.rating;
    }

    public ArrayList<Rating> getRatingHistory() {
        return this.performanceHistory;
    }

    public void setRatingHistory(ArrayList<Rating> ratinglist) {
        this.performanceHistory = ratinglist;
    }

    public void setLeaves(Queue<Leave> leaves) {
        this.leaves = leaves;
    }

    /**
     * Check whether a person is currently on leave
     * @return a string representation of "true" if the person is currently on leave, "false" otherwise.
     */
    public String onLeaveStatus() {
        for (Leave leave: leaves) {
            if (leave.isOnLeave()) {
                return "True";
            }
        }
        return "False";
    }

    /**
     * Check whether a person is currently on leave
     * @return true if the person is currently on leave, false otherwise.
     */
    public boolean isOnLeave() {
        for (Leave leave: leaves) {
            if (leave.isOnLeave()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    private boolean isSameLeaves(Queue<Leave> queue) {
        Queue<Leave> copy1 = new PriorityQueue<>(this.leaves);
        Queue<Leave> copy2 = new PriorityQueue<>(queue);
        if (!(this.leaves.size() == queue.size())) {
            return false;
        } else {
            for (Leave leave: copy1) {
                if (copy1.poll() != copy2.poll()) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getEmployeeId().equals(getEmployeeId())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getPosition().equals(getPosition())
                && otherPerson.getDepartment().equals(getDepartment())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getTags().equals(getTags())
                && this.isSameLeaves(otherPerson.getLeaves())
                && otherPerson.getTotalNumberOfLeaves() == getTotalNumberOfLeaves()
                && otherPerson.getLeavesLeft() == getLeavesLeft()
                && otherPerson.getRating().equals(getRating())
                && otherPerson.getRatingHistory().equals(getRatingHistory());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, employeeId, phone, email, position, department, address, tags,
                leaves, totalNumberOfLeaves, leavesLeft, rating);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Employee ID: ")
                .append(getEmployeeId())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Position: ")
                .append(getPosition())
                .append("; Department: ")
                .append(getDepartment())
                .append("; Address: ")
                .append(getAddress())
                .append("; Total Leaves: ")
                .append(getTotalNumberOfLeaves())
                .append("; Number of Leaves Left: ")
                .append(getLeavesLeft())
                .append("; Performance: ")
                .append(getRating());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        Queue<Leave> leaves = getLeaves();
        if (!leaves.isEmpty()) {
            builder.append("; Leaves: ");
            leaves.forEach(builder::append);
        }

        ArrayList<Rating> performanceHistory = getRatingHistory();
        if (!performanceHistory.isEmpty()) {
            builder.append("; Performance History: ");
            performanceHistory.forEach(builder::append);
        }

        return builder.toString();
    }

}
