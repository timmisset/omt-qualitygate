package com.misset.omt.qualitygate.model.scalars.injected;

public abstract class InjectedFragment {

    private final String fragment;

    protected InjectedFragment(String fragment) {
        this.fragment = fragment;
    }

    public boolean usesVariable(String variableName) {
        return fragment.contains(variableName);
    }
}
