package seedu.address.testutil;

import java.util.List;
import java.util.Set;

import seedu.address.model.person.PersonMatchesPredicate;

/**
 * A utility class to help with building PersonMatchesPredicate objects.
 */
public class PersonMatchesPredicateBuilder {
    private PersonMatchesPredicate predicate = new PersonMatchesPredicate();

    public PersonMatchesPredicateBuilder() {

    }

    public PersonMatchesPredicateBuilder(PersonMatchesPredicate predicateToCopy) {
        predicate.setNamesList(predicateToCopy.getNamesList());
        predicate.setEmailsList(predicateToCopy.getEmailsList());
        predicate.setTypesList(predicateToCopy.getTypesList());
        predicate.setPhonesList(predicateToCopy.getPhonesList());
        predicate.setTagsSet(predicateToCopy.getTagsSet(), predicateToCopy.getNeedsAllTags());
        predicate.setGendersList(predicateToCopy.getGendersList());
        predicate.setModulesSet(predicateToCopy.getModulesSet(), predicateToCopy.getNeedsAllModules());
        predicate.setRatingsList(predicateToCopy.getRatingsList());
        predicate.setSpecList(predicateToCopy.getSpecList());
        predicate.setOfficeHoursList(predicateToCopy.getOfficeHoursList());
        predicate.setUserNamesList(predicateToCopy.getUserNamesList());
        predicate.setYearsList(predicateToCopy.getYearsList());
        predicate.setLocationsList(predicateToCopy.getLocationsList());
    }
    /**
     * Sets the List of Names for the PersonMatchesPredicate we are building.
     */
    public PersonMatchesPredicateBuilder withNamesList(List<String> namesList) {
        predicate.setNamesList(namesList);
        return this;
    }

    public PersonMatchesPredicateBuilder withLocationsList(List<String> locationsList) {
        predicate.setLocationsList(locationsList);
        return this;
    }

    public PersonMatchesPredicateBuilder withPhonesList(List<String> phonesList) {
        predicate.setPhonesList(phonesList);
        return this;
    }

    public PersonMatchesPredicateBuilder withEmailsList(List<String> emailsList) {
        predicate.setEmailsList(emailsList);
        return this;
    }

    public PersonMatchesPredicateBuilder withTypesList(List<String> typesList) {
        predicate.setTypesList(typesList);
        return this;
    }

    public PersonMatchesPredicateBuilder withGenderList(List<String> genderList) {
        predicate.setGendersList(genderList);
        return this;
    }

    public PersonMatchesPredicateBuilder withRatingsList(List<String> ratingsList) {
        predicate.setRatingsList(ratingsList);
        return this;
    }

    public PersonMatchesPredicateBuilder withYearsList(List<String> yearsList) {
        predicate.setYearsList(yearsList);
        return this;
    }

    public PersonMatchesPredicateBuilder withSpecList(List<String> specList) {
        predicate.setSpecList(specList);
        return this;
    }

    public PersonMatchesPredicateBuilder withOfficeHoursList(List<String> officeHoursList) {
        predicate.setOfficeHoursList(officeHoursList);
        return this;
    }

    public PersonMatchesPredicateBuilder withModulesSet(Set<String> modulesList, boolean needsAll) {
        predicate.setModulesSet(modulesList, needsAll);
        return this;
    }

    public PersonMatchesPredicateBuilder withTagsSet(Set<String> tagsList, boolean needsAll) {
        predicate.setTagsSet(tagsList, needsAll);
        return this;
    }

    public PersonMatchesPredicateBuilder withUserNamesList(List<String> userNamesList) {
        predicate.setUserNamesList(userNamesList);
        return this;
    }

    public PersonMatchesPredicate build() {
        return predicate;
    }
}
