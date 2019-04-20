package co.com.wolox.challengecanas.util;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;

public class ModelMapperUtil {

	@Bean
	private static ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		return modelMapper;
	}
	
	public static <D, T> D map(final T entity, Class<D> outClass) {
		
		//modelMapper().getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper().map(entity, outClass);
	}
	public static <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outCLass) {
		return entityList.stream().map(entity -> map(entity, outCLass)).collect(Collectors.toList());
	}

}
