package model;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CreateExcelFile {
	public static void create(List<AttendanceViewDTO> attendanceList,OutputStream os) throws Exception{
		try(Workbook workbook = new XSSFWorkbook()){
		    Sheet sheet = workbook.createSheet("勤怠記録");
		    
		    // ヘッダ行の作成
		    Row headerRow = sheet.createRow(0);
		    headerRow.createCell(0).setCellValue("社員ID");
		    headerRow.createCell(1).setCellValue("社員名");
		    headerRow.createCell(2).setCellValue("日付");
		    headerRow.createCell(3).setCellValue("出勤時間");
		    headerRow.createCell(4).setCellValue("退勤時間");
		    
		    int rowIdx = 1;
		    
		    for (AttendanceViewDTO dto : attendanceList) {
		        Row row = sheet.createRow(rowIdx++);
		        row.createCell(0).setCellValue(dto.getEmployeeId());
		        row.createCell(1).setCellValue(dto.getEmployeeName());
		        row.createCell(2).setCellValue(dto.getClockDate() != null ? dto.getClockDate().toString() : "");
		        row.createCell(3).setCellValue(dto.getWorkin() != null ? dto.getWorkin().toString() : "");
		        row.createCell(4).setCellValue(dto.getWorkout() != null ? dto.getWorkout().toString() : "");

		    }
		    workbook.write(os);
		}catch (IOException e){
			e.printStackTrace();
			throw new IOException("Excelファイルの出力中にエラーが発生しました");
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Excelファイルの作成中にエラーが発生しました");
		}
	}
}
