package com.mindtree.simplecal;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
public class SimpleCalTest {
	

	private simplecal objCalcUnderTest;
	
	@Before
	public void setUp()
	{
		//Arrange
		objCalcUnderTest = new simplecal();
	}
	@Test
	public void testAdd()
	{
		int a=10;
		int b= 20;
		int expectedResult = 30;
		//Act
		long result = simplecal.add(a,b);
		
		//Assert
		Assert.assertEquals(expectedResult, result);
	}
	@Test
	public void testSubtract() {
		int a = 25, b= 20;
		int expectedResult = 5;
		long result = simplecal.subtract(a,b);
		Assert.assertEquals(expectedResult, result);
	}
}
