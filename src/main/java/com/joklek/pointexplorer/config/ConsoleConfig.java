package com.joklek.pointexplorer.config;

import com.joklek.pointexplorer.PointExplorer;
import com.joklek.pointexplorer.modules.*;
import com.joklek.pointexplorer.repo.DefaultShapeRepo;
import com.joklek.pointexplorer.repo.ShapeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ConsoleConfig {

    @Bean
    public ShapeRepository shapeRepository() {
        return new DefaultShapeRepo();
    }

    @Bean
    public ConsoleModule circleModule() {
        return new CircleModule();
    }

    @Bean
    public ConsoleModule triangleModule() {
        return new TriangleModule();
    }

    @Bean
    public ConsoleModule donutModule() {
        return new DonutModule();
    }

    @Bean
    public ConsoleModule helpModule() {
        return new HelpModule();
    }

    @Bean
    public PointExplorer pointExplorer(List<ConsoleModule> modules) {
        return new PointExplorer(modules);
    }
}
