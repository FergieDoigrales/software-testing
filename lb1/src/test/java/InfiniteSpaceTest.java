import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class InfiniteSpaceTest {

    @ParameterizedTest
    @MethodSource("valuesInfiniteSpaceProperties")
    public void testsForInfiniteSpaceProperties(boolean expectedIsInfinity, Infinity infinity) {
        assertEquals(expectedIsInfinity, infinity.isInfinity());
    }

    private static Stream<Arguments> valuesInfiniteSpaceProperties() {
        return Stream.of(
                Arguments.of(true, new Infinity(true, false, false)),
                Arguments.of(false, new Infinity(true, false, true)),
                Arguments.of(false, new Infinity(true, true, false)),
                Arguments.of(false, new Infinity(true, true, true)),
                Arguments.of(false, new Infinity(false, false, false)),
                Arguments.of(false, new Infinity(false, false, true)),
                Arguments.of(false, new Infinity(false, true, false)),
                Arguments.of(false, new Infinity(false, true, true))
        );
    }

    @ParameterizedTest
    @MethodSource("spaces")
    void testDescribesInfinityWell(boolean expectedResult, Space space) {
        assertEquals(expectedResult, space.describesInfinityWell());
    }

    private static Stream<Arguments> spaces() {
        return Stream.of(
                Arguments.of(false, new Infinity(true, false, false)),
                Arguments.of(false, new Infinity(true, true, false)),
                Arguments.of(false, new Infinity(true, false, true)),
                Arguments.of(false, new Infinity(true, true, true)),
                Arguments.of(false, new Infinity(false, false, false)),

                Arguments.of(true, new Room(1001.0, 1001.0, 1001.0)),
                Arguments.of(false, new Room(1001.0, 1000.0, 1000.0)),
                Arguments.of(false, new Room(10.0, 10.0, 10.0))
        );
    }

    @Test
    void testCanFitInRoom() {
        Aeromobile aeromobile = new Aeromobile(2.0, 3.0, 4.0);
        Room smallRoom = new Room(1.0, 1.0, 1.0);
        Room largeRoom = new Room(10.0, 10.0, 10.0);
        Room sameRoom = new Room(2.0, 3.0, 4.0);

        assertFalse(aeromobile.canFitInRoom(smallRoom));
        assertTrue(aeromobile.canFitInRoom(largeRoom));
        assertFalse(aeromobile.canFitInRoom(sameRoom));
    }

    @ParameterizedTest
    @MethodSource("roomSizes")
    void testCalculateIsHuge(double width, double height, double length, boolean expectedIsHuge) {
        Room room = new Room(width, height, length);
        assertEquals(expectedIsHuge, room.isHuge());
    }

    private static Stream<Arguments> roomSizes() {
        return Stream.of(
                Arguments.of(10.0, 10.0, 10.0, false),
                Arguments.of(999.0, 999.0, 999.0, false),
                Arguments.of(1001.0, 1001.0, 1001.0, true),
                Arguments.of(1001.0, 1001.0, 999.0, false),
                Arguments.of(1500.0, 999.0, 1500.0, false),

                Arguments.of(Double.POSITIVE_INFINITY, 1001.0, 1001.0, false),
                Arguments.of(1001.0, Double.POSITIVE_INFINITY, 1001.0, false),
                Arguments.of(1001.0, 1001.0, Double.POSITIVE_INFINITY, false),

                Arguments.of(Double.NaN, 1001.0, 1001.0, false),
                Arguments.of(1001.0, Double.NaN, 1001.0, false),
                Arguments.of(1001.0, 1001.0, Double.NaN, false)
        );
    }

    @ParameterizedTest
    @MethodSource("invalidSizes")
    void testInvalidDimensions(double width, double height, double length) {
        assertThrows(IllegalArgumentException.class, () -> new Room(width, height, length));
        assertThrows(IllegalArgumentException.class, () -> new Aeromobile(width, height, length));
    }
    private static Stream<Arguments> invalidSizes() {
        return Stream.of(
                Arguments.of(0.0, 2.0, 3.0),
                Arguments.of(1.0, 2.0, -3.0)
        );
    }

    @Test
    void testDistanceForNan() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Distance(Double.NaN);;
        });
    }

    @Test
    void testMeaningfulForInfiniteDistance() {
        Distance finiteDistance = new Distance(1000.0);
        Distance infiniteDistance = new Distance(Double.POSITIVE_INFINITY);
        assertTrue(finiteDistance.isMeaningful());
        assertFalse(infiniteDistance.isMeaningful());
    }

    @Test
    void testSkyIsVisibleForFiniteDistance() {
        Distance distance = new Distance(1000.0);
        Sky sky = new Sky(distance);
        assertFalse(sky.isVisible());
    }

    @Test
    void testSkyIsVisibleForInfiniteDistance() {
        Distance distance = new Distance(Double.POSITIVE_INFINITY);
        Sky sky = new Sky(distance);
        assertTrue(sky.isVisible());
    }

}
