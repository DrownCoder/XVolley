package com.study.xuan.xvolleyutil.build;

import java.util.Map;

/**
 * Author : xuan.
 * Data : 2017/8/15.
 * Description :input the description of this file.
 */

public interface ContainParams {
    RequestBuilder params(Map<String, String> params);

    RequestBuilder addParam(String key, String val);

    RequestBuilder addParams(Map<String, String> params);
}
