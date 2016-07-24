-- :name create-user! :! :n
-- :doc creates a new user record
INSERT INTO users
(id, first_name, last_name, email, pass)
VALUES (:id, :first_name, :last_name, :email, :pass)

-- :name update-user! :! :n
-- :doc update an existing user record
UPDATE users
SET first_name = :first_name, last_name = :last_name, email = :email
WHERE id = :id

-- :name get-users :? :*
-- :doc retrieve all users.
SELECT * FROM users

-- :name get-user :? :1
-- :doc retrieve a user given the id.
SELECT * FROM users
WHERE id = :id

-- :name get-merits :? :*
-- :doc retrieve all merits.
SELECT * FROM merits

-- :name add-merit-to-user :! :n
-- :doc Add merit to user
INSERT INTO users_merits (user_id, merit_id) VALUES (:user_id, :merit_id)

-- :name get-meritted-users
-- :doc Get users in the leaderboard
SELECT
  u.id,
  u.first_name,
  m.value as merit_value,
  um.created_at as meritted_on,
  m.name as score_name
FROM
  users u
LEFT JOIN
  users_merits um
ON
  um.user_id = u.id
INNER JOIN
  merits m
ON
  m.id = um.merit_id;
