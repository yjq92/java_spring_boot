package com.hqyj.javaSpringBoot.filter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

@WebFilter(filterName ="requestParamFilter",urlPatterns = "/**")
public class RequestParamFilter implements Filter {
    private final static Logger LOGGER = LoggerFactory.getLogger(RequestParamFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.debug("====Init Request Param Filter ======");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        LOGGER.debug("====Do Filter Request Param Filter ======");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        //        Map<String, String[]> paramsMap = httpRequest.getParameterMap();
        //        paramsMap.put("paramKey", new String[]{"***"});  paramMap属性是locked,禁止修改，不能进行值的替换
        HttpServletRequestWrapper wrapper=new HttpServletRequestWrapper(httpServletRequest){

            @Override
            public String getParameter(String name) {
                String value=httpServletRequest.getParameter(name);
                if(StringUtils.isNotBlank(value)){
                    return value.replaceAll("fuck","***");
                }
                return super.getParameter(name);
            }
            @Override
            public String[] getParameterValues(String name) {
                String[] values=httpServletRequest.getParameterValues(name);
                if(values!=null&&values.length>0){
                    for (int i = 0; i <values.length ; i++) {
                        values[i]=values[i].replaceAll("fuck","***");
                    }
                    return values;
                }
                return super.getParameterValues(name);
            }
        };
        filterChain.doFilter(wrapper,response);
    }

    @Override
    public void destroy() {
        LOGGER.debug("==== Destroy Filter Request Param Filter ======");
    }
}
