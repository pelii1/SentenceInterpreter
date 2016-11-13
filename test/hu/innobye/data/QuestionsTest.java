package hu.innobye.data;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class QuestionsTest {

	@Test
	public void test() {
		String filePath = new File(".").getAbsolutePath() + "\\files\\questions.xml";
		
		File file = new File(filePath);
		if (file.isFile()) {
			file.delete();
		}

		Questions testObj = new Questions();
		
		testObj.addQuestion(Question.builder()
				.id("SZAMLAEGYENLEG")
				.question("Mennyi a számlám egyenlege?")
				.build());
		
		testObj.addQuestion(Question.builder()
				.id("SZAMLEKERDEZES")
				.question("Mi a telefonszámom?")
				.build());
		
		testObj.addQuestion(Question.builder()
				.id("SZOLGALTATASLEKERDEZES")
				.question("Milyen szolgáltatásaim vannak?")
				.build());
		
		assertEquals(testObj.questions.size(),3);
		
		testObj.saveIntoXML(filePath);
	}

}
