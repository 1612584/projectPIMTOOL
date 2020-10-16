package vn.elca.training.mapper;

import vn.elca.protobuf.Date;
import vn.elca.protobuf.GrpcProjectDto;
import vn.elca.training.model.Project;

import java.util.List;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Mapper {
    public static Project toProject(GrpcProjectDto dto, ResourceBundle bundle) {
        Project project = toProjectNotIncludeGroupAndVisaString(dto, bundle);
        project.setGroupId(dto.getGroupId());
        project.setVisaList(dto.getVisaString());
        project.setVersion(dto.getVersion());
        return project;
    }

    public static Project toProjectNotIncludeGroupAndVisaString(GrpcProjectDto dto, ResourceBundle bundle) {
        Project project = new Project();
        project.setId(dto.getId());
        project.setProjectNumber(dto.getProjectNumber());
        project.setName(dto.getName());
        project.setCustomer(dto.getCustomer());
        project.setStatus(bundle.getString(String.format("status.%s",dto.getStatus())));
        project.setStartDate(toLocalDate(dto.getStartDate()));
        if (dto.hasEndDate()) {
          project.setEndDate(toLocalDate(dto.getEndDate()));
        }
        project.setVersion(dto.getVersion());
        return project;
    }

    public static List<Project> toProjectList(List<GrpcProjectDto> projectDtoList, ResourceBundle bundle) {
       return projectDtoList
               .stream()
               .map(dto -> Mapper.toProjectNotIncludeGroupAndVisaString(dto, bundle))
               .collect(Collectors.toList());
    }

    public static LocalDate toLocalDate(Date date) {
        return LocalDate.of(date.getYear(),date.getMonth(),date.getDay());
    }

    public static GrpcProjectDto toGrpcProject(Project project) {
        return toGrpcProjectNotIncludeIdAndVersion(project)
                .toBuilder()
                .setId(project.getId())
                .setVersion(project.getVersion())
                .build();
    }

    public static GrpcProjectDto toGrpcProjectNotIncludeIdAndVersion(Project project) {
        GrpcProjectDto dto = GrpcProjectDto
                .newBuilder()
                .setProjectNumber(project.getProjectNumber())
                .setName(project.getName())
                .setCustomer(project.getCustomer())
                .setStatus(project.getStatus())
                .setGroupId(project.getGroupId())
                .setVisaString(project.getVisaList())
                .setStartDate(toGrpcDate(project.getStartDate()))
                .buildPartial();
        if (project.getEndDate() != null) {
           dto = dto.toBuilder().setEndDate(toGrpcDate(project.getEndDate())).build();
        }
        return dto;
    }

    public static Date toGrpcDate(LocalDate date) {
        return Date.newBuilder()
                .setDay(date.getDayOfMonth())
                .setMonth(date.getMonthValue())
                .setYear(date.getYear())
                .build();
    }
}
