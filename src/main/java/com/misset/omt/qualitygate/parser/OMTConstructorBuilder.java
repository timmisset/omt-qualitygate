package com.misset.omt.qualitygate.parser;

import com.misset.omt.qualitygate.model.maps.modelitem.Activity;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Tag;

import java.util.ArrayList;
import java.util.List;

public class OMTConstructorBuilder {

    private static List<TypeDescription> typeDescriptions = new ArrayList<>();
    static {
        typeDescriptions.add(new TypeDescription(Activity.class, new Tag("!Activity")));
    }

    public static Constructor getConstructor() {
        Constructor constructor = new Constructor();
        typeDescriptions.forEach(constructor::addTypeDescription);

        return constructor;
    }

}
