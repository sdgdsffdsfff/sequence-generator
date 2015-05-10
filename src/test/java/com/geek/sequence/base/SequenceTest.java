package com.geek.sequence.base;

import javax.annotation.Resource;

import org.junit.Test;

import com.geek.sequence.Sequence;

public class SequenceTest extends AbstractTest {
    @Resource(name = "test1")
    private Sequence test1;
    @Resource(name = "test2")
    private Sequence test2;

    @Test
    public void test() {
        System.out.println("test1: " + test1.nextValue());
        System.out.println("test1: " + test1.nextValue());
        System.out.println("test2: " + test2.nextValue());
    }
}
