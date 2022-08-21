## OMT Quality Gate

The OMT Quality Gate Sonarqube plugin adds the OMT Language to Sonarqube along with an "OMT Rules" Quality Profile.

The project consists of 3 modules:

- omt-loader: parses the yaml to a java structure
- omt-analyzer: analyses the java structure with a set of rules
- omt-server-plugin: implements all required classes to make the omt language available to the Sonarqube server
  See README in the modules to get more information.

## Contribute

When you want to contribute to this project these are the steps:

### OMT structure extended

If the OMT structure is modified in the OPP project, the changes must be reflected in the omt-loader module.
This is straight-forward. Let's say you want to add a property to the Activity class. This is map so it resides under
maps / modelitem / Activity.java.
Then extend the static block that adds properties with a new key:value pair. The value is a function that takes a yaml
node
and returns an OMTElement. If it is a scalar field you can pick on the available literal scalars available. If you want
to
do certain checks on this field it is advised to extend a base scalar with your own implementation.
For this example, let's assume we create a new MyScalar.java that extends StringElement. The new class must contain a
constructor
that accepts a node.

### Adding a rule

If the MyScalar field described must be validated, a new rule needs to be added to the omt-analyzer:

#### omt-analyzer

- Add a key to the Rules.java$Keys class for the new rule: MyNewRule
- If the rule takes arguments, add them to the Rules.java$Attributes
- Create a new implementation of Rule for MyNewRule in the RULE_LIST of Rules.java. You need to provide a message
  for the issue that is set on the specific location of the issue. You can provide message arguments to tailor the
  message
  based on the specific value/conditions of the scalar that is being handled.
- Create a new visitor for MyScalar.java by extending AbstractElementVisitor with MyScalar type: MyScalarVisitor.java.
  The AbstractElementVisitor will take care that MyScalarVisitor.java will now only trigger on MyScalar.java
- Create an implementation for the visit(MyScalar element) method. It is advised to create a Validator class for more
  generic
  checks that can be re-used by other visitors.
- Call the newIssue method in the visitor to register if the scalar field violates whatever rule you create.

That's it for the omt-analyzer. The newIssue call takes care of setting the specific location where the issue occured
and creates the
issue message. Don't forget to create a test to confirm your rule setup works.

#### omt-server-plugin

To make the rule available for Sonarqube you need to implement some required classes for Sonarqube server:

- Add a new class in rules: MyNewRule.java and annotate it using @Rule and @RuleProperty, this will allow the server
  admin
  to provide specific settings for MyNewRule. See ImportMustBeUsed.java for an example with properties.
- Add the class to the SonarRules.java ACTIVE_RULES or INACTIVE_RULES. The activity can be modified on the server later,
  this sets the default state
- Finally, add a html and json file in resources/org/sonar/l10n/omt/rules/omt. The html file is the 'why is this an
  issue' documentation
  that will be shown on the server. The json determines the default type (bug, code-smell). Again, these settings serve
  as default settings
  but can be overwritten server-side by the server admin.

That's all for adding a new rule. Of course the complexity resides in the implementation of the validation. Make sure
you pick the right visitor type
to do the analysis. The TreeUtil is a good tool to quickly find elements in the scope of the visitors target element.
The idea is to always
work from a top-down model traversion. This means that if you need to check if a variable is being used, you don't
create a visitor
for a variable but for a modelitem (like an Activity) which is the right scope for variable declaration and usage.
This way, the visitor can first obtain all variable declarations and then check the remainder of the modelitem structure
for their usage.
