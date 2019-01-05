package com.joklek.pointexplorer.modules;

import com.joklek.pointexplorer.exception.IncorrectModuleArgumentException;

public interface ConsoleModule {
    String parseCommand(String[] arguments) throws IncorrectModuleArgumentException;
    String getModuleHandle();
    String getHelpMessage();
}
