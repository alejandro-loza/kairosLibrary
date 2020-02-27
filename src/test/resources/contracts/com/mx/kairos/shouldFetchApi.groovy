package com.mx.kairos

org.springframework.cloud.contract.spec.Contract.make {
  description("""
Represents a fetch to resource
```
when:
    wants to fethc an user
then:
    service'd fetch an user
```
""")

  request {
    method 'GET'
    url value(consumer(regex('/api/v1/base')), producer('/base'))


  }
  response {
    status OK()
    body([
        "message": "hello microservices",
    ])
  }
}
