package greenwhishes;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExcel {

    private String inputFile;
    private int row;
    

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }
//lecture du fichier excel
    public void read() throws IOException  {
        File inputWorkbook = new File(inputFile);
        Workbook w;
        try {
            w = Workbook.getWorkbook(inputWorkbook);
            // Get the first sheet
            Sheet sheet = w.getSheet(0);
            

            for (int j = 0; j < sheet.getColumns(); j++) {
                for (int i = 0; i < sheet.getRows(); i++) {
                    Cell cell = sheet.getCell(j, i);
                    CellType type = cell.getType();
                    //je r�cupere le num�ro de la ligne pour l'ajout de nouveaux �l�ments et l'incr�mentation de l'id
                    this.setRow(i+1);
                }
            }
        } catch (BiffException e) {
            e.printStackTrace();
        }
    }
    //getter et setter 
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

}