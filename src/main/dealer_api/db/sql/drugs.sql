-- :name drugs :? :*
-- :doc Get all drugs
SELECT * FROM drugs;

-- :name new-drug :insert :1
INSERT INTO
drugs(name, availability, price)
VALUES(:name, :availability, :price)
RETURNING id;

-- A ":result" value of ":1" specifies a single record
-- (as a hashmap) will be returned
-- :name drug-by-id :? :1
-- :doc Get drug by id
select * from drugs
where id = :id

-- :name update-drug! :! :n
UPDATE drugs set name = :name, availability = :availability, price = :price where id = :id