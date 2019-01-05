package com.joklek.pointexplorer.modules;

public interface ConsoleModule {
    String parseCommand(String[] arguments) throws IllegalStateException;
    String getModuleHandle();
    String getHelpMessage();
}
