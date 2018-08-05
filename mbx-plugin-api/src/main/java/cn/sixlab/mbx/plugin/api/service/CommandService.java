/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018/5/23 19:54
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.api.service;

import cn.sixlab.mbx.core.common.base.BaseService;
import cn.sixlab.mbx.core.common.util.LogUtil;
import cn.sixlab.mbx.plugin.api.util.Exec;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CommandService extends BaseService{
    private Logger logger = LogUtil.getLogger(this);
    
    public void runCommand(String command) {
    
        switch (command) {
            case "nginx":
                nginx();
                break;
            case "config":
                pullCode("/var/www/sixlab_config");
                break;
            case "hexo":
                hexo("/var/www/blogs/");
                break;
            case "hexog":
                hexoGen("/var/www/blogs/");
                break;
            case "push":
                hexoPush("/var/www/blogs/", "autoBot");
                break;
            case "publish":
                hexoGen("/var/www/blogs/");
                break;
            case "server":
                pullCode("/var/www/code_repo/MineboatX");
                mavenServer("/var/www/code_repo/MineboatX");
                startServer("/var/www/code_repo/MineboatX");
                break;
            default:
                logger.info("没有：" + command);
        }
        
    }
    
    public boolean startServer(String dir) {
        try {
            String log = Exec.run(dir, "./onekey.sh");
            logger.info(log);
            
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean mavenServer(String dir) {
        try {
            String log = Exec.run(dir, "mvn", "clean", "install","-Dmaven.test.skip=true");
            logger.info(log);
            
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean nginx() {
        pullCode("/var/www/sixlab_config");
        
        nginxReload();
        
        return true;
    }
    
    public boolean hexo(String dir) {
        hexoPull(dir);
    
        hexoGen(dir);
        
        return false;
    }
    
    public boolean hexoPull(String dir) {
        pullCode(dir);
        
        try {
            String log = Exec.run(dir, "git", "submodule", "update");
            logger.info(log);
            
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean hexoPush(String dir, String msg) {
        try {
            String log = Exec.run(dir, "git", "add", ".");
            logger.info(log);
    
            log = Exec.run(dir, "git", "commit", "-m", msg);
            logger.info(log);
    
            log = Exec.run(dir, "git", "push");
            logger.info(log);
            
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean hexoGen(String dir) {
        try {
            String log = Exec.run(dir, "hexo", "g");
            logger.info(log);
            
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean nginxReload() {
        try {
            String log;
            
            log = Exec.run("/usr/sbin/", "service", "nginx", "reload");
            logger.info(log);
    
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean pullCode(String dir) {
        try {
            String log;
            log = Exec.run(dir, "git", "pull");
            logger.info(log);
            
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
