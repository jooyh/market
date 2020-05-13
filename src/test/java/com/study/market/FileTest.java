package com.study.market;

import java.io.File;

import org.junit.Test;

public class FileTest {

	@Test
	public void FileDirTest() {
		if(new File("C:/Users/jyh/Desktop/test/t1/t2/t3/t4/t5").exists()) {
			System.out.println("TRUE");
		}else{
			System.out.println("FALSE");
		};
	}

}
