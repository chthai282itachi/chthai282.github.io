package ch.unibas.medizin.osce.shared;

import java.util.Arrays;
import java.util.List;

public enum MaritalStatus {
	UNMARRIED(0),
	MARRIED(1),
	CIVIL_UNION(2),
	DIVORCED(3),
	SEPARATED(4),
	WIDOWED(5);
	
	
    private final int value;
	
    private MaritalStatus(int value) {
		this.value = value;
    }

	public int getValue() {
		return this.value; 
	}
	
	public String getKey() {
		return name();
	}
	
	public static List<MaritalStatus> list() {
		return Arrays.asList(MaritalStatus.values());
	}
}
