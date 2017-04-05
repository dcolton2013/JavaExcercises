package sorting;

/**
 *
 * @author Donovan Colton
 * 
 * implements sorting algorithms
 * 
 */
public class Sorting {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int[] nums = {200,1,4,14,16,240,109,1000,10,99,-9,4000,26,26};
        nums = insertionSort(nums);
        printNums(nums);
        
        System.out.println("\n"+mult(5));
    }
    
    public static void printNums(int[] nums){
        for (int num: nums)
            System.out.print(num + ", ");
    }
    
    static int findMax(int arr[], int n)
    {
        int mi, i;
        for (mi = 0, i = 0; i < n; ++i)
            if (arr[i] > arr[mi])
                mi = i;
        return mi;
    }
    public static int[] swapNums(int i, int j, int[] array){
        int temp;
        temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        return array;
    }
    
    //O(n^2)
    //best case O(n) - no swaps made
    public static int[] bubbleSort(int[] nums){
        //if no swaps occur, stop excecution
        boolean flag = true;
        
        //j points to the last bubbled value so we dont check it with
        //each iteration
        int j = 1;
        
        while (flag){
            //await a swap
            flag = false;
            for (int i = 0; i < nums.length - j;i++){
                if (nums[i] > nums[i+1]){
                    //make swap
                    int temp = nums[i];
                    nums[i] = nums[i+1];
                    nums[i+1] = temp;
                    //indicates swap made
                    //if false after loop no swap
                    //was made
                    flag = true;
                }
            }
            j++;
        }
        return nums;
    }
    
    public static int mult(int n){
        if (n==1)
            return 1;
        return 3*mult(n/2);
    }
    
    public static int[] insertionSort(int[] nums){
        
        for (int i = 1; i<nums.length; i++){
            //key begins with item at index 1
            //compares with item 0 and so on
            int key = nums[i];
            int prev = i - 1;
            
            //comparison loop finds first value before key
            //that is less than key
            while (prev > -1 && nums[prev] > key){
                
                //shift items > key up one
                nums[prev+1] = nums[prev];
                prev--;
                
            }
            
            //insert key after value found less than key
            nums[prev+1] = key;
        }
        return nums;
    }
    
    //average case/worst case O(n^2)
    public static int[] selectionSort(int[] nums){
        for (int i = 0; i < nums.length - 1; i++){
            for (int j = i+1; j < nums.length; j++){
                if (nums[i] > nums[j]){
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }   
            }
        }
        return nums;
    }
    
    public static int[] pancakeSort(int [] nums){
        int max = 0;
        int index = 0;
        for(int i = 0; i < nums.length - 1; i++){
            max = nums[i];
            index = i;
            for (int j = i+1; j < nums.length;j++){
                if (nums[j] > max){
                    max = nums[j];
                    index = j;
                }
            }
            int start = i;
            int temp = 0;
            while (start < index){
                temp = nums[index];
                nums[index] = nums[start];
                nums[start] = temp;
                start++;
                index--;
            }     
        }
        return nums;
    }
    
    public static int[] shellSort(int[] nums){
        int h = 1;
        while (h>nums.length/3){
            h = (h * 3) + 1;
        }
        return nums;
    }
    
    static int[] quickSort(int[] nums){
        return quickSort(nums,0,nums.length-1);
    }
    
    static int[] quickSort(int[] nums,int low,int high){
        if (nums == null || nums.length == 1 || low>high)
            return nums;
        
        int pivot = nums[(low + high )/ 2];
        int l = low;
        int r = high;
        while (low < high){
            while(nums[l]<pivot)
                l++;
            while(nums[r] > pivot)
                r--;
            if (l<=r){
                int temp = nums[l];
                nums[l] = nums[r];
                nums[r] = temp;           
            }
        }
        return nums;
    }
    
}
