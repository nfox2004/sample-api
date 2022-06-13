(ns dealer-api.config)

(def db
  {:classname "org.postgresql.Driver"
   :subprotocol "postgresql"
   :subname "//localhost:5432/testdb"
   :user "postgres"
   :password "postgres"})