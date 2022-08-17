package com.misset.omt.qualitygate.validators;

import java.util.Optional;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.TreeUtil;
import com.misset.omt.qualitygate.model.maps.Prefixes;
import com.misset.omt.qualitygate.model.scalars.AbstractScalar;
import com.misset.omt.qualitygate.model.scalars.OntologyPrefixStringElement;
import com.misset.omt.qualitygate.rules.PrefixMustBeUsed;
import com.misset.omt.qualitygate.visitors.AbstractElementVisitor;
import com.misset.omt.qualitygate.visitors.ScalarVisitorUtil;

public class PrefixUsageValidator<T extends OMTElement> implements Validator<T>  {

    private final AbstractElementVisitor<? extends OMTElement> visitor;

    public PrefixUsageValidator(AbstractElementVisitor<? extends OMTElement> visitor) {
        this.visitor = visitor;
    }

    public void validate(OMTElement element) {
        Optional<Prefixes> prefixes = TreeUtil.findChild(element, Prefixes.class);
        if (prefixes.isEmpty()) {
            return;
        }
        Prefixes prefixesValue = prefixes.get();

        prefixesValue.getKeys()
                .stream()
                .filter(prefix -> !isUsed(prefix, prefixesValue, element))
                .forEach(prefix -> setIssue(prefix, prefixesValue));
    }

    private boolean isUsed(String prefix, Prefixes prefixes, OMTElement scope) {
        return ScalarVisitorUtil.containsValue(scope, prefixes, prefix + ":") ||
                TreeUtil.findChildren(scope, OntologyPrefixStringElement.class)
                        .stream()
                        .map(AbstractScalar::getValueOrEmpty)
                        .anyMatch(prefix::equals);
    }

    private void setIssue(String prefix, Prefixes prefixes) {
        prefixes.findKeyNode(prefix).ifPresent(scalarNode -> visitor.newIssue(PrefixMustBeUsed.KEY, scalarNode));
    }

}
