package com.ilya.art.dto.converters;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.ilya.art.domain.Exhibition;
import com.ilya.art.domain.ExhibitionImages;
import com.ilya.art.domain.User;
import com.ilya.art.dto.ExhibitionAnnounceDto;
import com.ilya.art.dto.ExhibitionEditionDto;
import com.ilya.art.utils.SimpleStringURLEncoderDecoder;

public abstract class ExhibitionDtoEntityConverters {

	static public Exhibition convert(ExhibitionAnnounceDto exhibAnounceDTO, User user, String prefix) {
		Exhibition exib = new Exhibition();
		exib.setDescription(exhibAnounceDTO.getDescription());
		exib.setTitle(exhibAnounceDTO.getTitle());
		exib.setStarts(DateDtoDateJavaConverter.convert(exhibAnounceDTO.getStartDate()));
		exib.setEnds(DateDtoDateJavaConverter.convert(exhibAnounceDTO.getEndDate()));
		exib.setAnnouncedBy(user);

		
		if (!exhibAnounceDTO.getExhiMedia().isEmpty()) {
			for (MultipartFile media : exhibAnounceDTO.getExhiMedia()) {
				String ultPath = Integer.toString(media.hashCode());
				
				StringBuilder sb = new StringBuilder();
				sb.append(SimpleStringURLEncoderDecoder.encode(File.separator + exhibAnounceDTO.getTitle())
						+ File.separator);
				
				int i = 0;

				for (i = 0; i < ultPath.length() - 2; i = i + 2) {
					sb.append(ultPath.substring(i, i + 2));
					sb.append(File.separator);
				}
				

				new File(prefix + sb.toString()).mkdirs();

				String filen = ultPath.substring(i, ultPath.length());
				sb.append(filen);
				exib.getExhibitionImages().add(new ExhibitionImages(sb.toString(), exib));

				try {
					media.transferTo(new File(prefix + sb.toString()));
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}

			}
		}
		return exib;
	}

	static public Exhibition convert(ExhibitionEditionDto exhibEditionDTO, User user, String prefix) {
		Exhibition exhibition = convert((ExhibitionAnnounceDto) exhibEditionDTO, user, prefix);
		for (String st : exhibEditionDTO.getRelativePathToMedia()) {
			exhibition.getExhibitionImages().add(new ExhibitionImages(st, exhibition));
		}
		exhibition.setId(exhibEditionDTO.getId());
		return exhibition;

	}

}
