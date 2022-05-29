package org.gorany.springbatch

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
/**
 * @EnableBatchProcessing
 * 스프링 배치가 작동하기 위해 선언해야 하는 Annotation
 *
 * 총 4개의 설정 클래스를 실행시키며, 스프링 배치의 모든 초기화 및 실행 구성이 이뤄짐
 * Spring Boot Batch의 자동 설정 클래스가 실행됨으로 빈으로 등록된 모든 Job을 검색해서 초기화와 동시에 Job을 수행하도록 구성됨
 * */
@EnableBatchProcessing
class SpringBatchApplication

fun main(args: Array<String>) {
    runApplication<SpringBatchApplication>(*args)
}

/**
 * Spring Batch Init Configure Classes
 *
 * 1. BatchAutoConfiguration
 *  - 스프링 배치가 초기화 될 때 자동으로 실행되는 설정 클래스
 *  - Job을 수행하는 JobLauncherApplicationRunner 빈을 생성
 *
 * 2. SimpleBatchConfiguration
 *  - JobBuilderFactory 와 StepBuilderFactory 생성
 *  - 스프링 배치의 주요 구성 요소 생성 - 프록시 객체로 생성됨
 *
 * 3. BatchConfigurerConfiguration
 *  - BasicBatchConfigurer
 *   - SimpleBatchConfiguration 에서 생성한 프록시 객체의 실제 대상 객체를 설정하는 설정 클래스
 *   - 빈으로 의존성 주입 받아 주요 객체들을 참조해서 사용할 수 있다.
 *
 *  - JpaBatchConfigurer
 *   - JPA 관련 객체를 생성하는 설정 클래스
 *   - BasicBatchConfigurer를 상속한다.
 *
 *  - 사용자 정의 BatchConfigurer 인터페이스를 구현하여 사용할 수 있다. (여기서 부턴 깊은 내용)
 * */