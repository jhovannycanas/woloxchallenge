package co.com.wolox.challengecanas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.wolox.challengecanas.entity.AccessLevel;
import co.com.wolox.challengecanas.entity.AlbumUser;
import co.com.wolox.challengecanas.repository.AlbumRepository;
import co.com.wolox.challengecanas.repository.AlbumUserRepository;
import co.com.wolox.challengecanas.repository.UserRepository;

@Service
public class AlbumUserImpl implements AlbumUserService {

	@Autowired
	AlbumUserRepository albumUserRepository;
	
	@Autowired
	AlbumRepository albumRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public AlbumUser saveAlbumUser(AlbumUser albumUser) {
		
		
		if (albumUserRepository.findByAlbum_restIdAndUser_restId(albumUser.getAlbum().getRestId(),
				albumUser.getUser().getRestId()) == null && 
				!albumUser.getAlbum().getOwnerRestId().equals(albumUser.getUser().getRestId())) {
			if (albumRepository.findByRestId(albumUser.getAlbum().getRestId()) == null) {
				albumRepository.save(albumUser.getAlbum());

			} else {
				albumUser.setAlbum(albumRepository.findByRestId(albumUser.getAlbum().getRestId()));

			}

			if (userRepository.findByRestId(albumUser.getUser().getRestId()) == null) {
				userRepository.save(albumUser.getUser());

			} else {
				albumUser.setUser(userRepository.findByRestId(albumUser.getAlbum().getRestId()));

			}

			return albumUserRepository.save(albumUser);
		}
		return albumUserRepository.findByAlbum_restIdAndUser_restId(albumUser.getAlbum().getRestId(),
				albumUser.getUser().getRestId());
	}

	@Override
	public void updateAccessLevelAlbumUser(co.com.wolox.challengecanas.dto.AlbumUser albumUser) throws Exception {
		
		AlbumUser albumUserDb = albumUserRepository.findByAlbum_restIdAndUser_restId(albumUser.getAlbumId(),
				albumUser.getUserId());
		
		if (albumUserDb != null) {
			AccessLevel accessLevel = AccessLevel.getById(albumUser.getAccesLevel());
			
			if (accessLevel == null) {
				throw new Exception("Nivel de acceso no encontrado");
			}
			else {
				albumUserDb.setAccesLevel(accessLevel);
				albumUserRepository.save(albumUserDb);

			}
			
		}
		
	}
}
