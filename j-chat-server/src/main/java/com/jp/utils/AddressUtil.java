package com.jp.utils;

import com.alibaba.fastjson.JSONObject;
import com.jp.constants.Constant;
import io.micrometer.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author JP
 * @Date 2024/11/1 9:37
 */
public class AddressUtil {
    private static final Logger log = LoggerFactory.getLogger(AddressUtil.class);

    // IP地址查询
    public static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp";

    // 未知地址
    public static final String UNKNOWN = "未知";

    public static String getRealAddressByIP(String ip) {
        // 内网不查询
        if (IpUtil.internalIp(ip)) {
            return "内网IP";
        }
        //ture：获取ip地址开关
        try {
            String rspStr = HttpUtil.sendGet(IP_URL, "ip=" + ip + "&json=true", Constant.GBK);
            if (StringUtils.isEmpty(rspStr)) {
                log.error("获取地理位置异常 {}", ip);
                return UNKNOWN;
            }
            JSONObject obj = JSONObject.parseObject(rspStr);
            String region = obj.getString("pro");
            String city = obj.getString("city");
            return String.format("%s %s", region, city);
        } catch (Exception e) {
            log.error("获取地理位置异常 {}", ip);
        }
        return UNKNOWN;
    }
}
