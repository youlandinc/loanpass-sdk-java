
# Overview of the LoanPASS SDK for Java #
The SDK was designed to make LoanPASS APIs to be easily accessible with just a few lines of code.
Here are the highlighted features:
- Provided both sync and async versions of the LoanPASS APIs
- Convenient builder(s) to construct API requests safely through code-generated strong typed
- Convenient **nested** annotation(s) and converters for safely:
    - Serializing a POJO to an API request
    - De-serializing an API response to a POJO
- Utilities to:
    - Generate ```enum KnownEnumId``` from get_configuration API
    - Generate ```enum KnownFieldId``` from get_configuration API
    - Generate GraphQL schema from ```enum KnownEnumId```

If you would like to contribute to this project, please contact [long@youland.com](mailto:long@youland.com)

# Getting Started #

```java
//Initialize LoanPass client
String api_key  = "Provide your LoanPass API Key"
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

**Strong Typed Version**
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

**Weak Typed Version**
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

# The prerequisite of Using Strong Typed Builder #
In order to build requests with strong typed, you need to generate the codes 
containing strong types of "creditApplicationFields" and "enumerations" from the 
result of get_configuration API. Here are the steps to generate the code:

1. Run the unit test of "LoanPassClientTest.test_getConfiguration" to retrieve the latest configuration.
2. Replace "test/testdata/config/response_sample.json" with the result above.
3. Run these 2 unit tests to generate the latest version of strong types above. 
   1. "EnumType2JavaGenTest"
   2. "FieldDef2JavaGenTest"
4. Replace "KnownEnumId.java" and "KnownFieldId.java" with "temp/KnownEnumId.java.codegen"
and "temp/KnownFieldId.java.codegen" generated from the previous step.

# License Summary #
Apache 2.0
