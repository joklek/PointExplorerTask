package com.joklek.pointexplorer.modules;

import com.joklek.pointexplorer.exception.IncorrectModuleArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;

import java.util.List;

// Todo, maybe this should be a standalone module?
public class HelpModule implements ConsoleModule {

    @Autowired
    private List<ConsoleModule> modules;
    private static final String HANDLE = "help";

    @Override
    @NonNull
    public String parseCommand(@NonNull String[] arguments) throws IncorrectModuleArgumentException {
        StringBuilder messageBuilder = new StringBuilder();

        if(arguments.length != 0) {
            messageBuilder.append(String.format("%s should not be called with any arguments. Either way, help for all modules will be shown %n", HANDLE));
        }
        for(ConsoleModule module : modules) {
            messageBuilder.append(String.format("%s: %s%n", module.getModuleHandle(), module.getHelpMessage()));
        }
        messageBuilder.append(String.format("exit: used to exit the program%n"));

        return messageBuilder.toString();
    }

    @Override
    @NonNull
    public String getModuleHandle() {
        return HANDLE;
    }

    @Override
    @NonNull
    public String getHelpMessage() {
        return String.format("Usage should be: %s", HANDLE);
    }
}
