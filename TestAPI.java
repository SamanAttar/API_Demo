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
		StringBuilder result = new StringBuilder();
		try {	
			URL url = new URL(weatherURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
		    	while ((line = rd.readLine()) != null) {
		    		result.append(line);
		    	}
		    rd.close();
	
		    double longitude = parseJson(result.toString());
		    System.out.println("Longitude is: " + longitude);
		    return result.toString();
		}
		catch(Exception e){return "Error! Exception: " + e;}
	}
	
	static double parseJson(String json) {
        JsonElement jelement = new JsonParser().parse(json);
        JsonObject  MasterWeatherObject = jelement.getAsJsonObject();
        
        JsonObject  coordinateObject = MasterWeatherObject.getAsJsonObject("coord");
        JsonObject sunsetObject = MasterWeatherObject.getAsJsonObject("sunset");
        double longitude = coordinateObject.get("lon").getAsDouble();
        double latitude = sunsetObject.get("sunset").getAsDouble();
        
        System.out.println("LAT: " + latitude);
        
return longitude;
}
}