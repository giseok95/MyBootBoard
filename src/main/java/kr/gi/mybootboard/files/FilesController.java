package kr.gi.mybootboard.files;


import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriUtils;

import kr.gi.mybootboard.board.entity.Board;
import kr.gi.mybootboard.board.service.BoardService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class FilesController {

   @Autowired
   private FilesService filesService;

   @Autowired
   private FilesRepository filesRepo;
   
   @Autowired
   BoardService boardService;

   // 이미지 출력
   @GetMapping("/images/{fileSeq}")
   @ResponseBody
   public Resource downloadImage(@PathVariable("fileSeq") Long fileSeq, Model model) throws IOException {

      Files files = filesRepo.findById(fileSeq).orElse(null);

      return new UrlResource("file:"+files.getUploadPath());

   }

   @GetMapping("/attach/{fileSeq}")
   public ResponseEntity<UrlResource> downloadAttach(@PathVariable Long fileSeq) throws MalformedURLException {
      System.out.println("============>>>>>" + fileSeq);
      
      Files files = filesRepo.findById(fileSeq).orElse(null);

      UrlResource resource = new UrlResource("file:" + files.getUploadPath());

      String encodedFileName = UriUtils.encode(files.getFileName(), StandardCharsets.UTF_8);

      // 파일 다운로드 대화상자가 뜨도록 하는 헤더를 설정해주는 것
      // 채ㅜ
      String contentDisposition = "attachment; filename=\"" + encodedFileName + "\"";

      return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition).body(resource);
   }

   @GetMapping("images/deleteFile")
   public String deleteFile(Files files, Board board) throws IOException {

      
       filesRepo.deleteById(files.getFileSeq());
      
      return "redirect:/board/getBoard?seq="+board.getSeq();
   }
   
   
}
