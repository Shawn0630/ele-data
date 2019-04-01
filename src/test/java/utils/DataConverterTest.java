package utils;

import com.ele.data.utils.DataConverter;
import com.example.dto.DenovoCandidate;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class DataConverterTest {

    @Test
    public void testReadFileFromResource() throws IOException {
        InputStream denovo = DataConverterTest.class.getClassLoader().getResourceAsStream("result/Denovo.json");
        DataConverter.parseProtoArray(denovo, DenovoCandidate.newBuilder());
    }
}
