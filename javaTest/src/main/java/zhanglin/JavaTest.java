package zhanglin;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaTest {
    public static void main(String[] args){
//        try{
//            FileInputStream fileInputStream = new FileInputStream(new File("C:/Users/pactera/Desktop/commons-lang3-3.7-bin.zip"));
//            FileOutputStream fileOutputStream = new FileOutputStream(new File("C:/Users/pactera/Desktop/aaa.zip"));
//            byte[] len = new byte[1024*1024];
//            int b;
//            while( ( b = fileInputStream.read(len) )!= -1){
//                fileOutputStream.write(len,0,b);
//            }
//
//
//
//
//        }catch (IOException e){
//            e.printStackTrace();
//        }
        Pattern p = Pattern.compile("[1]\\d{10}");
        Matcher m = p.matcher("12312312312");
        boolean b = m.matches();
        System.out.println(b);

    }
}
