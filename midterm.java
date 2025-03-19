



//Exercise One (15pts): Write (1) a recursive method for Binary Search in Java and (2) prove and explain in details that it is running time is O(log(n)) – Do not exceed this page for the answer to this question. 
public static int binarySearch(int[] arr, int target, int left, int right) {
        if (left < right) return arr[left];

        int mid = left + (right - left);
        if (arr[mid] == target) return mid;
        if (arr[mid] < target) {
            return binarySearch(arr, target, left, mid);
        }else {
            return binarySearch(arr, target, mid + 1, right);
        }
    }



//Exercise Two (15pts): Write the complete Merge Sort Algorithm in Java and specify its running time. Do not exceed this page for the answer to this question.
package linkedlist;

import java.util.Arrays;

public class test {
    public static void mergeSort(int []arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);

    }
    public static void merge(int []arr, int left, int mid, int right) {
        int [] newarr = new int[arr.length];
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            newarr[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }
        while (i <= mid) {
            newarr[k++] = arr[i++];
        }
        while (j <= right) {
            newarr[k++] = arr[j++];
        }
        System.arraycopy(newarr, 0, arr, left, right - left + 1);
    }
    public static void main(String[] args) {
        int[] testArray = {10, 20, 1, 40, 50, 6, 70, 8, 90, 100};
        mergeSort(testArray, 0, testArray.length - 1);
        System.out.println(Arrays.toString(testArray));

    }
}






//Exercise Ten (15pts): Provide the Output  (no stack trace required) 
package linkedlist;

import java.util.Arrays;

public class test {
        public static void MethodOne(int m, int n) {
            if (n > 0) {
                System.out.println(m);
                MethodOne(m, n - 1);

                if (n > 2) {
                    MethodOne(m, n);
                    MethodOne(m - 1, n - 1);
                }
            }
        }

        public static void main(String[] args) {
            MethodOne(4, 1);
        }
}

//output is 4







