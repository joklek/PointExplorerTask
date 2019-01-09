package com.joklek.pointexplorer.modules;

import com.joklek.pointexplorer.exception.IncorrectModuleArgumentException;
import com.joklek.pointexplorer.repo.ShapeRepository;
import com.joklek.pointexplorer.shape.Circle;
import com.joklek.pointexplorer.shape.factory.CircleFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.stream.Stream;

import static java.lang.Double.parseDouble;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class CircleModuleTest {
    @Mock
    private ShapeRepository repo;

    @Mock
    private CircleFactory factory;

    @InjectMocks
    private CircleModule module;

    @BeforeEach
    public void init() {
        initMocks(this);
    }

    @Test
    public void shouldParseArgumentsCorrectly() throws IncorrectModuleArgumentException {
        String x_s = "0", y_s = "0", radius_s = "1";
        Circle mockCircle = mock(Circle.class);
        double x = parseDouble(x_s), y = parseDouble(y_s), radius = parseDouble(radius_s);
        String[] arguments = new String[]{x_s, y_s, radius_s};

        when(factory.createNew(x, y, radius))
                .thenReturn(mockCircle);

        module.parseCommand(arguments);

        verify(factory).createNew(x, y, radius);
        verify(repo).addNew(mockCircle);
    }

    @Test
    public void shouldThrowExceptionWhenNotEnoughArguments() {
        String[] arguments = new String[]{"0", "1"};

        Executable parseExecution = () -> module.parseCommand(arguments);
        assertThrows(IncorrectModuleArgumentException.class, parseExecution);
    }

    @Test
    public void shouldThrowExceptionWhenTooManyEnoughArguments() {
        String[] arguments = new String[]{"0", "1", "2", "3"};

        Executable parseExecution = () -> module.parseCommand(arguments);
        assertThrows(IncorrectModuleArgumentException.class, parseExecution);
    }

    private static Stream<Arguments> badRadius() {
        return Stream.of(
                arguments("0"),
                arguments("-1"),
                arguments("-100")
        );
    }
    @ParameterizedTest
    @MethodSource("badRadius")
    public void shouldThrowExceptionWhenBadRadius(String badRadius) {
        String[] arguments = new String[]{"0", "1", badRadius};

        Executable parseExecution = () -> module.parseCommand(arguments);
        assertThrows(IncorrectModuleArgumentException.class, parseExecution);
    }

    private static Stream<Arguments> incorrectInput() {
        return Stream.of(
                arguments("0", "d", "0"),
                arguments("d", "0", "0"),
                arguments("0", "0", "d")
        );
    }
    @ParameterizedTest
    @MethodSource("incorrectInput")
    public void shouldThrowExceptionWhenArgumentsNotCorrectDoubles(String x_s, String y_s, String radius_s) {
        String[] arguments = new String[]{x_s, y_s, radius_s};

        Executable parseExecution = () -> module.parseCommand(arguments);
        assertThrows(IncorrectModuleArgumentException.class, parseExecution);
    }
}