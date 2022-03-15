import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

public class SelenideFilesTest {



    @Test
    void SelenideDownloadTest() throws Exception {
        //ChromeDriver driver = new ChromeDriver();
        //driver.get("https://github.com/junit-team/junit5/blob/main/README.md");

        open("https://github.com/junit-team/junit5/blob/main/README.md");
        File downloadFile = $("#raw-url").download();


        //метод работает для селекторов у которых есть ссылка href
        try (InputStream is = new FileInputStream(downloadFile)) {
            assertThat(new String(is.readAllBytes(), UTF_8)).contains("This repository is the home of the next generation of JUnit");
        }

        //метод такой же как и try, только для более новых версий джава
        String readString = Files.readString(downloadFile.toPath(), UTF_8);

    }


    @Test
    void SelenideUploadTest() {
        Selenide.open("https://the-internet.herokuapp.com/upload");
        //$("input[type='file']").uploadFile(new File("C:/Users/leeny/IdeaProjects/HomeWork9/src/test/resources/files/1.txt"))
        $("input[type='file']").uploadFromClasspath("files/1.txt");
        $("#file-submit").click();
        Selenide.$("div.example").shouldHave(Condition.text("File Uploaded!"));
        Selenide.$("#uploaded-files").shouldHave(Condition.text("1.txt"));
    }
}
