package hu.innobye.dummy;

import static org.junit.Assert.*;

import org.junit.Test;

import hu.innobye.InterpreterData;

public class DummyInterpreterTest {

	@Test
	public void test() {
		String path = System.getProperty("user.dir") + "\\files\\questions.xml";

		DummyInterpreter testObj = new DummyInterpreter(path);
		InterpreterData interpreterData = testObj.interpreterSentence("Mennyi a számlám egyenlege?");
		
		assertEquals(interpreterData.getId(),"SZAMLAEGYENLEG");
	}
}
