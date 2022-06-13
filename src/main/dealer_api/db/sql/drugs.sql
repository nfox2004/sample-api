-- :name drugs :? :*
-- :doc Get all drugs
SELECT * FROM drugs;

-- :name new-drug :insert :1
INSERT INTO
drugs(name, availability, price)
VALUES(:name, :availability, :price)
RETURNING id;