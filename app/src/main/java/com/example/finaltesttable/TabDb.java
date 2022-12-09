package com.example.finaltesttable;

public class TabDb {



        public static Class[] getFragment(){
            return new Class[]{FirstPageFragment.class, SecondPageFragment.class};
        }

        public static String[] getTabsTxt(){
            return new String[]{"第一页","第二页"};
        }

}
