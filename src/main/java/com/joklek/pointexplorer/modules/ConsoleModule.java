package com.joklek.pointexplorer.modules;

import com.joklek.pointexplorer.exception.IncorrectModuleArgumentException;

/**
 * A console module interface
 */
public interface ConsoleModule {
    /**
     * Parses the given command with arguments and returns the massage to print.
     * @param arguments arguments for module
     * @return message to print
     * @throws IncorrectModuleArgumentException exception with message provided by module
     */
    String parseCommand(String[] arguments) throws IncorrectModuleArgumentException;

    /**
     * Gets handle by which the module will be called
     */
    String getModuleHandle();

    /**
     * Help message to be provided for the user
     */
    String getHelpMessage();
}
