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

    private List<String> namesList = new ArrayList<>();
    private List<String> phonesList = new ArrayList<>();
    private List<String> emailsList = new ArrayList<>();
    private List<String> gendersList = new ArrayList<>();
    private List<String> locationsList = new ArrayList<>();
    private List<String> typesList = new ArrayList<>();
    private List<String> userNamesList = new ArrayList<>();
    private List<String> ratingsList = new ArrayList<>();
    private List<String> yearsList = new ArrayList<>();
    private List<String> specList = new ArrayList<>();
    private List<String> officeHoursList = new ArrayList<>();

    private Set<String> tagsSet = new HashSet<>();
    private Set<String> modulesSet = new HashSet<>();

    private boolean hasModulesList = false;
    private boolean needsAllModules = false;

    private boolean hasTagsList = false;
    private boolean needsAllTags = false;

    private boolean hasNamesList = false;
    private boolean hasPhonesList = false;
    private boolean hasEmailsList = false;
    private boolean hasGendersList = false;
    private boolean hasLocationsList = false;
    private boolean hasTypesList = false;
    private boolean hasUserNamesList = false;
    private boolean hasRatingsList = false;
    private boolean hasYearsList = false;
    private boolean hasSpecList = false;
    private boolean hasOfficeHoursList = false;

    @Override
    public boolean test(Person person) {
        return nameMatches(person)
                && moduleMatches(person) && phoneMatches(person)
                && emailMatches(person) && genderMatches(person)
                && tagMatches(person) && locationMatches(person)
                && typeMatches(person) && userNameMatches(person)
                && ratingMatches(person) && yearMatches(person)
                && specialisationMatches(person) && officeHoursMatches(person);
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
            assert namesList.size() == 0 : "There is a names list provided!";
            return true;
        } else {
            return namesList.stream()
                    .anyMatch(name -> StringUtil.containsWordIgnoreCase(person.getName().toString(), name));
        }
    }

    /**
     * Checks if all or some of the person's module matches any of the provided modules.
     * Returns true if no modules have been provided by the user to filter by.
     *
     * @param person the person to check against the specified modules
     * @return true if a match is found or if no modules were provided
     */
    public boolean moduleMatches(Person person) {
        if (!hasModulesList) {
            assert modulesSet.size() == 0 : "There is a modules list provided!";
            return true;
        }
        return person.doModulesMatch(modulesSet, needsAllModules);
    }

    /**
     * Checks if the person's phone number matches any of the provided numbers.
     * Returns true if no phone numbers have been provided by the user to filter by.
     *
     * @param person the person to check against the specified numbers.
     * @return true if a match is found or if no numbers were provided
     */
    public boolean phoneMatches(Person person) {
        if (!hasPhonesList) {
            assert phonesList.size() == 0 : "There is a phones list provided!";
            return true;
        } else {
            return phonesList.stream()
                    .anyMatch(phone -> StringUtil.containsWordIgnoreCase(person.getPhone().toString(), phone));
        }
    }

    /**
     * Checks if the person's email matches any of the provided emails.
     * Returns true if no emails have been provided by the user to filter by.
     *
     * @param person the person to check against the specified emails.
     * @return true if a match is found or if no emails were provided
     */
    public boolean emailMatches(Person person) {
        if (!hasEmailsList) {
            assert emailsList.size() == 0 : "There is an emails list provided!";
            return true;
        } else {
            return emailsList.stream()
                    .anyMatch(email -> StringUtil.containsWordIgnoreCase(person.getEmail().toString(), email));
        }
    }

    /**
     * Checks if the person's gender matches any of the provided genders.
     * Returns true if no genders have been provided by the user to filter by.
     *
     * @param person the person to check against the specified genders.
     * @return true if a match is found or if no genders were provided
     */
    public boolean genderMatches(Person person) {
        if (!hasGendersList) {
            assert gendersList.size() == 0 : "There is a gender list provided!";
            return true;
        } else {
            return gendersList.stream()
                    .anyMatch(gender -> StringUtil.containsWordIgnoreCase(person.getGender().toString(), gender));
        }
    }

    /**
     * Checks if the person's location matches any of the provided location.
     * Returns true if no locations have been provided by the user to filter by.
     *
     * @param person the person to check against the specified locations.
     * @return true if a match is found or if no locations were provided.
     */
    public boolean locationMatches(Person person) {
        if (!hasLocationsList) {
            assert locationsList.size() == 0 : "There is a location list provided!";
            return true;
        } else {
            return locationsList.stream()
                    .anyMatch(location -> StringUtil.containsWordIgnoreCase(person.getLocation().toString(), location));
        }
    }

    /**
     * Checks if the person's type matches any of the provided types.
     * Returns true if no types have been provided by the user to filter by.
     *
     * @param person the person to check against the specified types.
     * @return true if a match is found or if no types were provided
     */
    public boolean typeMatches(Person person) {
        if (!hasTypesList) {
            assert typesList.size() == 0 : "There is a types list provided!";
            return true;
        } else {
            return typesList.stream()
                    .anyMatch(type -> StringUtil.containsWordIgnoreCase(person.getTypeString(), type));
        }
    }

    /**
     * Checks if all or some of the person's tags matches any of the provided tags.
     * Returns true if no tags have been provided by the user to filter by.
     *
     * @param person the person to check against the specified tags.
     * @return true if a match is found or if no tags were provided.
     */
    public boolean tagMatches(Person person) {
        if (!hasTagsList) {
            assert tagsSet.size() == 0 : "There is a tag set provided!";
            return true;
        }
        Set<String> personList = makeTagsList(person);
        if (needsAllTags) {
            return personList.containsAll(tagsSet) || personList.equals(tagsSet);
        } else {
            personList.retainAll(tagsSet);
            return !personList.isEmpty();
        }
    }

    /**
     * Checks if the person's username matches any of the provided username.
     * Returns true if no usernames have been provided by the user to filter by.
     *
     * @param person the person to check against the specified usernames.
     * @return true if a match is found or if no usernames were provided.
     */
    public boolean userNameMatches(Person person) {
        if (!hasUserNamesList) {
            assert userNamesList.size() == 0 : "There is a usernames list provided!";
            return true;
        } else {
            return userNamesList.stream()
                    .anyMatch(username -> StringUtil.containsWordIgnoreCase(person.getUsername().toString(), username));
        }
    }

    /**
     * Checks if the person's rating matches any of the provided ratings.
     * Returns true if no ratings have been provided by the user to filter by.
     *
     * @param person the person to check against the specified ratings.
     * @return true if a match is found or if no ratings were provided.
     */
    public boolean ratingMatches(Person person) {
        if (!hasRatingsList) {
            assert ratingsList.size() == 0 : "There is a ratings list provided!";
            return true;
        } else {
            return person.doesRatingMatch(ratingsList);
        }
    }

    /**
     * Checks if the person's year matches any of the provided years.
     * Returns true if no years have been provided by the user to filter by.
     *
     * @param person the person to check against the specified year.
     * @return true if a match is found or if no years were provided.
     */
    public boolean yearMatches(Person person) {
        if (!hasYearsList) {
            assert yearsList.size() == 0 : "There is a years list provided!";
            return true;
        } else {
            return person.doesYearMatch(yearsList);
        }
    }

    /**
     * Checks if the person's specialisation matches any of the provided specialisations.
     * Returns true if no specialisation have been provided by the user to filter by.
     *
     * @param person the person to check against the specified specialisations.
     * @return true if a match is found or if no specialisations were provided.
     */
    public boolean specialisationMatches(Person person) {
        if (!hasSpecList) {
            assert specList.size() == 0 : "There is a specialisations list provided!";
            return true;
        } else {
            return person.doesSpecialisationMatch(specList);
        }
    }

    /**
     * Checks if the person's office hours matches any of the provided office hours
     * Returns true if no office hours have been provided by the user to filter by.
     *
     * @param person the person to check against the specified office hours.
     * @return true if a match is found or if no office hours were provided.
     */
    public boolean officeHoursMatches(Person person) {
        if (!hasOfficeHoursList) {
            assert officeHoursList.size() == 0 : "There is an office hours list provided!";
            return true;
        } else {
            return person.doesOfficeHourMatch(officeHoursList);
        }
    }

    public void setNamesList(List<String> otherList) {
        this.namesList = otherList;
        hasNamesList = true;
    }

    public void setModulesSet(Set<String> otherList, boolean needsAllModules) {
        this.modulesSet = otherList.stream().map(String::toLowerCase).collect(Collectors.toSet());
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

    public void setTagsSet(Set<String> tagsList, boolean needsAllTags) {
        this.tagsSet = tagsList.stream().map(String::toLowerCase).collect(Collectors.toSet());
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

    public void setRatingsList(List<String> ratingsList) {
        this.ratingsList = ratingsList;
        hasRatingsList = true;
    }

    public void setYearsList(List<String> yearsList) {
        this.yearsList = yearsList;
        hasYearsList = true;
    }

    public void setSpecList(List<String> specList) {
        this.specList = specList;
        hasSpecList = true;
    }

    public void setOfficeHoursList(List<String> officeHoursList) {
        this.officeHoursList = officeHoursList;
        hasOfficeHoursList = true;
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

    public boolean getHasSpecsList() {
        return hasSpecList;
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

    public List<String> getUserNamesList() {
        return userNamesList;
    }

    public List<String> getRatingsList() {
        return ratingsList;
    }

    public List<String> getYearsList() {
        return yearsList;
    }

    public List<String> getSpecList() {
        return specList;
    }

    public List<String> getOfficeHoursList() {
        return officeHoursList;
    }

    public boolean getNeedsAllModules() {
        return needsAllModules;
    }

    public boolean getNeedsAllTags() {
        return needsAllTags;
    }

    public boolean getHasUserNamesList() {
        return hasUserNamesList;
    }

    public boolean getHasRatingsList() {
        return hasRatingsList;
    }

    public boolean getHasYearsList() {
        return hasYearsList;
    }

    public boolean getHasSpecList() {
        return hasSpecList;
    }

    public boolean getHasOfficeHoursList() {
        return hasOfficeHoursList;
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
                && ratingsList.equals(((PersonMatchesPredicate) other).ratingsList)
                && yearsList.equals(((PersonMatchesPredicate) other).yearsList)
                && specList.equals(((PersonMatchesPredicate) other).specList)
                && officeHoursList.equals(((PersonMatchesPredicate) other).officeHoursList)
                && needsAllTags == ((PersonMatchesPredicate) other).needsAllTags
                && needsAllModules == ((PersonMatchesPredicate) other).needsAllModules); // state check
    }

    private Set<String> makeTagsList(Person person) {
        return person.getTags().stream()
                .map(tag -> tag.tagName.toLowerCase()).collect(Collectors.toSet());
    }
}
