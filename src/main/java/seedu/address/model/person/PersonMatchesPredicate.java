package seedu.address.model.person;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class PersonMatchesPredicate implements Predicate<Person> {

    private List<String> namesList;
    private List<String> moduleList;

    private boolean hasNamesList;
    private boolean hasModuleList;

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

    public boolean nameMatches(Person person) {
        if (!hasNamesList) {
            return true;
        } else {
            return namesList.stream()
                    .anyMatch(name -> StringUtil.containsWordIgnoreCase(person.getName().fullName, name));
        }
    }

    public boolean moduleMatches(Person person) {
        if (!hasModuleList) {
            return true;
        }
        Professor prof = null;
        TeachingAssistant ta = null;
        if (person instanceof Student) {
            return false;
        } else if (person instanceof Professor) {
            prof = (Professor) person;
        } else {
            ta = (TeachingAssistant) person;
        }

        if (!hasModuleList) {
            return true;
        } else {
            Professor finalProf = prof;
            TeachingAssistant finalTa = ta;
            return moduleList.stream()
                    .anyMatch(module -> {
                        if (person instanceof Professor) {
                            return StringUtil.containsWordIgnoreCase(finalProf.getModuleCode().toString(), module);
                        } else if (person instanceof TeachingAssistant) {
                            return StringUtil.containsWordIgnoreCase(finalTa.getModuleCode().toString(), module);
                        }
                        return false;
                    });
        }
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
