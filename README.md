# Programming 5

## Video Platform Application

### Author

> Anna Deineko

> Group: ACS 202

> anna.deineko@student.kdg.be

### About

This is an application where you can manage data about your channels like videos, comments and other necessary
information.

### Domain

> videos - comments: one to many
>
> videos - channels: many to many

### Week 2

**API: retrieve 1 channel**
> Request (success)
>
>`GET http://localhost:8080/api/channels/1`
>
> Response
>
>`HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 01 Mar 2024 14:12:25 GMT
Keep-Alive: timeout=60
Connection: keep-alive`
>
> Request (not found)
>
> `GET http://localhost:8080/api/channels/90`
>
> Response
>
> `HTTP/1.1 404
Content-Length: 0
Date: Fri, 01 Mar 2024 14:16:27 GMT
Keep-Alive: timeout=60
Connection: keep-alive`
>
**API: delete a channel**
> Request (deleted)
>
>`DELETE http://localhost:8080/api/channels/2`
>
> Response
>
>`HTTP/1.1 204
Date: Fri, 01 Mar 2024 14:18:46 GMT
Keep-Alive: timeout=60
Connection: keep-alive`
>
> Request (not found)
>
> `DELETE http://localhost:8080/api/channels/90`
>
> Response
>
> `HTTP/1.1 404
Content-Length: 0
Date: Fri, 01 Mar 2024 14:19:51 GMT
Keep-Alive: timeout=60
Connection: keep-alive`
>

### Week 3

**API: Creating a channel (successfully)**
> Request
>
> `POST http://localhost:8080/api/channels
Accept: application/json
Content-Type: application/json
{
"name": "The name",
"date": "2024-03-11",
"subscribers": 345
}`
>
> Response
>
> `HTTP/1.1 201`
> {
"id": 3,
"name": "The name",
"date": "2024-03-11",
"subscribers": 345
> }
> Response file saved.
>
**API: Update channel**
> Request
>
>`PATCH http://localhost:8080/api/channels/1
Content-Type: application/json
{
"name": "Channel",
"subscribers": 654
}`
>
> Response
>
> `HTTP/1.1 204`
> Response body is empty Response code: 204;
>
>
**API: retrieve one channel XML format**
> Request
>
>`GET http://localhost:8080/api/channels/1
> Accept: application/xml`
>
> Response
>
> `GET http://localhost:8080/api/channels/1
HTTP/1.1 200
<ChannelDto>
<id>1</id>
<name>Channel</name>
<date>2022-09-04</date>
<subscribers>654</subscribers>
</ChannelDto>
Response file saved.
> 2024-03-15T175733.200.xml`
<TODO add json format>
### Week 5
>Username: anna
> 
>Password: anna