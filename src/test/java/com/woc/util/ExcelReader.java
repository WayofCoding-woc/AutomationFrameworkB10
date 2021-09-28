package com.woc.util;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ExcelReader {

    public Object[][] readData(String filePath){
        List<List<Object>> rows = read(filePath);
        int rowSize = rows.size();
        int columnSize = rows.get(0).size();
        Object[][] d = new Object[rowSize][columnSize];
        for(int i=0; i < rowSize; i++){
            List<Object> row = rows.get(i);
            Object[] rowData = row.toArray();
            d[i] = rowData;
        }
        return d;
    }

    public List<List<Object>> read(String filePath){
        List<List<Object>> result = new LinkedList<>();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filePath);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()){
                Row row = iterator.next();
                List<Object> dataRow = new LinkedList<>();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()){
                        case NUMERIC:
                            if(HSSFDateUtil.isCellDateFormatted(cell)){
                                dataRow.add(DateUtil.convertDate(cell.getDateCellValue()));
                            }else {
                                dataRow.add(cell.getNumericCellValue());
                            }
                            break;
                        case STRING:
                            dataRow.add(cell.getStringCellValue());
                            break;
                        case BOOLEAN:
                            dataRow.add(cell.getBooleanCellValue());
                            break;
                        case FORMULA:
                            dataRow.add(cell.getCellFormula());
                            break;
                    }
                }
                result.add(dataRow);
            }
        } catch (IOException e) {
           throw new RuntimeException(e);
        }finally {
            if(null != fis){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }
}
