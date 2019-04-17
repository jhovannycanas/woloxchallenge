package co.com.wolox.challengecanas.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "albumuser", uniqueConstraints = {@UniqueConstraint(columnNames = {"album","user"})})
public class AlbumUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "album" , referencedColumnName="restIdAlbum" ,  nullable=false)
	private Album album;
	
	@ManyToOne
	@JoinColumn(name = "user" , referencedColumnName="restIdUser" ,nullable=false)
	private User user;
	
	@Enumerated(EnumType.STRING)
	private AccessLevel accesLevel;

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public AccessLevel getAccesLevel() {
		return accesLevel;
	}

	public void setAccesLevel(AccessLevel accesLevel) {
		this.accesLevel = accesLevel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AlbumUser(Album album, User user, AccessLevel accesLevel) {
		super();
		this.album = album;
		this.user = user;
		this.accesLevel = accesLevel;
	}
	
}
