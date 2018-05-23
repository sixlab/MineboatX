/**
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018/1/26 17:38
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.api.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Exec {
    private static Logger logger = LoggerFactory.getLogger(Exec.class);
    
    public static void run(String command) throws IOException {
        run(null, command);
    }
    
    public static String run(String dir, String... commands) throws IOException {
        logger.debug("-----------------------");
        logger.info("开始运行命令：" + commands);
        logger.debug("目录：" + dir);
        
        ProcessBuilder pb = new ProcessBuilder(commands);
        if (null != dir) {
            File env = new File(dir);
            if (env.isDirectory()) {
                pb.directory(new File(dir));
            }
        }
        
        StringBuilder sb = new StringBuilder();
        try {
            
            //得到进程实例
            Process process = pb.start();
            
            //等待进程执行完毕
            InputStream is = null;
            if (process.waitFor() != 0) {
                //如果进程运行结果不为0,表示进程是错误退出的
                //获得进程实例的错误输出
                is = process.getErrorStream();
                sb.append("错误：\n");
            } else {
                is = process.getInputStream();
                sb.append("正确：\n");
            }
            
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            sb.append("错误：\n");
            sb.append(e);
        }
        
        String result = sb.toString();
        logger.info("运行结果：\n"+result);
        return result;
    }
}
