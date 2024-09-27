package ss14.insertion_sort_example;

public class InsertionSortExample {

    public static void insertionSort(int[] list) {
        for (int i = 1; i < list.length; i++) {
            int key = list[i]; // Lưu giá trị cần chèn
            int j = i - 1;

            // Di chuyển các phần tử lớn hơn key sang phải
            while (j >= 0 && list[j] > key) {
                list[j + 1] = list[j];
                j--;
            }
            list[j + 1] = key; // Chèn key vào vị trí thích hợp
        }
    }

    public static void main(String[] args) {
        int[] array = {5, 3, 8, 1, 2};

        System.out.println("Mảng trước khi sắp xếp:");
        for (int num : array) {
            System.out.print(num + " ");
        }

        insertionSort(array); // Gọi phương thức sắp xếp

        System.out.println("\nMảng sau khi sắp xếp:");
        for (int num : array) {
            System.out.print(num + " ");
        }
    }
}