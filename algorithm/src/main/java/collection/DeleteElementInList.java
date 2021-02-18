package collection;

import java.util.ArrayList;

/**
 * @description:
 * @author: Zhoust
 * @date: 2020/06/22 下午6:27
 * @version: V1.0
 */
public class DeleteElementInList {

    public static void main(String[]args) {
        ArrayList<String> list= new ArrayList<String>();
        list.add("b");
        list.add("b");
        list.add("b");
        list.add("b");
        list.add("b");
        list.add("b");
        //remove(list);
        forEachDelete(list);
        for(String s:list) {
            System.out.println("element : "+s);
        }
    }

    public static void remove(ArrayList<String> list) {
        for (int i = 0; i < list.size(); ) {
            if (list.get(i).equals("b")) {
                list.remove(i);
            } else {
                i++;
            }
        }
    }

    public static void forEachDelete(ArrayList<String> list) {
        for (String str : list) {
            if (str.equals("b")) {
                list.remove(str);
            }
        }
    }


}