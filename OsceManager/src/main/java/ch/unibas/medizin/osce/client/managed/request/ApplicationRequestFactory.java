// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ch.unibas.medizin.osce.client.managed.request;

import ch.unibas.medizin.osce.shared.scaffold.ScaffoldRequestFactory;

public interface ApplicationRequestFactory extends ScaffoldRequestFactory {

    UsedMaterialRequest usedMaterialRequest();

    TaskRequest taskRequest();

    StudentRequest studentRequest();

    StudentOscesRequest studentOscesRequest();

    StandardizedRoleRequest standardizedRoleRequest();

    StandardizedPatientRequest standardizedPatientRequest();

    SpokenLanguageRequest spokenLanguageRequest();

    SpecialisationRequest specialisationRequest();

    SimpleSearchCriteriaRequest simpleSearchCriteriaRequest();

    SemesterRequest semesterRequest();

    ScarRequest scarRequest();

    RoomRequest roomRequest();

    RoleTopicRequest roleTopicRequest();

    RoleTemplateRequest roleTemplateRequest();

    RoleTableItemValueRequest roleTableItemValueRequest();

    RoleTableItemRequest roleTableItemRequest();

    RoleSubItemValueRequest roleSubItemValueRequest();

    RoleParticipantRequest roleParticipantRequest();

    RoleItemAccessRequest roleItemAccessRequest();

    RoleBaseItemRequest roleBaseItemRequest();

    ProfessionRequest professionRequest();

    PatientInSemesterRequest patientInSemesterRequest();

    PatientInRoleRequest patientInRoleRequest();

    OsceRequest osceRequest();

    OscePostRoomRequest oscePostRoomRequest();

    OscePostRequest oscePostRequest();

    OsceDayRequest osceDayRequest();

    OfficeRequest officeRequest();

    NationalityRequest nationalityRequest();

    MediaContentTypeRequest mediaContentTypeRequest();

    MediaContentRequest mediaContentRequest();

    MaterialListRequest materialListRequest();

    LogEntryRequest logEntryRequest();

    LangSkillRequest langSkillRequest();

    KeywordRequest keywordRequest();

    FileRequest fileRequest();

    EliminationCriterionRequest eliminationCriterionRequest();

    DoctorRequest doctorRequest();

    DescriptionRequest descriptionRequest();

    CourseRequest courseRequest();

    ClinicRequest clinicRequest();

    ChecklistTopicRequest checklistTopicRequest();

    ChecklistQuestionRequest checklistQuestionRequest();

    ChecklistOptionRequest checklistOptionRequest();

    ChecklistCriteriaRequest checklistCriteriaRequest();

    CheckListRequest checkListRequest();

    BankaccountRequest bankaccountRequest();

    AssignmentRequest assignmentRequest();

    AnamnesisFormRequest anamnesisFormRequest();

    AnamnesisChecksValueRequest anamnesisChecksValueRequest();

    AnamnesisCheckRequest anamnesisCheckRequest();

    AdvancedSearchCriteriaRequest advancedSearchCriteriaRequest();

    AdministratorRequest administratorRequest();
}