package org.gorany.springbatch.config

import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Job을 정의한다.
 * */
@Configuration
class HelloJobConfiguration(
    private val jobBuilderFactory: JobBuilderFactory,
    private val stepBuilderFactory: StepBuilderFactory,
) {
    /**
     * Job -> Step -> Tasklet
     * */
    @Bean
    fun helloJob(): Job = jobBuilderFactory.get("helloJob") //Job 생성
        .start(helloStep())
        .next(helloStep2())
        .build()

    @Bean
    fun helloStep(): Step = stepBuilderFactory.get("helloStep") //Step 생성
        .tasklet { contribution, chunkContext -> //tasklet : Step 안에서 단일 태스크로 수행되는 로직 구현 (비지니스 로직)
            println("======================")
            println("<<Hello Spring Batch>>")
            println("======================")
            /**
             * return null -> 1번 실행하고 종료 == FINISHED
             * else -> 반복
             * */
            RepeatStatus.FINISHED
        }
        .build()

    @Bean
    fun helloStep2() = stepBuilderFactory.get("helloStep2")
        .tasklet { contribution, chunkContext ->
            println("======================")
            println("<<Hello Spring Batch 2>>")
            println("======================")
            RepeatStatus.FINISHED
        }
        .build()
}

/**
 * Job (일, 일감)
 * - 여러 단계의 항목을 구성할 수 있다.
 * - ㅇ->ㅁ->ㅁ->ㅇ
 *
 * Step (일의 항목, 단계)
 * - ㅇ->"ㅁ"->ㅁ->ㅇ
 *
 * Tasklet (작업 내용)
 * - 비지니스 로직
직* */