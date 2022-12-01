package kr.gi.mybootboard.files;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FilesService {

	Files insertFiles(MultipartFile multifiles) throws IOException;
}
