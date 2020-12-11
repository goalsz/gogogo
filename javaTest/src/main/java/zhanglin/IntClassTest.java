package zhanglin;

public class IntClassTest {
	
	/**
	 * int.class 疑问
	 * 有9个预先定义好的Class对象代表8个基本类型和void,它们被java虚拟机创建,和基本类型有相同的名字boolean, byte, char, short, int, long, float, and double.
	 * 这8个基本类型的Class对象可以通过java.lang.Boolean.TYPE,java.lang.Integer.TYPE等来访问,同样可以通过int.class,boolean.class等来访问.
	 * int.class与Integer.TYPE是等价的,但是与Integer.class是不相等的,int.class指的是int的Class对象,Integer.class是Integer的Class的类对象
	 * @param args
	 */
	public static void main(String[] args) {
		Class a = int.class;
		Class b = Integer.TYPE;
		Class c = Integer.class;
		System.out.println(System.identityHashCode(a));
		System.out.println(System.identityHashCode(b));
		System.out.println(System.identityHashCode(c));
	}
	
	
}
