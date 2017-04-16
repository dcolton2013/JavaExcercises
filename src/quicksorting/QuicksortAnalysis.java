//package quicksorting;
import java.util.*;

/**
 * @authors Donovan Colton
 *
 *
 * 3/21/17
 * Analysis of quicksorting algorithms (Hoare's and Lomuto's)
 */
public class QuicksortAnalysis {
    static long start,finish;
    static int[] nums1, nums2 = null;
    static int swaps, comparisons = 0;
    
    public static void main(String[] args) {
          //tests
//        nums1 = new int[10];
//        populate(nums1);
//        hoares(nums1);
//        System.out.print("\t");
//        for (int num: nums1)
//            System.out.print(num + "  ");
//        System.out.println("\n");
//        
//        
//        populate(nums1);
//        lomuto(nums1);
//        System.out.print("\t");
//        for (int num: nums1)
//            System.out.print(num + "  ");
        int[] lengths = new int[10];
        lengths[0] = 100;  lengths[1] = 500;
        lengths[2] = 700;  lengths[3] = 1000;
        lengths[4] = 2000; lengths[5] = 5000;
        lengths[6] = 6000; lengths[7] = 7000;
        lengths[8] = 8000; lengths[9] = 10000;
        
        int i = 0;
        while (i != 10){
            System.out.println("# of elements: " + lengths[i]);
            nums1 = new int[lengths[i]];
            nums2 = new int[lengths[i++]];
            
            populate(nums1);
            populate(nums2);
            
            hoares(nums1);
            lomuto(nums2);
            
            System.out.println();
        }
        
    }
    
    //lomutos methods
    public static void lomuto(int[] nums){
        System.out.println("\tLomuto: ");
        lomuto(nums, 0, nums.length - 1);
        finish();
        System.out.println("\t start:\t\t\t"+start);
        System.out.println("\t finish:\t\t"+finish);
        System.out.println("\t time elapsed (ns):\t"+(finish-start));
        System.out.println("\t comparisons:\t\t"+comparisons);
        System.out.println("\t swaps:\t\t\t"+swaps);
        comparisons = 0;
        swaps = 0;
    }

    public static void lomuto(int[] nums, int low, int high){
        start();
        if (low < high){
            int pivot = lomutoPivot(nums,low,high);
            lomuto(nums,low,pivot-1);
            lomuto(nums,pivot+1,high);
        }
    }

    public static int lomutoPivot(int[] nums, int low, int high){
        int pivot = nums[high];
        int i = low;
        for (int j = low; j<high;j++){
            comparisons++;
            if (nums[j] <= pivot){
                swap(nums,i,j);
                swaps++;
                i++;
            }
        }
        swap(nums,i,high);
        swaps++;
        return i;
    }
    
    //hoares methods
    public static void hoares(int[] nums){
        System.out.println("\tHoares: ");
        hoares(nums, 0, nums.length-1);
        finish();
        System.out.println("\t start:\t\t\t"+start);
        System.out.println("\t finish:\t\t"+finish);
        System.out.println("\t time elapsed (ns):\t"+(finish-start));
        System.out.println("\t comparisons:\t\t"+comparisons);
        System.out.println("\t swaps:\t\t\t"+swaps);
        comparisons = 0;
        swaps = 0;
    }

    public static void hoares(int[] nums, int low, int hi){
        start();
        if (low < hi){
            int pivot = hoaresPivot(nums,low,hi);
            hoares(nums,low,pivot);
            hoares(nums,pivot+1,hi);
        }
    }

    public static int hoaresPivot(int[] nums, int low, int high){
        int pivot = nums[low];
        int i = low-1;
        int j = high+1;
        
        while (true) {
            do {
                i++;
                comparisons++;
            } while (nums[i] < pivot);
            do {
                j--;
                comparisons++;
            } while (nums[j] > pivot);
            if (i >= j) {
                return j;
            }
            swap(nums,i, j);
            swaps++;
        }
    }
    
    //swap elements in the array
    private static void swap(int nums[], int i, int j) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }
    
    //method to time algorithms
    private static void start(){
        start=System.nanoTime();
    }

    private static void finish(){
        finish=System.nanoTime();
    }
    
    //populate arrays
    private static void populate(int[] array){
        Random r = new Random();
        for (int i = 0; i < array.length - 1; i++) {
            array[i] = r.nextInt(10000);
        }
    }

}
