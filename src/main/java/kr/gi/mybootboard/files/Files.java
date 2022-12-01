package kr.gi.mybootboard.files;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import kr.gi.mybootboard.board.entity.Board;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@ToString(exclude = "board")
@NoArgsConstructor
public class Files {

   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_file_generator")
   @SequenceGenerator(name = "seq_file_generator", sequenceName = "seq_file", allocationSize = 1, initialValue = 1)
   private Long fileSeq;
   
   private String fileName;
   private String uploadPath;
   private String uuid;
   private String image;
   
   @Builder
   public Files(Long fileSeq, String fileName, String uploadPath, String uuid, String image) {
      this.fileSeq=fileSeq;
      this.fileName=fileName;
      this.uploadPath=uploadPath;
      this.uuid=uuid;
      this.image=image;
      
   }
   
   
   @ManyToOne
   @JoinColumn(name = "seq", nullable = false, updatable = false)
   private Board board;
   
   public void setBoard(Board board) {
      this.board = board;
      board.getFileList().add(this);
   }
}