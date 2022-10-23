package nus.climods.model.module;

import static java.util.Objects.requireNonNull;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.openapitools.client.ApiException;
import org.openapitools.client.api.ModulesApi;
import org.openapitools.client.model.ModuleInformationSemesterDataInner;
import org.openapitools.client.model.SemestersEnum;

/**
 * A wrapper class for <code>ModuleInformation</code>
 */
public class Module {

    private final org.openapitools.client.model.ModuleInformation apiModuleInfo;
    private String academicYear;

    /**
     * Contains detailed module information from API. Only initialised when needed
     */
    private org.openapitools.client.model.Module apiModule;
    private boolean isFocused = false;

    /**
     * Constructor for Module.
     *
     * @param apiModuleInfo ModuleInformation from nusmods API
     */
    public Module(org.openapitools.client.model.ModuleInformation apiModuleInfo) {
        this.apiModuleInfo = apiModuleInfo;
    }

    /**
     * Constructor for Module.
     *
     * @param apiModuleInfo ModuleInformation from nusmods API
     * @param academicYear  academic year
     */
    public Module(org.openapitools.client.model.ModuleInformation apiModuleInfo, String academicYear) {
        this(apiModuleInfo);
        this.academicYear = academicYear;
    }

    /**
     * Returns the number of modular credits.
     * <p>
     * String is used as the return type as there module credits may not be a integer. Example: YSC2251 2.5 MCs Since
     * there are no calculations needed as of yet, we will preserve the String representation
     * </p>
     *
     * @return module credits
     */
    public String getModuleCredit() {
        return apiModuleInfo.getModuleCredit();
    }

    /**
     * Returns the module title.
     *
     * @return module title
     */
    public String getTitle() {
        return apiModuleInfo.getTitle();
    }

    /**
     * Returns the module code.
     *
     * @return module code
     */
    public String getCode() {
        return apiModuleInfo.getModuleCode();
    }

    /**
     * Returns the department that offers this module.
     *
     * @return module department
     */
    public String getDepartment() {
        return apiModuleInfo.getDepartment();
    }

    /**
     * Returns the module description
     *
     * @return module description
     */
    public String getDescription() {
        return apiModuleInfo.getDescription();
    }

    /**
     * Returns the semesters that this module is offered.
     *
     * @return list of semesters
     */
    public List<SemestersEnum> getSemesters() {
        List<ModuleInformationSemesterDataInner> apiSemesterData = apiModuleInfo.getSemesterData();

        return apiSemesterData.stream().map(ModuleInformationSemesterDataInner::getSemester).filter(Objects::nonNull)
            .map(SemestersEnum::fromValue).collect(Collectors.toList());
    }

    /**
     * Returns the description of the module's preclusion.
     *
     * @return module preclusion description
     */
    public String getPreclusion() {
        requireNonNull(apiModule);
        return apiModule.getPreclusion();
    }

    /**
     * Returns the description of the module's Prerequisite.
     *
     * @return module Prerequisite description
     */
    public String getPrerequisite() {
        requireNonNull(apiModule);
        return apiModule.getPrerequisite();
    }

    /**
     * Check if module contains keyword.
     * <p>
     * A keyword is searched against a search range which includes the module's title and code
     * </p>
     *
     * @param keywordPattern keyword regex pattern
     * @return whether module contains keyword in its stated information
     */
    public boolean containsKeyword(Pattern keywordPattern) {
        List<String> searchRange = Arrays.asList(getCode(), getTitle());

        return searchRange.stream().anyMatch(range -> keywordPattern.asPredicate().test(range));
    }

    /**
     * Returns the focused state of the module.
     *
     * @return true if module is in focused else false
     */
    public boolean isFocused() {
        return isFocused;
    }

    /**
     * Clear the focused state of module
     */
    public void clearFocus() {
        isFocused = false;
    }

    /**
     * Focus on module.
     * <p>
     * This could trigger a API call to retrieve more data for the module. This side effect only happens when module
     * data is not cached locally.
     * </p>
     *
     * @throws ApiException if api call fails
     */
    public void requestFocus() throws ApiException {
        if (apiModule == null) {
            apiModule = ModulesApi.getInstance().acadYearModulesModuleCodeJsonGet(academicYear, getCode());
        }
        isFocused = true;
    }
}
