package com.study.xuan.xvolleyutil.build;

import java.util.Map;

/**
 * Author : xuan.
 * Data : 2017/8/15.
 * Description :input the description of this file.
 */

public interface ContainParams {
    RequestBuilder params(Map<String, String> params);

    RequestBuilder addParams(String key, String val);
}
