package hu.innobye.dummy;

import hu.innobye.InterpreterConnector;
import hu.innobye.InterpreterData;
import hu.innobye.data.Questions;

public class DummyInterpreter implements InterpreterConnector {
	private Questions questions = null;
	
	public DummyInterpreter(String path) {
		questions = Questions.loadFromFile(path);
	}
	
	public InterpreterData interpreterSentence(String sentence)  {
		return questions.getMaxConfidence(sentence);
	}
	
}
