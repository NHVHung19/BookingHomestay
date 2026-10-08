package com.booking.homestay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

//annotation được sử dụng để kích hoạt tính năng lập lịch (scheduling)
@EnableScheduling
// Chú thích này đánh dấu lớp là một cấu hình Spring, chỉ ra rằng nó chứa cấu hình bean của ứng dụng.
@Configuration
public class SchedulingConfig {

    //bean TaskScheduler, mà Spring sử dụng để thực hiện lịch trình các công việc đã được đặt lịch (@Scheduled)
    //cấu hình một ThreadPoolTaskScheduler để quản lý việc thực thi các công việc lịch trình
    @Bean
    public TaskScheduler taskScheduler() {
        final ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(10);
        return scheduler;
    }
}
