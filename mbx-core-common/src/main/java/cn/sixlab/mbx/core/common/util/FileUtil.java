/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018/5/24 22:32
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.core.common.util;

import java.io.File;
import java.io.FileOutputStream;

public class FileUtil {
    public static String uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
    
        File newFile = new File(filePath + File.separator + fileName);
        
        if(!newFile.exists()){
            FileOutputStream out = new FileOutputStream(newFile);
            out.write(file);
            out.flush();
            out.close();
            return "";
        }else{
            return "文件已存在";
        }
    }
}
