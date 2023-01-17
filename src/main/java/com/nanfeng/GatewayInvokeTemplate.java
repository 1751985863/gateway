package com.nanfeng;

import com.nanfeng.domain.GatewayContext;
import com.nanfeng.domain.GatewayRequest;
import com.nanfeng.domain.GatewayResponse;
import com.nanfeng.domain.GatewayRouter;
import com.nanfeng.intercepter.GatewayPostInterceptor;
import com.nanfeng.intercepter.GatewayPreInterceptor;

import java.util.LinkedList;
import java.util.Objects;

public class GatewayInvokeTemplate<P,R> {

    private LinkedList<GatewayPreInterceptor<P>> preInterceptors;

    private LinkedList<GatewayPostInterceptor<R>> postInterceptors;

    private GatewayRouter<P> gatewayRouter;

    private String description;

    public GatewayInvokeTemplate(LinkedList<GatewayPreInterceptor<P>> preInterceptors, LinkedList<GatewayPostInterceptor<R>> postInterceptors, GatewayRouter<P> gatewayRouter, String description) {
        this.preInterceptors = preInterceptors;
        this.postInterceptors = postInterceptors;
        this.gatewayRouter = gatewayRouter;
        this.description = description;
    }
    public GatewayResponse<R> invoke(GatewayRequest<P> request) {
        GatewayContext context = new GatewayContext();
        context.setDescription(description);
        context.setTraceId(request.getTraceId());
        context.setRequest(request.getParam());
        GatewayResponse<R> response = new GatewayResponse<>();
        try {
            doPre(request,context);
        } catch (Exception e) {
            context.setCatchedException(e);
            context.setReachedRoute(false);
            doPost(response,context);
            return response;
        }
        context.setReachedRoute(true);
        try {
            Object result = gatewayRouter.execute(request.getParam());
            context.setRowResponse(result);
        } catch (Exception e) {
            context.setCatchedException(e);
        } finally {
            doPost(response,context);
        }
        return response;
    }
    private void doPre(GatewayRequest<P> request,GatewayContext context) {
        for (GatewayPreInterceptor<P> preInterceptor : preInterceptors) {
            preInterceptor.intercept(request,context);
        }
    }

    private void doPost(GatewayResponse<R> response,GatewayContext context) {
        for (GatewayPostInterceptor<R> postInterceptor : postInterceptors) {
            try {
                if (postInterceptor.shouldFilter(context)) {
                    postInterceptor.intercept(response,context);
                }
            } catch (Exception e) {
                if (context.getCatchedException() == null) {
                    context.setCatchedException(e);
                } else {
                    System.out.println("duplicate catch exception");
                }
            }
        }
    }
}
