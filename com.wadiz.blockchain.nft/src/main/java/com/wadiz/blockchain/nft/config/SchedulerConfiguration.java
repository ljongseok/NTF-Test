package com.wadiz.blockchain.nft.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class SchedulerConfiguration {
    @Bean
    public TaskScheduler poolScheduler() {
        System.out.println("poolScheduler Setup");
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        int size = Runtime.getRuntime().availableProcessors() * 2;
        System.out.println("pool size: " + size);
        threadPoolTaskScheduler.setPoolSize(size);
        threadPoolTaskScheduler.setThreadNamePrefix("block-sc-");
        return threadPoolTaskScheduler;
    }
}
