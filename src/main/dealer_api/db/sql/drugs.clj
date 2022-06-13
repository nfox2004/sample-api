(ns dealer-api.db.sql.drugs
  (:require
    [hugsql.core :as hugsql]))

(hugsql/def-db-fns "dealer_api/db/sql/drugs.sql")
