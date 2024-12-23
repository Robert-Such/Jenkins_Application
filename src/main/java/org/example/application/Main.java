package org.example.application;

import org.example.library.Utility;

public class Main {
    public static void main(String[] args) {
        String name = System.getenv("TEST_NAME");
        System.out.println(Utility.greet(name));
    }
}
