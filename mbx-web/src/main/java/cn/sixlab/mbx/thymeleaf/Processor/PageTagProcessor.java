/*
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018/5/7 22:59
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.thymeleaf.Processor;

import org.springframework.data.domain.Page;
import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.templatemode.TemplateMode;

public class PageTagProcessor extends AbstractAttributeTagProcessor {
    
    private static final String TAG_NAME = "page";//标签名
    private static final int PRECEDENCE = 10000;//优先级
    
    public PageTagProcessor(String dialectPrefix) {
        //super(
        //        TemplateMode.HTML,      // 此处理器将仅应用于HTML模式
        //        dialectPrefix,          // 要应用于名称的匹配前缀
        //        TAG_NAME,               // 标签名称：匹配此名称的特定标签
        //        true,   // 将标签前缀应用于标签名称
        //        null,       // 无属性名称：将通过标签名称匹配
        //        false,  // 没有要应用于属性名称的前缀
        //        PRECEDENCE);            // 优先(内部方言自己的优先)
        super(
                TemplateMode.HTML, // This processor will apply only to HTML mode
                dialectPrefix,     // Prefix to be applied to name for matching
                null,              // No tag name: match any tag name
                false,             // No prefix to be applied to tag name
                TAG_NAME,         // Name of the attribute that will be matched
                true,              // Apply dialect prefix to attribute name
                PRECEDENCE,        // Precedence (inside dialect's own precedence)
                true);             // Remove the matched attribute afterwards
    }
    
    //protected void doProcess(ITemplateContext iTemplateContext,
    //        IProcessableElementTag iProcessableElementTag,
    //        IElementTagStructureHandler iElementTagStructureHandler) {
    //
    //    System.out.println("------");
    //
    //    // ApplicationContext context = SpringContextUtils.getApplicationContext(iTemplateContext);
    //
    //    // 从标签读取属性
    //    final String matterid = iProcessableElementTag.getAttributeValue("matterid");
    //
    //    String logoimgText = matterid;
    //
    //
    //    /*
    //     * 创建将替换自定义标签的DOM结构。
    //     * logo将显示在“<div>”标签内, 因此必须首先创建,
    //     * 然后必须向其中添加一个节点。
    //     */
    //    final IModelFactory modelFactory = iTemplateContext.getModelFactory();
    //
    //    final IModel model = modelFactory.createModel();
    //
    //    model.add(modelFactory.createOpenElementTag("ul"));
    //
    //    model.add(modelFactory.createOpenElementTag("li"));
    //    model.add(modelFactory.createOpenElementTag("a","class","active"));
    //    model.add(modelFactory.createText("1"));
    //    model.add(modelFactory.createCloseElementTag("a"));
    //    model.add(modelFactory.createCloseElementTag("li"));
    //
    //    model.add(modelFactory.createCloseElementTag("ul"));
    //
    //    // 指示引擎用指定的模型替换整个元素。
    //    iElementTagStructureHandler.replaceWith(model, false);
    //}
    
    @Override
    protected void doProcess(ITemplateContext context, IProcessableElementTag tag,
            AttributeName attributeName, String attributeValue,
            IElementTagStructureHandler structureHandler) {
        final IEngineConfiguration configuration = context.getConfiguration();
        /*
         * Obtain the Thymeleaf Standard Expression parser
         */
        final IStandardExpressionParser parser =
                StandardExpressions.getExpressionParser(configuration);
        /*
         * Parse the attribute value as a Thymeleaf Standard Expression
         */
        final IStandardExpression expression = parser.parseExpression(context, attributeValue);
        /*
         * Execute the expression just parsed
         */
        final Page page = (Page) expression.execute(context);
        
        final IModelFactory modelFactory = context.getModelFactory();
        
        final IModel model = modelFactory.createModel();
        
        model.add(modelFactory.createOpenElementTag("ul","class","mbx-pagination"));
        
        // 上一页
        addLi(model, modelFactory, "<", page.isFirst() ? "left symbol disabled" : "left symbol active");
        
        if (page.getTotalPages() <= 5) {
            for (int i = 0; i < page.getTotalPages(); i++) {
                addLi(model, modelFactory, String.valueOf(i + 1), (i == page.getNumber()) ? "current num disabled" : "num active");
            }
        } else {
            //第一页
            addLi(model, modelFactory, "1", page.isFirst()?"current num disabled":"num active");
            
            // dot
            int start = 1;
            int end = page.getNumber() + 2;

            if (page.getNumber() >= 4) {
                addLi(model, modelFactory, "...", "dot disabled");

                start = page.getNumber() - 1;
                if ((page.getTotalPages() - page.getNumber()) <= 4) {
                    end = page.getTotalPages() - 1;
                }
            }
            
            // 至当前页
            for (int i = start; i < end; i++) {
                addLi(model, modelFactory, String.valueOf(i + 1), (i == page.getNumber()) ? "current num disabled" : "num active");
            }

            if ((page.getTotalPages() - page.getNumber()) > 4) {
                addLi(model, modelFactory, "...", "dot disabled");
            }

            // 最后一页
            addLi(model, modelFactory, String.valueOf(page.getTotalPages()), page.isLast() ? "current num disabled" : "num active");
        }
        
        // 下一页
        addLi(model, modelFactory, ">", page.isLast() ? "right symbol disabled" : "right symbol active");
        
        //关闭标签
        model.add(modelFactory.createCloseElementTag("ul"));
        structureHandler.replaceWith(model, true);
        
        // <div id="ttt" mbx:page="${result}"></div>

        // 12.6         0 [1,0+2)
        // 123.6        1 [1,1+2)
        // 1234.6       2 [1,2+2)
        // 123456       3 [1,3+2)------------------
        // 1.456        4 [3,5)
        // 1.56         5 [4,5)
        
        //12.7          0 [1,0+2)
        //123.7         1 [1,1+2)
        //1234.7        2 [1,2+2)
        //1234567       3 [1,3+2)------------------
        //1.4567        4 [3,6)
        //1.567         5 [4,6)
        //1.67          6 [5,6)
        
        //12.8          0 [1,0+2)
        //123.8         1 [1,1+2)
        //1234.8        2 [1,2+2)
        //12345.8       3 [1,3+2)------------------
        //1.45678       4 [3,7)
        //1.5678        5 [4,7)
        //1.678         6 [5,7)
        //1.78          7 [6,7)
        
        //12.9          0 [1,0+2)
        //123.9         1 [1,1+2)
        //1234.9        2 [1,2+2)
        //12345.9       3 [1,3+2)------------------
        //1.456.9       4 [3,6)
        //1.56789       5 [4,8)------------------
        //1.6789        6 [5,8)
        //1.789         7 [6,8)
        //1.89          8 [7,8)
    }

    private void addLi(IModel model, IModelFactory modelFactory, String text, String clz) {
        model.add(modelFactory.createOpenElementTag("li"));
        model.add(modelFactory.createOpenElementTag("a", "class", clz));
        model.add(modelFactory.createText(text));
        model.add(modelFactory.createCloseElementTag("a"));
        model.add(modelFactory.createCloseElementTag("li"));
    }
}
