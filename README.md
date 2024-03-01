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
>Request (success)
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
>Request (deleted)
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

