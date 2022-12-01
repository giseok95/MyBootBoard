package kr.gi.mybootboard.excel;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.gi.mybootboard.board.entity.Board;
import kr.gi.mybootboard.board.repository.BoardRepository;


@Service
public class ExcelServiceImpl extends AbstractExcelService {

   @Autowired
   private BoardRepository boardRepo;
   
   @Override
   public void excelDown(Board board, HttpServletResponse response) {
      
      List<Board> listBoard = boardRepo.listBoard(board);
      
      try {
         // Excel Download 시작
         Workbook workbook = new HSSFWorkbook();
         
         // sheet 생성
         Sheet sheet = workbook.createSheet("게시물 목록");
         
         // 행,열,열번호
         Row row = null;
         Cell cell = null;
         int rowNo = 0;
         
         // 테이블 헤더용 스타일
         CellStyle headStyle = workbook.createCellStyle();
         // 가는 경계선을 가집니다.
         headStyle.setBorderTop(BorderStyle.THIN);
         headStyle.setBorderBottom(BorderStyle.THIN);
         headStyle.setBorderLeft(BorderStyle.THIN);
         headStyle.setBorderRight(BorderStyle.THIN);
         // 배경은 노란색입니다.
         headStyle.setFillForegroundColor(HSSFColorPredefined.YELLOW.getIndex());
         headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
         // 데이터용 경계 스타일 테두리만 지정
         CellStyle bodyStyle = workbook.createCellStyle();
         bodyStyle.setBorderTop(BorderStyle.THIN);
         bodyStyle.setBorderBottom(BorderStyle.THIN);
         bodyStyle.setBorderLeft(BorderStyle.THIN);
         bodyStyle.setBorderRight(BorderStyle.THIN);
         
         // 헤더명 설정
         String[] headerArray= {"번호", "분류", "제목", "작성자", "등록일", "조회수"};
         row=sheet.createRow(rowNo++);
         for (int i=0; i<headerArray.length; i++) {
            cell = row.createCell(i);
            cell.setCellStyle(headStyle);
            cell.setCellValue(headerArray[i]);
         }
         
         for (Board excelData : listBoard) {
            row = sheet.createRow(rowNo++);
            
            cell = row.createCell(0);
            cell.setCellStyle(bodyStyle);
            cell.setCellValue(excelData.getSeq());
            
            cell = row.createCell(1);
            cell.setCellStyle(bodyStyle);
            cell.setCellValue(excelData.getCategory());
            
            cell = row.createCell(2);
            cell.setCellStyle(bodyStyle);
            cell.setCellValue(excelData.getTitle());
            
            cell = row.createCell(3);
            cell.setCellStyle(bodyStyle);
            cell.setCellValue(excelData.getMember().getName());
            
            cell = row.createCell(4);
            cell.setCellStyle(bodyStyle);
            cell.setCellValue(String.format(excelData.getCreateDate().toString()));
            
            cell = row.createCell(5);
            cell.setCellStyle(bodyStyle);
            cell.setCellValue(excelData.getCnt());
         }
         
         // 컨텐츠 타입과 파일명 지정
         response.setContentType("ms-vnd/excel");
         response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode("게시물 목록.xls", "UTF8"));
         
         // 엑셀 출력
         workbook.write(response.getOutputStream());
         workbook.close();
      } catch (Exception ex) {
         ex.printStackTrace();
      }
   }
   
}