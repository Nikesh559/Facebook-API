# Facebook-API
It is a REST API for user to fetch and modify there account details, search & create posts, accept/reject requests from other members.
# Table of Contents
* About
* Technologies used 
* REST Endpoints
* Rate Limiting
* HATOEAS
* Pagination and Filtering

# Technologies used
* Java
* Spring Boot
* Spring MVC
* Spring JPA
* SQL


# REST Endpoints
<b> Profile Details </b>
  * GET    /profile/{profileId}
  * PATCH /profile/{profileId}/contact
  * GET   /profile/{profileId}/cover_photo
  
<b> Manage Post </b>
* POST /profile/{profileId}/post
* GET /profile/{profileId}/posts
* GET /profile/{profileId}/post/{postId}

<b> Post Comments </b>
*  DELETE /profile/{profileId}/post/{postId}/comment/{commentId}
*  POST /profile/{profileId}/post/{postId}/comment
*  GET /profile/{profileId}/post/{postId}/comments

<b> Connection Request </b>
* GET /profile/{profileId}/requests
* PATCH /profile/{profileId}/request/{requestId}

# Rate Limiting 
  * <b> GET Request </b> : 50 request per hour
  * <b> POST Request </b> : 10 request per hour
  * <b> DELETE Request </b> : 5 request per hour
  * <b> PATCH Request </b> : 5 request per hour
  
  Response from API contains <b> X-Rate-Limit-Remaining </b> indicating number of request remaining in your bucket. Once you are exhausted of all request check response header <b> X-Rate-Limit-Retry-After-Seconds </b> to find after how many seconds you can make request.
  
# HATEOAS (Hypermedia as the Engine of Application State)


# Pagination and Filtering 


# Getting Started

Login to Generate API Key <br/><br/>

<img src="https://user-images.githubusercontent.com/59741887/183670219-1504ce57-a9aa-43d4-89f9-aaf89c771b19.png" width="600" height="500"/>
<br/><br/>
<img src="https://user-images.githubusercontent.com/59741887/183671668-b72e445e-95b9-4bbf-82fb-d33aa8ce89bb.png" width="600" height="500"/>
<br/><br/><br/>
Use the above generated api key in your header or query parameter to explore rest api.
<br/><br/><br/>
<img src="https://user-images.githubusercontent.com/59741887/183672060-5c102a72-8689-43d6-bbfc-63cd4339446c.png" width="600" height="500"/>



