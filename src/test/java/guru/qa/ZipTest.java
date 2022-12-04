package guru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.function.Try;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class ZipTest {

    ClassLoader cl = ZipTest.class.getClassLoader();

    @Test
    void zipTest() throws Exception {
        ZipFile tf = new ZipFile(new File("src/test/resources/TestFiles.zip"));
        ZipInputStream is = new ZipInputStream(cl.getResourceAsStream("TestFiles.zip"));
        ZipEntry entry;
        while ((entry = is.getNextEntry()) != null) {
            try (InputStream inputStream = tf.getInputStream(entry)) {
                if (entry.getName().contains(".pdf")) {
                    PDF pdf = new PDF(inputStream);
                    assertThat(pdf.text).contains("TDV8");
                } else if (entry.getName().contains(".xls")) {
                    XLS xls = new XLS(inputStream);
                    assertThat(xls.excel.getSheetAt(0).getRow(0).getCell(0).getStringCellValue()).isEqualTo("Дата заказа:");
                } else {
                    CSVReader csvReader = new CSVReader(new InputStreamReader(is));
                    List<String[]> csvContent = csvReader.readAll();
                    assertThat(csvContent.get(3)[0]).contains("4");
                    assertThat(csvContent.get(3)[1]).contains("R380");
                    assertThat(csvContent.get(4)[0]).contains("5");
                    assertThat(csvContent.get(4)[1]).contains("Holmes HEPA Air Purifie");
                }
            }
        }
    }
}