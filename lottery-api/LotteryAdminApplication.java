package com.lottery;

import cn.hutool.core.util.URLUtil;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceAutoConfiguration;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@EnableDiscoveryClient
@RequiredArgsConstructor
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, DruidDataSourceAutoConfigure.class})
@EnableAsync
@Import({DynamicDataSourceAutoConfiguration.class})
public class LotteryAdminApplication extends SpringBootServletInitializer implements ApplicationRunner {
	private final ServerProperties serverProperties;

	public static void main(String[] args) {
		SpringApplication.run(LotteryAdminApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(LotteryAdminApplication.class);
	}

	/**
	 * 访问首页提示
	 */
	@Hidden
	@GetMapping("/")
	public String index() throws UnknownHostException {
		String hostAddress = InetAddress.getLocalHost().getHostAddress();
		Integer port = serverProperties.getPort();
		String contextPath = serverProperties.getServlet().getContextPath();
		String baseUrl = URLUtil.normalize(String.format("%s:%s%s", hostAddress, port, contextPath));
		return String.format("Backend Service Started Successfully.<br/>API: %s/doc.html", baseUrl);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		String hostAddress = InetAddress.getLocalHost().getHostAddress();
		Integer port = serverProperties.getPort();
		String contextPath = serverProperties.getServlet().getContextPath();
		String baseUrl = URLUtil.normalize(String.format("%s:%s%s", hostAddress, port, contextPath));
	}

}
