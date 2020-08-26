package huyue.model;

import java.io.InputStream;
import java.sql.Blob;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: HHH.Y
 * Date: 2020-08-27
 */
public class Audio {
    public String contentType;
    public InputStream inputStream;

    public Audio(String type, InputStream content) {
        this.contentType = type;
        this.inputStream = content;
    }
}
