SELECT
    u.id AS user_id,
    u.username,
    u.first_name,
    u.last_name,
    p.id AS participation_id,
    p.competition_id,
    p.score AS current_score,
    h.id AS hunt_id,
    h.weight AS hunt_weight,
    s.id AS species_id,
    s.name AS species_name,
    s.points AS species_points,
    s.category AS species_category,
    s.difficulty AS species_difficulty,
    CASE
        WHEN s.category = 'Gros Gibiers' THEN 3
        WHEN s.category = 'Oiseaux' THEN 9
        WHEN s.category = 'Poissons' THEN 5
        ELSE 0
        END AS poids, -- Weight (Poids)
    CASE
        WHEN s.difficulty = 'Commun' THEN 1
        WHEN s.difficulty = 'Rare' THEN 2
        WHEN s.difficulty = 'Epique' THEN 3
        WHEN s.difficulty = 'Legendaire' THEN 5
        ELSE 1
        END AS difficulty_factor
FROM "user" u
         JOIN participation p ON u.id = p.user_id
         JOIN hunt h ON p.id = h.participation_id
         JOIN species s ON h.species_id = s.id
WHERE u.id = '700b55e3-0a5e-4635-854c-01f11f78656a'
  AND p.id = 'eee25252-9484-4dbc-8b60-eb85c84400d2';





/*Here’s the query that will calculate the score for a specific participation:
*/
SELECT
    p.id AS participation_id,
    u.id AS user_id,
    SUM(
            s.points +
            (h.weight *
             CASE
                 WHEN s.category = 'Gros Gibier' THEN 3
                 WHEN s.category = 'Oiseau' THEN 9
                 WHEN s.category = 'Poisson' THEN 5
                 ELSE 1
                 END) *
            CASE
                WHEN s.difficulty = 'Commun' THEN 1
                WHEN s.difficulty = 'Rare' THEN 2
                WHEN s.difficulty = 'Epique' THEN 3
                WHEN s.difficulty = 'Legendaire' THEN 5
                ELSE 1
                END
    ) AS calculated_score
FROM
    participation p
        JOIN
    "user" u ON p.user_id = u.id
        JOIN
    hunt h ON h.participation_id = p.id
        JOIN
    species s ON s.id = h.species_id
WHERE
    p.id = :participation_id
GROUP BY
    p.id, u.id;


/*calculate the total score of one participation for one user */
SELECT
    u.id AS user_id,
    u.username,
    u.first_name,
    u.last_name,
    p.id AS participation_id,
    p.competition_id,
    p.score AS current_score,
    SUM(
            s.points +
            (h.weight *
             CASE
                 WHEN s.category = 'Gros Gibiers' THEN 3
                 WHEN s.category = 'Oiseaux' THEN 9
                 WHEN s.category = 'Poissons' THEN 5
                 ELSE 1
                 END) *
            CASE
                WHEN s.difficulty = 'Commun' THEN 1
                WHEN s.difficulty = 'Rare' THEN 2
                WHEN s.difficulty = 'Epique' THEN 3
                WHEN s.difficulty = 'Legendaire' THEN 5
                ELSE 1
                END
    ) AS total_score
FROM "user" u
         JOIN participation p ON u.id = p.user_id
         JOIN hunt h ON p.id = h.participation_id
         JOIN species s ON h.species_id = s.id
WHERE u.id = '700b55e3-0a5e-4635-854c-01f11f78656a'
  AND p.id = 'eee25252-9484-4dbc-8b60-eb85c84400d2'
GROUP BY u.id, p.id;


/*calculate the total score of all participation for all the users  */
SELECT
    u.id AS user_id,
    u.first_name || ' ' || u.last_name AS user_name,
    p.id AS participation_id,
    SUM(
            s.points +
            (h.weight *
             CASE
                 WHEN s.category = 'Gros Gibiers' THEN 3
                 WHEN s.category = 'Oiseaux' THEN 9
                 WHEN s.category = 'Poissons' THEN 5
                 ELSE 1
                 END) *
            CASE
                WHEN s.difficulty = 'Commun' THEN 1
                WHEN s.difficulty = 'Rare' THEN 2
                WHEN s.difficulty = 'Epique' THEN 3
                WHEN s.difficulty = 'Legendaire' THEN 5
                ELSE 1
                END
    ) AS total_score
FROM "user" u
         JOIN participation p ON u.id = p.user_id
         JOIN hunt h ON p.id = h.participation_id
         JOIN species s ON h.species_id = s.id
GROUP BY u.id, u.first_name, u.last_name, p.id
ORDER BY u.id, p.id;
