package com.misset.omt.qualitygate.model.maps.files;

public enum OMTFileType {

    MODULE("module.omt"),
    INTERFACE("interface.omt"),
    DEFAULT("omt");

    private final String extension;
    OMTFileType(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }
}
