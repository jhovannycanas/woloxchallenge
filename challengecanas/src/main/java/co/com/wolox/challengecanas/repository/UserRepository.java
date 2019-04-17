package co.com.wolox.challengecanas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.com.wolox.challengecanas.entity.AccessLevel;
import co.com.wolox.challengecanas.entity.User;


public interface UserRepository extends JpaRepository<User, Long>{

	@Query("SELECT T.user FROM AlbumUser T LEFT JOIN T.album G WHERE G.restId=:albumId "
			+ "AND T.accesLevel=:accessLevelId")
	public List<User> findUserByAlbumAndAccess(@Param("albumId") Long albumId, 
			@Param("accessLevelId") AccessLevel accessLevel);
}
