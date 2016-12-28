package com.dataart.qa.workshop.test;


import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;

public class ExceptionTest {

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_exception(){
        new ArrayList<String>().get(0);
    }

    @Test
    public void test_exception_message(){
        try {
            new ArrayList<String>().get(0);
        } catch (IndexOutOfBoundsException indexOutOfBoundsException){
            Assert.assertThat(indexOutOfBoundsException.getMessage(), is("Index: 0, Size: 0"));
            Assert.fail(indexOutOfBoundsException.getMessage());
        }
    }
}

