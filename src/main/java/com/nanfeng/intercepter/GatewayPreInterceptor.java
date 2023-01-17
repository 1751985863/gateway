package com.nanfeng.intercepter;

import com.nanfeng.domain.GatewayContext;
import com.nanfeng.domain.GatewayRequest;
import com.nanfeng.domain.GatewayResponse;

public interface GatewayPreInterceptor<T> {

    void intercept(GatewayResponse<T> request, GatewayContext context);

    default boolean shouldFilter(GatewayContext context) {
        return true;
    }

    void intercept(GatewayRequest<T> request, GatewayContext context);
}
