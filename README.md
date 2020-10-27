# Quick setup

- Use an Axon docker image. For fresh startups the following alias can be of help :
`alias newaxon='docker stop axonserver; docker rm axonserver; docker run -d -p 8024:8024 -p 8124:8124 --name axonserver  axoniq/axonserver'`

- start up Spring Boot app and use OrganisationsImporter REST API 
