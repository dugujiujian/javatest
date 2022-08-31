package com.dugu.test.service.oop.ext;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author cihun
 * @date 2022-08-31 10:05 上午
 */
public class AnimalTest extends TestCase {


    @Test
    public void testOrderExec(){
       Puru puru=new Puru();
       puru.puru();
       puru.eat();
    }

}