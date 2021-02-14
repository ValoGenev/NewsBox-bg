package scam.exception;

import org.aspectj.apache.bcel.classfile.annotation.RuntimeTypeAnnos;

public class ThumbNailNotFoundException extends RuntimeException {

    public ThumbNailNotFoundException(String message) {
        super(message);
    }
}
