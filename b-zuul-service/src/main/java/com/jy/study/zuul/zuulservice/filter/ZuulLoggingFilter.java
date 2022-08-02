package com.jy.study.zuul.zuulservice.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


//zuul 게이트웨이 필터
@Slf4j //롬복이 만들어주는 log객체 Logger log = LoggerFactory.getLogger(현재 클래스명.class) 
@Component
public class ZuulLoggingFilter extends ZuulFilter {

    //실제 동작할 메소드 지정
    @Override
    public Object run() throws ZuulException {
        log.info("********** printing logs: ");

        //현재 request 객체를 얻어오는 방법
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info("********** " + request.getRequestURI());

        return null;
    }

    //사전인지, 사후인지 필터 지정
    //pre : 사전  /  post: 사후
    @Override
    public String filterType() {
        return "pre";
    }

    //필터 순서 지정
    @Override
    public int filterOrder() {
        return 1;
    }

    
    //필터 사용 여부 지정
    @Override
    public boolean shouldFilter() {
        return true;
    }
    
    

}
