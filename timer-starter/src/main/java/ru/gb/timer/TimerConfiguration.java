package ru.gb.timer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.gb.timer.aspect.TimerAspect;

@Configuration
public class TimerConfiguration {
    @Bean
    TimerAspect timerAspect(){
        return new TimerAspect();
    }
}
