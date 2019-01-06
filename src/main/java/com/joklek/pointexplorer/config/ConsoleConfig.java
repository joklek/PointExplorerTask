package com.joklek.pointexplorer.config;

import com.joklek.pointexplorer.PointExplorer;
import com.joklek.pointexplorer.modules.*;
import com.joklek.pointexplorer.repo.DefaultShapeRepo;
import com.joklek.pointexplorer.repo.ShapeRepository;
import com.joklek.pointexplorer.shape.factory.*;
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
    public CircleFactory circleFactory() {
        return new CircleFactory();
    }

    @Bean
    public ConsoleModule triangleModule() {
        return new TriangleModule();
    }

    @Bean
    public TriangleFactory triangleFactory() {
        return new TriangleFactory();
    }

    @Bean
    public ConsoleModule donutModule() {
        return new DonutModule();
    }

    @Bean
    public DonutFactory donutFactory() {
        return new DonutFactory();
    }

    @Bean
    public ConsoleModule helpModule() {
        return new HelpModule();
    }

    @Bean
    public EllipseModule ellipseModule() {
        return new EllipseModule();
    }

    @Bean
    public EllipseFactory ellipseFactory() {
        return new EllipseFactory();
    }

    @Bean
    public PointExplorer pointExplorer(List<ConsoleModule> modules) {
        return new PointExplorer(modules);
    }
}
