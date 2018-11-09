package com.tedu.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

@Component
public class ZuulHelloController implements FallbackProvider{

	@Override
	public String getRoute() {
		return "provider-user";
	}

	@Override
	public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
		// TODO Auto-generated method stub
		return new ClientHttpResponse() {
			
	
		//设置响应头信息和响应头的编码格式
	@Override
	public HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		return headers;
	}
	//设置响应体信息
	@Override
	public InputStream getBody() throws IOException {
		String result = "gogoPowerReniger";
		return new ByteArrayInputStream(result.getBytes());
	}
	
	//返回文字描述(http状态码坏请求的原因短语)
	@Override
	public String getStatusText() throws IOException {
		return HttpStatus.BAD_REQUEST.getReasonPhrase();
	}
	
	//返回状态码
	@Override
	public HttpStatus getStatusCode() throws IOException {
		// TODO Auto-generated method stub
		return HttpStatus.BAD_REQUEST;
	}
	
	//返回二进制状态码
	@Override
	public int getRawStatusCode() throws IOException {
		return HttpStatus.BAD_REQUEST.value();
	}
	
    //关闭释放资源
	@Override
	public void close() {
	}
    };
}
}
