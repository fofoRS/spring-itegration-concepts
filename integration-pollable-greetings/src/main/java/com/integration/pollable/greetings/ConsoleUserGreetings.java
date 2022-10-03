package com.integration.pollable.greetings;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.function.Function;

@Component
public class ConsoleUserGreetings {

    public void greetingToInputUserName(Function<String,String> userInputCallbackProcessor) throws IOException {
        String userInput = "";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            do {
                System.out.print("Enter User Name or enter 'q' to quit: ");
                userInput = reader.readLine();
                if (!userInput.equals("q")) {
                    String greetings = userInputCallbackProcessor.apply(userInput);
                    System.err.println(greetings);
                }
            } while (!userInput.equals("q"));
        }
        System.exit(1);
    }
}
