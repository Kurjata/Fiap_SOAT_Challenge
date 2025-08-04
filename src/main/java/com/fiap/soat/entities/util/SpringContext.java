package com.fiap.soat.entities.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class SpringContext implements ApplicationContextAware {

  private static ApplicationContext context;

  private static void setContext(final ApplicationContext context) {
    SpringContext.context = context;
  }

  public static ReactiveMongoTemplate getBean() {
    return context.getBean(ReactiveMongoTemplate.class);
  }

  @Override
  public void setApplicationContext(final ApplicationContext applicationContext)
      throws BeansException {
    setContext(applicationContext);
  }
}
