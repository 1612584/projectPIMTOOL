package vn.elca.training.service;

import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.elca.protobuf.*;
import vn.elca.training.mapper.Mapper;
import vn.elca.training.model.Project;

import java.util.List;

public class ProjectServiceClient {

    private static final String server = "localhost:6790";

    private static final Logger log = LoggerFactory.getLogger(ProjectServiceClient.class);

    public static List<GrpcProjectDto> findByNameAndStatus(String name, String status) {
         final ManagedChannel channel = ManagedChannelBuilder.forTarget(server).usePlaintext().build();
        ProjectServiceGrpc.ProjectServiceBlockingStub stub = ProjectServiceGrpc.newBlockingStub(channel);
        SearchRequest request = SearchRequest
                .newBuilder()
                .setName(name)
                .setStatus(status)
                .build();
        SearchResponse response = stub.findByNameAndStatus(request);
        log.debug(String.format("project cnt = %d", response.getProjectsCount()));
        channel.shutdownNow();
        return response.getProjectsList();
    }

    public static GrpcProjectDto getProjectById(Long projectId) {
        final ManagedChannel channel = ManagedChannelBuilder.forTarget(server).usePlaintext().build();
        ProjectServiceGrpc.ProjectServiceBlockingStub stub = ProjectServiceGrpc.newBlockingStub(channel);
        GetOneRequest request = GetOneRequest.newBuilder().setId(projectId).build();
        GrpcProjectDto response =  stub.getOne(request);
        channel.shutdownNow();
        return response;
    }

    public static GrpcGroupList getGroupList() {
        final ManagedChannel channel = ManagedChannelBuilder.forTarget(server).usePlaintext().build();
        ProjectServiceGrpc.ProjectServiceBlockingStub stub = ProjectServiceGrpc.newBlockingStub(channel);
        Empty request = Empty.getDefaultInstance();
        GrpcGroupList response = stub.getGroups(request);
        channel.shutdownNow();
        return response;
    }

    public static ResponseUpdate createProject(Project project) {
        final ManagedChannel channel = ManagedChannelBuilder.forTarget(server).usePlaintext().build();
        ProjectServiceGrpc.ProjectServiceBlockingStub stub = ProjectServiceGrpc.newBlockingStub(channel);
        GrpcProjectDto request = Mapper.toGrpcProjectNotIncludeIdAndVersion(project);
        ResponseUpdate response = stub.createProject(request);
        channel.shutdownNow();
        return response;
    }

    public static ResponseUpdate updateProject(Project project) {
        final ManagedChannel channel = ManagedChannelBuilder.forTarget(server).usePlaintext().build();
        ProjectServiceGrpc.ProjectServiceBlockingStub stub = ProjectServiceGrpc.newBlockingStub(channel);
        GrpcProjectDto request = Mapper.toGrpcProject(project);
        ResponseUpdate response = stub.updateProject(request);
        channel.shutdownNow();
        return response;
    }

    public static ResponseUpdate deleteById(long id) {
        final ManagedChannel channel = ManagedChannelBuilder.forTarget(server).usePlaintext().build();
        ProjectServiceGrpc.ProjectServiceBlockingStub stub = ProjectServiceGrpc.newBlockingStub(channel);
        DeleteOneRequest request = DeleteOneRequest.newBuilder().setId(id).build();
        ResponseUpdate response = stub.deleteProject(request);
        channel.shutdownNow();
        return response;
    }

    public static ResponseUpdate deleteMultipleProject(List<Long> listId) {
        final ManagedChannel channel = ManagedChannelBuilder.forTarget(server).usePlaintext().build();
        ProjectServiceGrpc.ProjectServiceBlockingStub stub = ProjectServiceGrpc.newBlockingStub(channel);
        DeleteMultipleRequest request = DeleteMultipleRequest.newBuilder().addAllIds(listId).build();
        ResponseUpdate response = stub.deleteProjectList(request);
        channel.shutdownNow();
        return response;
    }
}
