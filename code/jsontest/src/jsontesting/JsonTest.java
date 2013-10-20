package jsontesting;

import java.io.*;
import java.nio.charset.Charset;
import org.apache.commons.*;
import org.apache.commons.io.FileUtils;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonTest {

  public static void main(String[] args) {
    JSONParser parser = new JSONParser();
    String foo = "";
    try {
      foo = FileUtils.readFileToString(new File("/Users/dnorthrup/temp/gdrive/Data/Sets/Set001/CardDefinitions/HEX_000202.card"));
    } catch (IOException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }

    foo = foo.replaceAll("'", "\"");
    //    System.out.print(foo);
    //    JSONArray ja = new JSONArray();
    try {
      System.out.println(parser.parse(foo));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    System.out.println(JSONValue.parse(foo));
    System.out.println(JSONValue.toJSONString(foo));
//    JSONObject json = new JSONObject();
//    System.out.println(new JSONObject(foo));
  }

}
