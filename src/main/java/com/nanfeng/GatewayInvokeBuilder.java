package com.nanfeng;

import com.nanfeng.domain.GatewayRouter;
import com.nanfeng.intercepter.GatewayPostInterceptor;
import com.nanfeng.intercepter.GatewayPreInterceptor;

import java.util.LinkedList;

public class GatewayInvokeBuilder<P,R> {

    private LinkedList<GatewayPreInterceptor<P>> preInterceptors = new LinkedList<>();

    private LinkedList<GatewayPostInterceptor<R>> postInterceptors = new LinkedList<>();

    private GatewayRouter<P> gatewayRouter;

    private String description;

    public GatewayInvokeBuilder<P,R> description(String description) {
        this.description = description;
        return this;
    }

    public GatewayInvokeBuilder<P,R> gatewayRouter(GatewayRouter<P> router){
        this.gatewayRouter = router;
        return this;
    }

    public GatewayInvokeBuilder<P,R> addFirst(GatewayPreInterceptor<P> interceptor) {
        this.preInterceptors.addFirst(interceptor);
        return this;
    }

    public GatewayInvokeBuilder<P,R> addLast(GatewayPreInterceptor<P> interceptor) {
        this.preInterceptors.addLast(interceptor);
        return this;
    }

    public GatewayInvokeBuilder<P,R> addLast(GatewayPostInterceptor<R> interceptor) {
        this.postInterceptors.addLast(interceptor);
        return this;
    }

    public GatewayInvokeTemplate<P,R> build() {
        return new GatewayInvokeTemplate<>(preInterceptors,postInterceptors,gatewayRouter,description);
    }

}
