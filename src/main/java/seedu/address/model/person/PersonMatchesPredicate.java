package seedu.address.model.person;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.commons.util.StringUtil;


/**
 * Tests that a {@code Person}'s {@code Name} and {@code ModuleCode} matches any of the keywords given.
 */
public class PersonMatchesPredicate implements Predicate<Person> {

    private List<String> namesList;
    private List<String> modulesList;
    private List<String> phonesList;
    private List<String> emailsList;
    private List<String> gendersList;
    private Set<String> tagsList;
    private List<String> locationsList;

    private boolean hasNamesList;
    private boolean hasModulesList;
    private boolean hasPhonesList;
    private boolean hasEmailsList;
    private boolean hasGendersList;
    private boolean hasTagsList;
    private boolean hasLocationsList;
    private boolean hasAllTags;
    /**
     * Creates a PersonMatchesPredicate object and initialises
     * the required variables.
     */
    public PersonMatchesPredicate() {
        namesList = new ArrayList<>();
        modulesList = new ArrayList<>();
        phonesList = new ArrayList<>();
        emailsList = new ArrayList<>();
        gendersList = new ArrayList<>();
        tagsList = new HashSet<>();
        locationsList = new ArrayList<>();

        hasNamesList = false;
        hasModulesList = false;
        hasPhonesList = false;
        hasEmailsList = false;
        hasGendersList = false;
        hasTagsList = false;
        hasAllTags = false;
        hasLocationsList = false;
    }

    @Override
    public boolean test(Person person) {
        return nameMatches(person)
                && moduleMatches(person) && phoneMatches(person)
                && emailMatches(person) && genderMatches(person)
                && tagMatches(person) && locationMatches(person);
    }

    /**
     * Checks if a person's name matches any of the provided names.
     * Returns true if no names have been provided by the user to filter by.
     *
     * @param person the person to check against the specified names
     * @return true if a match is found or if no names were provided
     */
    public boolean nameMatches(Person person) {
        if (!hasNamesList) {
            return true;
        } else {
            return namesList.stream()
                    .anyMatch(name -> StringUtil.containsWordIgnoreCase(person.getName().toString(), name));
        }
    }

    /**
     * Checks if a person's module matches any of the provided modules.
     * Returns true if no modules have been provided by the user to filter by.
     *
     * @param person the person to check against the specified modules
     * @return true if a match is found or if no modules were provided
     */
    public boolean moduleMatches(Person person) {
        if (!hasModulesList) {
            return true;
        }
        if (person instanceof Student) {
            return false;
        }

        return modulesList.stream()
                .anyMatch(module -> {
                    if (person instanceof Professor) {
                        Professor prof = (Professor) person;
                        return StringUtil.containsWordIgnoreCase(prof.getModuleCode().toString(), module);
                    } else if (person instanceof TeachingAssistant) {
                        TeachingAssistant ta = (TeachingAssistant) person;
                        return StringUtil.containsWordIgnoreCase(ta.getModuleCode().toString(), module);
                    }
                    return false;
                });
    }

    public boolean phoneMatches(Person person) {
        if (!hasPhonesList) {
            return true;
        } else {
            return phonesList.stream()
                    .anyMatch(phone -> StringUtil.containsWordIgnoreCase(person.getPhone().toString(), phone));
        }
    }

    public boolean emailMatches(Person person) {
        if (!hasEmailsList) {
            return true;
        } else {
            return emailsList.stream()
                    .anyMatch(email -> StringUtil.containsWordIgnoreCase(person.getEmail().toString(), email));
        }
    }

    public boolean genderMatches(Person person) {
        if (!hasGendersList) {
            return true;
        } else {
            return gendersList.stream()
                    .anyMatch(gender -> StringUtil.containsWordIgnoreCase(person.getGender().toString(), gender));
        }
    }

    public boolean locationMatches(Person person) {
        if (!hasLocationsList) {
            return true;
        } else {
            return locationsList.stream()
                    .anyMatch(location -> StringUtil.containsWordIgnoreCase(person.getLocation().toString(), location));
        }
    }

    public boolean tagMatches(Person person) {
        if (!hasTagsList) {
            return true;
        }
        Set<String> personList = makeTagsList(person);
        if (hasAllTags) {
            return personList.equals(tagsList);
        } else {
            personList.retainAll(tagsList);
            return !personList.isEmpty();
        }
    }

    public void setNamesList(List<String> otherList) {
        this.namesList = otherList;
        hasNamesList = true;
    }

    public void setModulesList(List<String> otherList) {
        this.modulesList = otherList;
        hasModulesList = true;
    }

    public void setPhonesList(List<String> phonesList) {
        this.phonesList = phonesList;
        hasPhonesList = true;
    }

    public void setEmailsList(List<String> emailsList) {
        this.emailsList = emailsList;
        hasEmailsList = true;
    }

    public void setGendersList(List<String> gendersList) {
        this.gendersList = gendersList;
        hasGendersList = true;
    }

    public void setTagsList(Set<String> tagsList, boolean hasAllTags) {
        this.tagsList = tagsList;
        this.hasAllTags = hasAllTags;
        hasTagsList = true;
    }

    public void setLocationsList(List<String> locationsList) {
        this.locationsList = locationsList;
        hasLocationsList = true;
    }

    public boolean getHasNamesList() {
        return hasNamesList;
    }

    public boolean getHasModulesList() {
        return hasModulesList;
    }

    public boolean getHasPhonesList() {
        return hasPhonesList;
    }

    public boolean getHasEmailsList() {
        return hasEmailsList;
    }

    public boolean getHasGendersList() {
        return hasGendersList;
    }

    public boolean getHasLocationsList() {
        return hasLocationsList;
    }

    public List<String> getNamesList() {
        return namesList;
    }

    public List<String> getModulesList() {
        return modulesList;
    }

    public Set<String> getTagsList() {
        return tagsList;
    }

    public List<String> getGendersList() {
        return gendersList;
    }

    public List<String> getLocationsList() {
        return locationsList;
    }

    public List<String> getPhonesList() {
        return phonesList;
    }

    public List<String> getEmailsList() {
        return emailsList;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonMatchesPredicate // instanceof handles nulls
                && namesList.equals((((PersonMatchesPredicate) other).namesList))
                && modulesList.equals(((PersonMatchesPredicate) other).modulesList)
                && phonesList.equals(((PersonMatchesPredicate) other).phonesList)
                && emailsList.equals(((PersonMatchesPredicate) other).emailsList)
                && gendersList.equals(((PersonMatchesPredicate) other).gendersList)
                && tagsList.equals(((PersonMatchesPredicate) other).tagsList)
                && locationsList.equals(((PersonMatchesPredicate) other).locationsList)); // state check
    }

    private Set<String> makeTagsList(Person person) {
        return person.getTags().stream()
                .map(tag -> tag.tagName.toLowerCase()).collect(Collectors.toSet());
    }
}
