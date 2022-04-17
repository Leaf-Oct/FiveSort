package cn.leaf.sort;

import java.util.Random;

public class Sort {
    //	測試數據量控制在int的範圍内，即21億多
    private static int length;

    private static void setLength(int number[]) {
        length = number.length;
    }
    //	OK
    public static void chooseSort(int number[]) {
        setLength(number);
        for (int i = 0; i < length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < length; j++) {
                if (number[j] < number[min]) {
                    min = j;
                }
            }
			if(min!=i) {
				int a=number[min];
				number[min]=number[i];
				number[i]=a;
			}
        }
    }
    //	OK
    public static void bubbleSort(int number[]) {
        setLength(number);
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (number[j] > number[j + 1]) {
                    int a = number[j];
                    number[j] = number[j + 1];
                    number[j + 1] = a;
                }
            }

        }

    }

    private static int[] merge_sort_array, merge_sort_used;
    //	OK
    public static void mergeSort(int number[]) {
        setLength(number);
        merge_sort_array = number;
        merge_sort_used = new int[length];
        mergeSort(0, length - 1);
    }

    private static void mergeSort(int left, int right) {
        if (left == right) {
            return;
        }
        int middle = (left + right) / 2;
        mergeSort(left, middle);
        mergeSort(middle + 1, right);
        int left_begin = left, right_begin = middle + 1;
        int index = 0;
        while (left_begin <= middle || right_begin <= right) {
            if (left_begin > middle) {
                merge_sort_used[index++] = merge_sort_array[right_begin++];
            } else if (right_begin > right) {
                merge_sort_used[index++] = merge_sort_array[left_begin++];
            } else {
                if (merge_sort_array[left_begin] < merge_sort_array[right_begin]) {
                    merge_sort_used[index++] = merge_sort_array[left_begin++];
                } else {
                    merge_sort_used[index++] = merge_sort_array[right_begin++];
                }
            }
        }
        for (int i = 0, j = left; i < index; i++, j++) {
            merge_sort_array[j] = merge_sort_used[i];
        }
    }

    public static void quickSort(int number[]) {
        setLength(number);
        quickSort(number, 0, length-1);
    }
    private static void quickSort(int number[],int left,int right) {
        if(left<right) {
            int pivot_location=getPivotPosition(number, left, right);
            quickSort(number, left, pivot_location-1);
            quickSort(number, pivot_location+1, right);
        }

    }
    private static int getPivotPosition(int number[],int left,int right) {
        int pivot=number[right];
        int real_pivot_index=left;
        for(int i=left;i<right;i++) {
//			第99行不要，经测试会延长时间
            if(number[i]<pivot) {
//				if(i!=real_pivot_index) {
                int temp=number[i];
                number[i]=number[real_pivot_index];
                number[real_pivot_index]=temp;
//				}
                real_pivot_index++;
            }
        }
        int temp=number[real_pivot_index];
        number[real_pivot_index]=number[right];
        number[right]=temp;
        return real_pivot_index;
    }
    public static void insertSort(int number[]) {
        setLength(number);
        for (int i = 1; i < length; i++) {
            int number_to_sort = number[i];
            int index_before_i = i - 1;
            while (index_before_i >= 0 && number_to_sort < number[index_before_i]) {
                number[index_before_i + 1] = number[index_before_i];
                index_before_i--;
            }
            number[index_before_i+1]=number_to_sort;
        }
    }
    private static Random r=new Random();
    public static void randomArray(int number[]) {
        int length=number.length;
        for(int i=0;i<length;i++) {
            number[i]=r.nextInt();
        }
    }
}