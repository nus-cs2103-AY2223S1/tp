package gim.logic.generators;


/**
 * Represents a generator, with the ability to generate a suggestion based on level specified and exercise name.
 * Each generator can implement its suggest() logic separately.
 */
public interface Generator {
    String suggest();
}
