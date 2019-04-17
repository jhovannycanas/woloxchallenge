package co.com.wolox.challengecanas.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude()
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect
@Getter
@Setter
@NoArgsConstructor
public class Album {

	@JsonProperty("id")
	private Long restId;
	
	@JsonProperty("userId")
	private Long ownerRestId;
	
	@JsonProperty("title")
	private String title;

}
