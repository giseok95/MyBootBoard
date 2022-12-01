package kr.gi.mybootboard.files;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FilesSerivceImpl  implements FilesService{

   @Autowired
   FilesRepository filesRepo;
   
   @Value("${file.dir}")
   private String fileDir;
   
   
   public Files insertFiles(MultipartFile multifiles) throws IOException {
      
      //원래 파일 이름 추춘5
      String origName= multifiles.getOriginalFilename();
      
      //파일이름으로 쓸 uuid 생성
      String uuid=UUID.randomUUID().toString();
      
      //확장자 추출(ex : .png)
      String extension= origName.substring(origName.lastIndexOf("."));
      
      //uuid와 확장자 결홤
      String saveName= uuid + extension;
      
      //파일을 불러올 때 사용할 파일 경로
      String savePath= fileDir+saveName;
      
      //파일 엔티티 생성
      Files files=
            Files.builder()
            .fileName(origName)
            .uuid(saveName)
            .uploadPath(savePath)
            .build();
      
      //실제로 로컬에 uuid를 파일명으로 저장
      multifiles.transferTo(new File(savePath));
      
      return files;
   }
   
}