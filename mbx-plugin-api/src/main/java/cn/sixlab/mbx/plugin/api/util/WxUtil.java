/**
 * Copyright (c) 2017 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2017/11/4 01:09
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.api.util;

import cn.sixlab.mbx.plugin.api.beans.MsxWxMsg;
import cn.sixlab.mbx.plugin.api.dao.WxMsgRepo;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Date;

public class WxUtil {
    private static Logger logger = LoggerFactory.getLogger(WxUtil.class);
    
    public static MsxWxMsg saveMsg(String message, WxMsgRepo msgRepo){
        MsxWxMsg wxMsg = null;
    
        try {
            Document document = DocumentHelper.parseText(message);
            Element rootElement = document.getRootElement();
            
            String msgType = rootElement.element("MsgType").getText();
        
            String toUserName = rootElement.element("ToUserName").getText();
            String fromUserName = rootElement.element("FromUserName").getText();
            String createTime = rootElement.element("CreateTime").getText();
        
            String msgId;
        
            if ("event".equals(msgType)) {
                msgId = fromUserName + " " + createTime;
            } else {
                msgId = rootElement.element("MsgId").getText();
            }
        
            if (StringUtils.hasLength(msgId)) {
                wxMsg = msgRepo.findByMsgId(msgId);
    
                if (null == wxMsg) {
                    wxMsg = new MsxWxMsg();
                    wxMsg.setMsgId(msgId);
                
                    wxMsg.setToUserName(toUserName);
                    wxMsg.setFromUserName(fromUserName);
                    wxMsg.setCreateTime(createTime);
                
                    wxMsg.setMsgType(msgType);
                
                
                    // location - Scale
                    // link     - Title
                    // event    - Event
                    String title = null;
                
                    // voice    - Recognition
                    // location - Label
                    // link     - Description
                    // text     - Content
                    // event    - EventKey
                    String content = null;
                
                    // image      - MediaId
                    // voice      - MediaId
                    // video      - MediaId
                    // shortvideo - MediaId
                    // event      - Ticket
                    String mediaId = null;
                
                    // video      - ThumbMediaId
                    // shortvideo - ThumbMediaId
                    String thumbMediaId = null;
                
                    // image      - PicUrl
                    // shortvideo - ThumbMediaId
                    String url = null;
                
                    // voice      - Format
                    String format = null;
                
                    // location   - Location_X
                    // event      - Latitude
                    String locationX = null;
                    // location   - Location_Y
                    // event      - Longitude
                    String locationY = null;
                    // location   - Scale
                    // event      - Precision
                    String scale = null;
    
                    switch (msgType) {
                        case "text":
                            // 文本消息 text
                            content = rootElement.element("Content").getText();
                        
                            break;
                        case "image":
                            // 图片消息 image
                            mediaId = rootElement.element("MediaId").getText();
                            url = rootElement.element("PicUrl").getText();
                        
                        
                            break;
                        case "voice":
                            // 语音消息 voice
                            mediaId = rootElement.element("MediaId").getText();
                            content = rootElement.element("Recognition").getText();
                            format = rootElement.element("Format").getText();
                        
                            break;
                        case "video":
                            // 视频消息 video
                            mediaId = rootElement.element("MediaId").getText();
                            thumbMediaId = rootElement.element("ThumbMediaId").getText();
                        
                        
                            break;
                        case "shortvideo":
                            // 小视频消息 shortvideo
                            mediaId = rootElement.element("MediaId").getText();
                            thumbMediaId = rootElement.element("ThumbMediaId").getText();
                        
                            break;
                        case "location":
                            // 地理位置消息 location
                            locationX = rootElement.element("Location_X").getText();
                            locationY = rootElement.element("Location_Y").getText();
                            scale = rootElement.element("Scale").getText();
                            content = rootElement.element("Label").getText();
                        
                            break;
                        case "link":
                            // 链接消息 link
                            title = rootElement.element("Title").getText();
                            content = rootElement.element("Description").getText();
                            url = rootElement.element("Url").getText();
                        
                            break;
                        case "event":
                            // 接收事件推送 event
                            title = rootElement.element("Event").getText();
    
                            if ("TEMPLATESENDJOBFINISH".equals(title)) {
                                logger.info("TEMPLATESENDJOBFINISH>>>\n" + message);
                                return null;
                            }
                            
                            content = rootElement.element("EventKey").getText();
                            
                            locationX = rootElement.element("Latitude").getText();
                            locationY = rootElement.element("Longitude").getText();
                            scale = rootElement.element("Precision").getText();
    
                            //mediaId = rootElement.element("Ticket").getText();
                        
                            break;
                    }
                
                    wxMsg.setTitle(title);
                    wxMsg.setContent(content);
                    wxMsg.setMediaId(mediaId);
                    wxMsg.setThumbMediaId(thumbMediaId);
                    wxMsg.setUrl(url);
                    wxMsg.setFormat(format);
                    wxMsg.setLocationX(locationX);
                    wxMsg.setLocationY(locationY);
                    wxMsg.setScale(scale);
                    wxMsg.setInsertTime(LocalDateTime.now());
                
                    msgRepo.saveAndFlush(wxMsg);
                }else{
                    wxMsg = null;
                }
            }
        
        } catch (Exception e) {
            logger.info("报错消息>>>\n"+message);
            e.printStackTrace();
        }
    
        return wxMsg;
    }
    
    public static String returnMsg(String msgType, String toUsername, String fromUsername, String event, String msgId){
        String msg = "";
        if ("event".equals(msgType)) {
            if ("subscribe".equals(event)) {
                msg = "<xml>" +
                        "<ToUserName><![CDATA[" + toUsername + "]]></ToUserName>" +
                        "<FromUserName><![CDATA[" + fromUsername + "]]></FromUserName>" +
                        "<CreateTime>" + String.valueOf(new Date().getTime() / 1000) + "</CreateTime>" +
                        "<MsgType><![CDATA[text]]></MsgType>" +
                        "<Content><![CDATA[感谢您的关注，更多功能敬请期待，现在您可以访问 sixlab.cn 发现更多精彩。也可以发消息和我互动哦。]]></Content>" +
                        "</xml>";
            }
        } else {
            msg = "<xml>" +
                    "<ToUserName><![CDATA[" + toUsername + "]]></ToUserName>" +
                    "<FromUserName><![CDATA[" + fromUsername + "]]></FromUserName>" +
                    "<CreateTime>" + String.valueOf(new Date().getTime() / 1000) + "</CreateTime>" +
                    "<MsgType><![CDATA[text]]></MsgType>" +
                    "<Content><![CDATA[消息已收到，请访问 https://sixlab.cn/wx/pub/msg/" + msgId + " 查看消息。]]></Content>" +
                    "</xml>";
        }
        return msg;
    }

}
