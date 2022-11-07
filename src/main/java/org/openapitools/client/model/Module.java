
package org.openapitools.client.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModelProperty;


/**
 * Module
 */
@JsonPropertyOrder({
        Module.JSON_PROPERTY_ACAD_YEAR,
        Module.JSON_PROPERTY_PRECLUSION,
        Module.JSON_PROPERTY_DESCRIPTION,
        Module.JSON_PROPERTY_TITLE,
        Module.JSON_PROPERTY_DEPARTMENT,
        Module.JSON_PROPERTY_FACULTY,
        Module.JSON_PROPERTY_WORKLOAD,
        Module.JSON_PROPERTY_PREREQUISITE,
        Module.JSON_PROPERTY_MODULE_CREDIT,
        Module.JSON_PROPERTY_MODULE_CODE,
        Module.JSON_PROPERTY_SEMESTER_DATA,
        Module.JSON_PROPERTY_PREREQ_TREE,
        Module.JSON_PROPERTY_FULFILL_REQUIREMENTS
})
@javax.annotation.processing.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-10-03T22:22:26.802458+08:00[Asia/Singapore]")
public class Module {

    public static final String JSON_PROPERTY_ACAD_YEAR = "acadYear";
    public static final String JSON_PROPERTY_PRECLUSION = "preclusion";
    public static final String JSON_PROPERTY_DESCRIPTION = "description";
    public static final String JSON_PROPERTY_TITLE = "title";
    public static final String JSON_PROPERTY_DEPARTMENT = "department";
    public static final String JSON_PROPERTY_FACULTY = "faculty";
    public static final String JSON_PROPERTY_WORKLOAD = "workload";
    public static final String JSON_PROPERTY_PREREQUISITE = "prerequisite";
    public static final String JSON_PROPERTY_MODULE_CREDIT = "moduleCredit";
    public static final String JSON_PROPERTY_MODULE_CODE = "moduleCode";
    public static final String JSON_PROPERTY_SEMESTER_DATA = "semesterData";
    public static final String JSON_PROPERTY_PREREQ_TREE = "prereqTree";
    public static final String JSON_PROPERTY_FULFILL_REQUIREMENTS = "fulfillRequirements";
    private String acadYear;
    private String preclusion;
    private String description;
    private String title;
    private String department;
    private String faculty;
    private Workload workload;
    private String prerequisite;
    private String moduleCredit;
    private String moduleCode;
    private List<SemesterData> semesterData = new ArrayList<>();
    private PrereqTree prereqTree;
    private List<String> fulfillRequirements = null;

    public Module() {
    }

    public Module acadYear(String acadYear) {
        this.acadYear = acadYear;
        return this;
    }

    /**
     * Get acadYear
     *
     * @return acadYear
     **/
    @javax.annotation.Nonnull
    @ApiModelProperty(example = "2018/2019", required = true, value = "")
    @JsonProperty(JSON_PROPERTY_ACAD_YEAR)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)

    public String getAcadYear() {
        return acadYear;
    }


    @JsonProperty(JSON_PROPERTY_ACAD_YEAR)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setAcadYear(String acadYear) {
        this.acadYear = acadYear;
    }


    public Module preclusion(String preclusion) {
        this.preclusion = preclusion;
        return this;
    }

    /**
     * Get preclusion
     *
     * @return preclusion
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(example = "CS1104 or Students from department of ECE", value = "")
    @JsonProperty(JSON_PROPERTY_PRECLUSION)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

    public String getPreclusion() {
        return preclusion;
    }


    @JsonProperty(JSON_PROPERTY_PRECLUSION)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setPreclusion(String preclusion) {
        this.preclusion = preclusion;
    }


    public Module description(String description) {
        this.description = description;
        return this;
    }

    /**
     * Get description
     *
     * @return description
     **/
    @javax.annotation.Nonnull
    @ApiModelProperty(example = "The objective of this module is to familiarise students with the fundamentals of computing devices. Through this module students will understand the basics of data representation, and how the various parts of a computer work, separately and with each other. This allows students to understand the issues in computing devices, and how these issues affect the implementation of solutions. Topics covered include data representation systems, combinational and sequential circuit design techniques, assembly language, processor execution cycles, pipelining, memory hierarchy and input/output systems.", required = true, value = "")
    @JsonProperty(JSON_PROPERTY_DESCRIPTION)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)

    public String getDescription() {
        return description;
    }


    @JsonProperty(JSON_PROPERTY_DESCRIPTION)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setDescription(String description) {
        this.description = description;
    }


    public Module title(String title) {
        this.title = title;
        return this;
    }

    /**
     * Get title
     *
     * @return title
     **/
    @javax.annotation.Nonnull
    @ApiModelProperty(example = "Computer Organisation", required = true, value = "")
    @JsonProperty(JSON_PROPERTY_TITLE)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)

    public String getTitle() {
        return title;
    }


    @JsonProperty(JSON_PROPERTY_TITLE)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setTitle(String title) {
        this.title = title;
    }


    public Module department(String department) {
        this.department = department;
        return this;
    }

    /**
     * Get department
     *
     * @return department
     **/
    @javax.annotation.Nonnull
    @ApiModelProperty(example = "Computer Science", required = true, value = "")
    @JsonProperty(JSON_PROPERTY_DEPARTMENT)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)

    public String getDepartment() {
        return department;
    }


    @JsonProperty(JSON_PROPERTY_DEPARTMENT)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setDepartment(String department) {
        this.department = department;
    }


    public Module faculty(String faculty) {
        this.faculty = faculty;
        return this;
    }

    /**
     * Get faculty
     *
     * @return faculty
     **/
    @javax.annotation.Nonnull
    @ApiModelProperty(example = "Computing", required = true, value = "")
    @JsonProperty(JSON_PROPERTY_FACULTY)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)

    public String getFaculty() {
        return faculty;
    }


    @JsonProperty(JSON_PROPERTY_FACULTY)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }


    public Module workload(Workload workload) {
        this.workload = workload;
        return this;
    }

    /**
     * Get workload
     *
     * @return workload
     **/
    @javax.annotation.Nonnull
    @ApiModelProperty(required = true, value = "")
    @JsonProperty(JSON_PROPERTY_WORKLOAD)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)

    public Workload getWorkload() {
        return workload;
    }


    @JsonProperty(JSON_PROPERTY_WORKLOAD)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setWorkload(Workload workload) {
        this.workload = workload;
    }


    public Module prerequisite(String prerequisite) {
        this.prerequisite = prerequisite;
        return this;
    }

    /**
     * Get prerequisite
     *
     * @return prerequisite
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(example = "CS1010 or its equivalent", value = "")
    @JsonProperty(JSON_PROPERTY_PREREQUISITE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

    public String getPrerequisite() {
        return prerequisite;
    }


    @JsonProperty(JSON_PROPERTY_PREREQUISITE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setPrerequisite(String prerequisite) {
        this.prerequisite = prerequisite;
    }


    public Module moduleCredit(String moduleCredit) {
        this.moduleCredit = moduleCredit;
        return this;
    }

    /**
     * Get moduleCredit
     *
     * @return moduleCredit
     **/
    @javax.annotation.Nonnull
    @ApiModelProperty(example = "4", required = true, value = "")
    @JsonProperty(JSON_PROPERTY_MODULE_CREDIT)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)

    public String getModuleCredit() {
        return moduleCredit;
    }


    @JsonProperty(JSON_PROPERTY_MODULE_CREDIT)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setModuleCredit(String moduleCredit) {
        this.moduleCredit = moduleCredit;
    }


    public Module moduleCode(String moduleCode) {
        this.moduleCode = moduleCode;
        return this;
    }

    /**
     * Get moduleCode
     *
     * @return moduleCode
     **/
    @javax.annotation.Nonnull
    @ApiModelProperty(example = "CS2100", required = true, value = "")
    @JsonProperty(JSON_PROPERTY_MODULE_CODE)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)

    public String getModuleCode() {
        return moduleCode;
    }


    @JsonProperty(JSON_PROPERTY_MODULE_CODE)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }


    public Module semesterData(List<SemesterData> semesterData) {
        this.semesterData = semesterData;
        return this;
    }

    public Module addSemesterDataItem(SemesterData semesterDataItem) {
        this.semesterData.add(semesterDataItem);
        return this;
    }

    /**
     * Get semesterData
     *
     * @return semesterData
     **/
    @javax.annotation.Nonnull
    @ApiModelProperty(required = true, value = "")
    @JsonProperty(JSON_PROPERTY_SEMESTER_DATA)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)

    public List<SemesterData> getSemesterData() {
        return semesterData;
    }


    @JsonProperty(JSON_PROPERTY_SEMESTER_DATA)
    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    public void setSemesterData(List<SemesterData> semesterData) {
        this.semesterData = semesterData;
    }


    public Module prereqTree(PrereqTree prereqTree) {
        this.prereqTree = prereqTree;
        return this;
    }

    /**
     * Get prereqTree
     *
     * @return prereqTree
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(value = "")
    @JsonProperty(JSON_PROPERTY_PREREQ_TREE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

    public PrereqTree getPrereqTree() {
        return prereqTree;
    }


    @JsonProperty(JSON_PROPERTY_PREREQ_TREE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setPrereqTree(PrereqTree prereqTree) {
        this.prereqTree = prereqTree;
    }


    public Module fulfillRequirements(List<String> fulfillRequirements) {
        this.fulfillRequirements = fulfillRequirements;
        return this;
    }

    public Module addFulfillRequirementsItem(String fulfillRequirementsItem) {
        if (this.fulfillRequirements == null) {
            this.fulfillRequirements = new ArrayList<>();
        }
        this.fulfillRequirements.add(fulfillRequirementsItem);
        return this;
    }

    /**
     * Get fulfillRequirements
     *
     * @return fulfillRequirements
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(example = "[\"CS2106\",\"CS3210\"]", value = "")
    @JsonProperty(JSON_PROPERTY_FULFILL_REQUIREMENTS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

    public List<String> getFulfillRequirements() {
        return fulfillRequirements;
    }


    @JsonProperty(JSON_PROPERTY_FULFILL_REQUIREMENTS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setFulfillRequirements(List<String> fulfillRequirements) {
        this.fulfillRequirements = fulfillRequirements;
    }


    /**
     * Return true if this Module object is equal to o.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Module module = (Module) o;
        return Objects.equals(this.acadYear, module.acadYear) &&
                Objects.equals(this.preclusion, module.preclusion) &&
                Objects.equals(this.description, module.description) &&
                Objects.equals(this.title, module.title) &&
                Objects.equals(this.department, module.department) &&
                Objects.equals(this.faculty, module.faculty) &&
                Objects.equals(this.workload, module.workload) &&
                Objects.equals(this.prerequisite, module.prerequisite) &&
                Objects.equals(this.moduleCredit, module.moduleCredit) &&
                Objects.equals(this.moduleCode, module.moduleCode) &&
                Objects.equals(this.semesterData, module.semesterData) &&
                Objects.equals(this.prereqTree, module.prereqTree) &&
                Objects.equals(this.fulfillRequirements, module.fulfillRequirements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(acadYear, preclusion, description, title, department, faculty, workload, prerequisite,
                moduleCredit, moduleCode, semesterData, prereqTree, fulfillRequirements);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Module {\n");
        sb.append("    acadYear: ").append(toIndentedString(acadYear)).append("\n");
        sb.append("    preclusion: ").append(toIndentedString(preclusion)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    title: ").append(toIndentedString(title)).append("\n");
        sb.append("    department: ").append(toIndentedString(department)).append("\n");
        sb.append("    faculty: ").append(toIndentedString(faculty)).append("\n");
        sb.append("    workload: ").append(toIndentedString(workload)).append("\n");
        sb.append("    prerequisite: ").append(toIndentedString(prerequisite)).append("\n");
        sb.append("    moduleCredit: ").append(toIndentedString(moduleCredit)).append("\n");
        sb.append("    moduleCode: ").append(toIndentedString(moduleCode)).append("\n");
        sb.append("    semesterData: ").append(toIndentedString(semesterData)).append("\n");
        sb.append("    prereqTree: ").append(toIndentedString(prereqTree)).append("\n");
        sb.append("    fulfillRequirements: ").append(toIndentedString(fulfillRequirements)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

}
