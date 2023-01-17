package com.nanfeng.test;

import com.dubbo.invoker.RequestDto;
import com.nanfeng.atomic.TestAtomicService;

public class Test {

    public static void main(String[] args) {
        TestAtomicService atomicService = new TestAtomicService();
        RequestDto requestDto = new RequestDto();
        atomicService.mock(requestDto);
    }
}
