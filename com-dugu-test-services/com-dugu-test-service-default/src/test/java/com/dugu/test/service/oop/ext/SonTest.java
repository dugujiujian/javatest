package com.dugu.test.service.oop.ext;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author cihun
 * @date 2022-08-31 9:52 上午
 */
public class SonTest extends TestCase {

    @Test
    public void testResetAge(){
        new Son().resetAge();
    }
}