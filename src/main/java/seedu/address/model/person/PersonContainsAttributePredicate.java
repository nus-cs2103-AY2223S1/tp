package seedu.address.model.person;

import java.util.Arrays;
import java.util.List;

import java.util.function.Predicate;
import java.util.stream.Collectors;


/**
 * Tests that a {@code Person}'s attributes matches any of the keywords given for each attribute.
 */
public class PersonContainsAttributePredicate implements Predicate<Person> {

    private final List<String> nameList;
    private final List<String> phoneList;
    private final List<String> emailList;
    private final List<String> addressList;
    private final List<String> genderList;
    private final List<String> birthdateList;
    private final List<String> raceList;
    private final List<String> religionList;
    private final List<String> surveysList;
    private final List<String> tagsList;

    /**
     * Every field must be non-null.
     */
    public PersonContainsAttributePredicate(List<String> nameList, List<String> phoneList, List<String> emailList,
                                        List<String> addressList, List<String> genderList, List<String> birthdateList,
                                        List<String> raceList, List<String> religionList, List<String> surveysList,
                                        List<String> tagsList) {

        this.nameList = nameList;
        this.phoneList = phoneList;
        this.emailList = emailList;
        this.addressList = addressList;
        this.genderList = genderList;
        this.birthdateList = birthdateList;
        this.raceList = raceList;
        this.religionList = religionList;
        this.surveysList = surveysList;
        this.tagsList = tagsList;
    }

    @Override
    public boolean test(Person person) {

        boolean containsName = nameList.isEmpty() || nameList.stream()
                .anyMatch(containsIgnoreCase(person.getName().toString()));
        boolean containsPhone = phoneList.isEmpty() || phoneList.stream()
                .anyMatch(containsIgnoreCase(person.getPhone().toString()));
        boolean containsEmail = emailList.isEmpty() || emailList.stream()
                .anyMatch(email -> person.getEmail().toString().contains(email));
        boolean containsAddress = addressList.isEmpty() || addressList.stream()
                .anyMatch(containsIgnoreCase(person.getAddress().toString()));
        boolean containsGender = genderList.isEmpty() || genderList.stream()
                .anyMatch(containsIgnoreCase(person.getGender().toString()));
        boolean containsBirthdate = birthdateList.isEmpty() || birthdateList.stream()
                .anyMatch(birthdate -> person.getBirthdate().toString().contains(birthdate));
        boolean containsRace = raceList.isEmpty() || raceList.stream()
                .anyMatch(containsIgnoreCase(person.getRace().toString()));
        boolean containsReligion = religionList.isEmpty() || religionList.stream()
                .anyMatch(containsIgnoreCase(person.getReligion().toString()));
        boolean containsSurvey = surveysList.isEmpty() || surveysList.stream()
                .anyMatch(keyword -> person.getSurveys().stream()
                        .flatMap(survey -> {
                            String surveyName = survey.toString()
                                    .replaceAll("\\[", "")
                                    .replaceAll("\\]", "");
                            return Arrays.stream(surveyName.split("\\s+"));
                        })
                        .anyMatch(keyword::equalsIgnoreCase));
        boolean containsTags = tagsList.isEmpty() || tagsList.stream()
                .anyMatch(keyword -> person.getTags().stream()
                        .flatMap(tag -> {
                            String tagName = tag.toString()
                                    .replaceAll("\\[", "")
                                    .replaceAll("\\]", "");
                            return Arrays.stream(tagName.split("\\s+"));
                        })
                        .anyMatch(keyword::equalsIgnoreCase));

        return (containsName && containsPhone && containsEmail && containsAddress && containsGender
                && containsBirthdate && containsRace && containsReligion && containsSurvey
                && containsTags);
    }

    public static Predicate<String> containsIgnoreCase(String targetString) {
        return keyword -> Arrays.stream(targetString.split("\\s+"))
                .anyMatch(targetWord -> targetWord.equalsIgnoreCase(keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonContainsAttributePredicate // instanceof handles nulls
                && nameList.equals(((PersonContainsAttributePredicate) other).nameList)
                && phoneList.equals(((PersonContainsAttributePredicate) other).phoneList)
                && emailList.equals(((PersonContainsAttributePredicate) other).emailList)
                && addressList.equals(((PersonContainsAttributePredicate) other).addressList)
                && genderList.equals(((PersonContainsAttributePredicate) other).genderList)
                && birthdateList.equals(((PersonContainsAttributePredicate) other).birthdateList)
                && raceList.equals(((PersonContainsAttributePredicate) other).raceList)
                && religionList.equals(((PersonContainsAttributePredicate) other).religionList)
                && surveysList.equals(((PersonContainsAttributePredicate) other).surveysList)
                && tagsList.equals(((PersonContainsAttributePredicate) other).tagsList)); //state check
    }

}
