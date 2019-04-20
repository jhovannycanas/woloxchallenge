package co.com.wolox.challengecanas.service;

import java.util.List;
import co.com.wolox.challengecanas.dto.User;


public interface UserService {

	public List<User> findUserByAccessLevelByAlbum(Long albumId, Long accesLevelId);
}
