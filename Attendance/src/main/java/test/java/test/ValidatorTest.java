package test.java.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Validator;

class ValidatorTest {

	@Test
	void isValidNameTest() {
		Boolean vnull =  Validator.isValidName(null);
		assertEquals(false, vnull);
		Boolean v0 =  Validator.isValidName("");
		assertEquals(false, v0);
		String s = "";
		for (int i = 0; i < 51; i++) {
			s = s + "あ";
		}
		Boolean v51 = Validator.isValidName(s);
		assertEquals(false, v51);
	}
	
	@Test
	void isValidCompanyNameTest() {
		Boolean vnull =  Validator.isValidCompanyName(null);
		assertEquals(false, vnull);
		Boolean v0 =  Validator.isValidCompanyName("");
		assertEquals(false, v0);
		String s = "";
		for (int i = 0; i < 101; i++) {
			s = s + "あ";
		}
		Boolean v51 = Validator.isValidCompanyName(s);
		assertEquals(false, v51);
	}
	
	@Test
	void isValidPasswordTest() {
		Boolean vnull =  Validator.isValidPassword(null);
		assertEquals(false, vnull);
		Boolean v7 =  Validator.isValidPassword("aid52d5");
		assertEquals(false, v7);
		String s1 = "";
		for (int i = 0; i < 101; i++) {
			s1 = s1 + "a";
		}
		Boolean v101 =  Validator.isValidPassword(s1);
		assertEquals(false, v101);
		String s2 = "あd2546wa4d5a";
		Boolean vzenkaku =  Validator.isValidPassword(s2);
		assertEquals(false, vzenkaku);
	}
	
	@Test
	void isValidCompanyIdTest() {
		Boolean vnull =  Validator.isValidCompanyId(null);
		assertEquals(false, vnull);
		Boolean v7 =  Validator.isValidCompanyId("aid52d5");
		assertEquals(false, v7);
		String s1 = "";
		for (int i = 0; i < 51; i++) {
			s1 = s1 + "a";
		}
		Boolean v101 =  Validator.isValidCompanyId(s1);
		assertEquals(false, v101);
		String s2 = "あd2546wa4d5a";
		Boolean vzenkaku =  Validator.isValidCompanyId(s2);
		assertEquals(false, vzenkaku);
	}

	@Test
	void isValidEmployeeNameTest() {
		Boolean vnull =  Validator.isValidEmployeeName(null);
		assertEquals(false, vnull);
		Boolean v0 =  Validator.isValidEmployeeName("");
		assertEquals(false, v0);
		String s1 = "";
		for (int i = 0; i < 51; i++) {
			s1 = s1 + "a";
		}
		Boolean v51 =  Validator.isValidEmployeeName(s1);
		assertEquals(false, v51);
	}
}
