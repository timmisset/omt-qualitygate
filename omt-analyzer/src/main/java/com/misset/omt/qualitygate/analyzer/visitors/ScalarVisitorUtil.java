package com.misset.omt.qualitygate.analyzer.visitors;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.TreeUtil;
import com.misset.omt.qualitygate.model.scalars.AbstractScalar;

public class ScalarVisitorUtil {

    /**
     * Checks all the scalar values in the scope for the presence of the searchFor value.
     * Returns true when searchFor is null
     * @param scope     - search scope, for example the entire OMT File
     * @param excluding - exclude from scope, for example the imports: block
     * @param searchFor - search token, for example the name of the imported member
     */
    public static boolean containsValue(OMTElement scope, OMTElement excluding, String searchFor) {
        if(searchFor == null) {
            return true;
        }
        return TreeUtil.findChildren(scope, AbstractScalar.class)
                .stream()
                .filter(scalar -> scalar.getValueOrEmpty().contains(searchFor))
                .anyMatch(abstractScalar -> !TreeUtil.isAncestor(excluding, abstractScalar));
    }

}
