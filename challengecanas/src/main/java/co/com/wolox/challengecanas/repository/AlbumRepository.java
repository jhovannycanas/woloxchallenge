package co.com.wolox.challengecanas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.wolox.challengecanas.entity.Album;

public interface AlbumRepository extends JpaRepository<Album, Long>{

	public Album findByRestId(Long restid);
	
}
