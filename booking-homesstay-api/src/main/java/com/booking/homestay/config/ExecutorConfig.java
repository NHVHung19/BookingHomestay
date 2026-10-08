package com.booking.homestay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class ExecutorConfig {

    //triển khai của giao diện Executor, được cung cấp bởi Spring để quản lý các luồng thực thi tác vụ
    @Bean(name = "threadPoolTaskExecutor")
    public Executor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //Đặt số lượng luồng tối thiểu trong pool là 5. Điều này có nghĩa là pool sẽ giữ ít nhất 5 luồng sẵn sàng để thực thi các tác vụ
        executor.setCorePoolSize(5);
        //Đặt số lượng luồng tối đa trong pool là 10. Điều này chỉ ra rằng pool có thể tạo ra tối đa 10 luồng để xử lý các tác vụ nếu cần
        executor.setMaxPoolSize(10);
        //Đặt dung lượng của hàng đợi cho các tác vụ chờ trong trường hợp không có luồng trống sẵn để thực thi.
        //Trong trường hợp này, hàng đợi có thể chứa tối đa 200 tác vụ
        executor.setQueueCapacity(200);
        // Khởi tạo ThreadPoolTaskExecutor. Sau khi thiết lập các thuộc tính, bạn cần gọi phương thức này để hoàn thành quá trình khởi tạo và sẵn sàng sử dụng executor
        executor.initialize();
        return executor;
    }
}
