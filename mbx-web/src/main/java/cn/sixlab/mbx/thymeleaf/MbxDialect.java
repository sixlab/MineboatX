/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018/5/7 22:54
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.thymeleaf;

import cn.sixlab.mbx.thymeleaf.Processor.PageTagProcessor;
import org.springframework.stereotype.Component;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import java.util.HashSet;
import java.util.Set;

@Component
public class MbxDialect extends AbstractProcessorDialect {
    
    private static final String DIALECT_NAME = "Mbx Dialect";//定义方言名称
    private static final String DIALECT_PRE = "mbx";//定义方言名称
    
    public MbxDialect() {
        // 我们将设置此方言与“方言处理器”优先级相同
        // 标准方言, 以便处理器执行交错。
        super(DIALECT_NAME, DIALECT_PRE, StandardDialect.PROCESSOR_PRECEDENCE);
    }
    
    @Override
    public Set<IProcessor> getProcessors(String dialectPrefix) {
        Set<IProcessor> processors = new HashSet<>();
        
        processors.add(new PageTagProcessor(dialectPrefix));//添加我们定义的标签
        //processors.add(new StandardXmlNsTagProcessor(TemplateMode.HTML, dialectPrefix));
        
        return processors;
    }
}
