package com.nanfeng.atomic;

import cn.hutool.core.lang.Snowflake;
import com.dubbo.invoker.RequestDto;
import com.dubbo.invoker.ResponseDto;
import com.nanfeng.domain.GatewayRequest;
import com.nanfeng.domain.GatewayResponse;
import com.nanfeng.invoker.TestInvoker;

public class TestAtomicService {

    Snowflake snowflake = new Snowflake();

    private TestInvoker testInvoker = new TestInvoker();

    public ResponseDto mock(RequestDto req) {
        GatewayResponse<ResponseDto> response = testInvoker.mock(makeRequest(req));
        return response.getResult();
    }

    private <T>GatewayRequest<T> makeRequest(T param) {
        GatewayRequest<T> gatewayRequest = new GatewayRequest<>();
        gatewayRequest.setTraceId(snowflake.nextIdStr());
        gatewayRequest.setParam(param);
        return gatewayRequest;
    }
}
