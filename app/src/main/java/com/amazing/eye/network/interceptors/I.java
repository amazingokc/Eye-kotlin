package com.amazing.eye.network.interceptors;


import java.util.logging.Level;

import okhttp3.internal.platform.Platform;

/**
 * Create by: xiaoguoqing
 * Date: 2018/12/19 0019
 * description:
 */
class I {

    protected I() {
        throw new UnsupportedOperationException();
    }

    static void log(int type, String tag, String msg) {
        java.util.logging.Logger logger = java.util.logging.Logger.getLogger(tag);
        switch (type) {
            case Platform.INFO:
                logger.log(Level.INFO, msg);
                break;
            default:
                logger.log(Level.WARNING, msg);
                break;
        }
    }
}
