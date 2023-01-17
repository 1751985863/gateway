package com.nanfeng.domain;

@FunctionalInterface
public interface GatewayRouter<P> {

    Object execute(P param);
}
