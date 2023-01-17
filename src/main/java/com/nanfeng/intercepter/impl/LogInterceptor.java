package com.nanfeng.intercepter.impl;

import cn.hutool.json.JSONUtil;
import com.nanfeng.domain.GatewayContext;
import com.nanfeng.domain.GatewayRequest;
import com.nanfeng.domain.GatewayResponse;
import com.nanfeng.intercepter.GatewayPostInterceptor;
import com.nanfeng.intercepter.GatewayPreInterceptor;

public class LogInterceptor implements GatewayPostInterceptor, GatewayPreInterceptor {
    @Override
    public void intercept(GatewayResponse response, GatewayContext context) {
        System.out.println("traceId " + context.getTraceId() + "description " +context.getDescription()+" output" + JSONUtil.toJsonStr(context.getRowResponse()));
    }

    @Override
    public boolean shouldFilter(GatewayContext context) {
        return true;
    }

    @Override
    public void intercept(GatewayRequest request, GatewayContext context) {
        System.out.println("traceId " + context.getTraceId() + "description " +context.getDescription()+" input" + JSONUtil.toJsonStr(request.getParam()));

    }
}
