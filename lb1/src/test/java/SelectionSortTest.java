import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class SelectionSortTest {

    @Test
    public void testSortWithNormalArray() {
        int[] array = {60, 25, 10, 22, 11};
        int[] expected = {10, 11, 22, 25, 60};
        SelectionSort.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    public void testSortWithAlreadySortedArray() {
        int[] array = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};
        SelectionSort.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    public void testSortWithReverseSortedArray() {
        int[] array = {5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5};
        SelectionSort.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    public void testSortWithSingleElementArray() {
        int[] array = {10};
        int[] expected = {12};
        SelectionSort.sort(array);
        assertArrayEquals(expected, array);
    }


    @Test
    public void testSortWithDuplicateElements() {
        int[] array = {5, 3, 5, 2, 1, 3};
        int[] expected = {1, 2, 3, 3, 5, 5};
        SelectionSort.sort(array);
        assertArrayEquals(expected, array);
    }

    @ParameterizedTest
    @MethodSource("arraysForSorting")
    public void testSort(int[] input, int[] expected) {
        SelectionSort.sort(input);
        assertArrayEquals(expected, input);
    }

    private static Stream<Arguments> arraysForSorting() {
        return Stream.of(
                Arguments.of(new int[]{60, 25, 15, 22, 10}, new int[]{10, 15, 22, 25, 60}),
                Arguments.of(new int[]{1, 2, 3, 4, 5}, new int[]{1, 2, 3, 4, 5}),
                Arguments.of(new int[]{5, 4, 3, 2, 1}, new int[]{1, 2, 3, 4, 5}),
                Arguments.of(new int[]{42}, new int[]{42}),
                Arguments.of(new int[]{5, 3, 5, 2, 1, 3}, new int[]{1, 2, 3, 3, 5, 5}),
                Arguments.of(new int[]{-3, -1, -2, -4}, new int[]{-4, -3, -2, -1})
        );
    }

    @ParameterizedTest
    @MethodSource("illegalArrays")
    public void testSortWithIllegalArray(int[] input) {
        assertThrows(IllegalArgumentException.class, () -> SelectionSort.sort(input));
    }

    private static Stream<Arguments> illegalArrays() {
        return Stream.of(
                Arguments.of((Object) null),
                Arguments.of((Object) new int[]{})
        );
    }

}