package com.example;

import io.micronaut.context.LocalizedMessageSource;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.context.ServerRequestContext;
import java.time.OffsetDateTime;
import java.util.Locale;
import reactor.core.publisher.Mono;

@Controller("/")
public class DummyController {

  private PageContextRequestScope pageContextSource;

  private LocalizedMessageSource messageSource;


  public DummyController(LocalizedMessageSource messageSource, PageContextRequestScope pageContextSource) {
    this.pageContextSource = pageContextSource;
    this.messageSource = messageSource;
  }

  @Get
  public Mono<HelloWorld> getHelloWorld() {
    var req = ServerRequestContext.currentRequest().get();
    pageContextSource.setPageContext(new PageContext(OffsetDateTime.now(),
                                                     req.getLocale().orElse(Locale.CANADA),
                                                     req.getParameters().asMap(String.class, Object.class)));
    return Mono.fromCallable(() -> new HelloWorld(pageContextSource.getPageContext().time(),
                                                  messageSource.getMessageOrDefault("test", "test")));
  }

  public record HelloWorld(OffsetDateTime time, String text) {}
}
