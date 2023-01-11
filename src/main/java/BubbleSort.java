public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {26,15,29,66,99,88,36,77,111,1,6,8,8};
        for (int i = 0; i < arr.length - 1; i++) {
            // 外层循环控制排序趟数
            for (int j = 0; j < arr.length - 1; j++) {
                //内层循环控制每一趟排序多少次
                // 把小的值交换到前面
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            System.out.print("第"+(i+1)+"次排序结果：");
            //列举每次排序的数据
            for(int a: arr) {
                System.out.print(a + "\t");
            }
            System.out.println("");
        }
        System.out.println("最终排序结果：");
        for (int a: arr) {
            System.out.println(a + "\t");
        }
    }
}
