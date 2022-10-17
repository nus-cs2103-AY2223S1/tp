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
    private Set<String> modulesSet;
    private List<String> phonesList;
    private List<String> emailsList;
    private List<String> gendersList;
    private Set<String> tagsSet;
    private List<String> locationsList;
    private List<String> typesList;
    private List<String> userNamesList;

    private boolean hasNamesList;

    private boolean hasModulesList;
    private boolean needsAllModules;

    private boolean hasPhonesList;
    private boolean hasEmailsList;
    private boolean hasGendersList;

    private boolean hasTagsList;
    private boolean needsAllTags;

    private boolean hasLocationsList;
    private boolean hasTypesList;
    private boolean hasUserNamesList;

    /**
     * Creates a PersonMatchesPredicate object and initialises
     * the required variables.
     */
    public PersonMatchesPredicate() {
        namesList = new ArrayList<>();
        modulesSet = new HashSet<>();
        phonesList = new ArrayList<>();
        emailsList = new ArrayList<>();
        gendersList = new ArrayList<>();
        tagsSet = new HashSet<>();
        locationsList = new ArrayList<>();
        typesList = new ArrayList<>();
        userNamesList = new ArrayList<>();

        hasNamesList = false;

        hasModulesList = false;
        needsAllModules = false;

        hasPhonesList = false;
        hasEmailsList = false;
        hasGendersList = false;

        hasTagsList = false;
        needsAllTags = false;

        hasLocationsList = false;
        hasTypesList = false;
        hasUserNamesList = false;
    }

    @Override
    public boolean test(Person person) {
        return nameMatches(person)
                && moduleMatches(person) && phoneMatches(person)
                && emailMatches(person) && genderMatches(person)
                && tagMatches(person) && locationMatches(person)
                && typeMatches(person) && userNameMatches(person);
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
        return person.doModulesMatch(modulesSet, needsAllModules);
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

    public boolean typeMatches(Person person) {
        if (!hasTypesList) {
            return true;
        } else {
            return typesList.stream()
                    .anyMatch(type -> StringUtil.containsWordIgnoreCase(person.getTypeString(), type));
        }
    }

    public boolean tagMatches(Person person) {
        if (!hasTagsList) {
            return true;
        }
        Set<String> personList = makeTagsList(person);
        if (needsAllTags) {
            return personList.equals(tagsSet);
        } else {
            personList.retainAll(tagsSet);
            return !personList.isEmpty();
        }
    }

    public boolean userNameMatches(Person person) {
        if (!hasUserNamesList) {
            return true;
        } else {
            return userNamesList.stream()
                    .anyMatch(username -> StringUtil.containsWordIgnoreCase(person.getUsername().toString(), username));
        }
    }

    public void setNamesList(List<String> otherList) {
        this.namesList = otherList;
        hasNamesList = true;
    }

    public void setModulesList(Set<String> otherList, boolean needsAllModules) {
        this.modulesSet = otherList;
        hasModulesList = true;
        this.needsAllModules = needsAllModules;
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

    public void setTagsList(Set<String> tagsList, boolean needsAllTags) {
        this.tagsSet = tagsList;
        this.needsAllTags = needsAllTags;
        hasTagsList = true;
    }

    public void setLocationsList(List<String> locationsList) {
        this.locationsList = locationsList;
        hasLocationsList = true;
    }

    public void setTypesList(List<String> typesList) {
        this.typesList = typesList;
        hasTypesList = true;
    }

    public void setUserNamesList(List<String> userNamesList) {
        this.userNamesList = userNamesList;
        hasUserNamesList = true;
    }

    //get methods are for tests. some have not been used yet
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

    public boolean getHasTagsList() {
        return hasTagsList;
    }

    public boolean getHasTypesList() {
        return hasTypesList;
    }

    public List<String> getNamesList() {
        return namesList;
    }

    public Set<String> getModulesSet() {
        return modulesSet;
    }

    public Set<String> getTagsSet() {
        return tagsSet;
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

    public List<String> getTypesList() {
        return typesList;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonMatchesPredicate // instanceof handles nulls
                && namesList.equals((((PersonMatchesPredicate) other).namesList))
                && modulesSet.equals(((PersonMatchesPredicate) other).modulesSet)
                && phonesList.equals(((PersonMatchesPredicate) other).phonesList)
                && emailsList.equals(((PersonMatchesPredicate) other).emailsList)
                && gendersList.equals(((PersonMatchesPredicate) other).gendersList)
                && tagsSet.equals(((PersonMatchesPredicate) other).tagsSet)
                && locationsList.equals(((PersonMatchesPredicate) other).locationsList)
                && typesList.equals(((PersonMatchesPredicate) other).typesList)
                && needsAllTags == ((PersonMatchesPredicate) other).needsAllTags
                && needsAllModules == ((PersonMatchesPredicate) other).needsAllModules); // state check
    }

    private Set<String> makeTagsList(Person person) {
        return person.getTags().stream()
                .map(tag -> tag.tagName.toLowerCase()).collect(Collectors.toSet());
    }
}
