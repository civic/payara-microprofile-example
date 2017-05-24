DROP TABLE IF EXISTS emp CASCADE;
CREATE TABLE emp(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    ename VARCHAR,
    hiredate DATE
);
INSERT INTO emp(ename, hiredate) VALUES('Scott', '2017-01-02');
INSERT INTO emp(ename, hiredate) VALUES('Tiger', '2017-01-12');
INSERT INTO emp(ename, hiredate) VALUES('John', '2017-01-11');
INSERT INTO emp(ename, hiredate) VALUES('Bob', '2017-04-13');
INSERT INTO emp(ename, hiredate) VALUES('Eli', '2017-04-11');
