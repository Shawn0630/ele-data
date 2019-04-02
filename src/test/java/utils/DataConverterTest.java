package utils;

import com.ele.data.utils.DataConverter;
import com.example.dto.ScanDenovoCandidate;
import org.junit.Test;

import java.io.InputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DataConverterTest {

    @Test
    public void testReadFileFromResource() throws Exception {
        InputStream denovo = DataConverterTest.class.getClassLoader().getResourceAsStream("result/Denovo.json");
        assertThat(DataConverter.parseProtoArray(denovo, ScanDenovoCandidate.newBuilder()).size(), is(4829));

    }
}
