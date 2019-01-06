package com.joklek.pointexplorer.modules;

import com.joklek.pointexplorer.exception.IncorrectModuleArgumentException;
import com.joklek.pointexplorer.repo.ShapeRepository;
import com.joklek.pointexplorer.shape.Point;
import com.joklek.pointexplorer.shape.Triangle;
import com.joklek.pointexplorer.shape.factory.TriangleFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.stream.Stream;

import static java.lang.Double.parseDouble;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class TriangleModuleTest {
    @Mock
    private ShapeRepository repo;

    @Mock
    private TriangleFactory factory;

    @InjectMocks
    private TriangleModule module;

    @BeforeEach
    public void init() {
        initMocks(this);
    }

    @Test
    public void shouldParseArgumentsCorrectly() throws IncorrectModuleArgumentException {
        Triangle mockTriangle = mock(Triangle.class);
        String p0x_s = "0", p0y_s = "1";
        String p1x_s = "2", p1y_s = "3";
        String p2x_s = "4", p2y_s = "5";
        double p0x = parseDouble(p0x_s), p0y = parseDouble(p0y_s);
        double p1x = parseDouble(p1x_s), p1y = parseDouble(p1y_s);
        double p2x = parseDouble(p2x_s), p2y = parseDouble(p2y_s);
        String[] arguments = new String[]{p0x_s, p0y_s, p1x_s, p1y_s, p2x_s, p2y_s};
        ArgumentCaptor<Point> captor = ArgumentCaptor.forClass(Point.class);

        when(factory.createNew(any(), any(), any()))
                .thenReturn(mockTriangle);

        module.parseCommand(arguments);

        verify(repo).addNew(mockTriangle);
        verify(factory).createNew(captor.capture(), captor.capture(), captor.capture());
        List<Point> capturedValues = captor.getAllValues();
        assertThat(capturedValues.get(0).getX(), is(p0x));
        assertThat(capturedValues.get(0).getY(), is(p0y));
        assertThat(capturedValues.get(1).getX(), is(p1x));
        assertThat(capturedValues.get(1).getY(), is(p1y));
        assertThat(capturedValues.get(2).getX(), is(p2x));
        assertThat(capturedValues.get(2).getY(), is(p2y));
    }

    @Test
    public void shouldThrowExceptionWhenNotEnoughArguments() {
        String[] arguments = new String[]{"0", "1", "2", "3", "4"};

        Executable parseExecution = () -> module.parseCommand(arguments);
        assertThrows(IncorrectModuleArgumentException.class, parseExecution);
    }

    @Test
    public void shouldThrowExceptionWhenTooManyEnoughArguments() {
        String[] arguments = new String[]{"0", "1", "2", "3", "4", "5", "6"};

        Executable parseExecution = () -> module.parseCommand(arguments);
        assertThrows(IncorrectModuleArgumentException.class, parseExecution);
    }

    private static Stream<Arguments> incorrectInput() {
        return Stream.of(
                arguments("d", "0", "0", "0", "0", "0"),
                arguments("0", "d", "0", "0", "0", "0"),
                arguments("0", "0", "d", "0", "0", "0"),
                arguments("0", "0", "0", "d", "0", "0"),
                arguments("0", "0", "0", "0", "d", "0"),
                arguments("0", "0", "0", "0", "0", "d")
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