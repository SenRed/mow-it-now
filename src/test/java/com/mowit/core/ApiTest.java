package com.mowit.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ApiTest {
    @Test
    void test_dependency() {
        assertThat("foo").isEqualTo("foo");
    }

}
