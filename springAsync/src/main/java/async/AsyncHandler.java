package async;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class AsyncHandler {

    @SneakyThrows
    @Async("taskExecutor")
    public CompletableFuture<String> getBless(String user) {
        log.info("processing " + user);
        Thread.sleep(5000L);
        return CompletableFuture.completedFuture("Hello "+ user + "!");
    }

}
