-- showc.sql

SELECT table_name, constraint_name, r_constraint_name, constraint_type
FROM user_constraints
WHERE length(constraint_name) < 30
ORDER BY constraint_type, constraint_name;

