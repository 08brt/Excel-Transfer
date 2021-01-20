import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Codeframe {

    private Map<String, Integer> ID;
    private int tempID;
    private String address;

    public Codeframe(String address) throws IOException {
        this.ID = new HashMap<>();
        this.address = address;
    }

    public Map<String, Integer> createHashMap() throws IOException {
        //excel file is assigned
        File excelFile = new File(this.address);
        FileInputStream fis = new FileInputStream(excelFile);

        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIt = sheet.iterator();

        //while loops is created to read the excel rows and columns
        while (rowIt.hasNext()) {
            Row row = rowIt.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            //i will be used to determin what column the program is currently on
            int i = 1;
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                //if the cell is empty the loop is broken
                if (String.valueOf(cell).isEmpty()) {
                    break;
                } else {
                    if (i == 1) {
                        //if the program is on the first column it saved the value to temp
                        Double temp = Double.valueOf(String.valueOf(cell));
                        this.tempID = (int)Math.round(temp);
                    }

                    if (i == 2) {
                        //when the program is on the second column it saves the temp and
                        //current value of cell and throws it into a hashmap
                        this.ID.put(String.valueOf(cell).toLowerCase(), tempID);
                        i = 1;
                    }

                    i++;
                }
            }
        }
        workbook.close();
        fis.close();

        return this.ID;
    }
}
