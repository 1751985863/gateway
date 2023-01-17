package com.nanfeng.invoker;

import com.dubbo.invoker.RequestDto;
import com.dubbo.invoker.ResponseDto;
import com.dubbo.invoker.TestDubboInvoker;
import com.nanfeng.GatewayInvokeBuilder;
import com.nanfeng.domain.GatewayRequest;
import com.nanfeng.domain.GatewayResponse;
import com.nanfeng.intercepter.impl.parse.TestParseInterceptor;

public class TestInvoker {

    private TestDubboInvoker testDubboInvoker = new TestDubboInvoker();

    public GatewayResponse<ResponseDto> mock(GatewayRequest<RequestDto> req) {
        return new GatewayInvokeBuilder<RequestDto,ResponseDto>()
                .description("模拟测试： 首次使用gateway框架")
                .gatewayRouter(request -> testDubboInvoker.mock(request))
                .addLast(new TestParseInterceptor<>())
                .build().invoke(req);
    }
}
