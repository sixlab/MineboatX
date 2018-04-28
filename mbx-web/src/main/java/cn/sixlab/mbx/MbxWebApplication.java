package cn.sixlab.mbx;

import cn.sixlab.mbx.core.common.util.ContextUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

@EnableCaching
@SpringBootApplication
public class MbxWebApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(MbxWebApplication.class, args);
        ContextUtil.setCtx(ctx);
    }
}
