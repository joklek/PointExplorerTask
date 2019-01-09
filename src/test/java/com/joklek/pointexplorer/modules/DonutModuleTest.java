package com.joklek.pointexplorer.modules;

import com.joklek.pointexplorer.exception.IncorrectModuleArgumentException;
import com.joklek.pointexplorer.repo.ShapeRepository;
import com.joklek.pointexplorer.shape.Donut;
import com.joklek.pointexplorer.shape.factory.DonutFactory;
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

class DonutModuleTest {
    @Mock
    private ShapeRepository repo;

    @Mock
    private DonutFactory factory;

    @InjectMocks
    private DonutModule module;

    @BeforeEach
    public void init() {
        initMocks(this);
    }

    @Test
    public void shouldParseArgumentsCorrectly() throws IncorrectModuleArgumentException {
        String x_s = "0", y_s = "0", holeRadius_s = "1", outerRadius_s = "2";
        Donut mockDonut = mock(Donut.class);
        double x = parseDouble(x_s), y = parseDouble(y_s), holeRadius = parseDouble(holeRadius_s), outerRadius = parseDouble(outerRadius_s);
        String[] arguments = new String[]{x_s, y_s, holeRadius_s, outerRadius_s};

        when(factory.createNew(x, y, holeRadius, outerRadius))
                .thenReturn(mockDonut);

        module.parseCommand(arguments);

        verify(factory).createNew(x, y, holeRadius, outerRadius);
        verify(repo).addNew(mockDonut);
    }

    @Test
    public void shouldThrowExceptionWhenNotEnoughArguments() {
        String[] arguments = new String[]{"0", "1", "2"};

        Executable parseExecution = () -> module.parseCommand(arguments);
        assertThrows(IncorrectModuleArgumentException.class, parseExecution);
    }

    @Test
    public void shouldThrowExceptionWhenTooManyEnoughArguments() {
        String[] arguments = new String[]{"0", "1", "2", "3", "4"};

        Executable parseExecution = () -> module.parseCommand(arguments);
        assertThrows(IncorrectModuleArgumentException.class, parseExecution);
    }

    private static Stream<Arguments> badRadius() {
        return Stream.of(
                arguments("0", "0"),
                arguments("-1", "1"),
                arguments("1", "-1"),
                arguments("-100", "-100")
        );
    }
    @ParameterizedTest
    @MethodSource("badRadius")
    public void shouldThrowExceptionWhenBadRadius(String radius1, String radius2) {
        String[] arguments = new String[]{"0", "1", radius1, radius2};

        Executable parseExecution = () -> module.parseCommand(arguments);
        assertThrows(IncorrectModuleArgumentException.class, parseExecution);
    }

    private static Stream<Arguments> incorrectInput() {
        return Stream.of(
                arguments("0", "d", "1", "2"),
                arguments("d", "0", "1", "2"),
                arguments("0", "0", "d", "2"),
                arguments("0", "0", "1", "d")
        );
    }
    @ParameterizedTest
    @MethodSource("incorrectInput")
    public void shouldThrowExceptionWhenArgumentsNotCorrectDoubles(String x_s, String y_s, String holeRadius_s, String outerRadius_s) {
        String[] arguments = new String[]{x_s, y_s, holeRadius_s, outerRadius_s};

        Executable parseExecution = () -> module.parseCommand(arguments);
        assertThrows(IncorrectModuleArgumentException.class, parseExecution);
    }
}