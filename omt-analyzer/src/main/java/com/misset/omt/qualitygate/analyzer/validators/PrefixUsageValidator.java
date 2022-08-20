package com.misset.omt.qualitygate.analyzer.validators;

import com.misset.omt.qualitygate.analyzer.rules.Rules;
import com.misset.omt.qualitygate.analyzer.visitors.ElementVisitor;
import com.misset.omt.qualitygate.analyzer.visitors.ScalarVisitorUtil;
import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.TreeUtil;
import com.misset.omt.qualitygate.model.maps.Prefixes;
import com.misset.omt.qualitygate.model.scalars.AbstractScalar;
import com.misset.omt.qualitygate.model.scalars.OntologyPrefixStringElement;

import java.util.Optional;

public class PrefixUsageValidator<T extends OMTElement> implements Validator<T>  {

    private final ElementVisitor<? extends OMTElement> visitor;

    public PrefixUsageValidator(ElementVisitor<? extends OMTElement> visitor) {
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
        prefixes.findKeyNode(prefix).ifPresent(scalarNode ->
                visitor.newIssue(Rules.Keys.PREFIX_MUST_BE_USED, scalarNode, scalarNode.getValue()));
    }

}
