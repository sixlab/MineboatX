/**
 * Copyright (c) 2017 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2017
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.api.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

public class InputStreamUtil {
    
    public static String readString(InputStream is) {
        StringWriter writer = new StringWriter();
    
        InputStreamReader reader;
        try {
            reader = new InputStreamReader(is, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
        char[] buffer = new char[4096];
    
        int n;
        try {
            while (-1 != (n = reader.read(buffer))) {
                writer.write(buffer, 0, n);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return writer.toString();
    }
}
