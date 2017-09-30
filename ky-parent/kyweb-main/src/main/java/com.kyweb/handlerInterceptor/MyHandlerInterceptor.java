package com.kyweb.handlerInterceptor;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 * Created by Caomr on 2017/9/12.
 */
@lombok.extern.slf4j.Slf4j
public class MyHandlerInterceptor implements HandlerInterceptor {

    private static String ip;

    /**
     * 打印用户的ip和访问路径
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        generateIp(httpServletRequest);
        String requestUrl = httpServletRequest.getRequestURI();
        String queryString = getRequestString(httpServletRequest);
        String requestSessionId = httpServletRequest.getRequestedSessionId();
        if (!StringUtils.isEmpty(requestSessionId)) {
            log.info("request session id is {}", requestSessionId);
        }
        log.info("remote user ip :{} login in and request : {}", ip, requestUrl + " " + queryString);
        return true;
    }

    /**
     * 产生请求的参数
     *
     * @param httpServletRequest
     * @return
     */
    private String getRequestString(HttpServletRequest httpServletRequest) {
        Map<String, String[]> params = httpServletRequest.getParameterMap();
        String queryString = "";
        if (null != params && params.size() > 0) {
            for (String key : params.keySet()) {
                String[] values = params.get(key);
                for (int i = 0; i < values.length; i++) {
                    String value = values[i];
                    queryString += key + "=" + value + "&";
                }
            }
            // 去掉最后一个空格
            queryString = queryString.substring(0, queryString.length() - 1);
        }
        return queryString;
    }

    /**
     * 得到请求的ip
     *
     * @param httpServletRequest
     */
    private void generateIp(HttpServletRequest httpServletRequest) {
        ip = httpServletRequest.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = httpServletRequest.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = httpServletRequest.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = httpServletRequest.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = httpServletRequest.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = httpServletRequest.getRemoteAddr();
        }
        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            ip = "127.0.0.1";
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        log.info("user ip :{} has finished", ip);
    }
}
