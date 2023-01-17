package com.nanfeng.domain;

public enum GatewayResponseStatus {
    S,
    F,
    P,
    CM;
    public static boolean isSuccess(GatewayResponse gatewayResponse) {
        return S == gatewayResponse.getStatus();
    }

    public static boolean ifFinish(GatewayResponse gatewayResponse) {
        return S == gatewayResponse.getStatus() || F == gatewayResponse.getStatus();
    }
}
