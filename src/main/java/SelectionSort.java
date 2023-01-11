// 选择排序
public class SelectionSort {
    public static void main(String[] args) {
        int[] arr={20,60,51,81,285,12,165,51,81,318,186,9,70};
        for (int a: arr) {
            System.out.print(a + "");
        }

        System.out.println("\n"+"---------------从大到小---------------");

        arr=toSmall(arr);
        for(int a:arr)
        {
            System.out.print(a+" ");
        }

        System.out.println("\n"+"---------------从小到大---------------");

        arr=toBig(arr);
        for(int a:arr)
        {
            System.out.print(a+" ");
        }
    }

    // 从大到小
    public static int[] toSmall(int[] arr) {
        int time = 0;
        //遍历数组里除最后一个的其他所有数，因为最后的对象没有与之可以相比较的数
        for (int i = 0; i < arr.length - 1; i++) {
            /*遍历数组里没有排序的所有数，并与上一个数进行比较
             *“k=i+1”因为自身一定等于自身，所以相比没有意义
             *而前面已经排好序的数，在比较也没有意义
             */
            for (int k = i + 1; k < arr.length; k++) {
                time++;
                if (arr[k] > arr[i]) {
                    //交换
                    int number=arr[i];
                    arr[i]=arr[k];
                    arr[k]=number;
                }
            }
        }
        System.out.println(time);
        return arr;
    }

    // 从小到大
    public static int[] toBig(int[] arr) {
        int time = 0;
        for (int i=0;i<arr.length-1;i++) {
            for (int k=i+1; k < arr.length; k++) {
                time++;
                if (arr[k] < arr[i]) {
                    int number=arr[i];
                    arr[i]=arr[k];
                    arr[k]=number;
                }
            }
        }
        System.out.println(time);
        return arr;
    }
}