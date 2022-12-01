package kr.gi.mybootboard.reply;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import kr.gi.mybootboard.board.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = "board")
public class Reply {

   @Id
   @Column(name = "reply_ID")
   @GeneratedValue
   private Long rno;

   private String recontent;
   private String replyer;
   
   @Temporal(TemporalType.TIMESTAMP)
   private Date replydate=new Date();
   
   @Temporal(TemporalType.TIMESTAMP)
   private Date updatedate =new Date();

   @ManyToOne
   @JoinColumn(name = "seq", nullable = false, updatable = false)
   private Board board;

   public void setBoard(Board board) {
      this.board = board;
      board.getReplyList().add(this);
   }
}