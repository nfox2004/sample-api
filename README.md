

Start up postgres 
$ docker-compose up -d postgres


Once the server is running, sample request

See all entries 
```
curl http://localhost:5000/drugs

```

To add a new entry
```
curl -X POST -H 'Content-Type: application/json' -i http://localhost:5000/drugs --data '{"name": "Non Existant", "availability": 104, "price": 56.79}'
```

To update an entry
```
curl -X PUT --header 'Content-Type: application/json' \
-d '{ "name": "Bigger Biceps", "price": 100, "availability": "10" }' \
http://localhost:5000/drugs/11
```