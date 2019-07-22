package com.rs.app;

import com.rs.app.zuul.filter.CustomZuulFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
public class ZuulServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulServiceApplication.class, args);
	}
	@Bean
	public CustomZuulFilter preFilter() {
		return new CustomZuulFilter();
	}
//	@Bean
//	public PostFilter postFilter() {
//		return new PostFilter();
//	}
//	@Bean
//	public ErrorFilter errorFilter() {
//		return new ErrorFilter();
//	}
//	@Bean
//	public RouteFilter routeFilter() {
//		return new RouteFilter();
//	}
}
