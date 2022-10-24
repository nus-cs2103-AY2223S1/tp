package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Deadline;
import seedu.address.model.Name;
import seedu.address.model.client.Client;
import seedu.address.model.client.ClientEmail;
import seedu.address.model.client.ClientId;
import seedu.address.model.client.ClientPhone;
import seedu.address.model.issue.Description;
import seedu.address.model.issue.Issue;
import seedu.address.model.issue.IssueId;
import seedu.address.model.issue.Priority;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectId;
import seedu.address.model.project.Repository;


/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String name} to check if it is valid.
     * Leading and trailing whitespaces will be trimmed.
     * It is an overloaded method that doesn't create a Name object.
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static void parseNameValidity(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param clientId in string format from user input
     * @return Client of the client id
     * @throws ParseException if the fiven {@code client} is invalid.
     */
    public static ClientId parseClientId(String clientId) throws ParseException, NumberFormatException {
        requireNonNull(clientId);
        String trimmedClientId = clientId.trim();
        int clientIdInt;
        try {
            clientIdInt = Integer.parseInt(trimmedClientId);
        } catch (NumberFormatException e) {
            throw new ParseException(ClientId.MESSAGE_INVALID);
        }

        return new ClientId(clientIdInt);
    }

    /**
     * Parses a {@code String repository} into a {@code Repository}.
     * Leading a trailing whitespaces will be trimmed.
     *
     * @param repository string argument input
     * @return parsed Repository object
     * @throws ParseException if the given {@code repository} is invalid.
     */
    public static Repository parseRepository(String repository) throws ParseException {
        requireNonNull(repository);
        String trimmedRepository = repository.trim();
        if (!Repository.isValidRepository(trimmedRepository)) {
            throw new ParseException(Repository.MESSAGE_CONSTRAINTS);
        }
        return new Repository(trimmedRepository);
    }

    /**
     * Parses a {@code String deadline} into a {@code Deadline}.
     * Leading a trailing whitespaces will be trimmed.
     *
     * @param deadline string argument input
     * @return parsed Deadline object
     * @throws ParseException if the given {@code deadline} is invalid.
     */
    public static Deadline parseDeadline(String deadline) throws ParseException {
        requireNonNull(deadline);
        String trimmedDeadline = deadline.trim();
        if (!Deadline.isValidDeadline(trimmedDeadline)) {
            throw new ParseException(Deadline.MESSAGE_CONSTRAINTS);
        }
        return new Deadline(trimmedDeadline);
    }


    /**
     * Parses a {@code String phone} into a {@code ClientPhone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static ClientPhone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!ClientPhone.isValidClientPhone(trimmedPhone)) {
            throw new ParseException(ClientPhone.MESSAGE_CONSTRAINTS);
        }
        return new ClientPhone(trimmedPhone);
    }

    /**
     * Parses a {@code String phone} to check if it is valid.
     * Leading and trailing whitespaces will be trimmed.
     * It is an overloaded method that doesn't create a ClientPhone object.
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static void parsePhoneValidity(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!ClientPhone.isValidClientPhone(trimmedPhone)) {
            throw new ParseException(ClientPhone.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses a {@code String email} into an {@code ClientEmail}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static ClientEmail parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!ClientEmail.isValidEmail(trimmedEmail)) {
            throw new ParseException(ClientEmail.MESSAGE_CONSTRAINTS);
        }
        return new ClientEmail(trimmedEmail);
    }

    /**
     * Parses a {@code String email} to check if it is valid.
     * Leading and trailing whitespaces will be trimmed.
     * It is an overloaded method that doesn't create a ClientEmail object.
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static void parseEmailValidity(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!ClientEmail.isValidEmail(trimmedEmail)) {
            throw new ParseException(ClientEmail.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses a {@code String description} into a {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static Description parseDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (!Description.isValidDescription(trimmedDescription)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(trimmedDescription);
    }

    /**
     * Parses a {@code String priority} into a {@code Priority}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code priority} is invalid.
     */
    public static Priority parsePriority(String priority) throws ParseException {
        requireNonNull(priority);
        String trimmedPriority = priority.trim();
        if (!Priority.isValidPriority(trimmedPriority)) {
            throw new ParseException(Priority.MESSAGE_CONSTRAINTS);
        }
        switch (trimmedPriority) {
        case ("0"):
            return Priority.LOW;
        case ("1"):
            return Priority.MEDIUM;
        case ("2"):
            return Priority.HIGH;
        default:
            throw new ParseException(Priority.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses a {@code String projectId} into a {@code Project}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @return project id.
     * @throws ParseException if the given {@code projectId} is invalid.
     */
    public static ProjectId parseProjectId(String projectId) throws ParseException {
        requireNonNull(projectId);
        String trimmedId = projectId.trim();
        int trimmedIdInt = Integer.parseInt(trimmedId);
        if (!ProjectId.isValidProjectId(trimmedId)) {
            throw new ParseException(ProjectId.MESSAGE_CONSTRAINTS);
        }

        // Function throws error when not found.
        return new ProjectId(trimmedIdInt);
    }

    /**
     * Parses a {@code String projectId} into a stub default {@code Project}.
     *
     * @param projectId is the id of the project
     * @return A stub default project
     */
    public static Project parseProjectStub(String projectId) throws ParseException {
        requireNonNull(projectId);
        String trimmedId = projectId.trim();
        if (!ProjectId.isValidProjectId(trimmedId)) {
            throw new ParseException(ProjectId.MESSAGE_INVALID);
        }
        int trimmedIdInt = Integer.parseInt(trimmedId);
        return new Project(new Name("default"), new Repository("default/default"),
                new Deadline("2022-03-05"), new Client(new Name("default")),
                new ArrayList<>(), new ProjectId(trimmedIdInt));
    }


    /**
     * Parses a {@code String issueId} into a {@code IssueId}.
     *
     * @param issueId is the id of the issue
     * @return An issueId object
     */
    public static IssueId parseIssueId(String issueId) throws ParseException {
        requireNonNull(issueId);
        String trimmedId = issueId.trim();
        if (!IssueId.isValidIssueId(trimmedId)) {
            throw new ParseException(IssueId.MESSAGE_INVALID);
        }
        int trimmedIdInt = Integer.parseInt(trimmedId);

        return new IssueId(trimmedIdInt);
    }

    /**
     * Parses a {@code String key} into an {@code Integer}.
     *
     * @param key is the value entered by the user for sort by deadline.
     * @return Integer of 0 or 1 which specifies the chronology of sort
     */
    public static Integer parseDeadlineSortForProject(String key) throws ParseException {
        requireNonNull(key);
        String trimmedKey = key.trim();
        if (!Project.isValidDeadlineSortKey(trimmedKey)) {
            throw new ParseException(Project.MESSAGE_INVALID_DEADLINE_SORT_KEY);
        }
        return Integer.parseInt(trimmedKey);
    }

    /**
     * Parses a {@code String key} into an {@code Integer}.
     *
     * @param key is the value entered by the user for sort by issue count.
     * @return Integer of 0 or 1 which specifies the numeric order of sorting.
     */
    public static Integer parseIssueCountSort(String key) throws ParseException {
        requireNonNull(key);
        String trimmedKey = key.trim();
        if (!Project.isValidIssueCountSortKey(trimmedKey)) {
            throw new ParseException(Project.MESSAGE_INVALID_ISSUE_COUNT_SORT_KEY);
        }
        return Integer.parseInt(trimmedKey);
    }

    /**
     * Parses a {@code String key} into an {@code Integer}.
     *
     * @param key is the value entered by the user for sort by name.
     * @return Integer of 0 or 1 which specifies the alphabetical order of sorting.
     */
    public static Integer parseNameSort(String key) throws ParseException {
        requireNonNull(key);
        String trimmedKey = key.trim();
        if (!Project.isValidNameSortKey(trimmedKey)) {
            throw new ParseException(Project.MESSAGE_INVALID_NAME_SORT_KEY);
        }
        return Integer.parseInt(trimmedKey);
    }

    /**
     * Parses a {@code String key} into an {@code Integer}.
     *
     * @param key is the value entered by the user to sort by priority.
     * @return Integer of 0 or 1 which specifies the priority order of sorting.
     */
    public static Integer parsePrioritySort(String key) throws ParseException {
        requireNonNull(key);
        String trimmedKey = key.trim();
        if (!Issue.isValidPrioritySortKey(trimmedKey)) {
            throw new ParseException(Issue.MESSAGE_INVALID_PRIORITY_SORT_KEY);
        }
        return Integer.parseInt(trimmedKey);
    }


    /**
     * Parses a {@code String key} into an {@code Integer}.
     *
     * @param key is the value entered by the user for sort by deadline.
     * @return Integer of 0 or 1 which specifies the chronology of sort
     */
    public static Integer parseDeadlineSortForIssue(String key) throws ParseException {
        requireNonNull(key);
        String trimmedKey = key.trim();
        if (!Issue.isValidDeadlineSortKey(trimmedKey)) {
            throw new ParseException(Issue.MESSAGE_INVALID_DEADLINE_SORT_KEY);
        }
        return Integer.parseInt(trimmedKey);
    }
}
