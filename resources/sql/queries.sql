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
  u.id as user_id,
  u.first_name,
  m.value as value,
  um.created_at,
  m.name as merit_name
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

-- :name user-id-from-github-username
SELECT id FROM users where github_username = :github-username

-- :name persist-boyscouting-results! :! :n
INSERT INTO boyscouting (project, user_id, boyscouting_count) VALUES (:project, :user_id, :boyscouting_count)

-- :name reset-boyscouting! :!
Delete from boyscouting where 1;
