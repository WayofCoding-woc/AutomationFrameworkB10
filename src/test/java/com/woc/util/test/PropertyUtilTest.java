package com.woc.util.test;

import com.woc.util.PropertyUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PropertyUtilTest {

    @Test
    public void testMessageKey(){
        String message = PropertyUtil.getProperty("message");
        System.out.println("message = " + message);
        Assert.assertNotNull(message);
    }

    @Test
    public void testReqResBaseKey(){
        String reqresBaseUrl = PropertyUtil.getProperty("reqres.base.url");
        System.out.println("reqresBaseUrl = " + reqresBaseUrl);
        Assert.assertNotNull(reqresBaseUrl);
    }

    @Test
    public void testWrongKey(){
        String dbPassword = PropertyUtil.getProperty("db.pwd");
        System.out.println("dbPassword = " + dbPassword);
        Assert.assertNull(dbPassword);
    }


}
