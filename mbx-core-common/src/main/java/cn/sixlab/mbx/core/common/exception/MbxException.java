/**
 * Copyright (c) 2018 Sixlab. All rights reserved.
 * <p>
 * License information see the LICENSE file in the project's root directory.
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2018-04-20 17:01
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.mbx.core.common.exception;

public class MbxException extends RuntimeException {

    public MbxException() {
        super();
    }

    public MbxException(String message) {
        super(message);
    }

    public MbxException(Throwable cause) {
        super(cause);
    }

    public MbxException(String message, Throwable cause) {
        super(message, cause);
    }

}
