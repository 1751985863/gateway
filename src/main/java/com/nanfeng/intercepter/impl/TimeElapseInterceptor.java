package com.nanfeng.intercepter.impl;

import com.nanfeng.domain.GatewayContext;
import com.nanfeng.domain.GatewayRequest;
import com.nanfeng.domain.GatewayResponse;
import com.nanfeng.intercepter.GatewayPostInterceptor;
import com.nanfeng.intercepter.GatewayPreInterceptor;

public class TimeElapseInterceptor implements GatewayPreInterceptor, GatewayPostInterceptor {

    private static final String START_TIME_MARK = "START_TIME_MARK";

    public static TimeElapseInterceptor instance = new TimeElapseInterceptor();

    @Override
    public void intercept(GatewayRequest request, GatewayContext context) {
        context.getExtraInfo().put(START_TIME_MARK,System.currentTimeMillis());
    }

    @Override
    public void intercept(GatewayResponse response, GatewayContext context) {
        long endTime = System.currentTimeMillis();
        long startTime = (long) context.getExtraInfo().get(START_TIME_MARK);
        System.out.println("traceId:" + context.getTraceId()+ "description:"+ context.getDescription()+ "cost: "+(endTime -startTime));
    }

    @Override
    public boolean shouldFilter(GatewayContext context) {
        return GatewayPreInterceptor.super.shouldFilter(context);
    }


}
