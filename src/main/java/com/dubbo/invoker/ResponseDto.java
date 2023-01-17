package com.dubbo.invoker;

import lombok.Data;

@Data
public class ResponseDto {

    private String status;

    private String code;

    private String msg;
}
