package com.morg.webhook.utils;

public class Const {
    public static class Data{
        public static final String DATA = "/data/";
        public static final String DATABASE_PATH = "/databases";
        public static final String SHARED_PREF_PATH = "/shared_prefs";
        public static final String SLASH = "/";

        private Data(){
            throw new IllegalStateException(Data.class.getSimpleName());
        }
    }
}
