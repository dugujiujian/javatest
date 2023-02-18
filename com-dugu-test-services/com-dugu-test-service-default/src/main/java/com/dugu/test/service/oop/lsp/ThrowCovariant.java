package com.dugu.test.service.oop.lsp;

import java.io.IOException;

/**
 * @author cihun
 * @date 2022-08-31 6:42 下午
 */
public class ThrowCovariant {

    class T {
        void b( ) throws Throwable {}
    }

    class S extends T {
        @Override
        void b( ) throws IOException {}
    }

    class U extends S {
        @Override
        void b() {
        }
    }
}
