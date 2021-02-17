package training.weather;

import training.weather.WeatherStatus.consolidated_weather;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.gson.Gson;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WeatherForecast {

	public String getCityWeather(String city, LocalDate datetime) {

		HttpRequestFactory requestFactory;
		HttpRequest request;
		HttpResponse response;

		Gson gson = new Gson();
		
		try {
			if (datetime != null && datetime.compareTo(LocalDate.now().plusDays(6)) < 1) {

				requestFactory = new NetHttpTransport().createRequestFactory();
				request = requestFactory.buildGetRequest(new GenericUrl(UtilWeather.URL + city));
				response = request.execute();

				if (response.getStatusCode() == UtilWeather.OK) {

					CityID[] cityID = gson.fromJson(response.parseAsString(), CityID[].class);
					
					if (cityID.length == 0)
						return UtilWeather.Error_InvalidParameter;
					
					requestFactory = new NetHttpTransport().createRequestFactory();
					request = requestFactory.buildGetRequest(new GenericUrl(UtilWeather.URL_location + cityID[0].woeid));
					response = request.execute();

					if (response.getStatusCode() == UtilWeather.OK) {

						WeatherStatus result = gson.fromJson(response.parseAsString(), WeatherStatus.class);
						return parseResponse(result, datetime);
					}
				}
			}
			
		} catch (Exception e) {

			return e.toString();

		}
		
		return UtilWeather.Error_InvalidParameter;

	}

	public String parseResponse(WeatherStatus results, LocalDate datetime) {
		
		String targetDate = datetime.format(DateTimeFormatter.ofPattern(UtilWeather.dateFormat));

		for (consolidated_weather weather : results.consolidated_weather) {

			if (weather.applicable_date.equals(targetDate)) {

				return weather.weather_state_name;
			}
		}
		return UtilWeather.Error_EmptyResponse;
	}
}
