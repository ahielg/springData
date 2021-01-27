package async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@Component
public class AppRunner {

    private final AsyncHandler async;

    public AppRunner(AsyncHandler async) {
        this.async = async;
    }

    @PostConstruct
    public void init() throws ExecutionException, InterruptedException {
        run();
    }

    public void run() throws ExecutionException, InterruptedException {
        // Start the clock
        long start = System.currentTimeMillis();

        // Kick of multiple, asynchronous lookups
        CompletableFuture<String> page1 = async.getBless("Ahiel");
        CompletableFuture<String> page2 = async.getBless("David");
        CompletableFuture<String> page3 = async.getBless("Moshe");

        // Wait until they are all done
        CompletableFuture.allOf(page1, page2, page3).join();

        // Print results, including elapsed time
        log.info("Elapsed time: " + (System.currentTimeMillis() - start) + "ms");
        log.info("--> " + page1.get());
        log.info("--> " + page2.get());
        log.info("--> " + page3.get());
    }
}
