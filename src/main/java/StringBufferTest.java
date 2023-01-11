public class StringBufferTest {
    public static void main(String args[]){
        StringBuffer sBuffer = new StringBuffer("菜鸟教程官网：");
        sBuffer.append("www");
        sBuffer.append(".runoob");
        sBuffer.append(".com");
        System.out.println(sBuffer);
        sBuffer.delete(4, 6);
        System.out.println(sBuffer);
    }
}
