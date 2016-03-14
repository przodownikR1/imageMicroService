package pl.java.scalatech.audit;

import java.time.ZonedDateTime;
@FunctionalInterface
public interface DateTimeService {
    ZonedDateTime getCurrentDateAndTime();
}