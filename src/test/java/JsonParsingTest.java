import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;


public class JsonParsingTest {

    @Test
    void jsonJacksonTest() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream is = classLoader.getResourceAsStream("files/HomeWork.json")) {
            String json = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            Student student = mapper.readValue(json, Student.class);
            assertThat(student.name).isEqualTo("Lena");
            assertThat(student.surname).isEqualTo("Maslo");
            assertThat(student.films.get(1)).isEqualTo("Second film");
            assertThat(student.address.city).isEqualTo("SPB");
            assertThat(student.address.house).isEqualTo(100);
        }
    }

}
