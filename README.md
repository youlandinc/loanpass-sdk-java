
# Overview of the LoanPASS SDK for Java #
The SDK was designed to make LoanPASS APIs to be easily accessible with just a few lines of code.
Here are the highlighted features:
- Provided both sync and async versions of the LoanPASS APIs
- Convenient builder(s) to construct API requests safely through code-generated strong types
- Convenient annotation(s) and converters for safely:
    - Serializing a (**nested**) POJO to an API request
    - De-serializing an API response to a (**nested**) POJO
- Utilities to:
    - Generate ```enum KnownEnumId``` from get_configuration API
    - Generate ```enum KnownFieldId``` from get_configuration API
    - Generate GraphQL schema from ```enum KnownEnumId```

If you would like to contribute to this project, please contact [long@youland.com](mailto:long@youland.com)

# Getting started #

```java
//Initialize LoanPass client
String api_key  = "Bearer API Key"; //Provide your LoanPass API Key
Settings config = new Settings("https://api.loanpass.io/v1", api_key);
ILoanPassClient loanpassClient = new LoanPassClient(config);

//configuration
ConfigResponse configRes = loanpassClient.getConfiguration();

//execute-summary
ExecSummaryResponse sumRes = loanpassClient.postExecSummary(sumReq);

//execute-product
ExecProductResponse prodRes = loanpassClient.postExecProduct(prodReq);
```

# How to build a request #

There are two ways to build a request:

**Preferred option - Strong typed builder**

With this preferred option, the builder will validate the correctness of IDs and their values.

```java
FieldValueMapping.IBuilder fieldBuilder = new FieldValueMappingBuilder();
List<FieldValueMapping> fields = fieldBuilder
        .fieldEnum(KnownFieldId.LOAN_PURPOSE, "refinance")
        .fieldNumber(KnownFieldId.BASE_LOAN_AMOUNT, 2)
        .fieldString(KnownFieldId.STATE, "some text")
        .fieldDuration(KnownFieldId.DESIRED_LOAN_TERM, 3, DurationUnit.DAYS)
        .build();

ExecSummaryRequest.IBuilder builder = new ExecSummaryRequestBuilder();
builder.withTime(Instant.now())
        .withFields(fields);

ExecSummaryRequest request = builder.build();
```

**Fallback option - Weak typed builder**

You should only use this fallback option when your *WellKnownFieldId* or *WellKnownEnumId* is out of date.
With this option, there is absolutely no checking by the builder on the correctness of the IDs and their values.

```java
FieldValueMapping.IBuilder fieldBuilder = new FieldValueMappingBuilder();
List<FieldValueMapping> fields = fieldBuilder
        .fieldEnum("enumId", "a", "1")
        .fieldNumber("numId", 2)
        .fieldString("stId", "some text")
        .fieldDuration("durId", 3, DurationUnit.DAYS)
        .build();

ExecSummaryRequest.IBuilder builder = new ExecSummaryRequestBuilder();
builder.withTime(Instant.now())
        .withFields(fields);

ExecSummaryRequest request = builder.build();
```

# Prerequisites of using strong typed builder #
In order to build requests with strong types, you need to generate codes 
containing strong types of *creditApplicationFields* and *enumerations* from the 
result of get_configuration API. Here are the steps to generate the code:

1. Run the unit test of *LoanPassClientTest.test_getConfiguration* to retrieve the latest configuration.
2. Replace *test/testdata/config/response_sample.json* with the result above.
3. Run these 2 unit tests to generate the latest version of strong types above. 
   1. *EnumType2JavaGenTest*
   2. *FieldDef2JavaGenTest*
4. Replace *KnownEnumId.java* and *KnownFieldId.java* with *temp/KnownEnumId.java.codegen*
and *temp/KnownFieldId.java.codegen* generated from the previous step.

# Creating a request from a (nested) POJO in a few lines of code #
Creating a request using strong typed builder is good. Still, every field has to be hand drafted 
and most requests contain a dozen of fields. The better way to create a request in a few lines 
of code is to annotate your domain object model which is in turn converted into a request. 
Here is the unit test to demonstrate of use of 
[Obj2FieldValueMapping](https://github.com/youlandinc/loanpass-sdk-java/blob/main/src/test/java/com/youland/vendor/loanpass/converter/Obj2FieldValueMappingTest.java).

```java
private enum Loan_Purpose {
    PURCHASE,
    REFINANCE,
    MORTGAGE_MODIFICATION,
    ASSUMPTION
}

private class AnnotatedInputNested {
    @Tag(KnownFieldId.AFTER_REPAIR_VALUE_ARV)
    private Double afterRepairValue = 88.0;
}

private class AnnotatedInput {
    @Tag(KnownFieldId.LOAN_PURPOSE)
    private Loan_Purpose loanPurpose = Loan_Purpose.REFINANCE;

    @Tag(KnownFieldId.STATE)
    private String propertyState = "CA";

    @Tag(KnownFieldId.BASE_LOAN_AMOUNT)
    private Double loanAmount = 99.0;

    @TagDuration(value = KnownFieldId.DESIRED_LOAN_TERM, unit = DurationUnit.MONTHS)
    private Double loanTerm = 11.0;

    @TagObj
    private AnnotatedInputNested nestedInput = new AnnotatedInputNested();
}

//With a few annotations above, the AnnotatedInput source object can be converted into 
//fields of a request 
AnnotatedInput source = new AnnotatedInput();
Obj2FieldValueMapping converter = new Obj2FieldValueMapping(source);
List<FieldValueMapping> result = converter.convert();
```

# From API response to a (nested) POJO in a single line of code #
A response of product summary or product details API typically contained dozens of fields.
Mapping these fields into a (nested) POJO can be tedious and error-prone. The better way to
do this is through annotations and FieldValueMapping2Obj.

Continuing with the sample code "Creating a request from a (nested) POJO" above,
```java
List<FieldValueMapping> result = converter.convert();
```
is to be converted back to the POJO with
a single line of code.

```java
AnnotatedInput pojo = FieldValueMapping2Obj.create(result, AnnotatedInput.class);
```

# License summary #
Apache 2.0
