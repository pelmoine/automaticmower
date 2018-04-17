package com.mowitnow.automaticmower.File;

/**
 * Exception class extend of RuntimeException.
 *
 * Using such generic exceptions as Error, RuntimeException, Throwable, and Exception
 * prevents calling methods from handling true, system-generated exceptions differently
 * than application-generated errors.
 *
 * @author epelmoine
 */
public class CmdFileException extends RuntimeException {

    public CmdFileException() {
        super();
    }

    public CmdFileException(String message) {
        super(message);
    }
}
