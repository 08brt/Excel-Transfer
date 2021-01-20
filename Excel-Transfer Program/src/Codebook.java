import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Codebook {

    private Scanner reader;
    private Codeframe codeframe;
    private Map<String, Integer> ID;

    public Codebook(String codebookAddress, String codeframeAddress) throws IOException {
        //object of the codedfram class is made
        this.codeframe = new Codeframe(codeframeAddress);
        //set hashmap from codeframe to this.ID
        this.ID = this.codeframe.createHashMap();

        //openning an EXCEL file
        File excelFile = new File(codebookAddress);
        FileInputStream fis = new FileInputStream(excelFile);

        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);

        //set its rows
        Iterator<Row> rowIt = sheet.iterator();

        //loop to go through the lines in the excel file
        while (rowIt.hasNext()) {
            Row row = rowIt.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            //variable i to count the columns
            int i = 1;
            //string helper to build the output
            String buffer = "";

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                //if it is column 4 do this statement
                if (i == 4) {
                    //buffer gets the value of the current cell
                    buffer = String.valueOf(cell).toLowerCase();

                    //if it is column 5 do this statement
                } else if (i == 5) {
                    //do this IF if buffer variable isnt empty
                    if (!buffer.isEmpty()) {
                        //do this IF buffer is a key in the ID hashmap
                        if (this.ID.containsKey(buffer)) {
                            //set the cell value to the value of the buffer key
                            cell.setCellValue(this.ID.get(buffer));
                            //saves the file
                            FileOutputStream out = new FileOutputStream(codebookAddress);
                            workbook.write(out);
                            out.close();
                            System.out.println("Successful");
                        }
                        //i set back to one as the loops starts again
                        i = 1;
                        //buffer is cleared as it goes to the next line
                        buffer = "";
                    }
                }
                //i++ to go the next column
                i++;
            }
        }
        //closes the workbook
        workbook.close();
        fis.close();
    }
}







