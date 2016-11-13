package hu.innobye.data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import hu.innobye.InterpreterData;
import hu.innobyte.checker.CheckerFactory;
import hu.innobyte.checker.checkers.CheckerInitData;
import hu.innobyte.checker.checkers.CheckerType;
import hu.innobyte.interpreter.SentenceInterpreter;

@XmlRootElement(name = "questions")
public class Questions {
	@XmlElement(name="question")
	public List<Question> questions = new ArrayList<Question>();
	
    public static Questions loadFromFile(String path) {
    	Questions questions = null;
    	
    	try {
    		JAXBContext jaxbContext = JAXBContext.newInstance(Questions.class);
    		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    		
    		questions = (Questions) jaxbUnmarshaller.unmarshal(new File(path));
    		
    	} catch (Exception e) {
    		System.err.println(String.format("Error in XML load, exception: %s, message: %s",e.getClass(),e.getMessage()));
    	}
    	
    	return questions;
    }

	public void addQuestion(Question newQuestion) {
		for (Question question : questions) {
		    if (question.getQuestion().equalsIgnoreCase(newQuestion.getQuestion())) {
		    	//throw new DialogException();
		    }
		}
	
		questions.add(newQuestion);
	}
	
    public void saveIntoXML(String path) {
    	try {
	    	JAXBContext context = JAXBContext.newInstance(Questions.class);
	    	Marshaller marshaller = context.createMarshaller();
	    	marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,  Boolean.TRUE);
	    	marshaller.marshal(this, new File(path));
    	} catch (Exception e) {
    		System.err.println(String.format("Error in save XML, exception: %s, message: %s, path: %s", e.getClass(),e.getMessage(),path));
    	}
    	
    }	
    
    public InterpreterData getMaxConfidence(String sentence) {
		SentenceInterpreter sentenceInterpreter = SentenceInterpreter.builder()
				.checker(CheckerFactory.createInstance(CheckerType.StringComparator, CheckerInitData.builder().build()))
				.build();
		
		InterpreterData interpreterData = InterpreterData.createEmptyinstance();
		
		for(Question question : questions) {
			float confidence = sentenceInterpreter.checkTwoSentence(sentence, question.getQuestion());
			
			if (interpreterData.getConfidence() < confidence) {
				interpreterData.setConfidence((int) (confidence * 100));
				interpreterData.setId(question.getId());
			}
		}
    	
    	return interpreterData;
    }
}
