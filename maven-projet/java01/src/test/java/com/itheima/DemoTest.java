package com.itheima;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author: wuguangyuan
 * @create-date: 2022/9/3 14:11
 */
public class DemoTest {
    @Test
    public void testSay(){
        Demo d = new Demo();
        String ret = d.say("maven");
        Assert.assertEquals("hello maven", ret);
    }
}
