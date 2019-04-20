package co.com.wolox.challengecanas.service;

import java.util.List;

import org.apache.camel.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.wolox.challengecanas.dto.User;
import co.com.wolox.challengecanas.entity.AccessLevel;
import co.com.wolox.challengecanas.repository.UserRepository;
import co.com.wolox.challengecanas.util.ModelMapperUtil;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> findUserByAccessLevelByAlbum(@Header("albumid")Long albumId, @Header("accesid") Long accesLevelId) {
		AccessLevel accessLevel = AccessLevel.getById(accesLevelId);
		return ModelMapperUtil.mapAll(userRepository.findUserByAlbumAndAccess(albumId, accessLevel),User.class);
	}
	


}
