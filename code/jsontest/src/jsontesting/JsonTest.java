package jsontesting;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Iterator;

import org.apache.commons.*;
import org.apache.commons.io.FileUtils;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonTest {

  public static void main(String[] args) throws IOException {
    JSONParser parser = new JSONParser();
    
    try {
      String foo = "";
      try {
        foo = FileUtils.readFileToString(new File("/Users/dnorthrup/temp/gdrive/Data/Sets/Set001/CardDefinitions/HEX_000202.card"));
      } catch (IOException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }

      foo = foo.replaceAll("[ {]'", " \"");
      foo = foo.replaceAll("'[ }]", "\" ");
      foo.replace("\r\n","");
      
      Object obj = parser.parse(foo);
   
      JSONObject jsonObject = (JSONObject) obj;
   
      String name = (String) jsonObject.get("name");
      System.out.println(name);
   
      long age = (Long) jsonObject.get("age");
      System.out.println(age);
   
      // loop array
      JSONArray msg = (JSONArray) jsonObject.get("messages");
      @SuppressWarnings("unchecked")
      Iterator<String> iterator = msg.iterator();
      while (iterator.hasNext()) {
        System.out.println(iterator.next());
      }
   
    } catch (ParseException e) {
      e.printStackTrace();
    }
    
    String foo = "";
    try {
      foo = FileUtils.readFileToString(new File("/Users/dnorthrup/temp/gdrive/Data/Sets/Set001/CardDefinitions/HEX_000202.card"));
    } catch (IOException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }

    foo = foo.replaceAll("[ {]'", " \"");
    foo = foo.replaceAll("'[ }]", "\" ");
    foo.replace("\r\n","");
    // Doing this with previously set up parser
    try {
      System.out.println(parser.parse(foo));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    // Doing this with JSONValue static method
    try {
      System.out.println(JSONValue.parseWithException(foo));
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    System.out.println(JSONValue.toJSONString(foo));
  }

}
