INSERT INTO stores(tenant_id, name, description) VALUES(23, 'MJ', 'Michael Jordan');
INSERT INTO stores(tenant_id, name, description) VALUES(33, 'PIP', 'Scotie Pippen');
INSERT INTO stores(tenant_id, name, description) VALUES(34, 'Shaq', 'Shaquille Oneil');

INSERT INTO registers(store_id, name, timezone) VALUES(1, 'MJ STORE No1', '-05:00');
INSERT INTO registers(store_id, name, timezone) VALUES(1, 'MJ STORE No2', '-05:00');
INSERT INTO registers(store_id, name, timezone) VALUES(1, 'MJ STORE No3', '-05:00');
INSERT INTO registers(store_id, name, timezone) VALUES(2, 'Pippen Store No1', '-06:00');
INSERT INTO registers(store_id, name, timezone) VALUES(2, 'Pippen Store No2', '-06:00');
INSERT INTO registers(store_id, name, timezone) VALUES(2, 'Pippen Store No3', '-06:00');
INSERT INTO registers(store_id, name, timezone) VALUES(2, 'Pippen Store No4', '-06:00');
INSERT INTO registers(store_id, name, timezone) VALUES(2, 'Pippen Store No5', '-06:00');
INSERT INTO registers(store_id, name, timezone) VALUES(3, 'Shaq No1', '-08:00');
INSERT INTO registers(store_id, name, timezone) VALUES(3, 'Shaq No1', '-08:00');
INSERT INTO registers(store_id, name, timezone) VALUES(3, 'Shaq No1', '-08:00');

-- INSERT INTO contribution_transactions(register_id, order_granted_value, created_on, created_by) VALUES(1, 30, now() - interval '5 days', 'Jonh Doe');
-- INSERT INTO contribution_transactions(register_id, order_granted_value, created_on, created_by) VALUES(1, 50, now() - interval '3 days', 'Jonh Doe');

-- INSERT INTO consumption_transactions(register_id, consumed_value, created_on, created_by) VALUES(1, 15, now() - interval '4 days', 'Jane Doe');
-- INSERT INTO consumption_transactions(register_id, consumed_value, created_on, created_by) VALUES(1, 40, now() - interval '2 days', 'Jane Doe');
-- INSERT INTO consumption_transactions(register_id, consumed_value, created_on, created_by) VALUES(1, 25, now() - interval '1 day', 'Jane Doe');