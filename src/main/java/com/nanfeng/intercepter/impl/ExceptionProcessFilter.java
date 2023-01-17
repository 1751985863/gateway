package com.nanfeng.intercepter.impl;

import com.nanfeng.domain.GatewayContext;
import com.nanfeng.domain.GatewayResponse;
import com.nanfeng.domain.GatewayResponseStatus;
import com.nanfeng.intercepter.GatewayPostInterceptor;

public class ExceptionProcessFilter implements GatewayPostInterceptor {
    @Override
    public void intercept(GatewayResponse response, GatewayContext context) {
        System.out.println("gateway traceId :"+context.getTraceId()+""+"description :"+context.getDescription());
        if (context.isReachedRoute()) {
            response.setStatus(GatewayResponseStatus.P);
        } else {
            response.setStatus(GatewayResponseStatus.F);
        }
        response.setFailCode("99999999");

    }

    @Override
    public boolean shouldFilter(GatewayContext context) {
        return GatewayPostInterceptor.super.shouldFilter(context);
    }
}
