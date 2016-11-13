package hu.innobye;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class InterpreterData {
	private String id;
	private int confidence;
	
	public static InterpreterData createEmptyinstance() {
		return InterpreterData.builder()
				.id("")
				.confidence(0)
				.build();
	}
}
