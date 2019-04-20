package co.com.wolox.challengecanas.entity;

public enum AccessLevel {

	READ(1L), WRITE(2L), READ_WRITE(3L);
	
	private Long id;

	 AccessLevel(Long id) {
		this.id = id;
	}

	 public static AccessLevel getById(Long id) {
		    for(AccessLevel accessLevel : values()) {
		        if(accessLevel.id.equals(id)) {
		        	return accessLevel;
		        }
		    }
		    return null;
		}
	
	
}
