package com.zhai.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalance {
    /**
     * 收集服务器上所有的服务
     */
    ServiceInstance instance(List<ServiceInstance> serviceInstances);
}
