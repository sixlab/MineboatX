/**
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018-04-20 13:30
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.core.beans;

import org.springframework.ui.ModelMap;

public class ModelJson extends ModelMap {
    Integer status = 0;
    Boolean success = false;
    String message = "";
    Object data = null;

    public ModelJson(){
        put("status", status);
        put("success", success);
        put("message", message);
        put("data", data);
    }

    public Integer getStatus() {
        return status;
    }

    public ModelJson setStatus(Integer status) {
        this.status = status;
        put("status", status);
        return this;
    }

    public Boolean getSuccess() {
        return success;
    }

    public ModelJson setSuccess(Boolean success) {
        this.success = success;
        put("success", success);
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ModelJson setMessage(String message) {
        this.message = message;
        put("message", message);
        return this;
    }

    public Object getData() {
        return data;
    }

    public ModelJson setData(Object data) {
        this.data = data;
        put("data", data);
        return this;
    }
}
