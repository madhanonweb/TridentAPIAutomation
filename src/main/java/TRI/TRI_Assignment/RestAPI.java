package TRI.TRI_Assignment;

import io.restassured.RestAssured;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.json.JSONObject;
import io.restassured.path.json.*;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;

import static io.restassured.RestAssured.given;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import java.util.regex.Matcher;


public class RestAPI {
	
public Response response ;
	
	String ResponseValue;
	
	String Output;
	
	
	@Test
	public void GetTasks() throws IOException
	
	{
		
		
		
		RestAssured.baseURI="https://www.monicahq.com/api/tasks";
		
		// RestAssured.registerParser("text/html", Parser.JSON);
		
       
		
		response = 
				
				given()
					//	.header("Content-Type","application/json")
						
						.header("Authorization", "Bearer ganesh")
				.when()
						.get()
						
			   .then()
			          
			         .contentType(ContentType.HTML)
			   
					 	.extract()
					 	
					 	.response();
		
		
		
	//	System.out.println("  STATUS CODE IS ====> " + response.getStatusCode());
		
		ResponseValue = response.getBody().asString();
		
	
	//	System.out.println("********************");
		
	//	System.out.println(ResponseValue);
	
		
		
		Document doc = Jsoup.parse(ResponseValue);
		
		String output=doc.getElementsByClass("json").toString();
	
		String removed=output.replaceAll("<code class=\"json\">", ",");
		
		removed=removed.replaceAll("</code>", "");				
			
		removed=removed.substring(1)+"}";	
		
		System.out.println(removed);
			
		JsonPath jsonPathEvaluator = new JsonPath(removed);		
			
		int Size =jsonPathEvaluator.get("data.object.size()");
		
		String Tasks = " Number of Tasks is "  + Size;
		
		System.out.println(Tasks);
		
		ArrayList<String> Title = new ArrayList<String>()  ;
		
		String data=null;
		
		for ( int i=0 ;i<=Size-1;i++)
			
			
					
		{
			String path = "data.title["+ Integer.toString(i)+"]";
			
			data= jsonPathEvaluator.getString(path);
					
			System.out.println(data);
			
			Title.add(data);
		}
		
		
		FileWriter fw=new FileWriter("..\\TRI_Assignment\\Resources\\Monicahq.Txt");    
		
		fw.write(Tasks);
		
		fw.write("\n");
		
		fw.write(Title.toString());
		
		fw.close();
	}


}
