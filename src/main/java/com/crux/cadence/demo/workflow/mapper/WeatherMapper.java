package com.crux.cadence.demo.workflow.mapper;

import com.crux.cadence.demo.persister.entity.Weather;
import com.crux.cadence.demo.producer.entity.CurrentWeather;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WeatherMapper {
    WeatherMapper INSTANCE = Mappers.getMapper( WeatherMapper.class );

    @Mapping(source = "currentWeather.temperature", target = "temperature")
    @Mapping(source = "currentWeather.windspeed", target = "windspeed")
    @Mapping(source = "currentWeather.winddirection", target = "winddirection")
    @Mapping(source="city", target = "city")
    Weather toWeather(CurrentWeather currentWeather, String city);
}
