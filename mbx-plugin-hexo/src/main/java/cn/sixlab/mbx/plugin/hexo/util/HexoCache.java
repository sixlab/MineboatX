/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018/5/20 23:51
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.hexo.util;

import cn.sixlab.mbx.plugin.hexo.bean.HexoArticle;

import java.util.List;

public class HexoCache {
    public static long lastModified;
    public static List<HexoArticle> articles;
}
