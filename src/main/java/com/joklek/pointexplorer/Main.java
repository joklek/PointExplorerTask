package com.joklek.pointexplorer;

import com.joklek.pointexplorer.config.ConsoleConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ConsoleConfig.class);
        ctx.getBean(PointExplorer.class).run();
        ctx.close();
    }
}
