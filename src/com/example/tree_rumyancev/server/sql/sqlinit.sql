CREATE TABLE client_node (
    id BIGSERIAL PRIMARY KEY,
    parent_id BIGINT references client_node(id) on delete cascade,
    name VARCHAR(256),
    ip VARCHAR(256),
    port SMALLINT
);
