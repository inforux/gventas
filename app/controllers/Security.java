package controllers;

import models.*;

public class Security extends Secure.Security {

    static boolean authentify(String username, String password) {
        return User.connect(username, password) != null;
    }
    
    static boolean check(String profile) {
        if("admin".equals(profile)) {
            return User.find("byEmail", connected()).<User>first().getIsAdmin();
        }
        return false;
    }
    
    static void onDisconnected() {
        //Public.index();
    }
    
    static void onAuthenticated() {
        Public.index();
    }
    
}
