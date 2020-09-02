package com.test.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 스프링 부트 설정과 bean읽기 생성 관리
 * 이 위치부터 읽기 때문에 항상 프로젝트 최상단에 위치해야한다.
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        //tomcat이 아닌 내장 WAS를 실행한다
        SpringApplication.run(Application.class,args);
    }
}
