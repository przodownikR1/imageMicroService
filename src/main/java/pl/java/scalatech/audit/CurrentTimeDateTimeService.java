package pl.java.scalatech.audit;

import java.time.ZonedDateTime;

import org.springframework.stereotype.Component;
@Component
public class CurrentTimeDateTimeService implements DateTimeService {
    @Override
    public ZonedDateTime getCurrentDateAndTime() {
        return ZonedDateTime.now();
    }
}