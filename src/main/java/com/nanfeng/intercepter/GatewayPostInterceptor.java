package com.nanfeng.intercepter;

import com.nanfeng.domain.GatewayContext;
import com.nanfeng.domain.GatewayResponse;

public interface GatewayPostInterceptor<T> {

    void intercept(GatewayResponse<T> response, GatewayContext context);

    default boolean shouldFilter(GatewayContext context) {
        return true;
    }
}
