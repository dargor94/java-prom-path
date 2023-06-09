package org.dargor.batch.config;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
@EnableScheduling
@AllArgsConstructor
public class SchedulerConfig {

    @Qualifier("import-customers")
    private final Job job;
    private final JobLauncher jobLauncher;
    private final AtomicBoolean enabled = new AtomicBoolean(true);
    private final AtomicInteger batchRunCounter = new AtomicInteger(0);

    @Scheduled(fixedRate = 4000)
    public void launchJob() throws Exception {
        Date date = new Date();
        if (enabled.get()) {
            jobLauncher.run(job, new JobParametersBuilder().addDate("launchDate", date)
                    .toJobParameters());
            batchRunCounter.incrementAndGet();
        }
    }

}
