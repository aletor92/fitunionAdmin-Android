package it.asfitness.FitUnionAndroidAdmin.utils;

import android.content.Context;

/**
 * Created by a.digiacomo on 13/01/2016.
 */
public class Utils {
    private static Utils singleton;

    //public static User user;
    public static Context context;


    public static Utils getInstance(Context ctx) {
        if (singleton == null) {
            singleton = new Utils(ctx);
        }
        return singleton;
    }

    public Utils(Context ctx) {
        this.context = ctx;
    }

   /* public static void setUser(ParseUser user) {
        Utils.user = user;
    }

    public static ParseUser getUser() {
        return user;
    }*/

    public static String getClassificazione(int level){
        if (level==1){
            return "Regular";

        }else if(level==2){

            return "Premium";
        }else{
            return "Top Class";

        }

    }
}
