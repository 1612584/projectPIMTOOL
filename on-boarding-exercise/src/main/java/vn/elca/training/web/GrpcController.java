package vn.elca.training.web;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;
import vn.elca.protobuf.CoreServiceGrpc;
import vn.elca.protobuf.PingRequest;
import vn.elca.protobuf.PingResponse;
import vn.elca.training.service.PingService;

@Slf4j
@GRpcService
public class GrpcController extends CoreServiceGrpc.CoreServiceImplBase {
    private final PingService pingService;

    public GrpcController(PingService pingService) {
        this.pingService = pingService;
    }

    @Override
    public void ping(PingRequest request, StreamObserver<PingResponse> responseObserver) {
        responseObserver.onNext(pingService.ping(request));
        responseObserver.onCompleted();
    }
}