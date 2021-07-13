package com.tyler.mybatis3.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestInvokerHandler implements InvocationHandler {

  //真正的业务对象
  private Object target;

  public TestInvokerHandler(Object target) {
    this.target = target;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    //在执行业务方法之前的预处理
    Object result = method.invoke(target, args);
    //在执行业务方法之后的后置处理
    return result;
  }

  public Object getProxy() {
    //创建代理对象
    return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), target.getClass().getInterfaces(), this);
  }
}
