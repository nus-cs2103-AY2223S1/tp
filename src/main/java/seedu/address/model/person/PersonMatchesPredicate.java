package seedu.address.model.person;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;


/**
 * Tests that a {@code Person}'s {@code Name} and {@code ModuleCode} matches any of the keywords given.
 */
public class PersonMatchesPredicate implements Predicate<Person> {

    private List<String> namesList;
    private List<String> moduleList;

    private boolean hasNamesList;
    private boolean hasModuleList;

    /**
     * Creates a PersonMatchesPredicate object and initialises
     * the required variables.
     */
    public PersonMatchesPredicate() {
        namesList = new ArrayList<>();
        moduleList = new ArrayList<>();
        hasNamesList = false;
        hasModuleList = false;
    }

    @Override
    public boolean test(Person person) {
        return nameMatches(person) && moduleMatches(person);
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
                    .anyMatch(name -> StringUtil.containsWordIgnoreCase(person.getName().fullName, name));
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
        if (!hasModuleList) {
            return true;
        }
        if (person instanceof Student) {
            return false;
        }
        return moduleList.stream()
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

    public boolean hasNamesListPredicate() {
        return hasNamesList;
    }

    public boolean hasModuleListPredicate() {
        return hasModuleList;
    }

    public List<String> getNamesList() {
        return namesList;
    }

    public List<String> getModuleList() {
        return moduleList;
    }

    public void setNamesList(List<String> otherList) {
        this.namesList = otherList;
        hasNamesList = true;
    }

    public void setModuleList(List<String> otherList) {
        this.moduleList = otherList;
        hasModuleList = true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonMatchesPredicate // instanceof handles nulls
                && namesList.equals((((PersonMatchesPredicate) other).namesList))
                && moduleList.equals(((PersonMatchesPredicate) other).moduleList)); // state check
    }

}
