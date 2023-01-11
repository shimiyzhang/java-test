public class Puppy{
    int puppyAge;
    public Puppy(String name){
        //这个构造器仅有一个参数：name
        System.out.println("小狗的名字是 : " + name );
    }

    public Puppy(String name, int age){
        //这个构造器仅有一个参数：name
        System.out.println("小狗的名字是 : " + name + "年龄：" + age );
    }

    public void setAge(int age) {
        puppyAge = age;
    }

    public int getAge() {
        System.out.println("小狗的年龄为：" + puppyAge);
        return puppyAge;
    }

    public static void main(String[] args){
        // 下面的语句将创建一个Puppy对象
        Puppy myPuppy = new Puppy( "tommy" );
        myPuppy.setAge(2);
        myPuppy.getAge();
        System.out.println("变量值：" + myPuppy.puppyAge);
        Puppy yourPuppy = new Puppy("Jerry", 12);
    }
}