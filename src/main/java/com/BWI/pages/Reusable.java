package com.BWI.pages;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Reusable extends BasePage {
    public static String TESTDATA_SHEET_PATH = "C://Users//cramachandraia//IdeaProjects//com.BestWestern//BWI//src//test//TestData.xlsx";

    static Workbook book;
    static Sheet sheet;
    HotelSearchPage hotelSearch;
    HomePage homePage;

    public static Object[][] getTestData(String sheetName) {
        FileInputStream file = null;
        try {
            file = new FileInputStream(TESTDATA_SHEET_PATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            book = WorkbookFactory.create(file);
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
        sheet = book.getSheet(sheetName);
        Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
        // System.out.println(sheet.getLastRowNum() + "--------" +
        // sheet.getRow(0).getLastCellNum());
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
                data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
                // System.out.println(data[i][k]);
            }
        }
        return data;
    }

    public static String[] splitFunction(String splitDate){
        String strMain = splitDate;
        String[] arrSplit = strMain.split("-");
        for (int i=0; i < arrSplit.length; i++)
        {
            System.out.println(arrSplit[i]);
        }
        return arrSplit;
    }

    /** Verify destination and dates common method
     * @param destination
     * @param CheckInDate
     * @param CheckOutDate
     */
    public void verifyDestinationAndDates(String destination, String CheckInDate, String CheckOutDate) {

        hotelSearch = new HotelSearchPage();
        homePage = new HomePage();

        String destinationSummary = hotelSearch.destinationSummary();
        softAssertion.assertEquals(destinationSummary, destination);

        String summaryCheckin = hotelSearch.getSummaryCheckin().getText();
        softAssertion.assertEquals(summaryCheckin, CheckInDate);


        String summaryCheckout = hotelSearch.getSummaryCheckOut().getText();
        softAssertion.assertEquals(summaryCheckout, CheckOutDate);
    }
}
