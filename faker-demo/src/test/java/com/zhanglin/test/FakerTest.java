package com.zhanglin.test;

import java.util.Locale;

import org.junit.Test;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;

public class FakerTest {

    
    @Test
    public void testFaker(){
    	Faker chinese = new Faker(Locale.CHINA);
    	for (int i = 0; i < 100; i++) {
    		Name name = chinese.name();
			/*System.out.println(name.fullName());
			System.out.println(name.name());
			System.out.println(name.nameWithMiddle());*/
    		System.out.println("张"+name.firstName());
    		
			System.out.println("--------------");
		}
    	
    	
    	
    	
    }
    
}