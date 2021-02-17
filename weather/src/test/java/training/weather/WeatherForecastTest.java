package training.weather;

import static org.junit.Assert.*;

import java.io.IOException;
import java.time.LocalDate;
import org.junit.Test;

public class WeatherForecastTest {

	@Test
	public void normalTest() throws IOException {
		WeatherForecast weatherForecast = new WeatherForecast();
		String forecast = weatherForecast.getCityWeather("Barcelona", LocalDate.now().plusDays(1));
		assertTrue(forecast.length()>0);
	}
	
	@Test
	public void sendInvalidCityName() throws IOException {
		
		WeatherForecast weatherForecast = new WeatherForecast();
		String forecast = weatherForecast.getCityWeather("InvalidCity", LocalDate.now().plusDays(1));
		
		assertEquals(UtilWeather.Error_InvalidParameter,forecast);
	}
	
	@Test
	public void validateConnection() {
		
		
	}
	
	
}