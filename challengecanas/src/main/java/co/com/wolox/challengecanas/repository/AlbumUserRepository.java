package co.com.wolox.challengecanas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.wolox.challengecanas.entity.AlbumUser;

public interface AlbumUserRepository  extends JpaRepository<AlbumUser, Long>{

	public AlbumUser findByAlbum_restIdAndUser_restId(Long albumId, Long userId);
	
}
