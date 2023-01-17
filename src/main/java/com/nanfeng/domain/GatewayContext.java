package com.nanfeng.domain;


import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class GatewayContext {

    private Object request;

    private Object rowResponse;

    private String traceId;

    private String description;

    private Exception catchedException;

    private boolean reachedRoute;

    private Map<String,Object> extraInfo = new HashMap<>();
}
