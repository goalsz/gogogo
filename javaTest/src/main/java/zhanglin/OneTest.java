package zhanglin;


import java.util.ArrayList;
import java.util.Base64;

public class OneTest {

    public static void main(String[] args) {
        String[] aaa = new String[]{"1","2","3"};
        StringBuilder sb = new StringBuilder();
        ArrayList<byte[]> list = new ArrayList<>();
        for (int i =0;i<aaa.length;i++){
            sb.append(aaa[i]);
            list.add(aaa[i].getBytes());
        }
        String  sbStr = sb.toString();
        System.out.println(sbStr);
        System.out.println(list);
        Base64.Encoder encoder = Base64.getEncoder();
        Base64.Decoder decoder = Base64.getDecoder();
        String  str ="";
        for (byte[] n:list ) {
            str+=encoder.encodeToString(n);
        }
        System.out.println("str:"+str);
//        byte[] decode = decoder.decode(str);
//        System.out.println(Arrays.toString(decode));


        byte[] bytes = sb.toString().getBytes();
        String string = encoder.encodeToString(bytes);
        System.out.println("string: "+string);


    }

}
