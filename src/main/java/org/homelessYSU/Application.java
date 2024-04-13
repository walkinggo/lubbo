package org.homelessYSU;

import org.homelessYSU.beans.factory.annotation.EnableLubboApplication;

@EnableLubboApplication(port = 8080)
public class Application {
    public static void main(String[] args) {
        LubboApplication.run(Application.class);
    }
}
