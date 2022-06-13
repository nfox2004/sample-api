

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