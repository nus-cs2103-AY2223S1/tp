package nus.climods.model.module;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import io.swagger.annotations.Api;
import nus.climods.model.module.exceptions.DetailedModuleRetrievalException;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.ModulesApi;
import org.openapitools.client.model.Lesson;
import org.openapitools.client.model.ModuleInformationSemesterDataInner;

/**
 * A wrapper class for <code>ModuleInformation</code>
 */
public class Module {

    private static final ModulesApi modulesApi = new ModulesApi();
    private static String currentAcademicYear;
    private final org.openapitools.client.model.ModuleInformation apiModuleInfo;

    /**
     * Contains detailed module information from API. Only initialised when needed
     */
    private Optional<org.openapitools.client.model.Module> detailedModule = Optional.empty();

    private boolean isActive = false;

    private List<String> uniqueLessonTypes = List.of();
    public Module(org.openapitools.client.model.ModuleInformation apiModuleInfo) {
        this.apiModuleInfo = apiModuleInfo;
    }

    public static void setCurrentAcademicYear(String currAcademicYear) {
        currentAcademicYear = currAcademicYear;
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

    public String getDescription() {
        return apiModuleInfo.getDescription();
    }

    /**
     * Returns the semesters that this module is offered.
     *
     * @return list of integers representing semesters
     */
    public List<Integer> getSemesters() {
        List<ModuleInformationSemesterDataInner> apiSemesterData = apiModuleInfo.getSemesterData();
        return apiSemesterData.stream().map(ModuleInformationSemesterDataInner::getSemester).filter(Objects::nonNull)
                .map(BigDecimal::intValue).collect(Collectors.toList());
    }

    /**
     * Retrieves detailed module only if not already retrieved (lazily)
     * @throws ApiException if error occured during API request
     */
    private void getDetailedModule() throws DetailedModuleRetrievalException {
        // TODO: replace with reading of stored detailed module JSON file
        try {
            if (detailedModule.isEmpty()) {
                org.openapitools.client.model.Module module = modulesApi.acadYearModulesModuleCodeJsonGet(currentAcademicYear, this.getCode());
                detailedModule = Optional.of(module);
            }
        } catch (ApiException apiException) {
            throw new DetailedModuleRetrievalException(
                    String.format("Problem retrieving detailed module information for %s", this.getCode()),
                    apiException);
        }
    }

    /**
     * Retrieve unique lesson types for a module. Should be called only after getDetailedModule() has been called.
     * Otherwise, uniqueLessonTypes will remain an empty list
     */
    private void retrieveUniqueLessonTypes() {
        uniqueLessonTypes = detailedModule.stream()
                .flatMap(mod -> mod.getSemesterData().stream().findAny().stream())
                .flatMap(semData -> semData.getTimetable().stream().map(Lesson::getLessonType))
                .distinct()
                .collect(Collectors.toList());
    }
    public List<String> getUniqueLessonTypes() {
        return uniqueLessonTypes;
    }

    /**
     * Check if module contains keyword
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

    public boolean isActive() {
        return isActive;
    }

    public void makeActive() throws DetailedModuleRetrievalException {
        getDetailedModule();
        retrieveUniqueLessonTypes();

        this.isActive = true;
    }

    public void makeinActive() {
        this.isActive = false;
    }

}

