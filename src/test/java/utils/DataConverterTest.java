package utils;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import com.ele.data.utils.DataConverter;
import com.ele.data.utils.FileUtils;
import com.example.dto.ScanDenovoCandidate;
import org.junit.Test;

import java.io.InputStream;

public class DataConverterTest {

    @Test
    public void testReadFileFromResource() throws Exception {
        InputStream denovo = DataConverterTest.class.getClassLoader().getResourceAsStream("result/Denovo.json");
        String json = FileUtils.convertStreamToString(denovo);
        assertThat(DataConverter.parseProtoArray(json, ScanDenovoCandidate.newBuilder()).size(), is(4829));

    }
}
