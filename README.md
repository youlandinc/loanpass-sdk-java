
# Overview of the LoanPASS SDK for Java #
The SDK was designed to make LoanPASS APIs to be easily accessible with as few lines of code as possible.
Here are the highlighted features:
- Provided both sync and async versions of the LoanPASS APIs
- Convenient builder(s) to construct API requests safely through code-generated strong typed
- Convenient **nested** annotation(s) and converters for safely:
    - Serializing a POCO to an API request
    - De-serializing an API response to a POCO
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

# License Summary #
Apache 2.0
