package co.com.wolox.challengecanas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "album")
public class Album {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	
	@Column(name = "restIdAlbum")
	private Long restId;
	
	@Column(name = "userId")
	private Long ownerRestId;
	
	@Column(name = "title")
	private String title;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getRestId() {
		return restId;
	}

	public void setRestId(Long restId) {
		this.restId = restId;
	}

	public Long getOwnerRestId() {
		return ownerRestId;
	}

	public void setOwnerRestId(Long ownerRestId) {
		this.ownerRestId = ownerRestId;
	}
}
