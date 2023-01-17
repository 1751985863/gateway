package com.nanfeng.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class GatewayResponse<T> implements Serializable {

    private GatewayResponseStatus status;

    private String failCode;

    private String failMsg;

    private T result;
}
