package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.module.ModuleCode;
import seedu.address.model.tag.Tag;

/**
 * Represents a Student in the address book.
 */
public class Student extends Person {

    private final StudentId id;
    private final TelegramHandle telegramHandle;
    private final Set<ModuleCode> studentModuleInfo = new HashSet<>();
    /**
     * Every field must be present and not null.
     *
     * @param name
     * @param phone
     * @param email
     * @param address
     * @param tags
     */
    public Student(Name name, Phone phone, Email email, Address address, Set<Tag> tags, StudentId id,
                   TelegramHandle telegramHandle, Set<ModuleCode> studentModuleInfo) {
        super(name, phone, email, address, tags);
        requireAllNonNull(id, telegramHandle, studentModuleInfo);
        this.id = id;
        this.telegramHandle = telegramHandle;
        this.studentModuleInfo.addAll(studentModuleInfo);
    }

    public StudentId getId() {
        return id;
    }

    public TelegramHandle getTelegramHandle() {
        return telegramHandle;
    }

    public Set<ModuleCode> getStudentModuleInfo() {
        return Collections.unmodifiableSet(studentModuleInfo);
    }

    /**
     * Compares if students are the same.
     *
     * @param otherStudent
     * @return True if students are the same.
     */
    public boolean isSamePerson(Person otherStudent) {
        if (otherStudent == this) {
            return true;
        }
        if (otherStudent instanceof Student) {
            Student temp = (Student) otherStudent;
            return otherStudent != null
                    && temp.getId().equals(getId());
        }
        return false;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; ID: ")
                .append(getId())
                .append("; Telegram: ")
                .append(getTelegramHandle());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        Set<ModuleCode> moduleInfo = getStudentModuleInfo();
        if (!moduleInfo.isEmpty()) {
            builder.append("; Module Info: ");
            moduleInfo.forEach(builder::append);
        }
        return builder.toString();
    }
}
