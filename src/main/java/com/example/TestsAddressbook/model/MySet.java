package com.example.TestsAddressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MySet<T> extends ForwardingSet<T> {

    private Set<T> delegate;

    public MySet(Set<T> delegate) {
        this.delegate = new HashSet<T>(delegate);
    }

    public MySet() {
        this.delegate = new HashSet<>();
    }

    @Override
    protected Set<T> delegate() {
        return delegate;
    }


    public MySet<T> withAdded(T element){
        MySet<T> set = new MySet<T>(this);
        set.add(element);
        return set;
    }

    public MySet<T> without(T element){
        MySet<T> set = new MySet<T>(this);
        set.remove(element);
        return set;
    }
}
