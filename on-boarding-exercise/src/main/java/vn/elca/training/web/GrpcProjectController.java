package vn.elca.training.web;

import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;
import vn.elca.protobuf.*;
import vn.elca.training.service.GrpcProjectService;

@Slf4j
@GRpcService
public class GrpcProjectController extends ProjectServiceGrpc.ProjectServiceImplBase {

    private final GrpcProjectService grpcProjectService;

    public GrpcProjectController(GrpcProjectService grpcProjectService) {
        this.grpcProjectService = grpcProjectService;
    }

    @Override
    public void findByNameAndStatus(SearchRequest searchRequest, StreamObserver<SearchResponse> responseObserver) {
        responseObserver.onNext(grpcProjectService.findByNameAndStatus(searchRequest));
        responseObserver.onCompleted();
    }

    @Override
    public void getOne(GetOneRequest request, StreamObserver<GrpcProjectDto> responseObserver) {
        responseObserver.onNext(grpcProjectService.getOne(request));
        responseObserver.onCompleted();
    }

    @Override
    public void getGroups(Empty request, StreamObserver<GrpcGroupList> responseObserver) {
        responseObserver.onNext(grpcProjectService.getGroups());
        responseObserver.onCompleted();
    }

    @Override
    public void createProject(GrpcProjectDto request, StreamObserver<ResponseUpdate> responseObserver) {
        responseObserver.onNext(grpcProjectService.createProject(request));
        responseObserver.onCompleted();
    }

    @Override
    public void updateProject(GrpcProjectDto request, StreamObserver<ResponseUpdate> responseObserver) {
        responseObserver.onNext(grpcProjectService.updateProject(request));
        responseObserver.onCompleted();
    }

    @Override
    public void deleteProject(DeleteOneRequest request, StreamObserver<ResponseUpdate> responseObserver) {
        responseObserver.onNext(grpcProjectService.deleteProject(request));
        responseObserver.onCompleted();
    }

    @Override
    public void deleteProjectList(DeleteMultipleRequest request, StreamObserver<ResponseUpdate> responseObserver) {
        responseObserver.onNext(grpcProjectService.deleteMultipleProject(request));
        responseObserver.onCompleted();
    }
}

