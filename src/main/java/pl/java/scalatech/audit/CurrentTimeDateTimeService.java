package pl.java.scalatech.audit;

import java.time.ZonedDateTime;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class CurrentTimeDateTimeService implements DateTimeService {



    @Override
    public ZonedDateTime getCurrentDateAndTime() {
        ZonedDateTime currentDateAndTime =  ZonedDateTime.now();
        return currentDateAndTime;
    }
}