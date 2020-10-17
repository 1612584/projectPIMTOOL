package vn.elca.training.service;

import vn.elca.protobuf.*;

public interface GrpcProjectService {
    SearchResponse findByNameAndStatus(SearchRequest request);
    GrpcProjectDto getOne(GetOneRequest request);
    GrpcGroupList getGroups();
    ResponseUpdate createProject(GrpcProjectDto request);
    ResponseUpdate updateProject(GrpcProjectDto request);
    ResponseUpdate deleteProject(DeleteOneRequest request);
    ResponseUpdate deleteMultipleProject(DeleteMultipleRequest request);
}
