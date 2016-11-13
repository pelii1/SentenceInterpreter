package hu.innobye.data;


import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Getter
@Setter
@Builder
public class Question {
	private String id;
	private String question;
	
	@Tolerate
	public Question() {
		
	}

}
