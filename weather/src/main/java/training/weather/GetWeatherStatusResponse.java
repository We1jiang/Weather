package training.weather;


public class GetWeatherStatusResponse {

	public class consolidated_weather{
		public String id;
		public String weather_state_name;
		public String weather_state_abbr;
		public String wind_direction_compass;
		public String created; 
		public String applicable_date;
		public String min_temp;
		public String max_temp;
		public String wind_speed;
		public String wind_direction; 
		public String air_pressure;
		public String humidity;
		public String visibility;
		public String predictability;  
			
	}
	
	public  consolidated_weather[] consolidated_weather;
	public String woeid;
	public String title;
}
