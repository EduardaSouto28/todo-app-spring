CREATE TABLE IF NOT EXISTS lists (
    id UUID,
    name VARCHAR(255),
    user_id UUID,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS items (
    id UUID,
    name VARCHAR(255),
    checked BOOLEAN,
    list_id UUID,
    PRIMARY KEY (id)
);

INSERT INTO lists (id, name, user_id) VALUES ('1', 'Lista 1', '1');
INSERT INTO lists (id, name, user_id) VALUES ('2', 'Lista 2', '1');

INSERT INTO items (id, name, checked, list_id) VALUES ('1', 'Item 1', false, '1');
INSERT INTO items (id, name, checked, list_id) VALUES ('2', 'Item 2', true, '1');
INSERT INTO items (id, name, checked, list_id) VALUES ('3', 'Item 3', false, '2');
