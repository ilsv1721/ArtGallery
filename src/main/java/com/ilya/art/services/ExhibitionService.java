package com.ilya.art.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ilya.art.config.utils.LocalStorageProps;
import com.ilya.art.domain.Exhibition;
import com.ilya.art.domain.Painting;
import com.ilya.art.dto.ExhibitionAnnounceDto;
import com.ilya.art.dto.ExhibitionEditionDto;
import com.ilya.art.dto.converters.DateDtoDateJavaConverter;
import com.ilya.art.repositories.interfaces.ExhibitionDao;
import com.ilya.art.repositories.interfaces.UserDAO;
import com.ilya.art.utils.SimpleStringURLEncoderDecoder;
import com.ilya.art.utils.files.HashCodePathFileAssistant;
import com.ilya.art.utils.files.PathAndFileAssistant;
import com.ilya.art.utils.web.UrlEntityFieldAssistantMatcher;

@Service
@Transactional
public class ExhibitionService implements com.ilya.art.services.interfaces.ExhibitionService {

	PathAndFileAssistant<MultipartFile> pathAndFileAssistant = new HashCodePathFileAssistant(2);

	@Autowired
	LocalStorageProps localStorageProps;

	@Autowired
	ExhibitionDao exDao;

	@Autowired
	UserDAO userDao;

	public void persist(Exhibition entity) {
		exDao.persist(entity);
	}

	public void remove(Exhibition entity) {
		exDao.remove(entity);
	}

	public Exhibition findById(Long id) {
		return exDao.findById(id);
	}

	public void merge(Exhibition entity) {
		exDao.merge(entity);
	}

	public Exhibition findExhibition(String title) {
		return exDao.findExhibition(title);
	}

	public List<Exhibition> findAll() {
		return exDao.findAll();
	}

	@Override
	public List<UrlEntityFieldAssistantMatcher> getUrlEntityFieldAssistantMatchers() {
		List<UrlEntityFieldAssistantMatcher> list = new ArrayList<>();
		findAll().forEach((exhibition) -> {
			list.add(new UrlEntityFieldAssistantMatcher(exhibition.getTitle()));
		});
		return list;
	}

	@Override
	public void anounceNewExhibition(ExhibitionAnnounceDto exhibAnounceDTO) {

		Exhibition exib = new Exhibition();
		exib.setDescription(exhibAnounceDTO.getDescription());
		exib.setTitle(exhibAnounceDTO.getTitle());
		exib.setStarts(DateDtoDateJavaConverter.convert(exhibAnounceDTO.getStartDate()));
		exib.setEnds(DateDtoDateJavaConverter.convert(exhibAnounceDTO.getEndDate()));
		exib.setAnnouncedBy(userDao.findByEmail(exhibAnounceDTO.getEmailAnouncer()));
		String prefix = localStorageProps.getLocalStoragePathExhibs() + File.separator + exhibAnounceDTO.getTitle()
				+ File.separator;
		for (MultipartFile resource : exhibAnounceDTO.getExhiMedia()) {
			try {
				com.ilya.art.utils.files.Path path = pathAndFileAssistant.getPath(resource);
				pathAndFileAssistant.saveToFile(prefix, path, resource);
				exib.getPaintings().add(new Painting(path.getPathToFile() + path.getFilename()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		exDao.persist(exib);
	}

	@Override
	public void editExhibition(ExhibitionEditionDto exhibitionDto) {

	}

	@Override
	public ExhibitionEditionDto getExhibitionEditionDto(String title) {
		return new ExhibitionEditionDto(this.findExhibition(title));

	}

	@Override
	public ExhibitionEditionDto getExhibitionEditionDto(Long id) {
		return new ExhibitionEditionDto(this.findById(id));
	}

	@Override
	public void deleteExhibition(Long id) {
		Exhibition exhib = exDao.findById(id);
		Path directory = Paths.get(localStorageProps.getLocalStoragePathExhibs() + File.separator
				+ SimpleStringURLEncoderDecoder.decode(exhib.getTitle()));
		try {
			Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					Files.delete(file);
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
					Files.delete(dir);
					return FileVisitResult.CONTINUE;
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		exDao.remove(exhib);

	}

}
