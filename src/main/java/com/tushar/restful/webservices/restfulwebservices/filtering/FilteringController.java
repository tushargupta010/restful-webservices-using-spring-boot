package com.tushar.restful.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	@GetMapping(path = "/filtering")
	public MappingJacksonValue filtering() {
		SomeBean someBean = new SomeBean("val1", "val2", "val3");
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		mappingJacksonValue.setFilters(filters);
		return mappingJacksonValue;
	}

	@GetMapping(path = "/filtering-list")
	public MappingJacksonValue filteringList() {
		List<SomeBean> beanList = Arrays.asList(new SomeBean("val1", "val2", "val3"),
				new SomeBean("val4", "val5", "val6"), new SomeBean("val7", "val8", "val9"));

		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(beanList);

		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		mappingJacksonValue.setFilters(filters);

		return mappingJacksonValue;
	}

}
