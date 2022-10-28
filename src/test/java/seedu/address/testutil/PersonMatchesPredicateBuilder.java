package seedu.address.testutil;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import seedu.address.model.person.PersonMatchesPredicate;

/**
 * A utility class to help with building PersonMatchesPredicate objects.
 */
public class PersonMatchesPredicateBuilder {
    private PersonMatchesPredicate predicate;

    public PersonMatchesPredicateBuilder() {
        predicate = new PersonMatchesPredicate();
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

    public PersonMatchesPredicateBuilder withModulesList(Set<String> modulesList, boolean needsAll) {
        predicate.setModulesList(modulesList, needsAll);
        return this;
    }

    public PersonMatchesPredicateBuilder withTagsList(Set<String> tagsList, boolean needsAll) {
        predicate.setModulesList(tagsList, needsAll);
        return this;
    }

    public PersonMatchesPredicate build() {
        return predicate;
    }
}
