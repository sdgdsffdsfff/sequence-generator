package com.geek.sequence.exceptions;

/**
 * 
 * @author 伯牙
 * @version $Id: SequenceException.java, v 0.1 2013-4-3 下午01:35:41 Exp $
 */
public class SequenceException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public SequenceException() {
        super();
    }

    public SequenceException(String message) {
        super(message);
    }

    public SequenceException(String message, Throwable cause) {
        super(message, cause);
    }

    public SequenceException(Throwable cause) {
        super(cause);
    }
}