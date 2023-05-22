package com.crux.cadence.demo.workflow.workflow;

import com.crux.cadence.demo.persister.activity.SaveWeatherActivity;
import com.crux.cadence.demo.persister.entity.Weather;
import com.crux.cadence.demo.producer.activity.FetchWeatherActivity;
import com.crux.cadence.demo.producer.entity.CurrentWeather;
import com.crux.cadence.demo.workflow.WeatherWorkflow;
import com.crux.cadence.demo.workflow.mapper.WeatherMapper;
import com.uber.cadence.workflow.Workflow;

import java.time.Duration;

import static com.uber.cadence.workflow.Workflow.newActivityStub;

public class WeatherWorkflowImpl implements WeatherWorkflow {
    private final FetchWeatherActivity fetchWeatherActivity;
    private final SaveWeatherActivity saveWeatherActivity;

    public WeatherWorkflowImpl() {
        this.fetchWeatherActivity = newActivityStub(FetchWeatherActivity.class);
        this.saveWeatherActivity = newActivityStub(SaveWeatherActivity.class);
    }

    @Override
    public void process(Double latitude, Double longitude, String name) {
        while(true){
            try {
                System.out.println("--run workflow--");
                CurrentWeather currentWeather = fetchWeatherActivity.getWeather(latitude, longitude);
                Weather w = WeatherMapper.INSTANCE.toWeather(currentWeather, name);
                saveWeatherActivity.saveWeather(w);
            } catch (Exception ex) {
                System.out.println("eror " + ex.getMessage());
            }
            Workflow.sleep(Duration.ofMinutes(1));
        }
    }
}
