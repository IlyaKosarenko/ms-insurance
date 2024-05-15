INSERT INTO department(name, address) VALUES ('Kyiv Department', 'Kyiv, Shevchenko St. 100');
INSERT INTO department(name, address) VALUES ('Kharkiv Department', 'Kharkiv, Shevchenko St. 15');

INSERT INTO type(name) VALUES ('Medical');
INSERT INTO type(name) VALUES ('Vehicle');

INSERT INTO insurance(email, department_id, type_id, price, contract_number) VALUES ('ilya', 1, 1, 10000, '1f212e-2342f3-ssd6s5e');
INSERT INTO insurance(email, department_id, type_id, price, contract_number) VALUES ('ilya', 2, 2, 50000, '152g2e-23k2f3-ssd6s54');