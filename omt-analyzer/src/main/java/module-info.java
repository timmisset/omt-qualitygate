module omt.analyzer {
    requires omt.loader;
    requires org.yaml.snakeyaml;
    requires org.apache.commons.lang3;
    exports com.misset.omt.qualitygate.analyzer.context;
    exports com.misset.omt.qualitygate.analyzer.issue;
    exports com.misset.omt.qualitygate.analyzer.rules;
}
