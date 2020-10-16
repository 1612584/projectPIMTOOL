package vn.elca.training.service.impl;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.elca.protobuf.GetOneRequest;
import vn.elca.protobuf.GrpcGroupList;
import vn.elca.protobuf.GrpcProjectDto;
import vn.elca.protobuf.ResponseUpdate;
import vn.elca.protobuf.SearchRequest;
import vn.elca.protobuf.SearchResponse;
import vn.elca.training.model.dto.ProjectDto;
import vn.elca.training.model.exception.ConcurrentUpdateException;
import vn.elca.training.service.GroupService;
import vn.elca.training.service.GrpcProjectService;
import vn.elca.training.service.ProjectService;
import vn.elca.training.util.Mapper;

import java.util.List;

@Service
@Slf4j
public class GrpcProjectServiceImpl implements GrpcProjectService {

    private final ProjectService projectService;

    private final GroupService groupService;

    public GrpcProjectServiceImpl(ProjectService projectService, GroupService groupService) {
        this.projectService = projectService;
        this.groupService = groupService;
    }

    @Override
    public SearchResponse findByNameAndStatus(SearchRequest request) {
        log.info("Received find projects by name and status message {}", new Gson().toJson(request));
        List<ProjectDto> projectDtoList =
                this.projectService.findAllByNameAndStatus(request.getName(), request.getStatus());
        return Mapper.toGrpcSearchResponse(projectDtoList);
    }

    @Override
    public GrpcProjectDto getOne(GetOneRequest request) {
        return Mapper.toGrpcProjectDto(this.projectService.findById(request.getId()));
    }

    @Override
    public GrpcGroupList getGroups() {
        return Mapper.toGrpcGroupList(this.groupService.findAll());
    }

    @Override
    public ResponseUpdate createProject(GrpcProjectDto request) {
        try {
            return Mapper.toResponseUpdate(
                    this.projectService.createProject(Mapper.toProjectDto(request)));
        } catch (ConcurrentUpdateException ex) {
            return ResponseUpdate
                    .newBuilder()
                    .setIsConcurrentUpdate(true)
                    .setSuccess(false)
                    .setMessage(ex.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseUpdate updateProject(GrpcProjectDto request) {
        try {
            return Mapper.toResponseUpdate(
                    this.projectService.updateProject(request.getId(), Mapper.toProjectDto(request)));
        } catch (ConcurrentUpdateException ex) {
            return ResponseUpdate
                    .newBuilder()
                    .setIsConcurrentUpdate(true)
                    .setSuccess(false)
                    .setMessage(ex.getMessage())
                    .build();
        }
    }
}
