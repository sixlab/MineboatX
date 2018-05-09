/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018-05-08 17:29
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.init;

import cn.sixlab.mbx.core.beans.entity.MbxMeta;
import cn.sixlab.mbx.core.beans.entity.MbxUser;
import cn.sixlab.mbx.core.common.util.EntityUtil;
import cn.sixlab.mbx.core.dao.repository.MetaRepository;
import cn.sixlab.mbx.core.dao.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Order(100)
public class DataInitRunner implements ApplicationRunner {
    private static Logger logger = LoggerFactory.getLogger(DataInitRunner.class);

    @Autowired
    private MetaRepository metaRepository;

    @Autowired
    private UserRepository userRepository;

    @Value("${mbx.version}")
    private String version;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        MbxMeta mbxVersion = metaRepository.getByMetaCode("mbx-version");
        if (null == mbxVersion) {
            MbxUser user = new MbxUser();
            user.setUsername("admin");
            user.setNickname("admin");
            user.setPassword(new BCryptPasswordEncoder().encode("123456"));
            EntityUtil.setVal(user);
            userRepository.save(user);
            logger.info("初始用户：admin");
            logger.info("初始密码：123456");

            mbxVersion = new MbxMeta();
            mbxVersion.setMetaCode("mbx-version");
            mbxVersion.setMetaVal(version);
            EntityUtil.setVal(mbxVersion);
            metaRepository.save(mbxVersion);

            logger.info("初始完成，版本：" + version);
        } else {
            logger.info("之前版本：" + mbxVersion.getMetaVal());
            logger.info("目前版本：" + version);
            logger.info("非首次启动，不进行数据初始化。");
        }
    }
}
