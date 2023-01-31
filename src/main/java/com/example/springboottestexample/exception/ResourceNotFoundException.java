/**
 * Created By shoh@n
 * Date: 1/29/2023
 */

package com.example.springboottestexample.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
