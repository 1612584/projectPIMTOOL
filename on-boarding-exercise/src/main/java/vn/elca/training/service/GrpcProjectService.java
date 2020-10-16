package vn.elca.training.service;

import vn.elca.protobuf.GetOneRequest;
import vn.elca.protobuf.GrpcGroupList;
import vn.elca.protobuf.GrpcProjectDto;
import vn.elca.protobuf.ResponseUpdate;
import vn.elca.protobuf.SearchRequest;
import vn.elca.protobuf.SearchResponse;

public interface GrpcProjectService {
    SearchResponse findByNameAndStatus(SearchRequest request);
    GrpcProjectDto getOne(GetOneRequest request);
    GrpcGroupList getGroups();
    ResponseUpdate createProject(GrpcProjectDto request);
    ResponseUpdate updateProject(GrpcProjectDto request);
}
