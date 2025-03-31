import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Midterm {

    /*
    Exercise Zero.1 (15pts):  Write a Java method that calculates the sum of prime digits of an integer.
    The method should take an int as input and return the sum of its digits.
    You are not allowed to use arrays or convert the integer to a string.
    For example, is the input is 1793, then the output should 10 (7+3).
     */
    public static int sumPrime(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            if (digit == 2 || digit == 3 || digit == 5 || digit == 7) {
                sum += digit;
            }
            n /= 10;
        }
            return sum;
    }


    /*
    Exercise Zero.2 (15pts):
    Provide the Output of this Method (not stack trace is needed)
    f(4)
int f(int x){
{
  int y;
  if(x==0)
     return 1;
  else{
    y = 2 * f(x-1);
    return y+1;
  }
}
     */
    /*
    f(4)
        --> 2f(3) +1               2*15 + 1 = 31
            --> 2f(2) +1           2*7 + 1 = 15
                --> 2f(1) +1       2*3 + 1  = 7
                    --> 2f(0) +1   2+1 = 3

     */

    /*
    Exercise One (15pts): Write (1) a recursive method for Binary Search in Java and
    (2) prove and explain in details that it is running time is O(log(n)) –
    Do not exceed this page for the answer to this question.
     */
    public static int BinarySearch(int[] arr, int target, int low, int high) {
        //base case
        if (low > high) {
            return -1;
        }
        int mid = (low + high) >>> 1;
        if (arr[mid] == target) {
            return mid;
        }
        if (arr[mid] > target) {
            return BinarySearch(arr, target, low, mid);
        }else {
            return BinarySearch(arr, target, mid + 1, high);
        }
    }
    //each time binary search will divide array into two part in this loop we will have O(log(n))

    /*
    Exercise Two (15pts): Write the complete Merge Sort Algorithm in Java and specify its running time.
    Do not exceed this page for the answer to this question.
     */
    public static void MergeSort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = (low + high) >>> 1;
        MergeSort(arr, low, mid);
        MergeSort(arr, mid + 1, high);
        merge(arr, low, mid, high);
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int[] a = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            a[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }
        while (i <= mid) {
            a[k++] = arr[i++];
        }
        while (j <= right) {
            a[k++] = arr[j++];
        }
        System.arraycopy(a, 0, arr, left, right - left + 1);
    }

    /*
    Exercise Three (20pts): Show the recursive tree for applying the algorithm to the following array:
    7  7 | 2 |   9 |  4 |  3 |  8 |  6 |  1 |
    Label the order of the calls in the tree and write the first three pushes in the Stack (not the complete stack).
    Do not exceed this page for the answer to this question.
     */
    /*
    1: MergeSort(0,8)
├─ 2: MergeSort(0,4)
│   ├─ 3: MergeSort(0,2)
│   │    ├─ 4: MergeSort(0,1)
│   │    │    ├─ 5: MergeSort(0,0)
│   │    │    └─ 6: MergeSort(1,1)
│   │    └─ 7: MergeSort(2,2)
│   └─ 8: MergeSort(3,4)
│        ├─ 9: MergeSort(3,3)
│        └─ 10: MergeSort(4,4)
└─ 11: MergeSort(5,8)
    ├─ 12: MergeSort(5,6)
    │     ├─ 13: MergeSort(5,5)
    │     └─ 14: MergeSort(6,6)
    └─ 15: MergeSort(7,8)
          ├─ 16: MergeSort(7,7)
          └─ 17: MergeSort(8,8)
     */

    /*
    Exercise Four (15pts): Write the partition method in quicksort
    int index = partition (array, low, high) (do not share the signature of the method)
    Provide it is running time.
     */
    public static int partition(int[] arr, int left, int right) {
        int pv = arr[left];
        int i = left;
        int j = right;
        while (i < j) {
            while (i<j && arr[j] >= pv) {
                j --;
            }
            while (i<j && arr[i] <= pv) {
                i ++;
            }
            swap(arr, i, j);
        }
        swap(arr, left, i);
        return i;
    }
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    /*
    Exercise Five (15pts): Use the signature of method of exercise four to write a
    complete recursive O(nLog(n)) QuickSort Algorithm.
     */
    public static void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    public static void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int p = partition(arr, left, right);
        quickSort(arr, left, p - 1);
        quickSort(arr, p + 1, right);
    }

    /*
    Exercise Six (15pts): Show the first three recursive calls of applying the quicksort stack trace and
    how the array changes for the first three recursive calls when applying the algorithm to this array,
    Provide the calls of the quicksort method(s), pivots, how the array changes, and partition method(s)...
    5 | 2 | 9 |  4 |  3 |  8 |
     */

    /*quicksort(arr, 0, 5)
│
├─ Partition (pivot = 8) → p = 4, array becomes [5, 2, 4, 3, 8, 9]
│   ├─ Left: quicksort(arr, 0, 3)
│   │   │
│   │   ├─ Partition (pivot = 3) → p = 1, array becomes [2, 3, 4, 5, 8, 9]
│   │   │   ├─ Left: quicksort(arr, 0, 0)  (Base case)
│   │   │   └─ Right: quicksort(arr, 2, 3)
│   │   │         │
│   │   │         ├─ Partition (pivot = 5) → p = 3, array remains [2, 3, 4, 5, 8, 9]
│   │   │         │   ├─ Left: quicksort(arr, 2, 2)  (Base case)
│   │   │         │   └─ Right: quicksort(arr, 4, 3) (Base case)
│   │   │
│   └─ Right: quicksort(arr, 5, 5)  (Base case)

     */

    /*
    Exercise Seven (30pts): Provide the full implementation of two algorithms with different running times that take
    an array of size n and find all pairs of indices that are closest to each other. One of the algorithms must be
    in-place and have a logarithmic running time. Specify the running time for each algorithm. If there are multiple
    pairs with the same minimum distance, display all of them (with their indices). You may use any methods or
    algorithms from previous exercises in this exam.
     */
    public static class Pair {
        public int index1;
        public int index2;

        public Pair(int index1, int index2) {
            this.index1 = index1;
            this.index2 = index2;
        }

        @Override
        public String toString() {
            return "(" + index1 + ", " + index2 + ")";
        }
    }

    // Helper class for the efficient method: stores a value with its original index.
    public static class ValueIndex implements Comparable<ValueIndex> {
        int value;
        int index;

        public ValueIndex(int value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(ValueIndex other) {
            return Integer.compare(this.value, other.value);
        }
    }
    // ----------------------------------------------

    /**
     * Brute Force Approach
     *
     * This method examines every possible pair of indices in the array.
     * For each pair (i, j) (with i < j) it computes the absolute difference.
     * If a new minimum is found, the list of pairs is cleared and updated.
     * Otherwise, if the same minimum is encountered, the pair is added.
     *
     * Running Time: O(n^2)
     */
    public static ArrayList<Pair> findClosestPairsBrute(int[] arr) {
        ArrayList<Pair> pairs = new ArrayList<>();
        int n = arr.length;
        int minDiff = Integer.MAX_VALUE;

        // Check every pair (i, j) with i < j.
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int diff = Math.abs(arr[i] - arr[j]);
                if (diff < minDiff) {
                    minDiff = diff;
                    pairs.clear();
                    pairs.add(new Pair(i, j));
                } else if (diff == minDiff) {
                    pairs.add(new Pair(i, j));
                }
            }
        }
        System.out.println("Minimum difference (Brute Force): " + minDiff);
        return pairs;
    }

    /**
     * Efficient In-Place Approach
     *
     * This method first creates an array of ValueIndex objects so that each element is paired
     * with its original index. The array is then sorted in place by the element values using
     * Arrays.sort (which runs in O(n log n) time). Because the closest values must appear adjacent
     * in the sorted order, only adjacent pairs are scanned to determine the minimum difference.
     *
     * Running Time: O(n log n) (due to the sort step)
     */
    public static ArrayList<Pair> findClosestPairsEfficient(int[] arr) {
        int n = arr.length;
        ArrayList<Pair> pairs = new ArrayList<>();
        if (n < 2) {
            return pairs; // No pairs exist if fewer than 2 elements.
        }

        // Create an array of ValueIndex objects to track values and original indices.
        ValueIndex[] vi = new ValueIndex[n];
        for (int i = 0; i < n; i++) {
            vi[i] = new ValueIndex(arr[i], i);
        }

        // In-place sort by the value (O(n log n)).
        Arrays.sort(vi);

        int minDiff = Integer.MAX_VALUE;
        // Scan adjacent elements only.
        for (int i = 1; i < n; i++) {
            int diff = Math.abs(vi[i].value - vi[i - 1].value);
            if (diff < minDiff) {
                minDiff = diff;
                pairs.clear();
                pairs.add(new Pair(vi[i - 1].index, vi[i].index));
            } else if (diff == minDiff) {
                pairs.add(new Pair(vi[i - 1].index, vi[i].index));
            }
        }
        System.out.println("Minimum difference (Efficient): " + minDiff);
        return pairs;
    }

    /*Exercise Eight (15pts):

What is the running time of the following code in big-0 for the 4 Questions bellow (8.1 to 8.4)
Write the running time to the left of each question in a big font.


     */

//    for (int i = 0; i < n; i++) {
//    for (int j = 0; j < n; j++) {
//        if (/* some condition */) {
//            // O(1) work
//        } else {
//            // O(1) work
//        }
//        System.out.println("Printing"); // O(1)
//    }
//}O(n) \times O(n) \;=\; O(n^2).

    //for (int i = 0; i < n; i++) {
    //    for (int j = 0; j < i; j++) {
    //        // O(1) work
    //    }
    //    System.out.println("Printing"); // O(1)
    //}Total time complexity: O(n^2).

    //while (i < n) {
    //    i = i * 2;               // i doubles each time
    //    // possibly some O(1) work, e.g., an if/else
    //
    //    while (j < n) {
    //        j = j + 1;           // increments j
    //        // possibly some O(1) work, e.g., another if/else
    //    }
    //}
    //return i;Overall: O(n \,\log n)

    /*Exercise Nine (45pts):  Write three algorithms (as methods in Java) that take an unsorted
    array of integers and a target integer and return the rank of the target in the array.
    The rank of a number is defined as its position in a sorted version of the array, starting from 1.
    If the target number appears multiple times, return the rank of its first occurrence in the sorted array.
    Only one of the algorithms should involve sorting. Provide the running time of each algorithm.
    If the Target Integer does not exist in the array, your method should return -1.
    You are allowed to use and call the methods you created in the previous exercises in this exam.
     */
    //Algorithm 1 – Method in Java
    public static int rankUsingSort(int[] arr, int target) {
        // Create a copy so as not to alter the original array
        int[] copy = Arrays.copyOf(arr, arr.length);
        Arrays.sort(copy); // O(n log n)
        // Find the first occurrence of target in the sorted array.
        // Since the array is sorted, duplicates of target will be adjacent.
        for (int i = 0; i < copy.length; i++) { // O(n)
            if (copy[i] == target) {
                return i + 1; // Rank is index+1 (since rank starts at 1)
            }
        }
        return -1; // target not found
    }

    //Algorithm 2 – Method in Java
    public static int rankByCounting(int[] arr, int target) {
        boolean found = false;
        int countLess = 0;
        for (int x : arr) { // O(n)
            if (x < target) {
                countLess++;
            }
            if (x == target) {
                found = true;
            }
        }
        if (!found) {
            return -1; // target not present
        }
        return countLess + 1; // Rank = count of numbers less than target + 1
    }

    //Algorithm 3 – Method in Java
    public static int rankByDoubleLoop(int[] arr, int target) {
        int rank = Integer.MAX_VALUE;
        boolean found = false;

        // Outer loop: check each element.
        for (int i = 0; i < arr.length; i++) { // O(n)
            if (arr[i] == target) {
                found = true;
                int countLess = 0;
                // Inner loop: count numbers less than arr[i]
                for (int j = 0; j < arr.length; j++) { // O(n)
                    if (arr[j] < arr[i]) {
                        countLess++;
                    }
                }
                rank = Math.min(rank, countLess + 1);
            }
        }
        return found ? rank : -1;
    }


    //Exercise Ten (15pts): Provide the Output  (no stack trace required)
    public static void MethodOne(int m, int n) {
        if (n > 0) {
            System.out.println(m);
            MethodOne(m, n - 1);

            if (n >= 2) {
                MethodOne(m, n);
            }

            MethodOne(m - 1, n - 1);
        }
    }

    //4

    /*
    Exercise Eleven (20pts): Implement a recursive algorithm in Java to determine whether a
    given phrase remains identical when read forward and backward. The function should:
1.	Ignore spaces, punctuation, and case sensitivity.
2.	Operate in O(n) time complexity.
3.	Avoid using additional data structures such as arrays or lists (including no auxiliary storage for cleaned input).
4.	Handle Unicode characters (e.g., accented letters like "É", "ü").
5.	Efficiently process very large strings to minimize recursion depth.
Function Signature:
boolean isAdvancedMirrorRecursive(String phrase)
Examples
✅ "Mr. Owl ate my metal worm" → true
✅ "Was it a car or a cat I saw?" → true
✅ "Yo, Banana Boy!" → true
❌ "This is not reversible" → false
     */

    // Public method to check if a phrase is a palindrome (ignoring spaces, punctuation, and case)
    public static boolean isAdvancedMirrorRecursive(String phrase) {
        if (phrase == null) {
            return false;
        }
        return isMirror(phrase, 0, phrase.length() - 1);
    }

    // Recursive helper that checks characters from the left and right indices.
    private static boolean isMirror(String s, int left, int right) {
        // Base case: If pointers have met or crossed, all relevant characters have been compared.
        if (left >= right) {
            return true;
        }

        char leftChar = s.charAt(left);
        char rightChar = s.charAt(right);

        // Skip non-alphanumeric characters on the left.
        if (!isAlphanumeric(leftChar)) {
            return isMirror(s, left + 1, right);
        }

        // Skip non-alphanumeric characters on the right.
        if (!isAlphanumeric(rightChar)) {
            return isMirror(s, left, right - 1);
        }

        // Compare the characters in a case-insensitive manner.
        if (Character.toLowerCase(leftChar) != Character.toLowerCase(rightChar)) {
            return false;
        }

        // Recurse inward.
        return isMirror(s, left + 1, right - 1);
    }

    // Helper method to check if a character is alphanumeric (without using isLetterOrDigit)
    // It uses regex matching to see if the character is a Unicode letter (\p{L}) or digit (\p{Nd}).
    private static boolean isAlphanumeric(char c) {
        return String.valueOf(c).matches("[\\p{L}\\p{Nd}]");
    }



        // A simple main to demonstrate the function.
        public static void main(String[] args) {
            String[] examples = {
                    "Mr. Owl ate my metal worm",
                    "Was it a car or a cat I saw?",
                    "Yo, Banana Boy!",
                    "This is not reversible"
            };

            for (String phrase : examples) {
                System.out.println("\"" + phrase + "\" → " + isAdvancedMirrorRecursive(phrase));
            }
        }
        
    /*
    Exercise Twelve (20pts): Write a recursive and in-place algorithm implemented in Java or
    pseudo-code to reverse a stack of characters using recursion. You are not allowed to use
    loop constructs like while, for..etc.
     */
    // Recursively reverses the stack in-place.
    public static void reverseStack(Stack<Character> stack) {
        // Base case: if the stack is empty, nothing to reverse.
        if (stack.isEmpty()) {
            return;
        }
        // Remove the top element.
        char top = stack.pop();
        // Recursively reverse the remaining stack.
        reverseStack(stack);
        // Insert the removed element at the bottom.
        insertAtBottom(stack, top);
    }

    // Recursively inserts 'item' at the bottom of the stack.
    private static void insertAtBottom(Stack<Character> stack, char item) {
        // Base case: if the stack is empty, push the item.
        if (stack.isEmpty()) {
            stack.push(item);
            return;
        }
        // Temporarily remove the top element.
        char top = stack.pop();
        // Recursively call to reach the bottom.
        insertAtBottom(stack, item);
        // Push the temporarily removed element back.
        stack.push(top);
    }
}
