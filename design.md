### Resource Identification:

- Prices - name, amount, and other details of fixed pricing
- Periods - name, frequency, and other details of invoice timing
- Materials - name, description, and other details of materials handled
- Services - name, description and other details of services offered

### Model URIs
- /prices
- /prices/{id}
- /periods
- /periods/{id}
- /materials
- /materials/{id}
- /services
- /services/{id}

### Resource formats
- XML (indicated below)
- JSON?

### Collection XML formats
```
<prices size="4">
    <link rel="self" href="/prices"/>
    <price id="1">
        <link rel="self" href="/prices/1"/>
        <title>General labor</title>
        <value>50.00</value>
        <baseCurrency>USD</baseCurrency>
    </price>
    <price id="2">
        <link rel="self" href="/prices/2"/>
        <title>Consultation</title>
        <value>25.00</value>
        <baseCurrency>USD</baseCurrency>
    </price>
    <price id="3">
        <link rel="self" href="/prices/3"/>
        <title>Custom design</title>
        <value>100.00</value>
        <baseCurrency>USD</baseCurrency>
    </price>
    <price id="4">
        <link rel="self" href="/prices/4"/>
        <title>New construction</title>
        <value>150.00</value>
        <baseCurrency>USD</baseCurrency>
    </price>
</prices>
```

### Single XML formats
```
<price id="1">
    <id>1</id>
    <link rel="self" href="/prices/1"/>
    <title>General labor</title>
    <value>50.00</value>
    <baseCurrency>USD</baseCurrency>
</price>
```

### HTTP methods
##### Prices resource
- HTTP GET /prices
- HTTP GET /prices/{id}
- HTTP POST /prices
- HTTP PUT /prices/{id}
- HTTP DELETE /prices
##### Periods resource
- HTTP GET /periods
- HTTP GET /periods/{id}
- HTTP POST /periods
- HTTP PUT /periods/{id}
- HTTP DELETE /periods
##### Materials resource
- HTTP GET /materials
- HTTP GET /materials/{id}
- HTTP POST /materials
- HTTP PUT /materials/{id}
- HTTP DELETE /materials
##### Services resource
- HTTP GET /services
- HTTP GET /services/{id}
- HTTP POST /services
- HTTP PUT /services/{id}
- HTTP DELETE /services
