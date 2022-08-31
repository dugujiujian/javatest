package com.dugu.test.service.oop.lsp;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author cihun
 * @date 2022-08-31 12:47 下午
 */
public class SonTest extends TestCase {

    @Test
    public void testLSP(){
        Son son=new Son();
        son.map(new ConcurrentHashMap());
        son.map(new HashMap());
    }
}