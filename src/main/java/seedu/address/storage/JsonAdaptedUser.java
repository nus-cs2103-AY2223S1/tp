package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.CurrentModule;
import seedu.address.model.module.PlannedModule;
import seedu.address.model.module.PreviousModule;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Github;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.user.EmptyUser;
import seedu.address.model.person.user.ExistingUser;
import seedu.address.model.person.user.User;

/**
 * Jackson-friendly version of {@link User}.
 */
class JsonAdaptedUser {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "User's %s field is missing!";
    private final EmptyUser emptyUser = new EmptyUser();

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String github;
    private final List<JsonAdaptedCurrentModule> currModules = new ArrayList<>();
    private final List<JsonAdaptedPreviousModule> prevModules = new ArrayList<>();
    private final List<JsonAdaptedPlannedModule> planModules = new ArrayList<>();
    private final List<JsonAdaptedLesson> lessons = new ArrayList<>();
    private final boolean isEmpty;

    /**
     * Constructs a {@code JsonAdaptedUser} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedUser(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("address") String address,
                             @JsonProperty("github") String github,
                             @JsonProperty("currModules") List<JsonAdaptedCurrentModule> currModules,
                             @JsonProperty("prevModules") List<JsonAdaptedPreviousModule> prevModules,
                             @JsonProperty("planModules") List<JsonAdaptedPlannedModule> planModules,
                             @JsonProperty("lessons") List<JsonAdaptedLesson> lesson,
                             @JsonProperty("isEmpty") boolean isEmpty) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.github = github;
        if (currModules != null) {
            this.currModules.addAll(currModules);
        }
        if (prevModules != null) {
            this.prevModules.addAll(prevModules);
        }
        if (planModules != null) {
            this.planModules.addAll(planModules);
        }
        if (lesson != null) {
            this.lessons.addAll(lesson);
        }
        this.isEmpty = isEmpty;
    }

    /**
     * Converts a given {@code User} into this class for Jackson use.
     */
    public JsonAdaptedUser(User source) {
        if (source.equals(emptyUser)) {
            name = "";
            phone = "";
            email = "";
            address = "";
            github = "";
            isEmpty = true;
        } else {
            assert source instanceof ExistingUser : "User cannot be any other type";
            ExistingUser user = (ExistingUser) source;
            name = user.getName().fullName;
            phone = user.getPhone().value;
            email = user.getEmail().value;
            address = user.getAddress().value;
            github = user.getGithub().value;
            currModules.addAll(user.getCurrModules().stream()
                    .map(JsonAdaptedCurrentModule::new)
                    .collect(Collectors.toList()));
            prevModules.addAll(user.getPrevModules().stream()
                    .map(JsonAdaptedPreviousModule::new)
                    .collect(Collectors.toList()));
            planModules.addAll(user.getPlanModules().stream()
                    .map(JsonAdaptedPlannedModule::new)
                    .collect(Collectors.toList()));
            lessons.addAll(user.getLessons().stream()
                    .map(JsonAdaptedLesson::new)
                    .collect(Collectors.toList()));
            isEmpty = false;
        }
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code User} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public User toModelType() throws IllegalValueException {

        if (isEmpty) {
            return new EmptyUser();
        }

        final List<CurrentModule> personCurrModules = new ArrayList<>();
        for (JsonAdaptedCurrentModule currModule : currModules) {
            personCurrModules.add(currModule.toModelType());
        }

        final List<PreviousModule> personPrevModules = new ArrayList<>();
        for (JsonAdaptedPreviousModule prevModule : prevModules) {
            personPrevModules.add(prevModule.toModelType());
        }

        final List<PlannedModule> personPlanModules = new ArrayList<>();
        for (JsonAdaptedPlannedModule planModule : planModules) {
            personPlanModules.add(planModule.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (github == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Github.class.getSimpleName()));
        }
        if (!Github.isValidUsername(github)) {
            throw new IllegalValueException(Github.MESSAGE_CONSTRAINTS);
        }
        final Github modelGithub = new Github(github);

        final Set<CurrentModule> modelCurrModules = new HashSet<>(personCurrModules);

        final Set<PreviousModule> modelPrevModules = new HashSet<>(personPrevModules);

        final Set<PlannedModule> modelPlanModules = new HashSet<>(personPlanModules);

        User user = new ExistingUser(modelName, modelPhone, modelEmail, modelAddress, modelGithub,
                modelCurrModules, modelPrevModules, modelPlanModules);

        for (JsonAdaptedLesson lesson : lessons) {
            user.addLesson(lesson.toModelType());
        }
        return user;
    }

}
