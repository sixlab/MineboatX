/**
 * Copyright (c) 2017 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2017/11/3 13:25
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.plugin.api.service;

import cn.sixlab.mbx.core.common.beans.ModelJson;
import cn.sixlab.mbx.plugin.api.beans.MsxWxMsg;
import cn.sixlab.mbx.plugin.api.business.WxBusiness;
import cn.sixlab.mbx.plugin.api.dao.WxMsgRepo;
import cn.sixlab.mbx.plugin.api.util.InputStreamUtil;
import cn.sixlab.mbx.plugin.api.util.MsxWxVal;
import cn.sixlab.mbx.plugin.api.util.WxUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class WxService {
    private static Logger logger = LoggerFactory.getLogger(WxService.class);
    
    @Autowired
    MsxWxVal wxVal;
    
    @Autowired
    WxMsgRepo msgRepo;
    
    @Autowired
    private WxBusiness wxBusiness;
    
    public boolean checkToken(String signature, String timestamp, String nonce) {
        logger.info("验证微信签名");
    
        return wxBusiness.checkToken(signature, timestamp, nonce, wxVal.getWxToken());
    }
    
    public MsxWxMsg dealMsg(InputStream is) {
        String message = InputStreamUtil.readString(is);
        
        MsxWxMsg wxMsg = WxUtil.saveMsg(message, msgRepo);
        
        //template.convertAndSend(MqTopic.WX_SAVE_MSG, me);
        return wxMsg;
    }
    
    public ModelJson fetchMsg(Integer id) {
        MsxWxMsg wxMsg = msgRepo.getOne(id);
    
        return new ModelJson().setData(wxMsg);
    }
    
    public ModelJson fetchMsgs(Integer id) {
        MsxWxMsg wxMsg = msgRepo.getOne(id);
        List<MsxWxMsg> msgList = msgRepo.findByFromUserNameOrderById(wxMsg.getFromUserName());
        return new ModelJson().setData(msgList);
    }
}
