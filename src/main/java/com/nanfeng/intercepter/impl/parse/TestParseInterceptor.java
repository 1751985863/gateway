package com.nanfeng.intercepter.impl.parse;

import com.dubbo.invoker.ResponseDto;
import com.nanfeng.domain.GatewayContext;
import com.nanfeng.domain.GatewayResponse;
import com.nanfeng.domain.GatewayResponseStatus;
import com.nanfeng.intercepter.GatewayPostInterceptor;

public class TestParseInterceptor<T> implements GatewayPostInterceptor<T> {


    @Override
    public void intercept(GatewayResponse<T> response, GatewayContext context) {
        ResponseDto result = (ResponseDto)context.getRowResponse();
        response.setStatus(GatewayResponseStatus.S);
        response.setResult((T)result);

    }

    @Override
    public boolean shouldFilter(GatewayContext context) {
        return context.getCatchedException() == null;
    }
}
