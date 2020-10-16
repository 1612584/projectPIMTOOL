package vn.elca.training.service;

import com.google.gson.Gson;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.elca.protobuf.PingRequest;
import vn.elca.protobuf.PingResponse;

@Service
@Slf4j
public class PingService {

    public PingResponse ping(PingRequest request) {
        log.info("Received Ping message {}", new Gson().toJson(request));
        return PingResponse.newBuilder()
            .setTimestamp(request.getTimestamp())
            .setMessage("Pong")
            .build();
    }
}
