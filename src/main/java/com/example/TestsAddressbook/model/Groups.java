package com.example.TestsAddressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Groups extends ForwardingSet<GroupData> {

    private Set<GroupData> delegate;

    public Groups(Groups groupData) {
        this.delegate = new HashSet<GroupData>(groupData.delegate);
    }

    public Groups() {
        this.delegate = new HashSet<GroupData>();
    }

    @Override
    protected Set<GroupData> delegate() {
        return delegate;
    }



    public Groups withAdded(GroupData groupData){
        Groups groups = new Groups(this);
        groups.add(groupData);
        return groups;
    }

    public Groups without(GroupData groupData){
        Groups groups = new Groups(this);
        groups.remove(groupData);
        return groups;
    }
}
