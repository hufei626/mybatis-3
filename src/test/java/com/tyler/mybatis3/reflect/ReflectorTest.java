package com.tyler.mybatis3.reflect;

import org.apache.ibatis.reflection.Reflector;
import org.junit.Test;

public class ReflectorTest {

  @Test
  public void test() {
    new Reflector(User.class);
  }
}
