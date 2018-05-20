/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018/5/20 23:40
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.hexo.bean;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;

public class Test {
    
    public static void main(String[] args) {
        PageRequest pageRequest = PageRequest.of(1, 10);
        PageImpl<HexoArticle> content = new PageImpl(new ArrayList(), pageRequest, 8);
        System.out.println(content.getTotalElements());
        System.out.println(content.getTotalPages());
        System.out.println(content.getNumber());
        System.out.println(content.getNumberOfElements());
        System.out.println(content.getSize());
        System.out.println(content.getPageable().getPageNumber());
        System.out.println(content.getPageable().getPageSize());
        System.out.println(content.getPageable().getOffset());
    }
}
