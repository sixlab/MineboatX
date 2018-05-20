package cn.sixlab.mbx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching
@EnableScheduling
@SpringBootApplication
public class MbxWebApplication {

    public static void main(String[] args) {
        System.out.println(SpringApplication.run(MbxWebApplication.class, args));
    }
}
