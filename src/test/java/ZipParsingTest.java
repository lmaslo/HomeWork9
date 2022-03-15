import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


import static org.assertj.core.api.Assertions.assertThat;

public class ZipParsingTest {


    @Test
    void parseZipPDFTest() throws Exception {
        ZipFile zipFile = new ZipFile("src/test/resources/files/Archive.zip");
        ZipEntry zipEntry = zipFile.getEntry("simple.pdf");
        InputStream inputStream = zipFile.getInputStream(zipEntry);
        PDF pdf = new PDF(inputStream);
        assertThat(pdf.text).contains("Check Pdf file");
    }


    @Test
    void parseZipXLSTest() throws Exception {
        ZipFile zipFile = new ZipFile("src/test/resources/files/Archive.zip");
        ZipEntry zipEntry = zipFile.getEntry("schet-dogovor-postavki-tovara.xlsx");
        InputStream inputStream = zipFile.getInputStream(zipEntry);
        XLS xls = new XLS(inputStream);
        assertThat(xls.excel
                .getSheetAt(1)
                .getRow(0)
                .getCell(1)
                .getStringCellValue()).contains("test xls");

    }


    @Test
    void parseZipCSVTest() throws Exception {
        ZipFile zipFile = new ZipFile("src/test/resources/files/Archive.zip");
        ZipEntry zipEntry = zipFile.getEntry("TestUsers.csv");
        try (InputStream inputStream = zipFile.getInputStream(zipEntry);
             CSVReader csv = new CSVReader(new InputStreamReader(inputStream))) {
            List<String[]> content = csv.readAll();
            assertThat(content.get(0)).contains("74", "+79998880000","TestUser");
        }

    }
}

