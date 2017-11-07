import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TestAPI
{
	public static void main(String[] args) {
		startWebRequest("Austin");
	}
	
	static String startWebRequest(String city){
		String weatherURL = "http://api.openweathermap.org/data/2.5/weather?q="+city+"&APPID=26aa1d90a24c98fad4beaac70ddbf274";
		StringBuilder result = new StringBuilder(); //will hold the java String after converting from JSON 
		try {	
			URL url = new URL(weatherURL); //the url we want to parse
			HttpURLConnection conn = (HttpURLConnection) url.openConnection(); //Used to make a single connection to that URL
			conn.setRequestMethod("GET"); //The Type of request we want to make
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream())); //pass in the instance of HttpURLConnection 
			String line;
		    	while ((line = rd.readLine()) != null) { //turn BufferedReader value into type String
		    		result.append(line);
		    	}
		    rd.close();
		    //what do we want to search the API for?
		    double windGust = parseJson(result.toString());
		    System.out.println("Wind gust is currently: " + windGust + " mph");
		    return result.toString();
		}
		catch(Exception e){return "Error! Exception: " + e;}
	}
	
	static double parseJson(String json) {
        JsonElement jelement = new JsonParser().parse(json);
        JsonObject  MasterWeatherObject = jelement.getAsJsonObject();
    
        JsonObject windObject = MasterWeatherObject.getAsJsonObject("wind"); //type this exactly as it appears in the JSON response
        double gust = windObject.get("gust").getAsDouble();  //type exactly, type case matters!
        
	return gust;
	}
}
