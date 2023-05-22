package com.crux.cadence.demo.workflow.cfg;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(com.crux.cadence.demo.cfg.BeanDefinition.class)
public class BeanDefinitionWorkflow {
}