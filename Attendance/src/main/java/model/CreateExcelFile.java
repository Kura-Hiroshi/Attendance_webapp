package model;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *Excelファイルを作成し、ストリームへ出力するメソッドを持つクラス。 
 */
public class CreateExcelFile {
    public static void create(List<AttendanceViewDTO> attendanceList, OutputStream os) throws Exception {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("勤怠記録");

            // 共通スタイル
            CellStyle borderStyle = workbook.createCellStyle();
            borderStyle.setBorderTop(BorderStyle.THIN);
            borderStyle.setBorderBottom(BorderStyle.THIN);
            borderStyle.setBorderLeft(BorderStyle.THIN);
            borderStyle.setBorderRight(BorderStyle.THIN);

            CellStyle timeStyle = workbook.createCellStyle();
            timeStyle.cloneStyleFrom(borderStyle);
            CreationHelper createHelper = workbook.getCreationHelper();
            timeStyle.setDataFormat(createHelper.createDataFormat().getFormat("HH:mm"));

            CellStyle dateStyle = workbook.createCellStyle();
            dateStyle.cloneStyleFrom(borderStyle);
            dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd"));

            // ヘッダー行（2列追加）
            String[] headers = {
                "社員ID", "社員名", "日付", "出勤時間", "退勤時間", "休憩時間（分）", "勤務時間"
            };
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(borderStyle);
            }

            int rowIdx = 1;
            for (AttendanceViewDTO dto : attendanceList) {
                Row row = sheet.createRow(rowIdx);

                // 社員ID
                row.createCell(0).setCellValue(dto.getEmployeeId());
                row.getCell(0).setCellStyle(borderStyle);

                // 社員名
                row.createCell(1).setCellValue(dto.getEmployeeName());
                row.getCell(1).setCellStyle(borderStyle);

                // 日付
                if (dto.getClockDate() != null) {
                    Cell cell = row.createCell(2);
                    cell.setCellValue(java.sql.Date.valueOf(dto.getClockDate()));
                    cell.setCellStyle(dateStyle);
                } else {
                    row.createCell(2).setCellValue("");
                    row.getCell(2).setCellStyle(borderStyle);
                }

                // 出勤時間
                LocalDateTime workin = dto.getWorkin();
                if (workin != null) {
                    Cell cell = row.createCell(3);
                    cell.setCellValue(java.sql.Timestamp.valueOf(workin));
                    cell.setCellStyle(timeStyle);
                } else {
                    row.createCell(3).setCellValue("");
                    row.getCell(3).setCellStyle(borderStyle);
                }

                // 退勤時間
                LocalDateTime workout = dto.getWorkout();
                if (workout != null) {
                    Cell cell = row.createCell(4);
                    cell.setCellValue(java.sql.Timestamp.valueOf(workout));
                    cell.setCellStyle(timeStyle);
                } else {
                    row.createCell(4).setCellValue("");
                    row.getCell(4).setCellStyle(borderStyle);
                }
                
                //休憩時間
                Cell breakCell = row.createCell(5);
                breakCell.setCellValue(""); // 空にする
                breakCell.setCellStyle(borderStyle);

                // 勤務時間（数式）
                Cell formulaCell = row.createCell(6);
                String excelFormula = String.format(
                    "IF(OR(D%d=\"\",E%d=\"\"),\"？\",TEXT((E%d-D%d)-TIME(0,IF(F%d=0,0,F%d),0),\"hh:mm\"))",
                    rowIdx + 1, rowIdx + 1, rowIdx + 1, rowIdx + 1, rowIdx + 1
                );
                formulaCell.setCellFormula(excelFormula);
                formulaCell.setCellStyle(borderStyle);

                rowIdx++;
            }

            // カラム幅の自動調整
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(os);
        } catch (IOException e) {
            throw new IOException("Excelファイルの出力中にエラーが発生しました", e);
        } catch (Exception e) {
            throw new Exception("Excelファイルの作成中にエラーが発生しました", e);
        }
    }
}
