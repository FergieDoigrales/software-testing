public class SelectionSort {

    public static void sort(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array can't be empty or null");
        }
        int len = array.length;
        for (int i = 0; i < len - 1; i++) {
            int minPos = i;
            for (int j = i + 1; j < len; j++) {
                if (array[j] < array[minPos]) {
                    minPos = j;
                }
            }
            int temp = array[minPos];
            array[minPos] = array[i];
            array[i] = temp;
        }
    }
}