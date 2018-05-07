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
        
        model.add(modelFactory.createOpenElementTag("ul"));
        
        // 上一页
        model.add(modelFactory.createOpenElementTag("li"));
        if (page.isFirst()) {
            model.add(modelFactory.createOpenElementTag("a", "class", "active"));
        } else {
            model.add(modelFactory.createOpenElementTag("a", "class", "active"));
        }
        model.add(modelFactory.createText(page.getNumber() + "<"));
        model.add(modelFactory.createCloseElementTag("a"));
        model.add(modelFactory.createCloseElementTag("li"));
        
        if (page.getTotalPages() < 5) {
            for (int i = 0; i < page.getTotalPages(); i++) {
                addNo(page, modelFactory, model, i);
            }
        } else {
            //第一页
            model.add(modelFactory.createOpenElementTag("li"));
            if (page.isFirst()) {
                model.add(modelFactory.createOpenElementTag("a", "class", "active"));
            } else {
                model.add(modelFactory.createOpenElementTag("a"));
            }
            model.add(modelFactory.createText("1"));
            model.add(modelFactory.createCloseElementTag("a"));
            model.add(modelFactory.createCloseElementTag("li"));
            
            // dot
            int start = 1;
            if (page.getNumber() > 4) {
                model.add(modelFactory.createOpenElementTag("li"));
                model.add(modelFactory.createOpenElementTag("a"));
                model.add(modelFactory.createText("."));
                model.add(modelFactory.createCloseElementTag("a"));
                model.add(modelFactory.createCloseElementTag("li"));
                start = page.getNumber() - 1;
            }
            
            // 至当前页
            for (int i = start; i < page.getNumber() + 1; i++) {
                addNo(page, modelFactory, model, i);
            }
            
            // dot
            int end = page.getTotalPages() - 1 - 1;
            if ((page.getTotalPages() - page.getNumber() - 1) > 4) {
                addNo(page, modelFactory, model, page.getNumber() + 1 + 1);
                
                model.add(modelFactory.createOpenElementTag("li"));
                model.add(modelFactory.createOpenElementTag("a"));
                model.add(modelFactory.createText("."));
                model.add(modelFactory.createCloseElementTag("a"));
                model.add(modelFactory.createCloseElementTag("li"));
                end = page.getNumber() + 1;
                
                // 最后一页
                model.add(modelFactory.createOpenElementTag("li"));
                if (page.isLast()) {
                    model.add(modelFactory.createOpenElementTag("a", "class", "active"));
                } else {
                    model.add(modelFactory.createOpenElementTag("a"));
                }
            } else {
                for (int i = page.getNumber() + 1; i < page.getTotalPages(); i++) {
                    addNo(page, modelFactory, model, i);
                }
            }
            
            model.add(modelFactory.createText(String.valueOf(page.getTotalPages())));
            model.add(modelFactory.createCloseElementTag("a"));
            model.add(modelFactory.createCloseElementTag("li"));
        }
        
        // 下一页
        model.add(modelFactory.createOpenElementTag("li"));
        model.add(modelFactory.createOpenElementTag("a", "class", "active"));
        model.add(modelFactory.createText(">" + page.getTotalPages()));
        model.add(modelFactory.createCloseElementTag("a"));
        model.add(modelFactory.createCloseElementTag("li"));
        
        //关闭标签
        model.add(modelFactory.createCloseElementTag("ul"));
        structureHandler.replaceWith(model, true);
        
        // <div id="ttt" mbx:page="${result}"></div>
        
        // 12.5         1
        // 12345        234
        // 1.45         5
        
        // 12.6         1
        // 123.6        2
        // 1234.6       3
        // 123456       4
        // 1.456        5
        // 1.56         6
        
        //12.7          1
        //123.7         2
        //1234.7        3
        //12345.7       4
        //1.4567        5
        //1.567         6
        //1.67          7
        
        //12.8          1
        //123.8         2
        //1234.8        3
        //12345.8       4
        //1.45678       5
        //1.5678        6
        //1.678         7
        //1.78         8
        
        //12.9          1
        //123.9         2
        //1234.9        3
        //12345.9       4
        //1.456.9       5
        //1.56789       6
        //1.6789        7
        //1.789         8
        //1.89          9
        
        // < 1234567.YZ > 1
        // < 1234567.YZ > 6
        // < 1234567 > 7
        // < 1.456.9X > 8
    }
    
    private void addNo(Page page, IModelFactory modelFactory, IModel model, int i) {
        model.add(modelFactory.createOpenElementTag("li"));
        if (i == page.getNumber()) {
            model.add(modelFactory.createOpenElementTag("a", "class", "active"));
        } else {
            model.add(modelFactory.createOpenElementTag("a"));
        }
        model.add(modelFactory.createText(String.valueOf(i + 1)));
        model.add(modelFactory.createCloseElementTag("a"));
        model.add(modelFactory.createCloseElementTag("li"));
    }
}
