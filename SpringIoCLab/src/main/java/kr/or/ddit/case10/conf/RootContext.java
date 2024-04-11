package kr.or.ddit.case10.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackages = "kr.or.ddit.case5", excludeFilters = {@Filter(classes = Controller.class)})
public class RootContext {

}
