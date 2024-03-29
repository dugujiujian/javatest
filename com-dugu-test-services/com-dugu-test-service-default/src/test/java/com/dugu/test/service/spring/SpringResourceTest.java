package com.dugu.test.service.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.annotation.Resource;
import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @Author cihun
 * @Date 2022-04-04 4:58 下午
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        loader = AnnotationConfigContextLoader.class,
        classes = SpringResource.class)
public class SpringResourceTest {

    @Resource
    @Qualifier("defaultFile")
    private File dependency1;

    @Resource
    @Qualifier("namedFile")
    private File dependency2;

    @Test
    public void givenResourceAnnotation_WhenField_ThenDependency1Valid() {
        assertNotNull(dependency1);
        assertEquals("defaultFile.txt", dependency1.getName());
    }

    @Test
    public void givenResourceQualifier_WhenField_ThenDependency2Valid() {
        assertNotNull(dependency2);
        assertEquals("namedFile.txt", dependency2.getName());
    }
}