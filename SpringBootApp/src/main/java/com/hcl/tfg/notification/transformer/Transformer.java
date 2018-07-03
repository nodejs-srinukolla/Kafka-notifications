package com.hcl.tfg.notification.transformer;

public interface Transformer<S, T> {

    T transform(S s);
}
