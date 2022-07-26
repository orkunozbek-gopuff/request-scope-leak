package com.example;

import java.time.OffsetDateTime;
import java.util.Locale;
import java.util.Map;

public record PageContext(OffsetDateTime time, Locale locale, Map<String, Object> query) {

}
