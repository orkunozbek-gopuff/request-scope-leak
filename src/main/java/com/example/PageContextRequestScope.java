package com.example;

import io.micronaut.runtime.http.scope.RequestScope;
import jakarta.annotation.PreDestroy;

@RequestScope
public class PageContextRequestScope {

  private PageContext pageContext;

  public PageContextRequestScope() {
  }

  public PageContext getPageContext() {
    return pageContext;
  }

  public void setPageContext(PageContext pageContext) {
    this.pageContext = pageContext;
  }

}
