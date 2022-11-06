package seedu.application.model;

/**
 * Container for common regex used by model package.
 */
public class CommonRegex {

    public static final String VALIDATION_REGEX_FOR_EXTRA_PREFIX = "[\\s][\\p{Alnum}]*[\\/].*";
    public static final String VALIDATION_REGEX_FOR_UNKNOWN_PREFIX_INPUT_WITHOUT_SPACE = "[\\p{Alnum}]*[\\/].*";
}
