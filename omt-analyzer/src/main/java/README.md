### OMT ANALYZER

The OMT analyzer will analyse the Java representation of the OMT structure with a given set of rules.
It is designed to be a standalone feature without any dependencies on the Sonarqube API. The reason for this is
to make it directly accessible for other features like the OMT-ODT IntelliJ language plugin.
The Sonarqube API dependency is quite heavy.

## OMT Sensor context

The conditions of the validation are set using the OMTSensorContext. When using the analyzer, an implementation
must be provided which will be used to store new issues and determine which rules are active and the current
values of their attributes.

## Rules

The Rules class contains 1 instance of every rule that is checked. The inner classes RuleKeys and RuleAttributes
contain the information that needs to be passed to the SensorContext.

## Visitors

When validating the OMT structure, the entire OMT tree is recursively traversed, touching every node once.
On every node, the set of registered visitors is triggered.
Visitors have specific implementations to be type-bound to specific OMTElement implementations. For example,
the ModelItemVisitor is triggered when a ModelItem node is reached.

## Validators

Visitors can do simple validations directly or call a validator to do the work for them. Aside from keeping the
code better maintained, this also provides for better re-usability. For example, prefixes can exist in the OMTFile
and a ModelItem. The 2 have distinct visitors but their visitors call the same validator (PrefixUsageValidator) to
analyse the prefix.

